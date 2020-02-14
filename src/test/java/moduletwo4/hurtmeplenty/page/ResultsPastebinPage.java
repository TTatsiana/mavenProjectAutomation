package moduletwo4.hurtmeplenty.page;

import moduletwo4.hardcore.page.EmailYourEstimatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPastebinPage extends AbstractPage {

    private WebDriverWait wait = new WebDriverWait(driver, 10);
    private static final By LOCATOR_FOR_ESTIMATE_FIELD = By.xpath("//md-list-item[@role='listitem']/div");
    private static final By LOCATOR_BUTTON_EMAIL = By.xpath("//button[@aria-label='Email Estimate']");

    public ResultsPastebinPage(WebDriver driver) {
        super(driver);
    }

    private String getRowOfTheESTIMATEfield(int lineId) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(LOCATOR_FOR_ESTIMATE_FIELD));
        List<WebElement> webElementList = driver.findElements(LOCATOR_FOR_ESTIMATE_FIELD);
        String searchString = webElementList.get(lineId).getText();
        driver.switchTo().defaultContent();
        return searchString;
    }

    private void waitFramesAndSwitchToIt() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }


    public String findPageLineVmClass() {
        int lineId = 1;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public String findPageLineInstanceType() {
        int lineId = 2;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public String findPageLineRegion() {
        int lineId = 3;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public String findPageLineLocalSSD() {
        int lineId = 4;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public String findPageLineCommitmentTerm() {
        int lineId = 5;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public String findPageLineEstimatedComponentCost() {
        int lineId = 6;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public EmailYourEstimatePage clickButtonEmail() {
        waitFramesAndSwitchToIt();
        WebElement element = driver.findElement(LOCATOR_BUTTON_EMAIL);
        wait.until(ExpectedConditions.elementToBeClickable(LOCATOR_BUTTON_EMAIL));
        element.click();
        driver.switchTo().defaultContent();
        return new EmailYourEstimatePage(driver);
    }
}
