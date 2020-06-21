package moduletwo10.yandex.page;

import moduletwo10.framework.constants.StringСonstants;
import moduletwo10.framework.ui.Browser;
import org.openqa.selenium.By;

public class YandexAccountPage {

    private static final By LOCATOR_LOGIN = By.name("login");
    private static final By LOCATOR_PASSWD = By.name("passwd");
    private static final By LOCATOR_SUBMIT = By.xpath("//button[@type='submit']");
    private static final By LOCATOR_FIELD_ERROR = By.xpath("//div[@class='passp-form-field__error']");


    public boolean haveAnErrorMessage() {
        return Browser.getInstance().getText(LOCATOR_FIELD_ERROR).equals(StringСonstants.ERROR_PASSWD);
    }

    public YandexAccountPage typeLogin(String login) {
        Browser.getInstance().type(LOCATOR_LOGIN, login);
        return this;
    }

    public YandexAccountPage typePassword(String password) {
        Browser.getInstance().type(LOCATOR_PASSWD, password);
        return this;
    }

    public YandexAccountPage clickSubmit() {
        Browser.getInstance().click(LOCATOR_SUBMIT);
        return this;
    }
}
