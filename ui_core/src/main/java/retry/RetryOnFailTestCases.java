package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RetryOnFailTestCases implements IRetryAnalyzer {

    private static final int RETRIES = 0;

    private static Map<String, Integer> retries = Collections.synchronizedMap(new HashMap<>());

    public boolean retry(ITestResult result) {
        if (result.getStatus() != ITestResult.FAILURE) {
            return false;
        }

        var key = result.getTestContext().getName() + "/" + result.getMethod().getMethodName();

        retries.putIfAbsent(key, 0);

        var curRetries = retries.get(key);

        if (curRetries < RETRIES) {
            retries.put(key, curRetries + 1);
            return true;
        }

        return false;
    }
}