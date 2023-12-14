package helpers;

import common.utils.OSValidation;
import common.wrappers.SecretText;
import driver.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Input extends Elements {

    public static DriverManager drManager;

    @Step("Insert [{text}] to input field [{locator}]]")
    public void input(By locator, String text) {
        _input(locator, text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void input(By locator, SecretText text) {
        _input(locator, text.getSecretText());
    }

    private void _input(By locator, String text) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithoutClear(By locator, String text) {
        _inputWithoutClear(locator, text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithoutClear(By locator, SecretText text) {
        _inputWithoutClear(locator, text.getSecretText());
    }

    private void _inputWithoutClear(By locator, String text) {
        waitUntilClickable(locator).sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithClear(By locator, String text) {
        _inputWithClear(locator, text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithClear(By locator, SecretText text) {
        _inputWithClear(locator, text.getSecretText());
    }

    private void _inputWithClear(By locator, String text) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(selectAllChord());
        waitUntilClickable(locator).sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithClearWithDelete(By locator, String text) {
        _inputWithClearWithDelete(locator, text);
    }

    @Step("Insert [{text}] to input field [{locator}]]")
    public void inputWithClearWithDelete(By locator, SecretText text) {
        _inputWithClearWithDelete(locator, text.getSecretText());
    }

    private void _inputWithClearWithDelete(By locator, String text) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(selectAllChord());
        waitUntilClickable(locator).sendKeys(Keys.DELETE);
        waitUntilClickable(locator).sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void input(WebElement element, String text) {
        _input(element, text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void input(WebElement element, SecretText text) {
        _input(element, text.getSecretText());
    }

    private void _input(WebElement element, String text) {
        waitUntilClickable(element).clear();
        waitUntilClickable(element).sendKeys(text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void inputAction(By element, String text) {
        _inputAction(element, text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void inputAction(By element, SecretText text) {
        _inputAction(element, text.getSecretText());
    }

    private void _inputAction(By element, String text) {
        Actions action = new Actions(drManager.getDriver());
        WebElement elem = waitUntilClickable(element);
        waitUntilClickable(elem).clear();
        action.click(elem).sendKeys(text).build().perform();
    }

    public void inputActionWithOutClear(By element, String text,By locatorListOfDropbox) {
        Actions action = new Actions(drManager.getDriver());
        waitUntilClickable(element).click();
        waitUntilVisible(locatorListOfDropbox);
        action.sendKeys(text).build().perform();
    }


    @Step("Insert [{text}] to input field [{locator}]")
    public void inputActionForDropBox(By element, String text) {
        _inputActionForDropBox(element, text);
    }

    @Step("Insert [{text}] to input field [{locator}]")
    public void inputActionForDropBox(By element, SecretText text) {
        _inputActionForDropBox(element, text.getSecretText());
    }

    private void _inputActionForDropBox(By element, String text) {
        Actions action = new Actions(drManager.getDriver());
        WebElement elem = waitUntilClickable(element);
        action.click(elem).sendKeys(text).build().perform();
    }

    @Step("Clean field")
    public void cleanField(By locator) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(selectAllChord());
        waitUntilClickable(locator).sendKeys(Keys.chord(Keys.BACK_SPACE)); //TODO зачем оборачивать в chord, если только одна клавиша?
    }

    private String selectAllChord() {
        if(OSValidation.isMac()) {
            return Keys.chord(Keys.COMMAND, "a");
        } else {
            return Keys.chord(Keys.CONTROL, "a");
        }
    }
}