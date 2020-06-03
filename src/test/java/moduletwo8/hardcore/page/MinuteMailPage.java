package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class MinuteMailPage extends AbstractPage {

    private static final By NEW_EMAIL = By.xpath("//input[@id='mail_address']");
    private static final String MINUTE_MAIL_PAGE = "https://10minutemail.com";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final By LOCATOR_FOR_LETTER = By.xpath("//div[@class='mail_message']");
    private static final By LOCATOR_FOR_COST_FROM_LETTER = By.xpath("//table[@class='quote']");
    private static ArrayList<String> tabs;
    private static int firstTab = 0;
    private static int secondTab = 1;
    private static MinuteMailPage minuteMailPage;

    private MinuteMailPage() {
        super();
    }

    public static MinuteMailPage openPage() {
        if (minuteMailPage == null) {
            minuteMailPage = new MinuteMailPage();
            ((JavascriptExecutor) driver).executeScript("window.open()");
            tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(secondTab));
            driver.get(MINUTE_MAIL_PAGE);
        }
        return minuteMailPage;
    }

    public String getEmail() {
        String str;
        do {
            str = driver.findElement(NEW_EMAIL).getAttribute(ATTRIBUTE_VALUE);
        } while (!str.contains("@"));
        driver.switchTo().window(tabs.get(firstTab));
        return str;
    }

    public String getDataFromLetter() {
        driver.switchTo().window(tabs.get(secondTab));
        js.executeScript("scrollTo(0,3000)");
        wait.until(ExpectedConditions.presenceOfElementLocated(LOCATOR_FOR_LETTER));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(LOCATOR_FOR_LETTER)));
        driver.findElement(LOCATOR_FOR_LETTER).click();
        String cost = driver.findElement(LOCATOR_FOR_COST_FROM_LETTER).getText();
        driver.switchTo().window(tabs.get(firstTab));
        return cost;
    }
}
