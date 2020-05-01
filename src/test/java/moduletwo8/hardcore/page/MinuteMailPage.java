package moduletwo8.hardcore.page;

import moduletwo8.hardcore.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MinuteMailPage extends AbstractPage {

    private static final By NEW_EMAIL = By.xpath("//input[@id='mailAddress']");
    private static final String MINUTE_MAIL_PAGE = "https://10minutemail.com";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final By LOCATOR_FOR_LETTER = By.xpath("//div[@id='messagesList']");
    private static final By LOCATOR_FOR_COST_FROM_LETTER = By.xpath("//table[@class='quote']");

    public MinuteMailPage(WebDriver driver) {
        super(driver);
    }

    public MinuteMailPage openPage() {
        driver.get(MINUTE_MAIL_PAGE);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getEmail() {
        WebElement element = driver.findElement(NEW_EMAIL);
        return element.getAttribute(ATTRIBUTE_VALUE);
    }

    public String getDataFromLetter() {
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,3000)");
        new WebDriverWait(driver, 40).until(ExpectedConditions.elementToBeClickable(driver.findElement(LOCATOR_FOR_LETTER)));
        WebElement element = driver.findElement(LOCATOR_FOR_LETTER);
        element.click();
        element = driver.findElement(LOCATOR_FOR_COST_FROM_LETTER);
        return element.getText();
    }
}
