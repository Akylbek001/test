package tests;

import common.config.app.AppConfig;
import common.config.app.AppConfigProvider;
import driver.BrowserManager;
import driver.DriverManager;
import driver.InitDriver;
import helpers.ElementsAttributes;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.ProductPage;
import pages.Navigation;
import steps.*;

@Slf4j
@Listeners(TestListener.class)
public abstract class BaseTest{

    private WebDriver driver = InitDriver.initDriver();

    protected BrowserManager brManager;
    protected ElementsAttributes elementsAttributes;
    protected DriverManager drManager;
    protected Navigation navigation;
    protected SoftAssertions softly;
    protected ProductPage loginPage;
    protected ProductSteps productSteps;
    protected MainSteps mainSteps;
    protected BasketSteps basketSteps;

    protected AppConfig config;

    @BeforeSuite(alwaysRun = true, description = "Логирование старта комплекта тестов")
    public void setUp(ITestContext ctx) {
        log.info("Executing suite: {}", ctx.getSuite().getName());
    }

    @BeforeClass(alwaysRun = true, description = "Инициализация переменных")
    public void initManagers() {
        managersInit();
        pagesInit();
        stepsInit();
        configInit();
    }

    @AfterSuite(alwaysRun = true, description = "Закрытие вкладки браузера")
    public void tearDown(ITestContext ctx) {
        log.info("Finishing suite: {}", ctx.getSuite().getName());
        brManager.closeTab();
    }

    private void managersInit() {
        drManager = new DriverManager(InitDriver.initDriver());
        brManager = new BrowserManager(InitDriver.initDriver());
        elementsAttributes = new ElementsAttributes();
        navigation = new Navigation(driver);
        softly = new SoftAssertions();
        log.info("Driver manager created");
    }

    private void pagesInit() {
        loginPage = new ProductPage(driver);

    }
    private void stepsInit() {
        productSteps = new ProductSteps(driver);
        mainSteps = new MainSteps(driver);
        basketSteps = new BasketSteps(driver);
    }

    private void configInit() {
       config = AppConfigProvider.get();
    }
}
