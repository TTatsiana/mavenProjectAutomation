package moduletwo4.hardcore.test;

import moduletwo4.hardcore.page.EmailYourEstimatePage;
import moduletwo4.hardcore.page.MinuteMailPage;
import moduletwo4.hurtmeplenty.page.CloudGoogleCalculatorPage;
import moduletwo4.hurtmeplenty.page.CloudGooglePage;
import moduletwo4.hurtmeplenty.page.ResultsPastebinPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleTest {

    private static final String SEARCH_LINE = "Google Cloud Platform Pricing Calculator";
    private static CloudGoogleCalculatorPage calculatorPage;
    private static ResultsPastebinPage resultsPage;
    private static int WAIT_TIMEOUT_SECONDS = 60;
    private static EmailYourEstimatePage emailPage;
    private static MinuteMailPage minuteMailPage;
    private WebDriver driver;
    private ArrayList<String> tabs;
    private int firstTab = 0;
    private int secondTab = 1;


    @BeforeClass
    public void getPrecondition() {
        driver = new FirefoxDriver();
        setWaitingsForDriver(driver);
        CloudGooglePage page = new CloudGooglePage(driver);
        page.openPage();
        calculatorPage = page.typeTextInSearchBoxAndGoToCalculatorPage(SEARCH_LINE);
        resultsPage = calculatorPage.fillPage();
        emailPage = resultsPage.clickButtonEmail();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));
        minuteMailPage = new MinuteMailPage(driver);
        minuteMailPage.openPage();
        String email = minuteMailPage.getEmail();
        driver.switchTo().window(tabs.get(firstTab));
        emailPage.fillEmail(email);
        emailPage.sendEmail();
    }

    private void setWaitingsForDriver(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void checkCost() {
        String costInCalculator = resultsPage.findPageLineEstimatedComponentCost();
        driver.switchTo().window(tabs.get(secondTab));
        String costInLetter = minuteMailPage.getDataFromLetter();
        Assert.assertEquals(findCostInTheLine(costInLetter), findCostInTheLine(costInCalculator));
    }

    private String findCostInTheLine(String startLine) {
        String regExp = "USD\\s\\d+.\\d+";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(startLine);
        return matcher.find() ? matcher.group() : null;
    }

    @AfterClass
    private void closePage() {
        driver.quit();
    }
}
