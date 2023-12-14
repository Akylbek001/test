package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static common.consts.ElemAttrConstants.*;

public class ElementsAttributes extends Elements {

    public ElementsAttributes() {
    }

    public String getAttribute(By locator, String attr) {
        return waitUntilVisible(locator).getAttribute(attr);
    }

    public String getAttributeElemPresent(By locator, String attr) {
        return waitUntilExist(locator).getAttribute(attr);
    }

    public Object getAttributes(By locator) {
        WebElement elem = waitUntilVisible(locator).findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object elementAttributes = js.executeScript("let items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", elem);
        return elementAttributes;
    }

    public String getValueAttribute(By locator) {
        return waitUntilVisible(locator).getAttribute("value");
    }

    public String getAttrValue(By locator) {
        return waitUntilVisible(locator).getAttribute(VALUE);
    }

    public String getAttrValueElemPresent(By locator) {
        return waitUntilExist(locator).getAttribute(VALUE);
    }

    public void setAttrValue(By locator, String attrName, String attrNewValue) {
        var element = waitUntilExist(locator).findElement(locator);
        var script = String.format("arguments[0].setAttribute('%1$s','%2$s')", attrName, attrNewValue);
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public String getAttrInnerText(By locator) {
        return waitUntilClickable(locator).getAttribute(INNER_TEXT);
    }

    public String getAttrInnerTextWithOutClick(By locator) {
        return waitUntilVisible(locator).getAttribute(INNER_TEXT);
    }

    public String getAttrInnerTextWithOutClick(WebElement locator) {
        return waitUntilVisible(locator).getAttribute(INNER_TEXT);
    }

    public String getAttrTextContent(By locator) {
        return waitUntilExist(locator).getAttribute(TEXT_CONTENT);
    }

    public boolean isPresent(By locator) {
        try {
            return waitUntilExist(locator) != null;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isElementVisibleAndEnabledWithoutWait(By locator) {
        try {
            WebElement el = driver.findElement(locator);
            return el.isDisplayed() && el.isEnabled();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isDisplayed(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return getWebElem(ExpectedConditions.visibilityOf(element)) != null;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isDisabled(By locator) {
        WebElement elem = driver.findElement(locator);
        return !elem.isDisplayed() && !elem.isEnabled();
    }

    public boolean isDisabledOnPage(By locator) {
        isDisplayed(locator);
        waitUntilExist(locator);
        WebElement elem = driver.findElement(locator);
        return !elem.isEnabled();
    }

    public boolean isSelected(By locator) {
        return getWebStaleElem(ExpectedConditions.elementToBeSelected(locator));
    }

    public boolean isSelected(WebElement element) {
        return getWebStaleElem(ExpectedConditions.elementToBeSelected(element));
    }

    public boolean isClickable(By locator) {
        return waitUntilClickable(locator).isEnabled();
    }

    public boolean isClickable(WebElement element) {
        return waitUntilClickable(element).isEnabled();
    }

    public boolean isVisible(WebElement element) {
        return getWebElem(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public boolean isVisible(By locator) {
        return getWebElem(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public boolean isInVisible(By locator) {
        return getWebElemBoolean(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean isEnabled(By locator) {
        return getWebElem(ExpectedConditions.elementToBeClickable(locator)) != null;
    }

    public boolean isEnabled(WebElement element) {
        return getWebElem(ExpectedConditions.elementToBeClickable(element)) != null;
    }

    public List<WebElement> getElements(By locator) {
        waitUntilVisible(locator);
        return driver.findElements(locator);
    }

    public WebElement getElement(By locator) {
        waitUntilExist(locator);
        return driver.findElement(locator);
    }

    public WebElement getElementFromList(By locator, int index) {
        waitUntilExist(locator);
        return driver.findElements(locator).get(index);
    }

    public WebElement getRandomElementFromList(By locator) {
        waitUntilExist(locator);
        return driver.findElements(locator).stream().findAny().get();
    }

    public List<String> getTextFromElems(By locator) {
        return getElements(locator)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getValue(By locator) {
        return waitUntilVisible(locator).getText();
    }

    public String getBackgroundColor(By locator) {
        return driver.findElement(locator).getCssValue(BACKGROUND_COLOR);
    }

    public String getBorderColor(By locator) {
        return driver.findElement(locator).getCssValue(BORDER_COLOR);
    }

    public String getTextColor(By locator) {
        return driver.findElement(locator).getCssValue(TEXT_COLOR);
    }
}