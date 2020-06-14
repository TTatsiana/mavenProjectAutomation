package moduletwo10.yandex.service;

import moduletwo10.framework.bo.Document;
import moduletwo10.yandex.page.YandexDiskPage;
import moduletwo10.yandex.page.enums.MainMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class YndexDiskService {

    public void createNewFolder(String name) {
        new YandexDiskPage().goToTab(MainMenu.FILES)
                .clickButtonCreate()
                .clickButtonNewFolder()
                .clearTheFieldFolderName()
                .typeFolderName(name)
                .clickButtonSaveNewFolder();
    }

    public void createdNewWordDocument(Document document) {
        new YandexDiskPage().clickButtonCreate()
                .clickButtonNewDocument()
                .typeTextIntoDocument(document.getText())
                .clickButtonFile()
                .clickButtonSaveAs()
                .typeDocumentName(document.getName());
    }

    public String openDocumentAndGetTextFromDocument(String name) {
        String text = new YandexDiskPage().openDocument(name)
                .getTextFromDocument();
        new YandexDiskPage().closeDocument();
        return text.trim();
    }

    public void deleteFolder(String name) {
        new YandexDiskPage().goToTab(MainMenu.FILES)
                .clickOnTheFolder(name)
                .clickButtonDelete();
    }
}
