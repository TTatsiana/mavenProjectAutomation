package moduletwo10.page;

import moduletwo10.driver.Driver;
import org.openqa.selenium.By;
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

    protected AbstractPage() {
        this.driver = Driver.getDriver();
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);//////////////////////////////////////////
    }

    protected void waitAndClick(By by, WebDriver driver) {
        waitAndClick(getElement(by, driver));
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

    protected WebElement getElement(By by, WebDriver driver) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    protected void waitAndSend(By by, String mess, WebDriver driver) {
        WebElement element = getElement(by, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(mess);
        wait.until(ExpectedConditions.textToBePresentInElementValue(by, mess));
    }

    protected String waitAndGetText(By by, WebDriver driver) {
        WebElement element = getElement(by, driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
    }
}
