package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CloudGooglePage extends AbstractPage {

    private static final String CLOUD_GOOGLE_PAGE = " https://cloud.google.com/";

    private CloudGooglePage(WebDriver driver) {
        super(driver);
    }


    public static CloudGooglePage openPage(WebDriver driver) {
        CloudGooglePage cloudGooglePage = new CloudGooglePage(driver);
        driver.get(CLOUD_GOOGLE_PAGE);
        return cloudGooglePage;
    }

    public CloudGoogleCalculatorPage typeTextInSearchBoxAndGoToCalculatorPage(String str) {
        String xpathSearchBox = "//input[@name='q']";
        String xpathSearchingResults = "//div[@class='gs-title']//a";
        WebElement element = driver.findElement(By.xpath(xpathSearchBox));
        clickByElement(element);
        element.sendKeys(str);
        element.sendKeys(Keys.ENTER);
        clickByElement(By.xpath(xpathSearchingResults));
        return new CloudGoogleCalculatorPage(driver);
    }
}
