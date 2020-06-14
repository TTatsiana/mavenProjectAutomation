package moduletwo10.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YandexSignInPage extends AbstractPage {

    private static final String YANDEX_DISK_SIGN_IN = "https://disk.yandex.by/";
    private static final By LOCATOR_SIGN_IN = By.xpath("//a[contains( text(),'Войти')]");

    private YandexSignInPage() {
        super();
    }

    public static YandexSignInPage openPage() {
        YandexSignInPage yandexSignInPage = new YandexSignInPage();
        driver.get(YANDEX_DISK_SIGN_IN);
        return yandexSignInPage;
    }

    public YandexAccountPage signIn() {
        WebElement element = driver.findElement(LOCATOR_SIGN_IN);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        return new YandexAccountPage();
    }
}
