package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudGooglePage extends AbstractPage {

    private static final String CLOUD_GOOGLE_PAGE = " https://cloud.google.com/";
    private WebDriverWait wait;
    private static final int WAIT_TIMEOUT_SECONDS = 120;

    public CloudGooglePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public CloudGooglePage openPage() {
        driver.get(CLOUD_GOOGLE_PAGE);
        return this;
    }

    public CloudGoogleCalculatorPage typeTextInSearchBoxAndGoToCalculatorPage(String str) {
        String xpathSearchBox = "//input[@name='q']";
        String xpathSearchingResults = "//div[@class='gs-title']//a";
        WebElement element = driver.findElement(By.xpath(xpathSearchBox));
        waitAndClick(element);
        element.sendKeys(str);
        element.sendKeys(Keys.ENTER);
        element = driver.findElement(By.xpath(xpathSearchingResults));
        waitAndClick(element);
        return new CloudGoogleCalculatorPage(driver);
    }

    private void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
