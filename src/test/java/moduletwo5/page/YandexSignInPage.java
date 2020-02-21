package moduletwo5.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexSignInPage extends AbstractPage {

    private static final String YANDEX_DISK_SIGN_IN = "https://disk.yandex.by/";
    private static final By LOCATOR_SIGN_IN = By.xpath("//a[contains( text(),'Войти')]");



    public YandexSignInPage(WebDriver driver) {
        super(driver);

    }

    public YandexSignInPage openPage() {
        driver.get(YANDEX_DISK_SIGN_IN);
        return this;
    }

    public PassportYandexPage signIn() {
        WebElement element = driver.findElement(LOCATOR_SIGN_IN);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        return new PassportYandexPage(driver);
    }
}
