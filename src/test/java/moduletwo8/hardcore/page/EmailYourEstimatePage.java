package moduletwo8.hardcore.page;

import moduletwo8.hardcore.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailYourEstimatePage extends AbstractPage {

    private static final String XPATH_INPUT_EMAIL = "//input[@type='email']";
    private static final String XPATH_BUTTON_SEND_EMAIL = "//button[@aria-label='Send Email']";
    private WebDriverWait wait = new WebDriverWait(driver, 10);
    JavascriptExecutor js;

    public EmailYourEstimatePage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
    }

    private void waitFramesAndSwitchToIt() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }

    private void fillField(String stringToFill, By by) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = driver.findElement(by);
        element.sendKeys(stringToFill);
        driver.switchTo().defaultContent();
    }

    private void clickField(By by) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = driver.findElement(by);
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(XPATH_INPUT_EMAIL)));
        element.click();
        driver.switchTo().defaultContent();
    }

    public EmailYourEstimatePage fillEmail(String email) {
        fillField(email, By.xpath(XPATH_INPUT_EMAIL));
        return new EmailYourEstimatePage(driver);
    }

    public EmailYourEstimatePage sendEmail() {
        clickField(By.xpath(XPATH_BUTTON_SEND_EMAIL));
        return new EmailYourEstimatePage(driver);
    }

}
