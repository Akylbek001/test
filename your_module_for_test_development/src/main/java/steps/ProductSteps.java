package steps;

import org.openqa.selenium.WebDriver;
import pages.ProductPage;

public class ProductSteps {
    private final ProductPage productPage;

    public ProductSteps(WebDriver driver) {
        productPage = new ProductPage(driver);
    }

    public void addToBasket() {
        productPage
                .addToBasket();
    }

    public void navigateToBasket() {
        productPage
                .navigateToBasket();
    }
}
