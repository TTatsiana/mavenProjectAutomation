package moduletwo10.framework.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import moduletwo10.framework.loger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Browser implements WrapsDriver {

    private static ThreadLocal<Browser> instance = new ThreadLocal<>();
    private WebDriver wrappedWebDriver;
    protected WebDriverWait wait;
    private static final int WAIT_TIMEOUT_SECONDS = 10;

    private Browser() {
        WebDriverManager.chromedriver().browserVersion("83").setup();
        wrappedWebDriver = new ChromeDriver();
        wrappedWebDriver.manage().window().maximize();
        wait = new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS);
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
        }
        return instance.get();
    }

    public void closeBrowser() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.set(null);
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedWebDriver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void get(String url) {
        Log.info("WebDriver navigated to url: " + url);
        wrappedWebDriver.get(url);
        makeScreenshot();
    }

    public void makeScreenshot() {
        File file = ((TakesScreenshot) wrappedWebDriver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File("logs/screenshots/" + System.nanoTime() + ".png");
            FileUtils.copyFile(file, destFile);
            Log.info("Taken screenshot <a href='screenshots/" + destFile.getName() + "'>"
                    + destFile.getName() + "</a>");
        } catch (IOException e) {
            throw new RuntimeException("Failed screenshot. " + e);
        }
    }

    public void highlightBackgroundAndMakeScreenshot(WebElement element) {
        String backgroundColor = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) wrappedWebDriver);
        js.executeScript("arguments[0].style.backgroundColor='" + "yellow" + "'", element);
        makeScreenshot();
        js.executeScript("arguments[0].style.backgroundColor='" + backgroundColor + "'", element);
    }

    public ArrayList<String> getAllWindowHandles() {
        wait.until(driver -> driver.getWindowHandles().size() > 0);
        return new ArrayList<>(wrappedWebDriver.getWindowHandles());
    }

    public WebElement waitForVisibilityOfElement(By by) {
        Log.debug("Wait for visibility of element " + by);
        return new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForInVisibilityOfElement(By by) {
        Log.debug("Wait for invisibility of element " + by);
        new WebDriverWait(wrappedWebDriver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOf(wrappedWebDriver.findElement(by)));
    }

    public Boolean waitTextToBePresentInElement(By by, String text) {
        Log.debug("Wait for visibility of element " + by);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public List<WebElement> waitForPresenceOfAllElements(By by) {
        Log.debug("Wait for presence of all elements " + by);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void click(By by) {
        WebElement element = waitForVisibilityOfElement(by);
        click(element);
    }

    public void click(WebElement element) {
        Log.info("Click " + element.getText());
        wait.until(ExpectedConditions.elementToBeClickable(element));
        highlightBackgroundAndMakeScreenshot(element);
        element.click();
    }

    public void doubleClick(WebElement element) {
        Log.info("Double click " + element.getText());
        highlightBackgroundAndMakeScreenshot(element);
        Actions actions = new Actions(wrappedWebDriver);
        actions.doubleClick(element).perform();
    }


    public void type(By by, String text) {
        Log.info("Type " + text + " in" + by);
        WebElement element = waitForVisibilityOfElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        highlightBackgroundAndMakeScreenshot(element);
        element.sendKeys(text);
    }

    public void type(By by, Keys keys) {
        Log.info("Type " + keys.toString() + " in" + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackgroundAndMakeScreenshot(element);
        element.sendKeys(keys);
    }

    public void clear(By by) {
        Log.info("Clear " + by);
        wrappedWebDriver.findElement(by).sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        waitTextToBePresentInElement(by, "");
    }

    public String getText(By by) {
        Log.debug("Get text " + by);
        WebElement element = waitForVisibilityOfElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
    }

    public void switchToFrame(By by) {
        if (by == null) {
            Log.debug("Switch to default frame");
            wrappedWebDriver.switchTo().defaultContent();
        } else {
            Log.debug("Switch to frame " + by);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
        }
    }

    public void switchToWindow(String str) {
        Log.debug("Switch to window " + str);
        wrappedWebDriver.switchTo().window(str);
    }
}
