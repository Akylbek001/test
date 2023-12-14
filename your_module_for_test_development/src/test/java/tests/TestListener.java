package tests;

import common.utils.AllureUtils;
import common.utils.LogUtils;
import driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

@Slf4j
public class TestListener extends TestListenerAdapter {

    private File logFile;

    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        DriverManager.takeScreen(((BaseTest) testClass).drManager.getDriver());
        AllureUtils.attachFile(logFile);
        LogUtils.detachAppender(logFile);
        super.onTestFailure(result);
    }

    public void onTestSkipped(ITestResult result) {
        LogUtils.detachAppender(logFile);
        super.onTestSkipped(result);
    }

    public void onTestSuccess(ITestResult result) {
        LogUtils.detachAppender(logFile);
        super.onTestSkipped(result);
    }

    public void onTestStart(ITestResult result) {
        log.info("Execute test: {}", result.getMethod().getMethodName());
        logFile = LogUtils.attachFileAppender(result.getName());
        super.onTestStart(result);
    }
}
