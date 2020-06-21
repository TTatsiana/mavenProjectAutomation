package moduletwo10.yandex.service;

import moduletwo10.framework.bo.model.Document;
import moduletwo10.framework.loger.Log;
import moduletwo10.yandex.page.YandexDiskPage;
import moduletwo10.yandex.page.enums.MainMenu;

public class YndexDiskService {

    public void createNewFolder(String name) {
        Log.info("Create new folder");
        new YandexDiskPage().goToTab(MainMenu.FILES)
                .clickButtonCreate()
                .clickButtonNewFolder()
                .clearTheFieldFolderName()
                .typeFolderName(name)
                .clickButtonSaveNewFolder();
    }

    public void createdNewWordDocument(Document document) {
        Log.info("Created new document");
        new YandexDiskPage().clickButtonCreate()
                .clickButtonNewDocument()
                .typeTextIntoDocument(document.getText())
                .clickButtonFile()
                .clickButtonSaveAs()
                .typeDocumentName(document.getName());
    }

    public String openDocumentAndGetTextFromDocument(String name) {
        Log.info("Get text from document");
        YandexDiskPage page = new YandexDiskPage();
        String text = page.openDocument(name)
                .getTextFromDocument();
        page.closeDocument();
        return text.trim();
    }

    public void deleteFolder(String name) {
        Log.info("Delete folder");
        YandexDiskPage page = new YandexDiskPage();
        page.goToTab(MainMenu.FILES)
                .clickOnTheFolder(name)
                .clickButtonDelete().goToTab(MainMenu.BASKET);
        while (!page.isTheFolderInTheBasket(name)) {
        }
        page.goToTab(MainMenu.FILES);
    }
}
