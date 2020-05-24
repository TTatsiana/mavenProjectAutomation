package moduletwo8.hardcore.test;

import moduletwo8.hardcore.model.Instance;
import moduletwo8.hardcore.page.CloudGooglePage;
import moduletwo8.hardcore.page.EmailYourEstimatePage;
import moduletwo8.hardcore.page.MinuteMailPage;
import moduletwo8.hardcore.page.ResultsPastebinPage;
import moduletwo8.hardcore.service.InstanceCreator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleTest extends CommonConditions {

    private static final String SEARCH_LINE = "Google Cloud Platform Pricing Calculator";

    @Test(description = "Check that Total Estimated Monthly Cost in the letter matches what is displayed in the calculator")
    public void checkCost() {
        ResultsPastebinPage resultsPage;
        EmailYourEstimatePage emailPage;
        MinuteMailPage minuteMailPage;
        ArrayList<String> tabs;
        int firstTab = 0;
        int secondTab = 1;
        Instance instance = InstanceCreator.withCredentionalsFromProperty();
        resultsPage = CloudGooglePage.openPage(driver)
                .typeTextInSearchBoxAndGoToCalculatorPage(SEARCH_LINE)
                .fillPageInstanceAndAddToEstimate(instance);
        emailPage = resultsPage.clickButtonEmail();

        ((JavascriptExecutor) driver).executeScript("window.open()");
        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(secondTab));

        minuteMailPage = MinuteMailPage.openPage(driver);
        String email = minuteMailPage.getEmail();
        driver.switchTo().window(tabs.get(firstTab));
        emailPage.fillEmail(email).sendEmail();

        String costInCalculator = resultsPage.findPageLineEstimatedComponentCost();
        driver.switchTo().window(tabs.get(secondTab));

        String costInLetter = minuteMailPage.getDataFromLetter();
        Assert.assertEquals(findCostInTheLine(costInLetter), findCostInTheLine(costInCalculator),
                "Total Estimated Monthly Cost in the letter does not match what is displayed in the calculator");
    }

    private String findCostInTheLine(String startLine) {
        String regExp = "USD\\s\\d+.\\d+";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(startLine);
        return matcher.find() ? matcher.group() : null;
    }
}