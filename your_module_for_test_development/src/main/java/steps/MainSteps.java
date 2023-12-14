package steps;

import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainSteps {
    private final MainPage mainPage;

    public MainSteps(WebDriver driver) {
        mainPage = new MainPage(driver);
    }

    public void searchProduct(String phone_model) {
        mainPage
                .inputSearchProductValue(phone_model)
                .clickSearchButton();
    }

    public void selectProduct() {
        mainPage
                .selectFoundedValue();
    }

    public void clickToBasketButton() {
        mainPage
                .clickToBasketButton();
    }

    public void navigateToBasket() {
        mainPage
                .navigateToBasket();
    }
}
