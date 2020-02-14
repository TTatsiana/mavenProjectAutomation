package moduletwo4.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PastebinPage extends AbstractPage {

    private static final String PASTEBIN_PAGE = "https://pastebin.com/";
    private static final String XPATH_FOR_EXPIRATION = "//*[@name='paste_expire_date']";
    private static final String XPATH_FOR_SYNTAX = "//*[@name='paste_format']";

    @FindBy(id = "paste_code")
    private WebElement searcheInputNewPaste;

    @FindBy(xpath = "//input[@name='paste_name']")
    private WebElement searcheInputPasteName;

    @FindBy(id = "submit")
    private WebElement searcheButtonCreateNewPaste;

    public PastebinPage(WebDriver driver) {
        super(driver);
    }

    public PastebinPage openPage() {
        driver.get(PASTEBIN_PAGE);
        return this;
    }

    public PastebinPage typeTextInInputNewPaste(String str) {
        searcheInputNewPaste.sendKeys(str);
        return new PastebinPage(driver);
    }

    public PastebinPage typeTextInInputPasteNameTitle(String str) {
        searcheInputPasteName.sendKeys(str);
        return new PastebinPage(driver);
    }

    public PastebinPage selectSyntaxHighlighting(String str) {
        Select select = new Select(driver.findElement(By.xpath(XPATH_FOR_SYNTAX)));
        select.selectByVisibleText(str);
        return new PastebinPage(driver);
    }

    public PastebinPage selectPasteExpiration(String str) {
        Select select = new Select(driver.findElement(By.xpath(XPATH_FOR_EXPIRATION)));
        select.selectByVisibleText(str);
        return new PastebinPage(driver);
    }

    public ResultsPastebinPage submitCreateNewPaste() {
        searcheButtonCreateNewPaste.click();
        return new ResultsPastebinPage(driver);
    }
}
