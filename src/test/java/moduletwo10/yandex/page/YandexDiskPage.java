package moduletwo10.yandex.page;

import moduletwo10.yandex.page.constants.StringСonstants;
import moduletwo10.yandex.page.enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
    private static final By LOCATOR_DELETE = By.xpath("//div[@class='groupable-buttons__visible-buttons']//span[3]//button");
    private static final By LOCATOR_EMPTY_TRASH = By.xpath("//div[@class='listing-head']//button");
    private static final By LOCATOR_DIALOG_BODY_CLEAR = By.xpath("//div[@class='dialog__body']//button[2]");
    private static final By LOCATOR_NEW_DOCUMENT = By.xpath("//div[@class='create-resource-popup-with-anchor__create-items']//button[2]//span");
    private static final By LOCATOR_INPUT_TEXT_INTO_DOCUMENT = By.xpath("//p[@class='Paragraph']//span");
    private static final By LOCATOR_BUTTON_FILE = By.xpath("//button[@data-unique-id='FileMenu']");
    private static final By LOCATOR_BUTTON_SAVE_AS = By.xpath("//div[@id='menuJewelSaveAs']//span");
    private static final By LOCATOR_INPUT_NAME_DOCUMENT = By.xpath("//input[@id='CommitNewDocumentTitle']");

    private static final By LOCATOR_CLICK_OK = By.id("WACDialogActionButton");
    private static final By LOCATOR_ALL_DOCUMENTS_IN_FOLDER = By.xpath("//div[@class='listing-item__info']//div//span");
    private static final By LOCATOR_STATE_DOCUMENT = By.xpath("//span[@data-unique-id='DocumentTitleSaveStatus']");
    private static final By LOCATOR_CHECK_NAME_OF_DOCUMENT = By.xpath("//span[@data-unique-id='DocumentTitleContent']");
    private static final By LOCATOR_FOR_ELEMENT_ALL_FOLDERS = By.xpath("//div[@class='listing__items']");
    private int count;
    private int countPages;
    private int firstTab = 0;
    private int secondTab = 1;
    private ArrayList<String> tabs;

    public YandexDiskPage() {
        super();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_USER_IMAGE));
    }

    public List<WebElement> getAllElementsFromDirectory(By directoryLocator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(directoryLocator));
    }

    private boolean isFilePresentInDirectory(String item, By directory) {
        List<WebElement> list = getAllElementsFromDirectory(directory);
        return list.stream().anyMatch(s -> s.getText().equals(item));
    }

    public boolean isTheDocumentContainedInFolder(String name) {
        return isFilePresentInDirectory(name, LOCATOR_ALL_DOCUMENTS_IN_FOLDER);
    }

    public boolean isTheFolderContained(String name) {
        return isFilePresentInDirectory(name, LOCATOR_ALL_FOLDERS);
    }

    public boolean isTheFolderInTheBasket(String name) {
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            driver.findElement(LOCATOR_ALL_FOLDERS_IN_BASKET);
            return isFilePresentInDirectory(name, LOCATOR_ALL_FOLDERS_IN_BASKET);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public YandexDiskPage clickButtonNewDocument() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_NEW_DOCUMENT));
        waitAndClick(LOCATOR_NEW_DOCUMENT);
        wait.until(driver -> driver.getWindowHandles().size() > countPages);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public YandexDiskPage typeTextIntoDocument(String mess) {
        WebElement element = getElement(LOCATOR_INPUT_TEXT_INTO_DOCUMENT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(mess);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX));
        return this;
    }

    public YandexDiskPage clickButtonFile() {
        waitAndClick(LOCATOR_BUTTON_FILE);
        return this;
    }

    public YandexDiskPage clickButtonSaveAs() {
        waitAndClick(LOCATOR_BUTTON_SAVE_AS);
        return this;
    }

    public YandexDiskPage goToTab(MainMenu tab) {
        List<WebElement> listElement = getAllElementsFromDirectory(LOCATOR_ALL_MAIN_MENU_ITEMS);
        waitAndClick(listElement.get(tab.getIndex()));
        return this;
    }

    public String getTabName(MainMenu tab) {
        goToTab(tab);
        return waitAndGetText(getTabHeaderLocator(tab));
    }

    public YandexDiskPage clickButtonCreate() {
        countPages = driver.getWindowHandles().size();
        waitAndClick(LOCATOR_BUTTON_CREATE);
        return this;
    }

    public YandexDiskPage clickButtonNewFolder() {
        count = driver.findElements(LOCATOR_ALL_FOLDERS).size();
        waitAndClick(LOCATOR_NEW_FOLDER);
        return this;
    }

    public YandexDiskPage typeFolderName(String name) {
        waitAndSend(LOCATOR_FILL_FOLDER_NAME, name);
        return this;
    }

    public YandexDiskPage typeDocumentName(String documentName) {
        waitAndSend(LOCATOR_INPUT_NAME_DOCUMENT, documentName);
        driver.findElement(LOCATOR_INPUT_NAME_DOCUMENT).sendKeys(Keys.ENTER);
        // wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_STATE_DOCUMENT));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(LOCATOR_CHECK_NAME_OF_DOCUMENT, documentName));
        driver.close();
        driver.switchTo().window(tabs.get(firstTab));
        return this;
    }

    public YandexDiskPage clickButtonSaveNewFolder() {
        waitAndClick(LOCATOR_BUTTON_SAVE);
        wait.until(driver1 -> driver.findElements(LOCATOR_ALL_FOLDERS).size() > count);
        return this;
    }

    public YandexDiskPage clearTheFieldFolderName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_FILL_FOLDER_NAME));
        driver.findElement(LOCATOR_FILL_FOLDER_NAME).clear();
        wait.until(ExpectedConditions.textToBePresentInElementValue(LOCATOR_FILL_FOLDER_NAME, ""));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    private WebElement getWebElementFromDirectory(String name, By by) {
        List<WebElement> list = getAllElementsFromDirectory(by);
        List<WebElement> listElements = list.stream().filter(s -> s.getText().equals(name)).collect(Collectors.toList());
        return listElements.get(0);
    }

    public YandexDiskPage goToFolder(String folder) {
        waitAndDoubleClick(getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS), driver);
        return this;
    }

    public YandexDiskPage openDocument(String nameDocument) {
        waitAndDoubleClick(getWebElementFromDirectory(nameDocument, LOCATOR_ALL_DOCUMENTS_IN_FOLDER), driver);
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_STATE_DOCUMENT));
        return this;
    }

    public String getTextFromDocument()
    {
        System.out.println("mm");
        return waitAndGetText(LOCATOR_INPUT_TEXT_INTO_DOCUMENT);
    }

    public YandexDiskPage closeDocument() {
        driver.switchTo().defaultContent();
        driver.close();
        driver.switchTo().window(tabs.get(firstTab));
        return this;
    }

    public String getTiteFolder(String folder) {
        goToFolder(folder);
        return waitAndGetText(LOCATOR_FOLDER_TITLE);
    }

    public YandexDiskPage clickOnTheFolder(String folder) {
        WebElement element = getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS);
        waitAndClick(element);
        return this;
    }

    public YandexDiskPage clickButtonDelete()  {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_DELETE));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(LOCATOR_DELETE)));
        waitAndClick(LOCATOR_DELETE);
      //  wait.until(ExpectedConditions.invisibilityOf(element));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
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
        waitAndClick(LOCATOR_USER_IMAGE);
        return waitAndGetText(LOCATOR_USER_ACCOUNT_NAME);
    }

    public YandexDiskPage clearTrash() {
        waitAndClick(LOCATOR_EMPTY_TRASH);
        waitAndClick(LOCATOR_DIALOG_BODY_CLEAR);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOCATOR_FOR_ELEMENT_ALL_FOLDERS));
        return this;
    }
}
