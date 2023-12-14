package events;

import common.wrappers.SecretText;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class EventLog implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        //stub
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        //stub
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        //stub
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        //stub
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("Should navigate to: " + url);
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        log.info("Successful navigated to: " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        //stub
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        //stub
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        //stub
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        //stub
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        //stub
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        //stub
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log.info("Should find element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        log.info("Successful found element: " + by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("Should click on element: " + element);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("Successful clicked on element: " + element);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend != null) {
            log.info("Value should be inputted: " + Arrays
                    .stream(keysToSend)
                    .map(charSequence -> SecretText.maskIfSecret(charSequence.toString()))
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (keysToSend != null) {
            log.info("Value has been inputted: " + Arrays
                    .stream(keysToSend)
                    .map(charSequence -> SecretText.maskIfSecret(charSequence.toString()))
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        log.info("Script should be executed: " + script);
    }

    @Override
    public void afterScript(String script, WebDriver driver) {
        log.info("Script fulfilled: " + script);
    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {
        //stub
    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {
        //stub
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        //stub
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {
        //stub
    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
        //stub
    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {
        //stub
    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {
        log.info("Text in element: " + element.getText());
    }
}
