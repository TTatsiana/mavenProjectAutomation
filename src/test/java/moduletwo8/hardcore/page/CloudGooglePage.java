package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CloudGooglePage extends AbstractPage {

    private static final String CLOUD_GOOGLE_PAGE = " https://cloud.google.com/";
    private static final By LOCATOR_BUTTON_SEARCH = By.xpath("//input[@name='q']");
    private static final By LOCATOR_SEARCHING_RESULTS_CALCULATOR_PAGE = By.xpath("//div[@class='gs-title']//a");

    private CloudGooglePage() {
        super();
    }

    public static CloudGooglePage openPage() {
        CloudGooglePage cloudGooglePage = new CloudGooglePage();
        driver.get(CLOUD_GOOGLE_PAGE);
        return cloudGooglePage;
    }

    public CloudGooglePage clickToSearch() {
        clickByElement(driver.findElement(LOCATOR_BUTTON_SEARCH));
        return this;
    }

    public CloudGooglePage typeTextInSearchBox(String str) {
        driver.findElement(LOCATOR_BUTTON_SEARCH).sendKeys(str);
        return this;
    }

    public CloudGoogleCalculatorPage sendEnterAndGoToCalculatorPage() {
        driver.findElement(LOCATOR_BUTTON_SEARCH).sendKeys(Keys.ENTER);
        clickByElement(LOCATOR_SEARCHING_RESULTS_CALCULATOR_PAGE);
        return new CloudGoogleCalculatorPage();
    }
}
