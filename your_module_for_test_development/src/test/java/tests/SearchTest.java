package tests;

import common.utils.WaitUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static pages.BasketPage.BASKET_ITEM_LOCATOR;
import static pages.ProductPage.BASKET_LOCATOR;

@Owner("Алибек Акылбеков")
@Feature("Функционал поиска")
public class SearchTest extends BaseTest {
    @BeforeMethod(alwaysRun = true, description = "Подготовка браузера")
    public void setUpMethod() {
        brManager.clearCache();
        navigation.gotoMainPage();
        WaitUtils.wait(1);
    }

    @Test(description="Добавление продукта в корзину из карточки продукта", groups = {"automated"})
    @Issue("https://jira/QA-1")
    @Description("Add product to basket from product card")
    @Severity(SeverityLevel.NORMAL)
    public void addProductToBasketFromProductPage(){
        step("Search iphone", () -> {
            mainSteps.searchProduct(config.productName());
        });
        step("Click founded product", () -> {
            mainSteps.selectProduct();
        });
        step("Add product to basket", () -> {
            productSteps.addToBasket();
            Assert.assertEquals("1", elementsAttributes.getValue(BASKET_LOCATOR));
        });
        step("Navigate to basket page", () -> {
            productSteps.navigateToBasket();
        });
        Assert.assertEquals(config.productName(), elementsAttributes.getValue(BASKET_ITEM_LOCATOR));
    }

    @Test(description="Добавление продукта в корзину из главной страницы", groups = {"automated"})
    @Issue("https://jira/QA-2")
    @Description("Add product to basket from main page")
    @Severity(SeverityLevel.NORMAL)
    public void addProductToBasketFromMainPage(){
        step("Search iphone", () -> {
            mainSteps.searchProduct(config.productName());
        });
        step("Click 'to basket' button", () -> {

            mainSteps.clickToBasketButton();
            Assert.assertEquals("1", elementsAttributes.getValue(BASKET_LOCATOR));
        });
        step("Navigate to basket page", () -> {
            mainSteps.navigateToBasket();
        });
        Assert.assertEquals(config.productName(), elementsAttributes.getValue(BASKET_ITEM_LOCATOR));
    }
}
