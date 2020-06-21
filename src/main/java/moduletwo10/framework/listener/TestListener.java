package moduletwo10.framework.listener;

import moduletwo10.framework.loger.Log;
import moduletwo10.framework.ui.Browser;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error("Test failed");
        Browser.getInstance().makeScreenshot();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("Test " + iTestResult.getTestName() + " started");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("Test stoped");
    }
}
