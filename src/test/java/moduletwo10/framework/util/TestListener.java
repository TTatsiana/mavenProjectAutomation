package moduletwo10.framework.util;

import moduletwo10.framework.driver.Driver;
import moduletwo10.framework.loger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    private void saveScreenshot() {
        File file = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(".//target/screenshots/" + getCurrentTimeAsString() + ".png"));
            Log.error("Screenshot taken. File: " +"<a href="+file.getAbsolutePath()+"/a>");
        } catch (IOException e) {
            throw new RuntimeException("Failed screenshot. " + e);
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
