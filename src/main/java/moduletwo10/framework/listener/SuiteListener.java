package moduletwo10.framework.listener;

import moduletwo10.framework.loger.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info("Suite finished " + iSuite.getName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        Log.info("Suite started " + iSuite.getName());
    }
}
