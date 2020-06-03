package moduletwo8.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ResultsPastebinPage extends AbstractPage {

    private static final By LOCATOR_FOR_ESTIMATE_FIELD = By.xpath("//md-list-item[@role='listitem']/div");
    private static final By LOCATOR_BUTTON_EMAIL = By.xpath("//button[@aria-label='Email Estimate']");

    public ResultsPastebinPage() {
        super();
    }

    private String getRowOfTheESTIMATEfield(int lineId) {
        waitFramesAndSwitchToIt();
        wait.until(ExpectedConditions.elementToBeClickable(LOCATOR_FOR_ESTIMATE_FIELD));
        List<WebElement> webElementList = driver.findElements(LOCATOR_FOR_ESTIMATE_FIELD);
        String searchString = webElementList.get(lineId).getText();
        driver.switchTo().defaultContent();
        return searchString;
    }

    public String findPageLineEstimatedComponentCost() {
        int lineId = 6;
        return getRowOfTheESTIMATEfield(lineId);
    }

    public EmailYourEstimatePage clickButtonEmail() {
        waitFramesAndSwitchToIt();
        clickByElement(LOCATOR_BUTTON_EMAIL);
        driver.switchTo().defaultContent();
        return new EmailYourEstimatePage();
    }
}
