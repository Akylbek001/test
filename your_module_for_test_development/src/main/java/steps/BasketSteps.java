package steps;

import org.openqa.selenium.WebDriver;
import pages.BasketPage;

public class BasketSteps {
    private final BasketPage basketPage;

    public BasketSteps(WebDriver driver) {
        basketPage = new BasketPage(driver);
    }

    public void getProductTitle() {
        basketPage
                .getTitleText();
    }
}
