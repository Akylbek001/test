package helpers;

import common.utils.WaitUtils;
import driver.InitDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Can be used with feature like Color picker
 */
public class Move extends Elements {

    private Actions action = new Actions(InitDriver.initDriver());

    @Step("Scroll to element: [{element}]")
    public void scrollToElement(WebElement element) {
        var js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Scroll to element: [{element}]")
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        var js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"center\"});", element);
        WaitUtils.wait(2);

    }

    @Step("Move to element: [{element}]")
    public void moveToElemAndDoubleClick(WebElement element) {
        waitUntilClickable(element);
        action.moveToElement(element).doubleClick(element).build().perform();
    }

    @Step("Move to element: [{locator}]")
    public void moveToElemAndDoubleClick(By locator) {
        waitUntilClickable(locator);
        action.moveToElement(driver.findElement(locator)).doubleClick(driver.findElement(locator)).build().perform();
    }

    @Step("Move to element and click: [{locator}]")
    public void moveToElemAndClick(By locator, int x, int y) {
        waitUntilClickable(locator);
        action.moveToElement(driver.findElement(locator), x, y).click().build().perform();
    }

    @Step("Move to element: [{element}]")
    public void moveToElemAndClick(WebElement element) {
        waitUntilClickable(element);
        action.moveToElement(element).click().build().perform();
    }

    @Step("Move to element: [{locator}]")
    public void moveToElemAndClick(By locator) {
        waitUntilVisible(locator);
        waitUntilClickable(locator);
        action.moveToElement(driver.findElement(locator)).click().build().perform();
    }

    public void moveToElemAndClickWithOutWait(By locator) {
        action.moveToElement(driver.findElement(locator)).click().build().perform();
    }

    @Step("Move to element: [{locator}]")
    public void moveToElem(By locator) {
        waitUntilInvisible(locator);
        action.moveToElement(driver.findElement(locator)).build().perform();
    }

    @Step("Move to element: [{element}]")
    public void moveToElem(WebElement element) {
        waitUntilClickable(element);
        action.moveToElement(element).build().perform();
    }

    @Step("Move to element: [{locator}]")
    public By moveToElemAndReturnLocator(By locator) {
        waitUntilClickable(locator);
        action.moveToElement(driver.findElement(locator)).build().perform();
        return locator;
    }

    @Step("Move to element: [{locator}]")
    public void moveByOffset(WebElement element, int x, int y) {
        waitUntilVisible(element).isDisplayed();
        action.moveToElement(element, x, y).build().perform();
    }

    @Step("Move to element: [{locator} by offset X: [{x}] y[{y}]")
    public void moveByOffset(By locator, int x, int y) {
        waitUntilVisible(locator).isDisplayed();
        action.moveToElement(driver.findElement(locator), x, y).click().build().perform();
    }

    @Step("Move to element: [{locator}]")
    public void hoverOverElem(By locator) {
        waitUntilExist(locator);
        action.moveToElement(driver.findElement(locator)).build().perform();
    }

    public void useKeyboard(Keys keys) {
        action.sendKeys(keys).build().perform();
    }
}