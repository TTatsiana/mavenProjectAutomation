package moduletwo4.icanwin.test;

import moduletwo4.icanwin.page.PastebinPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PastebinTest {

    private static final String HELLO = "Hello from WebDriver";
    private static final String HELLOWEB = "helloweb";
    private static final String PASTE_EXPIRATION = "10 Minutes";

    @Test
    public void taskScenarioICanWin() {
        PastebinPage pastebinPage = new PastebinPage(new ChromeDriver());
        pastebinPage.openPage();
        pastebinPage.typeTextInInputNewPaste(HELLO);
        pastebinPage.selectPasteExpiration(PASTE_EXPIRATION);
        pastebinPage.typeTextInInputPasteNameTitle(HELLOWEB);
        pastebinPage.submitCreateNewPaste();
        pastebinPage.closeDriver();
    }
}
