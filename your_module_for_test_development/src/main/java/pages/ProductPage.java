package pages;

import common.utils.WaitUtils;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ProductPage extends BasePage {
    private static final By BASKET_ICON_LOCATOR = By.cssSelector("div.product-page__aside div.order");
    public static final By BASKET_LOCATOR = By.className("navbar-pc__notify");


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add product to basket by add button")
    public ProductPage addToBasket() {
        button.btnClick(BASKET_ICON_LOCATOR);
        WaitUtils.wait(3);
        return this;
    }

    @Step("Navigate to basket")
    public ProductPage navigateToBasket() {
        button.btnClick(BASKET_ICON_LOCATOR);
        return this;
    }
}
