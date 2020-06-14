package moduletwo10.yandex.page;

import moduletwo10.yandex.page.constants.StringСonstants;
import org.openqa.selenium.By;

public class YandexAccountPage extends AbstractPage {

    private static final By LOCATOR_LOGIN = By.name("login");
    private static final By LOCATOR_PASSWD = By.name("passwd");
    private static final By LOCATOR_SUBMIT = By.xpath("//button[@type='submit']");
    private static final By LOCATOR_FIELD_ERROR = By.xpath("//div[@class='passp-form-field__error']");

    public YandexAccountPage() {
        super();
    }

    public boolean haveAnErrorMessage() {
        return waitAndGetText(LOCATOR_FIELD_ERROR).equals(StringСonstants.ERROR_PASSWD);
    }

    public YandexAccountPage typeLogin(String login) {
        waitAndSend(LOCATOR_LOGIN, login);
        return this;
    }

    public YandexAccountPage typePassword(String password) {
        waitAndSend(LOCATOR_PASSWD, password);
        return this;
    }

    public YandexAccountPage clickSubmit() {
        waitAndClick(LOCATOR_SUBMIT);
        return this;
    }
}
