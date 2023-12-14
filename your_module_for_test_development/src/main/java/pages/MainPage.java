package pages;

import common.utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class MainPage extends BasePage {

    private static final By SEARCH_FIELD_LOCATOR = By.id("searchInput");
    private static final By SEARCH_ICON_LOCATOR = By.id("applySearchBtn");
    private static final By SEARCHING_RESULT_LOCATOR = By.id("c178630990");
    private static final By TO_BASKET_BUTTON_LOCATOR = By.cssSelector("a[class='product-card__add-basket j-add-to-basket btn-main-sm-2']");
    public static final By BASKET_LOCATOR = By.className("navbar-pc__notify");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Input search request")
    public MainPage inputSearchProductValue(String productName) {
        input.inputWithClear(SEARCH_FIELD_LOCATOR, productName);
        return this;
    }

    @Step("Click search button")
    public MainPage clickSearchButton() {
        button.btnClick(SEARCH_ICON_LOCATOR);
        WaitUtils.wait(2);
        return this;
    }

    @Step("Select search result")
    public MainPage selectFoundedValue() {
        button.btnClick(SEARCHING_RESULT_LOCATOR);
        WaitUtils.wait(2);
        return this;
    }

    @Step("Click 'to basket' button")
    public MainPage clickToBasketButton() {
        Actions action = new Actions(drManager.getDriver());
        WebElement elem = drManager.getDriver().findElement(SEARCHING_RESULT_LOCATOR);
        action.moveToElement(elem);
        action.perform();
        WaitUtils.wait(2);
        button.btnClick(TO_BASKET_BUTTON_LOCATOR);
        return this;
    }

    @Step("Navigate to basket")
    public MainPage navigateToBasket() {
        button.btnClick(BASKET_LOCATOR);
        WaitUtils.wait(2);
        return this;
    }
}
