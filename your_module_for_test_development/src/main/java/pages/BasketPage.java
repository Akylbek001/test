package pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BasketPage extends BasePage {
    public static final By BASKET_ITEM_LOCATOR = By.cssSelector("span.good-info__good-name");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get product title")
    public BasketPage getTitleText() {
        elementsAttributes.getAttrInnerText(BASKET_ITEM_LOCATOR);
        return this;
    }
}
