package moduletwo10.page;

import moduletwo10.model.User;
import moduletwo10.page.constants.StringСonstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PassportYandexPage extends AbstractPage {

    private static final By LOCATOR_LOGIN = By.name("login");
    private static final By LOCATOR_PASSWD = By.name("passwd");
    private static final By LOCATOR_SUBMIT = By.xpath("//button[@type='submit']");
    private static final By LOCATOR_FIELD_ERROR = By.xpath("//div[@class='passp-form-field__error']");

    public PassportYandexPage(WebDriver driver) {
        super();
    }

    public YandexDiskPage enterValidLoginAndPasswd(User user) {
        return new YandexDiskPage(enterLoginAndPasswd(user));
    }

    public PassportYandexPage enterWrongLoginAndPasswd(User user) {
        return new PassportYandexPage(enterLoginAndPasswd(user));
    }

    public boolean haveAnErrorMessage() {
        return waitAndGetText(LOCATOR_FIELD_ERROR, driver).equals(StringСonstants.ERROR_PASSWD);
    }

    private WebDriver enterLoginAndPasswd(User user) {
        waitAndSend(LOCATOR_LOGIN, user.getLogin(), driver);
        waitAndClick(LOCATOR_SUBMIT, driver);
        waitAndSend(LOCATOR_PASSWD, user.getPassword(), driver);
        waitAndClick(LOCATOR_SUBMIT, driver);
        return driver;
    }
}
