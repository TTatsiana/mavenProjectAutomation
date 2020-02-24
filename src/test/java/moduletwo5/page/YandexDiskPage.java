package moduletwo5.page;

import moduletwo5.page.enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

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



    public YandexDiskPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LOCATOR_USER_IMAGE));
    }

    public YandexDiskPage goToTab(MainMenu tab) {
        List<WebElement> listElement = getAllMainMenuItems();
        waitAndClick(listElement.get(tab.getIndex()), driver);
        return new YandexDiskPage(driver);
    }

    public String getTabName(MainMenu tab) {
        goToTab(tab);
        return waitAndGetText(getTabHeaderLocator(tab), driver);
    }

    public YandexDiskPage createNewFolder(String name) {
        waitAndClick(LOCATOR_BUTTON_CREATE, driver);
        waitAndClick(LOCATOR_NEW_FOLDER, driver);
        waitAndSend(LOCATOR_FILL_FOLDER_NAME, Keys.CONTROL + "a", driver);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitAndSend(LOCATOR_FILL_FOLDER_NAME, name, driver);
        waitAndClick(LOCATOR_BUTTON_SAVE, driver);
        return new YandexDiskPage(driver);
    }

    public boolean isTheFolderContained(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = getAllFoldersFromFiles();
        return list.stream().filter(s -> s.getText().equals(name)).count() > 0;
    }

    public boolean isTheFolderInTheBasket(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> list = getAllFoldersFromBasket();
        return list.stream().filter(s -> s.getText().equals(name)).count() > 0;
    }

    private List<WebElement> getAllFoldersFromFiles() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_ALL_FOLDERS));
        return driver.findElements(LOCATOR_ALL_FOLDERS);
    }

    private List<WebElement> getAllFoldersFromBasket() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_ALL_FOLDERS_IN_BASKET));
        return driver.findElements(LOCATOR_ALL_FOLDERS_IN_BASKET);
    }

    private WebElement getWebElementFoldersFromFiles(String folder) {
        List<WebElement> list = getAllFoldersFromFiles();
        List<WebElement> listElements = list.stream().filter(s -> s.getText().equals(folder)).collect(Collectors.toList());
        return listElements.get(0);
    }

    public String getTiteFolder(String folder) {
        // WebElement element = getWebElementFoldersFromFiles(folder);
        waitAndDoubleClick(getWebElementFoldersFromFiles(folder), driver);
//        Actions actions = new Actions(driver);
//        actions.doubleClick(element).perform();
        String title = waitAndGetText(LOCATOR_FOLDER_TITLE, driver);
        // driver.navigate().back();
        return title;
    }

    public YandexDiskPage deleteFolder(String folder) {
        waitAndClick(getWebElementFoldersFromFiles(folder), driver);
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

    private List<WebElement> getAllMainMenuItems() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_ALL_MAIN_MENU_ITEMS));
        return driver.findElements(LOCATOR_ALL_MAIN_MENU_ITEMS);
    }

    public String getAccountName() {
        waitAndClick(LOCATOR_USER_IMAGE, driver);
        return waitAndGetText(LOCATOR_USER_ACCOUNT_NAME, driver);
    }

    public YandexDiskPage clearTrash(){
        WebElement element=getAllFoldersFromBasket().get(0);
        waitAndClick(LOCATOR_EMPTY_TRASH,driver);
        waitAndClick(LOCATOR_DIALOG_BODY_CLEAR,driver);
  //      wait.until(ExpectedConditions.stalenessOf(element));//
        return new YandexDiskPage(driver);
    }

}
