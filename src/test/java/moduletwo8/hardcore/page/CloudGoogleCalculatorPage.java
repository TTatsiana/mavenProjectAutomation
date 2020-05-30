package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CloudGoogleCalculatorPage extends AbstractPage {

    private static final String ELEMENT_XPATH_INSTANCES = "//input[@name='quantity']";
    private static final String ELEMENT_XPATH_OPERATING_SYSTEM_CLICK = "//md-select-value[@id='select_value_label_51']/span";
    private static final String ELEMENT_XPATH_MACHINE_CLASS = "//md-select-value[@id='select_value_label_52']/span";
    private static final String ELEMENT_XPATH_MACHINE_TYPE = "//md-select-value[@id='select_value_label_55']/span";
    private static final String ELEMENT_XPATH_ADD_GPU = "//md-checkbox[@aria-label='Add GPUs']/div";
    private static final String ELEMENT_XPATH_GPU_NUMBER = "//md-select-value[@id='select_value_label_332']/span";
    private static final String ELEMENT_XPATH_GPU__TYPE = "//md-select-value[@id='select_value_label_333']/span";
    private static final String ELEMENT_XPATH_LOCAL_SSD = "//md-select-value[@id='select_value_label_169']/span";
    private static final String ELEMENT_XPATH_DATACENTER_LOCATION = "//md-select-value[@id='select_value_label_56']/span";
    private static final String ELEMENT_XPATH_COMMITED_USAGE = "//md-select-value[@id='select_value_label_57']/span";
    private static final String BUTTON_ADD_TO_ESTIMATE = "//button[@aria-label='Add to Estimate']";

    public CloudGoogleCalculatorPage() {
        super();
    }

    public CloudGoogleCalculatorPage fillNumberOfInstances(String str) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ELEMENT_XPATH_INSTANCES)));
        WebElement element = driver.findElement(By.xpath(ELEMENT_XPATH_INSTANCES));
        element.sendKeys(str);
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectSoftware(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_OPERATING_SYSTEM_CLICK));
        clickByElement(By.xpath(str));
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectVmClass(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_CLASS));
        clickByElement(By.xpath(str));
        WebElement element = driver.findElement(By.xpath(ELEMENT_XPATH_MACHINE_CLASS));
        js.executeScript("arguments[0].scrollIntoView();", element);
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectMacineType(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_MACHINE_TYPE));
        clickByElement(By.xpath(str));
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage addGPU(String number, String type) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_ADD_GPU));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU_NUMBER));
        clickByElement(By.xpath(number));
        clickByElement(By.xpath(ELEMENT_XPATH_GPU__TYPE));
        clickByElement(By.xpath(type));
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectLocalSSD(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_LOCAL_SSD));
        clickByElement(By.xpath(str));
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectLocation(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_DATACENTER_LOCATION));
        clickByElement(By.xpath(str));
        driver.switchTo().defaultContent();
        return this;
    }

    public CloudGoogleCalculatorPage selectCommittedUsage(String str) {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(ELEMENT_XPATH_COMMITED_USAGE));
        clickByElement(By.xpath(str));
        driver.switchTo().defaultContent();
        return this;
    }

    public ResultsPastebinPage clickByAddToEstimate() {
        waitFramesAndSwitchToIt();
        clickByElement(By.xpath(BUTTON_ADD_TO_ESTIMATE));
        driver.switchTo().defaultContent();
        return new ResultsPastebinPage();
    }
}
