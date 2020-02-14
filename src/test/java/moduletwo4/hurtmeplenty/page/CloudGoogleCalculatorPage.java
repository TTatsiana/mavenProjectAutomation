package moduletwo4.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloudGoogleCalculatorPage extends AbstractPage {

    private static final String NUMBER_OF_INSTANCES = "4";
    private static final int WAIT_TIMEOUT_SECONDS = 120;
    private static final String ELEMENT_XPATH_INSTANCES = "//input[@name='quantity']";
    private static final String ELEMENT_XPATH_OPERATING_SYSTEM = "//md-select-value[@id='select_value_label_46']/span";
    private static final String ELEMENT_XPATH_OPERATING_SYSTEM_CLICK = "//md-option[@id='select_option_55']/div";
    private static final String ELEMENT_XPATH_MACHINE_CLASS = "//md-select-value[@id='select_value_label_47']/span";
    private static final String ELEMENT_XPATH_MACHINE_CLASS_CLICK = "//md-option[@id='select_option_67']/div";
    private static final String ELEMENT_XPATH_MACHINE_TYPE = "//md-select-value[@id='select_value_label_50']/span";
    private static final String ELEMENT_XPATH_MACHINE_TYPE_CLICK = "//md-option[@id='select_option_212']/div";
    private static final String ELEMENT_XPATH_ADD_GPU = "//md-checkbox[@aria-label='Add GPUs']/div";
    private static final String ELEMENT_XPATH_GPU_NUMBER = "//md-select-value[@id='select_value_label_353']/span";
    private static final String ELEMENT_XPATH_GPU_NUMBER_CLICK = "//md-option[@id='select_option_360']/div";
    private static final String ELEMENT_XPATH_GPU__TYPE = "//md-select-value[@id='select_value_label_354']/span";
    private static final String ELEMENT_XPATH_GPU__TYPE_CLICK = "//md-option[@id='select_option_367']/div";
    private static final String ELEMENT_XPATH_LOCAL_SSD = "//md-select-value[@id='select_value_label_171']/span";
    private static final String ELEMENT_XPATH_LOCAL_SSD_CLICK = "//md-option[@id='select_option_233']/div";
    private static final String ELEMENT_XPATH_DATACENTER_LOCATION = "//md-select-value[@id='select_value_label_51']/span";
    private static final String ELEMENT_XPATH_DATACENTER_LOCATION_CLICK = "//md-option[@id='select_option_181']/div";
    private static final String ELEMENT_XPATH_COMMITED_USAGE = "//md-select-value[@id='select_value_label_52']/span";
    private static final String ELEMENT_XPATH_COMMITED_USAGE_CLICK = "//md-option[@id='select_option_85']/div";
    private static final String BUTTON_ADD_TO_ESTIMATE = "//button[@aria-label='Add to Estimate']";
    private WebDriverWait wait;
    JavascriptExecutor js;


    public CloudGoogleCalculatorPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        //Waiting for the last item to load on the page
//        waitFramesAndSwitchToIt();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ELEMENT_XPATH_INSTANCES)));
//        driver.switchTo().defaultContent();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ResultsPastebinPage fillPage() {
        typeToElement(ELEMENT_XPATH_INSTANCES);
        clickByElement(By.xpath(ELEMENT_XPATH_OPERATING_SYSTEM));
        clickByElement(By.xpath(ELEMENT_XPATH_OPERATING_SYSTEM_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_CLASS));
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_CLASS_CLICK));
        WebElement element = driver.findElement(By.xpath(ELEMENT_XPATH_MACHINE_CLASS));
        js.executeScript("arguments[0].scrollIntoView();", element);
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_TYPE));
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_TYPE_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_ADD_GPU));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU_NUMBER));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU_NUMBER_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU__TYPE));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU__TYPE_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_LOCAL_SSD));
        clickByElement(By.xpath(ELEMENT_XPATH_LOCAL_SSD_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_DATACENTER_LOCATION));
        clickByElement(By.xpath(ELEMENT_XPATH_DATACENTER_LOCATION_CLICK));
        clickByElement(By.xpath(ELEMENT_XPATH_COMMITED_USAGE));
        clickByElement(By.xpath(ELEMENT_XPATH_COMMITED_USAGE_CLICK));
        clickByElement(By.xpath(BUTTON_ADD_TO_ESTIMATE));
        driver.switchTo().defaultContent();
        return new ResultsPastebinPage(driver);
    }

    private void typeToElement(String locator) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(NUMBER_OF_INSTANCES);
    }

    private void waitFramesAndSwitchToIt() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }

    private void clickByElement(By by) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }
}
