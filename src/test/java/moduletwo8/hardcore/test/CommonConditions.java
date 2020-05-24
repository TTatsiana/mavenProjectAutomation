package moduletwo8.hardcore.test;

import moduletwo8.hardcore.driver.Driver;
import moduletwo8.hardcore.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        Driver.closeDriver();
    }
}
