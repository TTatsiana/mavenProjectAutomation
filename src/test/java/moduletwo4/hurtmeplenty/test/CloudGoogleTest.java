package moduletwo4.hurtmeplenty.test;


import moduletwo4.hurtmeplenty.page.CloudGoogleCalculatorPage;
import moduletwo4.hurtmeplenty.page.CloudGooglePage;
import moduletwo4.hurtmeplenty.page.ResultsPastebinPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CloudGoogleTest {

    private static final String SEARCH_LINE = "Google Cloud Platform Pricing Calculator";


    private static CloudGoogleCalculatorPage calculatorPage;
    private static ResultsPastebinPage resultsPage;
    private static int WAIT_TIMEOUT_SECONDS = 60;
    private WebDriver driver;


    @BeforeClass
    public void getPrecondition() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        CloudGooglePage page = new CloudGooglePage(driver);
        page.openPage();
        calculatorPage = page.typeTextInSearchBoxAndGoToCalculatorPage(SEARCH_LINE);
        resultsPage = calculatorPage.fillPage();
    }

    @Test
    public void checkPageLineVmClass() {
        String stringToCheck = "VM class: regular";
        Assert.assertEquals(stringToCheck, resultsPage.findPageLineVmClass());
    }

    @Test
    public void checkPageLineInstanceType() {
        String stringToCheck = "Instance type: n1-standard-8";
        Assert.assertEquals(stringToCheck, resultsPage.findPageLineInstanceType());
    }

    @Test
    public void checkPageLineRegion() {
        String stringToCheck = "Region: Frankfurt";
        Assert.assertEquals(stringToCheck, resultsPage.findPageLineRegion());
    }

    @Test
    public void checkPageLineLocalSSD() {
        String stringToCheck = "Total available local SSD space 2x375 GB";
        Assert.assertEquals(stringToCheck, resultsPage.findPageLineLocalSSD());
    }

    @Test
    public void checkPageLineCommitmentTerm() {
        String stringToCheck = "Commitment term: 1 Year";
        Assert.assertEquals(stringToCheck, resultsPage.findPageLineCommitmentTerm());
    }

    @AfterClass
    private void closePage() {
        driver.close();
        driver.quit();
    }
}
