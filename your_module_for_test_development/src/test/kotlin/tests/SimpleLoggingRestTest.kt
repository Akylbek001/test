package tests

import inlines.step
import io.qameta.allure.*
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.*
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import rest.RestAssuredSpecs
import java.util.*


@Owner("Андрей Усков")
@Feature("Произвольный функционал для примера")
//наследуем логгирующий листенер от базового класса
//1. Могут быть проблемы с многопоточным выполнением тестов
//2. Если в одном тесте несколько вызовов RestAssured, то все запросы будут в одном файле requests, а все ответы - в одном файле responses
//3. Файлы с логами отображаются в Allure в секции Test body
class SimpleLoggingRestTest: SimpleLoggingBaseRestTest() {
    private lateinit var specs: RestAssuredSpecs
    private lateinit var api: String

    @BeforeClass(alwaysRun = true, description = "Инициализация дополнительных переменных")
    fun initVars2() {
        api = "/api/v1/catalog/cities"
        specs = RestAssuredSpecs(api)
    }

    @Test(description = "Пример теста (проверка на соответствие json-схеме, простое логирование)", groups = ["rest", "smoke"])
    @Issue("https://jira.bcc.kz/browse/QA-811")
    @Description("Пример теста на RestAssured с проверкой ответа на соответствие json-схеме и с простым логированием в Allure")
    @Severity(SeverityLevel.NORMAL)
    fun firstTest() {
        step("Вызов API $api") {
            given().
                    spec(specs.commonRequestSpecification()).
                    header("Activity-Id", UUID.randomUUID().toString()).
            `when`().
                    get(api).
            then().
                    spec(specs.correctJsonResponseSpecification("get"))
        }
    }

}