package moduletwo8.hardcore.page;

import moduletwo8.hardcore.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor js;

    protected AbstractPage() {
        driver=setUp();
        wait = new WebDriverWait(driver, 120);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    private static WebDriver setUp() {
        return Driver.getDriver();
    }

    protected void waitFramesAndSwitchToIt() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }

    protected void clickByElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
        clickByElement(element);
    }

    protected void clickByElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
    }
}
