package moduletwo4.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PastebinPage {

    private WebDriver driver;
    private static final String PASTEBIN_PAGE = "https://pastebin.com/";
    private static final String XPATH_FOR_EXPIRATION = "//*[@name='paste_expire_date']";

    @FindBy(id = "paste_code")
    private WebElement searcheInputNewPaste;

    @FindBy(xpath = "//input[@name='paste_name']")
    private WebElement searcheInputPasteName;

    @FindBy(id = "submit")
    private WebElement searcheButtonCreateNewPaste;

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinPage openPage() {
        driver.get(PASTEBIN_PAGE);
        return this;
    }

    public PastebinPage typeTextInInputNewPaste(String str) {
        searcheInputNewPaste.sendKeys(str);
        return this;
    }

    public PastebinPage typeTextInInputPasteNameTitle(String str) {
        searcheInputPasteName.sendKeys(str);
        return this;
    }

    public PastebinPage selectPasteExpiration(String str) {
        Select select = new Select(driver.findElement(By.xpath(XPATH_FOR_EXPIRATION)));
        select.selectByVisibleText(str);
        return this;
    }

    public PastebinPage submitCreateNewPaste() {
        searcheButtonCreateNewPaste.click();
        return this;
    }

    public void closeDriver() {
        driver.quit();
    }
}
