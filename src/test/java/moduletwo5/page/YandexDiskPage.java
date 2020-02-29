package moduletwo5.page;

import moduletwo5.page.constants.StringСonstants;
import moduletwo5.page.enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YandexDiskPage extends AbstractPage {

    private static final By LOCATOR_USER_IMAGE = By.xpath("//*[@class='user-pic__image']");
    private static final By LOCATOR_USER_ACCOUNT_NAME = By.xpath("//span[@class='user-account__name']");
    private static final By LOCATOR_ALL_MAIN_MENU_ITEMS = By.xpath("//div[@class='navigation__scroll']//a");
    private static final By LOCATOR_TAB_TITLE_LATEST_FILES_ARCHIVE_BASKET = By.xpath("//div[@class='listing-head']//h1");
    private static final By LOCATOR_TAB_TITLE_PHOTO_GENERAL_ACCESS_ALBUMS = By.xpath("//section[@class='listing-stub__desc']//h1");
    private static final By LOCATOR_TAB_TITLE_HISTORY = By.xpath("//div[@class='journal-filter']//h1");
    private static final By LOCATOR_BUTTON_CREATE = By.xpath("//div[@class='sidebar__buttons']//button");
    private static final By LOCATOR_NEW_FOLDER = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']//button//span");
    private static final By LOCATOR_FILL_FOLDER_NAME = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private static final By LOCATOR_BUTTON_SAVE = By.xpath("//div[@class='confirmation-dialog__footer']//button");
    private static final By LOCATOR_ALL_FOLDERS = By.xpath("//div[@class='listing-item__info']//div//span");
    private static final By LOCATOR_ALL_FOLDERS_IN_BASKET = By.xpath("//div[@class='listing__items']//div//span");
    private static final By LOCATOR_FOLDER_TITLE = By.xpath("//div[@class='listing-head__heading-wrapper']//h1");
    private static final By LOCATOR_DELETE = By.xpath("//div[@class='groupable-buttons__visible-buttons']//span[3]");
    private static final By LOCATOR_EMPTY_TRASH = By.xpath("//div[@class='listing-head']//button");
    private static final By LOCATOR_DIALOG_BODY_CLEAR = By.xpath("//div[@class='dialog__body']//button[2]");
    private static final By LOCATOR_NEW_DOCUMENT = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']//button[2]//span");
    private static final By LOCATOR_INPUT_TEXT_INTO_DOCUMENT = By.xpath("//p[@class='Paragraph']//span");
    private static final By LOCATOR_BUTTON_FILE = By.xpath("//button//span");
    private static final By LOCATOR_BUTTON_SAVE_AS = By.xpath("//div[@id='menuJewelSaveAs']//span");
    private static final By LOCATOR_INPUT_NAME_DOCUMENT = By.xpath("//div[@id='DocumentNameBlock']//input");
    private static final By LOCATOR_CLICK_OK = By.id("WACDialogActionButton");
    private static final By LOCATOR_ALL_DOCUMENTS_IN_FOLDER = By.xpath("//div[@class='listing-item__info']//div//span");
    private static final By LOCATOR_STATE_DOCUMENT = By.xpath("//span[@id='WACRibbon-QATRowCenter']");
    private static final By LOCATOR_CHECK_NAME_OF_DOCUMENT = By.xpath("//span[@id='WACRibbon-QATRowCenter']//div");
    private static final By LOCATOR_FOR_ELEMENT_ALL_FOLDERS = By.xpath("//div[@class='listing__items']");


    public YandexDiskPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_USER_IMAGE));
    }

    public List<WebElement> getAllElementsFromDirectory(By directoryLocator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(directoryLocator));
    }

    private boolean isContained(String item, By directory) {
        List<WebElement> list = getAllElementsFromDirectory(directory);
        return list.stream().anyMatch(s -> s.getText().equals(item));
    }

    public boolean isTheDocumentContainedInFolder(String name) {
        return isContained(name, LOCATOR_ALL_DOCUMENTS_IN_FOLDER);
    }

    public boolean isTheFolderContained(String name) {
        return isContained(name, LOCATOR_ALL_FOLDERS);
    }

    public boolean isTheFolderInTheBasket(String name) {
        try {
            driver.findElement(LOCATOR_ALL_FOLDERS_IN_BASKET);
            return isContained(name, LOCATOR_ALL_FOLDERS_IN_BASKET);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public YandexDiskPage createdNewWordDocument(String folder, String documentName, String text) {
        int firstTab = 0;
        int secondTab = 1;
        int countPages = driver.getWindowHandles().size();
        waitAndClick(LOCATOR_BUTTON_CREATE, driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_NEW_DOCUMENT));
        waitAndClick(LOCATOR_NEW_DOCUMENT, driver);
        wait.until(driver -> driver.getWindowHandles().size() > countPages);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_STATE_DOCUMENT));
        completeDocument(LOCATOR_INPUT_TEXT_INTO_DOCUMENT, text);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX));
        waitAndClick(LOCATOR_BUTTON_FILE, driver);
        waitAndClick(LOCATOR_BUTTON_SAVE_AS, driver);
        waitAndSend(LOCATOR_INPUT_NAME_DOCUMENT, documentName, driver);
        waitAndClick(LOCATOR_CLICK_OK, driver);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(LOCATOR_CHECK_NAME_OF_DOCUMENT, documentName));
        driver.close();
        driver.switchTo().window(tabs.get(firstTab));
        return new YandexDiskPage(driver);
    }

    private void completeDocument(By by, String mess) {
        WebElement element = getElement(by, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(mess);
    }

    public YandexDiskPage goToTab(MainMenu tab) {
        List<WebElement> listElement = getAllElementsFromDirectory(LOCATOR_ALL_MAIN_MENU_ITEMS);
        waitAndClick(listElement.get(tab.getIndex()));
        return new YandexDiskPage(driver);
    }

    public String getTabName(MainMenu tab) {
        goToTab(tab);
        return waitAndGetText(getTabHeaderLocator(tab), driver);
    }

    public YandexDiskPage createNewFolder(String name) {
        int count = driver.findElements(LOCATOR_ALL_FOLDERS).size();
        waitAndClick(LOCATOR_BUTTON_CREATE, driver);
        waitAndClick(LOCATOR_NEW_FOLDER, driver);
        clearTheField(LOCATOR_FILL_FOLDER_NAME);
        waitAndSend(LOCATOR_FILL_FOLDER_NAME, name, driver);
        waitAndClick(LOCATOR_BUTTON_SAVE, driver);
        wait.until(driver1 -> driver.findElements(LOCATOR_ALL_FOLDERS).size() > count);
        return new YandexDiskPage(driver);
    }

    private void clearTheField(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).clear();
        wait.until(ExpectedConditions.textToBePresentInElementValue(by, ""));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement getWebElementFromDirectory(String name, By by) {
        List<WebElement> list = getAllElementsFromDirectory(by);
        List<WebElement> listElements = list.stream().filter(s -> s.getText().equals(name)).collect(Collectors.toList());
        return listElements.get(0);
    }

    private YandexDiskPage goToFolder(String folder) {
        waitAndDoubleClick(getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS), driver);
        return new YandexDiskPage(driver);
    }

    public String getTextFromDocument(String name) {
        int firstTab = 0;
        int secondTab = 1;
        waitAndDoubleClick(getWebElementFromDirectory(name, LOCATOR_ALL_DOCUMENTS_IN_FOLDER), driver);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_STATE_DOCUMENT));
        String text = waitAndGetText(LOCATOR_INPUT_TEXT_INTO_DOCUMENT, driver);
        driver.switchTo().defaultContent();
        driver.close();
        driver.switchTo().window(tabs.get(firstTab));
        return text.trim();
    }

    public String getTiteFolder(String folder) {
        goToFolder(folder);
        return waitAndGetText(LOCATOR_FOLDER_TITLE, driver);
    }

    public YandexDiskPage deleteFolder(String folder) {
        waitAndClick(getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS));
        waitAndClick(LOCATOR_DELETE, driver);
        return new YandexDiskPage(driver);
    }

    private By getTabHeaderLocator(MainMenu tab) {
        switch (tab) {
            case PHOTO:
            case GENERAL_ACCESS:
            case ALBUMS:
                return LOCATOR_TAB_TITLE_PHOTO_GENERAL_ACCESS_ALBUMS;
            case HISTORY:
                return LOCATOR_TAB_TITLE_HISTORY;
            case LATEST:
            case FILES:
            case ARCHIVE:
            case BASKET:
                return LOCATOR_TAB_TITLE_LATEST_FILES_ARCHIVE_BASKET;
            default:
                return null;
        }
    }

    public String getAccountName() {
        waitAndClick(LOCATOR_USER_IMAGE, driver);
        return waitAndGetText(LOCATOR_USER_ACCOUNT_NAME, driver);
    }

    public YandexDiskPage clearTrash() {
        waitAndClick(LOCATOR_EMPTY_TRASH, driver);
        waitAndClick(LOCATOR_DIALOG_BODY_CLEAR, driver);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOCATOR_FOR_ELEMENT_ALL_FOLDERS));
        return new YandexDiskPage(driver);
    }
}
