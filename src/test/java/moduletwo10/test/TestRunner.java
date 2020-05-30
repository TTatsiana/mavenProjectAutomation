package moduletwo10.test;

import moduletwo10.driver.Driver;
import moduletwo10.model.User;
import moduletwo10.page.PassportYandexPage;
import moduletwo10.page.YandexDiskPage;
import moduletwo10.page.YandexSignInPage;
import moduletwo10.page.constants.StringСonstants;
import moduletwo10.page.enums.MainMenu;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestRunner {
    //Usually, test classes have a domain-specific names

//I'd suggest not to use dependence between test methods. You can revert actions
// performed by the test method in AfterMethods or AfterGroups (of Before*) for example.

//    Consider using SoftAssert for multiple assertions. In case of errors, some lines of this method become unreachable.

    private PassportYandexPage passportYandexPage;
    private YandexDiskPage yandexDiskPage;
    User user = new User(StringСonstants.VALID_LOGIN, StringСonstants.VALID_PASSWD);

    @BeforeGroups("with login")
    public void getPrecondition() {
        passportYandexPage = YandexSignInPage.openPage().signIn();
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(user);
    }

    @AfterMethod
    public void revertActions() {
//        Driver.getDriver().navigate().back();
//        Driver.getDriver().navigate().refresh();
        Driver.closeDriver();
    }

    //+
    @Test(description = "Test login into yandex disk using credentials (negative checks)")
    public void negativeCheckSignInYande() {
        passportYandexPage = YandexSignInPage.openPage().signIn();
        User userWithAnIncorrectPassword = new User(StringСonstants.WRONG_LOGIN, StringСonstants.WRONG_PASSWD);
        passportYandexPage.enterWrongLoginAndPasswd(userWithAnIncorrectPassword);
        Assert.assertTrue(passportYandexPage.haveAnErrorMessage(),
                "The username or password you entered is correct.");
    }

    //+
    @Test(description = "Test login into yandex disk using credentials (positive checks)", groups = {"with login"})
    public void positiveCheckSignInYande() {
        Assert.assertEquals(StringСonstants.VALID_LOGIN, yandexDiskPage.getAccountName(),
                "The username or password you entered is incorrect.");
    }

    //+
    @Test(description = "Test that all main menu items works correctly and lead to correct page", groups = {"with login"})
    public void checkMainMenu() {
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

    @Test(groups = {"with login"})
    public void che() {
        SoftAssert asert = new SoftAssert();
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES).createNewFolder(StringСonstants.FOLDER_NAME);
        Boolean folderСreated = yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME);
        asert.assertTrue(folderСreated, StringСonstants.ERROR_FOLDER_NOT_CREATED);
        if (folderСreated) {
            yandexDiskPage.goToTab(MainMenu.FILES);
            asert.assertEquals(yandexDiskPage.getTiteFolder(StringСonstants.FOLDER_NAME),
                    StringСonstants.FOLDER_NAME,
                    StringСonstants.ERROR_CLICKING_FOLDER + StringСonstants.FOLDER_NAME);
            yandexDiskPage.createdNewWordDocument(StringСonstants.FOLDER_NAME,
                    StringСonstants.DOCUMENT_NAME,
                    StringСonstants.DOCUMENT_TEXT);
            Boolean documentCreated = yandexDiskPage.isTheDocumentContainedInFolder(StringСonstants.DOCUMENT_NAME + ".docx");
            asert.assertTrue(documentCreated, StringСonstants.ERROR_DOCUMENT_NOT_CREATED);
            if (documentCreated) {
                asert.assertEquals(yandexDiskPage.getTextFromDocument(StringСonstants.DOCUMENT_NAME + ".docx"),
                        StringСonstants.DOCUMENT_TEXT,
                        StringСonstants.ERROR_SAVING_DATA);
            }
            yandexDiskPage.goToTab(MainMenu.FILES).deleteFolder(StringСonstants.FOLDER_NAME);
            asert.assertFalse(yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME),
                    StringСonstants.ERROR_FOLDER_NOT_DELETED);
           yandexDiskPage.goToTab(MainMenu.BASKET);
           asert.assertTrue(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
                    StringСonstants.ERROR_FOLDER_NOT_IN_BASKET);
          yandexDiskPage.clearTrash();
            asert.assertFalse(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
                    StringСonstants.ERROR_BASKED_IS_NOT_CLEARED);
        }
        asert.assertAll();
    }
    //+
//    @Test(groups = {"with login"})
//    public void checkCreationOfNewFolder() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
//        yandexDiskPage.createNewFolder(StringСonstants.FOLDER_NAME);
//        Assert.assertTrue(yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME),
//                StringСonstants.ERROR_FOLDER_NOT_CREATED);
//    }


//    @Test(groups = {"with login"})
//    public void checkThePossibilityOfVisitingTheFolder() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
//        Assert.assertEquals(yandexDiskPage.getTiteFolder(StringСonstants.FOLDER_NAME), StringСonstants.FOLDER_NAME,
//                StringСonstants.ERROR_CLICKING_FOLDER + StringСonstants.FOLDER_NAME);
//    }

//    @Test(dependsOnMethods = "checkThePossibilityOfVisitingTheFolder")
//    public void checkThatCreatedNewWordDocumentInFolder() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES)
//                .goToFolder(StringСonstants.FOLDER_NAME)
//                .createdNewWordDocument(StringСonstants.FOLDER_NAME, StringСonstants.DOCUMENT_NAME,
//                StringСonstants.DOCUMENT_TEXT);
//        Assert.assertTrue(yandexDiskPage.isTheDocumentContainedInFolder(StringСonstants.DOCUMENT_NAME + ".docx"),
//                StringСonstants.ERROR_DOCUMENT_NOT_CREATED);
//    }
//
//    @Test(dependsOnMethods = "checkThatCreatedNewWordDocumentInFolder")
//    public void checkThatTheDocumentContainsText() {
//        Assert.assertEquals(yandexDiskPage.getTextFromDocument(StringСonstants.DOCUMENT_NAME + ".docx"), StringСonstants.DOCUMENT_TEXT,
//                StringСonstants.ERROR_SAVING_DATA);
//    }


//    @Test(dependsOnMethods = "checkThatTheDocumentContainsText")
//    public void checkThatTheFolderIsDeleted() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
//        yandexDiskPage.deleteFolder(StringСonstants.FOLDER_NAME);
//        Assert.assertFalse(yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME),
//                StringСonstants.ERROR_FOLDER_NOT_DELETED);
//    }

//    @Test(dependsOnMethods = "checkThatTheFolderIsDeleted")
//    public void checkThatTheFolderIsInTheBasket() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.BASKET);
//        Assert.assertTrue(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
//                StringСonstants.ERROR_FOLDER_NOT_IN_BASKET);
//    }

//    @Test(dependsOnMethods = "checkThatTheFolderIsInTheBasket")
//    public void checkThatTheBasketIsClean() {
//        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.BASKET);
//        yandexDiskPage = yandexDiskPage.clearTrash();
//        Assert.assertFalse(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
//                StringСonstants.ERROR_BASKED_IS_NOT_CLEARED);
//    }

//    @AfterClass
//    private void closePage() {
//        Driver.closeDriver();
//    }
}
