package moduletwo4.bringiton.test;


import moduletwo4.bringiton.page.PastebinPage;
import moduletwo4.bringiton.page.ResultsPastebinPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PastebinTest {

    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String SYNTAX_HIGHLIGHTING = "Bash";
    private static final String PASTE_NAME_TITLE = "how to gain dominance among developers";
    private static final String PASTE_EXPIRATION = "10 Minutes";
    private static ResultsPastebinPage resultsPastebinPage;

    @Test
    public void checkPageTitle() {
        Assert.assertEquals(PASTE_NAME_TITLE, resultsPastebinPage.findPageTitle());
    }

    @Test
    public void checkSyntaxHighlighting() {
        Assert.assertEquals(SYNTAX_HIGHLIGHTING, resultsPastebinPage.findSyntaxHighlighting());
    }

    @Test
    public void checkCode() {
        Assert.assertEquals(CODE, resultsPastebinPage.findCode());
    }

    @BeforeClass
    private static void getPrecondition() {
        PastebinPage pastebinPage = new PastebinPage(new ChromeDriver());
        pastebinPage.openPage();
        pastebinPage.typeTextInInputNewPaste(CODE);
        pastebinPage.selectSyntaxHighlighting(SYNTAX_HIGHLIGHTING);
        pastebinPage.selectPasteExpiration(PASTE_EXPIRATION);
        pastebinPage.typeTextInInputPasteNameTitle(PASTE_NAME_TITLE);
        resultsPastebinPage = pastebinPage.submitCreateNewPaste();
    }

    @AfterClass
    private static void closePage() {
        resultsPastebinPage.closeDriver();
    }
}
