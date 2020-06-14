package moduletwo10.yandex.page;

import moduletwo10.framework.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected static WebDriver driver;
    protected WebDriverWait wait;
    private static final int WAIT_TIMEOUT_SECONDS = 300;
    protected JavascriptExecutor js;

    protected AbstractPage() {
        driver = Driver.getDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    protected void waitAndClick(By by) {
        waitAndClick(getElement(by));
    }

    protected void waitAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void waitAndDoubleClick(WebElement element, WebDriver driver) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    protected WebElement getElement(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    protected void waitAndSend(By by, String mess) {
        WebElement element = getElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(mess);
        wait.until(ExpectedConditions.textToBePresentInElementValue(by, mess));
    }

    protected String waitAndGetText(By by) {
        WebElement element = getElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
    }
}
