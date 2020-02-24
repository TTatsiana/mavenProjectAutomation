package moduletwo5.test;

import moduletwo5.page.PassportYandexPage;
import moduletwo5.page.YandexDiskPage;
import moduletwo5.page.YandexSignInPage;
import moduletwo5.page.constants.StringСonstants;
import moduletwo5.page.enums.MainMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestRunner {

    private WebDriver driver;
    private PassportYandexPage passportYandexPage;
    private YandexDiskPage yandexDiskPage;
    private YandexSignInPage yandexSignIn;

    @BeforeClass
    public void getPrecondition() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        setWaitingsForDriver(driver);
        yandexSignIn = new YandexSignInPage(driver);
        yandexSignIn.openPage();
        passportYandexPage = yandexSignIn.signIn();
    }

    @Test
    public void negativeCheckSignInYande() {
        passportYandexPage.enterWrongLoginAndPasswd(StringСonstants.WRONG_LOGIN, StringСonstants.WRONG_PASSWD);
        Assert.assertTrue(passportYandexPage.haveAnErrorMessage(),
                "The username or password you entered is correct.");
    }

    @Test
    public void positiveCheckSignInYande() {
        driver.navigate().back();
        driver.navigate().refresh();
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, StringСonstants.VALID_PASSWD);
        Assert.assertEquals(StringСonstants.VALID_LOGIN, yandexDiskPage.getAccountName(),
                "The username or password you entered is incorrect.");
    }

    @Test
    public void checkMainMenu() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);/////////////////////////////////////////////////////////////////////////

        System.out.println(" ");
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

    @Test
    public void checkCreationOfNewFolder() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);////////////////////////////////////////////////////////////////////////
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
        yandexDiskPage.createNewFolder(StringСonstants.FOLDER_NAME);
        Assert.assertTrue(yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME),
                StringСonstants.ERROR_FOLDER_NOT_CREATED);
    }

    @Test
    public void checkThePossibilityOfVisitingTheFolder() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);////////////////////////////////////////////////////////////////////////
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
        Assert.assertEquals(yandexDiskPage.getTiteFolder(StringСonstants.FOLDER_NAME), StringСonstants.FOLDER_NAME,
                StringСonstants.ERROR_CLICKING_FOLDER + StringСonstants.FOLDER_NAME);

    }

////////////////////
    ///////////////////////
    ////////////////////////////////
    /////////////////////////////////
    ////////////////////////////////


    @Test
    public void checkThatTheFolderIsDeleted() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);////////////////////////////////////////////////////////////////////////
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.FILES);
        yandexDiskPage.deleteFolder(StringСonstants.FOLDER_NAME);
        Assert.assertFalse(yandexDiskPage.isTheFolderContained(StringСonstants.FOLDER_NAME),
                StringСonstants.ERROR_FOLDER_NOT_DELETED);
    }

    @Test
    public void checkThatTheFolderIsInTheBasket() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);////////////////////////////////////////////////////////////////////////
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.BASKET);
        Assert.assertTrue(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
                StringСonstants.ERROR_FOLDER_NOT_IN_BASKET);
    }

    @Test
    public void checkThatTheBasketIsClean() {
        yandexDiskPage = passportYandexPage.enterValidLoginAndPasswd(StringСonstants.VALID_LOGIN, /////////////////////
                StringСonstants.VALID_PASSWD);////////////////////////////////////////////////////////////////////////
        yandexDiskPage = yandexDiskPage.goToTab(MainMenu.BASKET);
yandexDiskPage=yandexDiskPage.clearTrash();
        Assert.assertFalse(yandexDiskPage.isTheFolderInTheBasket(StringСonstants.FOLDER_NAME),
               StringСonstants.ERROR_BASKED_IS_NOT_CLEARED);
    }

    private void setWaitingsForDriver(WebDriver driver) {

        // driver.manage().timeouts().pageLoadTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    private void closePage() {
        driver.quit();
    }
}
