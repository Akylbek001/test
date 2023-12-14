package retry;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Listeners({RetryListener.class})
public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();

        if (retry == null) {
            annotation.setRetryAnalyzer(RetryOnFailTestCases.class);
        }
    }
}
