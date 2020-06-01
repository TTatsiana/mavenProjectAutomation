package moduletwo8.hardcore.test;

import moduletwo8.hardcore.model.Instance;
import moduletwo8.hardcore.page.CloudGooglePage;
import moduletwo8.hardcore.page.MinuteMailPage;
import moduletwo8.hardcore.service.CloudGoogleCalculatorService;
import moduletwo8.hardcore.service.InstanceCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloudGoogleTest extends BaseTest {

    private static final String SEARCH_LINE = "Google Cloud Platform Pricing Calculator";
    private Instance instance;

    @Test(description = "Check that Total Estimated Monthly Cost in the letter matches what is displayed in the calculator")
    public void checkCost() {
        instance = InstanceCreator.withCredentionalsFromProperty();
        CloudGooglePage.openPage()
                .clickToSearch()
                .typeTextInSearchBox(SEARCH_LINE)
                .sendEnterAndGoToCalculatorPage();
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