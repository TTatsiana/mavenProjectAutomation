package moduletwo10.yandex.page;

import moduletwo10.framework.ui.Browser;
import org.openqa.selenium.By;

public class YandexSignInPage {

    private static final String YANDEX_DISK_SIGN_IN = "https://disk.yandex.by/";
    private static final By LOCATOR_SIGN_IN = By.xpath("//a[contains( text(),'Войти')]");

    public YandexSignInPage openPage() {
        Browser.getInstance().get(YANDEX_DISK_SIGN_IN);
        return this;
    }

    public YandexAccountPage signIn() {
        Browser.getInstance().click(LOCATOR_SIGN_IN);
        return new YandexAccountPage();
    }
}
