package moduletwo10.yandex.page;

import moduletwo10.framework.constants.StringСonstants;
import moduletwo10.framework.ui.Browser;
import moduletwo10.yandex.page.enums.MainMenu;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YandexDiskPage {

    private static final By LOCATOR_FRAME = By.xpath("//iframe");
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
    private static final By LOCATOR_ALL_DOCUMENTS_IN_FOLDER = By.xpath("//div[@class='listing-item__info']//div//span");
    private static final By LOCATOR_STATE_DOCUMENT = By.xpath("//span[@data-unique-id='DocumentTitleSaveStatus']");
    private static final By LOCATOR_CHECK_NAME_OF_DOCUMENT = By.xpath("//span[@data-unique-id='DocumentTitleContent']");
    private static final By LOCATOR_ITEMS_IN_THE_BASKET = By.xpath("//div[@class='listing-item__fields']");

    private int count;
    private int firstTab = 0;
    private int secondTab = 1;
    private ArrayList<String> tabs;

    public YandexDiskPage() {
        Browser.getInstance().waitForPresenceOfAllElements(LOCATOR_USER_IMAGE);
    }

    public List<WebElement> getAllElementsFromDirectory(By directoryLocator) {
        return Browser.getInstance().waitForPresenceOfAllElements(directoryLocator);
    }

    private boolean isFilePresentInDirectory(String item, By directory) {
        List<WebElement> list;
        try {
            list = getAllElementsFromDirectory(directory);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
        return list.stream().anyMatch(s -> s.getText().equals(item));
    }

    public boolean isTheDocumentContainedInFolder(String name) {
        return isFilePresentInDirectory(name, LOCATOR_ALL_DOCUMENTS_IN_FOLDER);
    }

    public boolean isTheFolderContained(String name) {
        return isFilePresentInDirectory(name, LOCATOR_ALL_FOLDERS);
    }

    public boolean isTheFolderInTheBasket(String name) {
        return isFilePresentInDirectory(name, LOCATOR_ALL_FOLDERS_IN_BASKET);
    }

    public YandexDiskPage clickButtonNewDocument() {
        Browser.getInstance().click(LOCATOR_NEW_DOCUMENT);
        tabs = Browser.getInstance().getAllWindowHandles();
        Browser.getInstance().switchToWindow(tabs.get(secondTab));
        return this;
    }

    public YandexDiskPage typeTextIntoDocument(String mess) {
        Browser.getInstance().switchToFrame(null);
        Browser.getInstance().switchToFrame(LOCATOR_FRAME);
        Browser.getInstance().waitTextToBePresentInElement(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX);
        Browser.getInstance().type(LOCATOR_INPUT_TEXT_INTO_DOCUMENT, mess);
        Browser.getInstance().waitTextToBePresentInElement(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX);
        return this;
    }

    public YandexDiskPage clickButtonFile() {
        Browser.getInstance().click(LOCATOR_BUTTON_FILE);
        return this;
    }

    public YandexDiskPage clickButtonSaveAs() {
        Browser.getInstance().click(LOCATOR_BUTTON_SAVE_AS);
        return this;
    }

    public YandexDiskPage goToTab(MainMenu tab) {
        List<WebElement> listElement = getAllElementsFromDirectory(LOCATOR_ALL_MAIN_MENU_ITEMS);
        Browser.getInstance().click(listElement.get(tab.getIndex()));
        return this;
    }

    public String getTabName(MainMenu tab) {
        goToTab(tab);
        return Browser.getInstance().getText(getTabHeaderLocator(tab));
    }

    public YandexDiskPage clickButtonCreate() {
        Browser.getInstance().click(LOCATOR_BUTTON_CREATE);
        return this;
    }

    public YandexDiskPage clickButtonNewFolder() {
        count = Browser.getInstance().getWrappedDriver().findElements(LOCATOR_ALL_FOLDERS).size();
        Browser.getInstance().click(LOCATOR_NEW_FOLDER);
        return this;
    }

    public YandexDiskPage typeFolderName(String name) {
        Browser.getInstance().type(LOCATOR_FILL_FOLDER_NAME, name);
        return this;
    }

    public YandexDiskPage typeDocumentName(String documentName) {
        Browser.getInstance().type(LOCATOR_INPUT_NAME_DOCUMENT, documentName);
        Browser.getInstance().type(LOCATOR_INPUT_NAME_DOCUMENT, Keys.ENTER);
        Browser.getInstance().waitTextToBePresentInElement(LOCATOR_CHECK_NAME_OF_DOCUMENT, documentName);
        Browser.getInstance().waitTextToBePresentInElement(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX);
        Browser.getInstance().getWrappedDriver().close();
        Browser.getInstance().switchToWindow(tabs.get(firstTab));
        return this;
    }

    public YandexDiskPage clickButtonSaveNewFolder() {
        Browser.getInstance().click(LOCATOR_BUTTON_SAVE);
        Browser.getInstance()
                .getWait()
                .until(driver1 -> Browser.getInstance()
                        .getWrappedDriver()
                        .findElements(LOCATOR_ALL_FOLDERS)
                        .size() > count);
        return this;
    }

    public YandexDiskPage clearTheFieldFolderName() {
        Browser.getInstance().waitForVisibilityOfElement(LOCATOR_FILL_FOLDER_NAME);
        Browser.getInstance().clear(LOCATOR_FILL_FOLDER_NAME);
        return this;
    }

    private WebElement getWebElementFromDirectory(String name, By by) {
        List<WebElement> list = getAllElementsFromDirectory(by);
        List<WebElement> listElements = list.stream().filter(s -> s.getText().equals(name)).collect(Collectors.toList());
        return listElements.get(0);
    }

    public YandexDiskPage goToFolder(String folder) {
        Browser.getInstance().doubleClick(getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS));
        return this;
    }

    public YandexDiskPage openDocument(String nameDocument) {
        Browser.getInstance().doubleClick(getWebElementFromDirectory(nameDocument, LOCATOR_ALL_DOCUMENTS_IN_FOLDER));
        tabs = Browser.getInstance().getAllWindowHandles();
        Browser.getInstance().switchToWindow(tabs.get(secondTab));
        Browser.getInstance().switchToFrame(null);
        Browser.getInstance().switchToFrame(LOCATOR_FRAME);
        Browser.getInstance().waitTextToBePresentInElement(LOCATOR_STATE_DOCUMENT, StringСonstants.SAVE_IN_YANDEX);
        return this;
    }

    public String getTextFromDocument() {
        return Browser.getInstance().getText(LOCATOR_INPUT_TEXT_INTO_DOCUMENT);
    }

    public YandexDiskPage closeDocument() {
        Browser.getInstance().switchToFrame(null);
        Browser.getInstance().getWrappedDriver().close();
        Browser.getInstance().switchToWindow(tabs.get(firstTab));
        return this;
    }

    public String getTiteFolder(String folder) {
        goToFolder(folder);
        return Browser.getInstance().getText(LOCATOR_FOLDER_TITLE);
    }

    public YandexDiskPage clickOnTheFolder(String folder) {
        WebElement element = getWebElementFromDirectory(folder, LOCATOR_ALL_FOLDERS);
        Browser.getInstance().click(element);
        return this;
    }

    public YandexDiskPage clickButtonDelete() {
        Browser.getInstance().waitForVisibilityOfElement(LOCATOR_DELETE);
        Browser.getInstance().click(LOCATOR_DELETE);
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
        Browser.getInstance().click(LOCATOR_USER_IMAGE);
        return Browser.getInstance().getText(LOCATOR_USER_ACCOUNT_NAME);
    }

    public YandexDiskPage clearTrash() {
        Browser.getInstance().click(LOCATOR_EMPTY_TRASH);
        Browser.getInstance().click(LOCATOR_DIALOG_BODY_CLEAR);
        Browser.getInstance().waitForInVisibilityOfElement(LOCATOR_ITEMS_IN_THE_BASKET);
        return this;
    }
}
