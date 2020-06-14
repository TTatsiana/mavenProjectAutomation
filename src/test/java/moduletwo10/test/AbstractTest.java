package moduletwo10.test;

import moduletwo10.framework.bo.UserFactory;
import moduletwo10.framework.driver.Driver;
import moduletwo10.framework.util.TestListener;
import moduletwo10.yandex.page.YandexSignInPage;
import moduletwo10.yandex.service.AccountService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    @BeforeGroups("with login")
    public void getPrecondition() {
        YandexSignInPage.openPage().signIn();
        new AccountService().signIn(UserFactory.getDefaultUser());
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        Driver.closeDriver();
    }
}

