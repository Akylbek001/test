package rest;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.PrintStream;

@Slf4j
public class RestAssuredListener extends TestListenerAdapter {
    private LogEntity logEntity = new LogEntity(null);

    @Override
    public void onStart(ITestContext testContext) {
        RestAssured.filters(
                Filters.requestLoggingFilter(new PrintStream(logEntity.getRequest(), true)),
                Filters.responseLoggingFilter(new PrintStream(logEntity.getResponse(), true))
        );
        super.onStart(testContext);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logEntity.attachToAllure();
        super.onTestFailure(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logEntity.attachToAllure();
        super.onTestSuccess(result);
    }

}
