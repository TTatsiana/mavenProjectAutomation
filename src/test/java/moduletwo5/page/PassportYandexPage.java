package moduletwo5.page;

import moduletwo5.page.constants.StringСonstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassportYandexPage extends AbstractPage {

    private static final By LOCATOR_LOGIN = By.name("login");
    private static final By LOCATOR_PASSWD = By.name("passwd");
    private static final By LOCATOR_SUBMIT = By.xpath("//button[@type='submit']");
    private static final By LOCATOR_FIELD_ERROR = By.xpath("//div[@class='passp-form-field__error']");

    public PassportYandexPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskPage enterValidLoginAndPasswd(String login, String passwd) {
        return new YandexDiskPage(enterLoginAndPasswd(login, passwd));
    }

    public PassportYandexPage enterWrongLoginAndPasswd(String login, String passwd) {
        return new PassportYandexPage(enterLoginAndPasswd(login, passwd));
    }

    public boolean haveAnErrorMessage() {
        return waitAndGetText(LOCATOR_FIELD_ERROR, driver).equals(StringСonstants.ERROR_PASSWD);
    }

    private WebDriver enterLoginAndPasswd(String login, String passwd) {
        waitAndSend(LOCATOR_LOGIN, login, driver);
        waitAndClick(LOCATOR_SUBMIT, driver);
        waitAndSend(LOCATOR_PASSWD, passwd, driver);
        waitAndClick(LOCATOR_SUBMIT, driver);
        return driver;
    }
}
