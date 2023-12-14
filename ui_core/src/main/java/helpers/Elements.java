package helpers;

import driver.InitDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Basic waiters for WebElements state
 */
abstract public class Elements {

    private static final int DEFAULT_IMPL_WAIT_SEC = 15;
    private static final int DEF_DELAY = 600;

    protected WebDriver driver = InitDriver.initDriver();
    //TODO  а зачем это поле, если дальше везде создаётся новый экземпляр через метод waiter()?
    protected WebDriverWait waiter = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_IMPL_WAIT_SEC));

    public Elements() {
    }

    /**
     * Get status of element
     * waiting until element isn't present in DOM
     *
     * @param webElemCond
     * @return
     */
    public WebElement getWebElem(ExpectedCondition<WebElement> webElemCond) {
        return waiter().until(webElemCond);
    }

    public boolean getWebElemBoolean(ExpectedCondition<Boolean> webElemCond) {
        return waiter().until(webElemCond);
    }

    /**
     * Get boolean status of element
     *
     * @param staleElemCond
     * @return
     */
    public boolean getWebStaleElem(ExpectedCondition<Boolean> staleElemCond) {
        return waiter().until(staleElemCond);
    }

    public WebElement waitUntilClickable(By locator) {
        return getWebElem(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitUntilClickable(WebElement element) {
        return getWebElem(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilVisible(WebElement element) {
        return getWebElem(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilVisible(By locator) {
        return getWebElem(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitUntilExist(By locator) {
        return getWebElem(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean waitUntilInvisible(By locator) {
        return getWebStaleElem(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitUntilInvisible(WebElement element) {
        return getWebStaleElem(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitUntilTextPresent(By locator, String txt) {
        return getWebStaleElem(ExpectedConditions.textToBe(locator, txt));
    }

    public boolean waitUntilTextPresent(WebElement element, String txt) {
        return getWebStaleElem(ExpectedConditions.textToBePresentInElement(element, txt));
    }

    //TODO может, эти два метода перенести в WaiterUtils?
    public WebDriverWait waiter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_IMPL_WAIT_SEC));
        wait.pollingEvery(Duration.ofMillis(DEF_DELAY));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(InterruptedException.class);
        wait.ignoring(UnknownError.class);
        return wait;
    }

    public WebDriverWait waiter(int defDelay) {
        WebDriverWait wait = waiter();
        wait.pollingEvery(Duration.ofMillis(defDelay));
        return wait;
    }
}