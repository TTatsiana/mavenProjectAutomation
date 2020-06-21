package moduletwo10.yandex.test;

import moduletwo10.framework.bo.factory.DocumentFactory;
import moduletwo10.framework.bo.factory.FolderFactory;
import moduletwo10.framework.bo.factory.UserFactory;
import moduletwo10.framework.bo.model.Document;
import moduletwo10.framework.bo.model.Folder;
import moduletwo10.yandex.page.YandexAccountPage;
import moduletwo10.yandex.page.YandexDiskPage;
import moduletwo10.yandex.page.YandexSignInPage;
import moduletwo10.framework.constants.StringСonstants;
import moduletwo10.yandex.page.enums.MainMenu;
import moduletwo10.yandex.service.AccountService;
import moduletwo10.yandex.service.YndexDiskService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestRunner extends AbstractTest {

    @Test(description = "Test login into yandex disk using credentials (negative checks)")
    public void negativeCheckSignInYande() {
        new YandexSignInPage().openPage().signIn();
        new AccountService().signIn(UserFactory.getWrongUser());
        Assert.assertTrue(new YandexAccountPage().haveAnErrorMessage(),
                "The username or password you entered is correct.");
    }

    @Test(description = "Test login into yandex disk using credentials (positive checks)", groups = {"authorized"})
    public void positiveCheckSignInYande() {
        Assert.assertEquals(StringСonstants.VALID_LOGIN, new YandexDiskPage().getAccountName(),
                "The username or password you entered is incorrect.");
    }

    @Test(description = "Test that all main menu items works correctly and lead to correct page", groups = {"authorized"})
    public void checkMainMenu() {
        YandexDiskPage yandexDiskPage = new YandexDiskPage();
        Assert.assertEquals(MainMenu.LATEST.getTitle(), yandexDiskPage.getTabName(MainMenu.LATEST),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.LATEST);
        Assert.assertEquals(MainMenu.FILES.getTitle(), yandexDiskPage.getTabName(MainMenu.FILES),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.FILES);
        Assert.assertEquals(MainMenu.ARCHIVE.getTitle(), yandexDiskPage.getTabName(MainMenu.ARCHIVE),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.ARCHIVE);
        Assert.assertEquals(MainMenu.BASKET.getTitle(), yandexDiskPage.getTabName(MainMenu.BASKET),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.BASKET);
        Assert.assertEquals(MainMenu.GENERAL_ACCESS.getTitle(), yandexDiskPage.getTabName(MainMenu.GENERAL_ACCESS),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.GENERAL_ACCESS);
        Assert.assertEquals(MainMenu.PHOTO.getTitle(), yandexDiskPage.getTabName(MainMenu.PHOTO),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.PHOTO);
        Assert.assertEquals(MainMenu.HISTORY.getTitle(), yandexDiskPage.getTabName(MainMenu.HISTORY),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.HISTORY);
        Assert.assertEquals(MainMenu.ALBUMS.getTitle(), yandexDiskPage.getTabName(MainMenu.ALBUMS),
                StringСonstants.ERROR_CLICKING_TAB + MainMenu.ALBUMS);
    }

    @Test(description = "The test checks the work with the document, file and the recycle bin (creation, deletion)"
            , groups = {"authorized"})
    public void checkCreationAndDeletionOfDocumentsAndFolders() {
        Document document = DocumentFactory.getDocument();
        Folder folder = FolderFactory.getFolder();
        YandexDiskPage yandexDiskPage = new YandexDiskPage();
        SoftAssert asert = new SoftAssert();
        YndexDiskService service = new YndexDiskService();
        service.createNewFolder(folder.getName());
        Boolean folderСreated = yandexDiskPage.isTheFolderContained(folder.getName());
        asert.assertTrue(folderСreated, StringСonstants.ERROR_FOLDER_NOT_CREATED);

        if (folderСreated) {
            asert.assertEquals(yandexDiskPage
                            .goToTab(MainMenu.FILES)
                            .getTiteFolder(folder.getName()),
                    folder.getName(),
                    StringСonstants.ERROR_CLICKING_FOLDER + folder.getName());

            service.createdNewWordDocument(document);
            Boolean documentCreated = yandexDiskPage
                    .isTheDocumentContainedInFolder(document.getName() + ".docx");
            asert.assertTrue(documentCreated, StringСonstants.ERROR_DOCUMENT_NOT_CREATED);

            if (documentCreated) {
                asert.assertEquals(service
                                .openDocumentAndGetTextFromDocument(document.getName() + ".docx"),
                        document.getText(),
                        StringСonstants.ERROR_SAVING_DATA);
            }

            service.deleteFolder(folder.getName());
            asert.assertFalse(yandexDiskPage
                            .isTheFolderContained(folder.getName()),
                    StringСonstants.ERROR_FOLDER_NOT_DELETED);

            asert.assertTrue(yandexDiskPage
                            .goToTab(MainMenu.BASKET)
                            .isTheFolderInTheBasket(folder.getName()),
                    StringСonstants.ERROR_FOLDER_NOT_IN_BASKET);

            asert.assertFalse(yandexDiskPage.clearTrash().isTheFolderInTheBasket(folder.getName()),
                    StringСonstants.ERROR_BASKED_IS_NOT_CLEARED);
        }
        asert.assertAll();
    }
}
