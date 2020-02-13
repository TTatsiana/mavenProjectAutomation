package moduletwo4.bringiton.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultsPastebinPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='paste_box_line1']")
    private WebElement searchePageTitle;

    @FindBy(xpath = "//*[@href='/archive/bash']")
    private WebElement searcheSyntaxHighlighting;

    @FindBy(xpath = "//*[@class='bash']")
    private WebElement searcheCde;


    public ResultsPastebinPage(WebDriver driver) {
        super(driver);
    }

    public String findPageTitle() {
        return searchePageTitle.getText();
    }

    public String findCode() {
        return searcheCde.getText();
    }

    public String findSyntaxHighlighting() {
        return searcheSyntaxHighlighting.getText();
    }

    public void closeDriver() {
        driver.quit();
    }
}
