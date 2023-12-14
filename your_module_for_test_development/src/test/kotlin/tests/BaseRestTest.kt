package tests

import common.config.rest.RestConfig
import common.config.rest.RestConfigProvider
import inlines.*
import org.testng.ITestContext
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeSuite

abstract class BaseRestTest {
    private val log = logger()
    protected lateinit var config: RestConfig

    @BeforeSuite(alwaysRun = true, description = "Логирование старта комплекта тестов")
    fun setUp(ctx: ITestContext) {
        log.info("Executing suite: {}", ctx.suite.name)
    }

    @BeforeClass(alwaysRun = true, description = "Инициализация базовых переменных")
    open fun initVars() {
        config = RestConfigProvider.get()
    }

    @AfterSuite(alwaysRun = true, description = "Логирование завершения комплекта тестов")
    fun tearDown(ctx: ITestContext) {
        log.info("Finishing suite: {}", ctx.suite.name)
    }

}