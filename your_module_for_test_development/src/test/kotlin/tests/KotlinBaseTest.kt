package tests

//import lombok.extern.slf4j.Slf4j
import common.config.app.AppConfig
import common.config.app.AppConfigProvider
import driver.BrowserManager
import driver.DriverManager
import driver.InitDriver
import helpers.ElementsAttributes
import inlines.*
import org.assertj.core.api.SoftAssertions
import org.testng.ITestContext
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Listeners
import pages.ProductPage
import pages.Navigation
import steps.ProductSteps

//https://kotlinlang.org/docs/lombok.html
//эта аннотация пока для котлина не поддерживается, поэтому создаём логгер через inline функцию
//@Slf4j
@Listeners(TestListener::class)
abstract class KotlinBaseTest {
    private val log = logger()
    private val driver = InitDriver.initDriver()
    protected lateinit var brManager: BrowserManager
    protected lateinit var elementsAttributes: ElementsAttributes
    protected lateinit var drManager: DriverManager
    protected lateinit var navigation: Navigation
    protected lateinit var softly: SoftAssertions
    protected lateinit var loginPage: ProductPage
    protected lateinit var loginSteps: ProductSteps
    protected lateinit var config: AppConfig


    @BeforeSuite(alwaysRun = true, description = "Логирование старта комплекта тестов")
    fun setUp(ctx: ITestContext) {
        log.info("Executing suite: {}", ctx.suite.name)
    }

    @BeforeClass(alwaysRun = true, description = "Инициализация переменных")
    open fun initManagers() {
        managersInit()
        pagesInit()
        stepsInit()
        configInit()
    }

    @AfterSuite(alwaysRun = true, description = "Закрытие вкладки браузера")
    fun tearDown(ctx: ITestContext) {
        log.info("Finishing suite: {}", ctx.suite.name)
        brManager.closeTab()
    }

    private fun managersInit() {
        drManager = DriverManager(InitDriver.initDriver())
        brManager = BrowserManager(InitDriver.initDriver())
        elementsAttributes = ElementsAttributes()
        navigation = Navigation(drManager.driver)
        softly = SoftAssertions()
        log.info("Driver manager created")
    }

    private fun pagesInit() {
        loginPage = ProductPage(drManager.driver)
    }

    private fun stepsInit() {
        loginSteps = ProductSteps(drManager.driver)
    }

    private fun configInit() {
        config = AppConfigProvider.get()
    }
}