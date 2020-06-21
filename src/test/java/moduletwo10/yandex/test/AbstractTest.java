package moduletwo10.yandex.test;

import moduletwo10.framework.bo.factory.UserFactory;
import moduletwo10.framework.listener.SuiteListener;
import moduletwo10.framework.listener.TestListener;
import moduletwo10.framework.ui.Browser;
import moduletwo10.yandex.page.YandexSignInPage;
import moduletwo10.yandex.service.AccountService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, SuiteListener.class})
public abstract class AbstractTest {

    @BeforeMethod(onlyForGroups = {"authorized"})
    public void getPrecondition() {
        new YandexSignInPage().openPage().signIn();
        new AccountService().signIn(UserFactory.getDefaultUser());
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        Browser.getInstance().closeBrowser();
    }
}

