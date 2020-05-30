package moduletwo8.hardcore.test;

import moduletwo8.hardcore.model.Instance;
import moduletwo8.hardcore.page.CloudGooglePage;
import moduletwo8.hardcore.page.EmailYourEstimatePage;
import moduletwo8.hardcore.page.MinuteMailPage;
import moduletwo8.hardcore.page.ResultsPastebinPage;
import moduletwo8.hardcore.service.CloudGoogleCalculatorService;
import moduletwo8.hardcore.service.CloudGooglePageService;
import moduletwo8.hardcore.service.InstanceCreator;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudGoogleTest extends CommonConditions {

    private static final String SEARCH_LINE = "Google Cloud Platform Pricing Calculator";
    private Instance instance;

    @BeforeTest
    private void preconditions() {
        instance = InstanceCreator.withCredentionalsFromProperty();
        CloudGooglePageService.goToCloudGoogleCalculatorPage(SEARCH_LINE);
    }

    @Test(description = "Check that Total Estimated Monthly Cost in the letter matches what is displayed in the calculator")
    public void checkCost() {
        String costInCalculator = CloudGoogleCalculatorService
                .fillPageInstanceAndAddToEstimate(instance)
                .clickButtonEmail()
                .fillEmail(MinuteMailPage.openPage().getEmail())
                .sendEmail()
                .findPageLineEstimatedComponentCost();
        String costInLetter = MinuteMailPage.openPage().getDataFromLetter();
        Assert.assertEquals(CloudGoogleCalculatorService.findCostInTheLine(costInLetter),
                CloudGoogleCalculatorService.findCostInTheLine(costInCalculator),
                "Total Estimated Monthly Cost in the letter does not match what is displayed in the calculator");
    }
}