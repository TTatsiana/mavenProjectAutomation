package moduletwo4.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ResultsPastebinPage extends AbstractPage {

    private WebDriverWait wait = new WebDriverWait(driver, 10);
    private static String XPATH_FOR_ESTIMATE_FIELD = "//md-list-item[@role='listitem']/div";

    public ResultsPastebinPage(WebDriver driver) {
        super(driver);
    }

    private String getRowOfTheESTIMATEfield(int lineId) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_ESTIMATE_FIELD)));
        List<WebElement> webElementList = driver.findElements(By.xpath(XPATH_FOR_ESTIMATE_FIELD));
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
}
