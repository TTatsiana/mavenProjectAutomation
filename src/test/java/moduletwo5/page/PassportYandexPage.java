package moduletwo5.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PassportYandexPage extends AbstractPage {

    private static final By LOCATOR_LOGIN = By.name("login");
    private static final By LOCATOR_PASSWD = By.name("passwd");
    private static final By LOCATOR_SUBMIT = By.xpath("//button[@type='submit']");

    public PassportYandexPage(WebDriver driver) {
        super(driver);
    }

    public PassportYandexPage enterLoginPasswd(String login, String passwd) {
        System.out.println(" ");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitAndSend(LOCATOR_LOGIN, login);
        waitAndClick(LOCATOR_SUBMIT);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitAndSend(LOCATOR_PASSWD, passwd);
        waitAndClick(LOCATOR_SUBMIT);
        return new PassportYandexPage(driver);
    }

    private void waitAndClick(By by) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private void waitAndSend(By by, String mess) {
        WebElement element = driver.findElement(by);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(mess);
    }
}
