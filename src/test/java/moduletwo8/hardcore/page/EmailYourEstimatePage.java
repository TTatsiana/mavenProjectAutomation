package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailYourEstimatePage extends AbstractPage {

    private static final String XPATH_INPUT_EMAIL = "//input[@type='email']";
    private static final String XPATH_BUTTON_SEND_EMAIL = "//button[@aria-label='Send Email']";

    public EmailYourEstimatePage() {
        super();
    }

    private void fillField(String stringToFill, By by) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement element = driver.findElement(by);
        element.sendKeys(stringToFill);
        js.executeScript("arguments[0].scrollIntoView();", element);
        driver.switchTo().defaultContent();
    }

    private void clickField(By by) {
        waitFramesAndSwitchToIt();
        clickByElement(by);
        driver.switchTo().defaultContent();
    }

    public EmailYourEstimatePage fillEmail(String email) {
        fillField(email, By.xpath(XPATH_INPUT_EMAIL));
        return this;
    }

    public ResultsPastebinPage sendEmail() {
        clickField(By.xpath(XPATH_BUTTON_SEND_EMAIL));
        return new ResultsPastebinPage();
    }
}
