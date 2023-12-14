package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropDown extends Elements {

    private Select select;

    @Step("Select element from list: [{locator}]")
    public String getFirstSelectedValue(By locator) {
        waitUntilClickable(locator);
        select = new Select(waitUntilClickable(locator));
        return select.getFirstSelectedOption().getText();
    }

    public List<String> getOptionsText(By locator) {
        select = new Select(waitUntilClickable(locator));
        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getDropDownLength(By locator) {
        select = new Select(waitUntilClickable(locator));
        return select.getOptions().size();
    }

    @Step("Select element: [{locator}] by index: [{index}]")
    public void selectByIndex(By locator, int index) {

        select = new Select(waitUntilClickable(locator));
        waitUntilVisible(locator);
        select.selectByIndex(index);
    }

    @Step("Select element: [{locator}] by value: [{value}]")
    public void selectByValue(By locator, String value) {
        select = new Select(waitUntilClickable(locator));
        waitUntilVisible(locator);
        select.selectByValue(value);
    }
}