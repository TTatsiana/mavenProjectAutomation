package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MinuteMailPage extends AbstractPage {

    private static final By NEW_EMAIL = By.xpath("//input[@id='mail_address']");
    private static final String MINUTE_MAIL_PAGE = "https://10minutemail.com";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final By LOCATOR_FOR_LETTER = By.xpath("//div[@class='mail_message']");
    private static final By LOCATOR_FOR_COST_FROM_LETTER = By.xpath("//table[@class='quote']");

    private MinuteMailPage(WebDriver driver) {
        super(driver);
    }

    public static MinuteMailPage openPage(WebDriver driver) {
        MinuteMailPage minuteMailPage = new MinuteMailPage(driver);
        driver.get(MINUTE_MAIL_PAGE);
        return minuteMailPage;
    }

    public String getEmail() {
        String str;
        do {
            str = driver.findElement(NEW_EMAIL).getAttribute(ATTRIBUTE_VALUE);
        } while (!str.contains("@"));
        return str;
    }

    public String getDataFromLetter() {
        js.executeScript("scrollTo(0,3000)");
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_FOR_LETTER));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(LOCATOR_FOR_LETTER)));
        driver.findElement(LOCATOR_FOR_LETTER).click();
        return driver.findElement(LOCATOR_FOR_COST_FROM_LETTER).getText();
    }
}
