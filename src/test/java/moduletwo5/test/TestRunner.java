package moduletwo5.test;

import moduletwo5.page.PassportYandexPage;
import moduletwo5.page.YandexSignInPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestRunner {

    private WebDriver driver;

   // @BeforeClass
    @Test
    public void getPrecondition() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver=new ChromeDriver();
        YandexSignInPage yandexSignIn=new YandexSignInPage(driver);
        yandexSignIn.openPage();
        PassportYandexPage passportYandexPage=yandexSignIn.signIn();
        passportYandexPage.enterLoginPasswd("kkk","ppppp");
    }

    @AfterClass
    private void closePage() {
        driver.quit();
        driver.close();
    }
}
