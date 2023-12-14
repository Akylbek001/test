package tests

import inlines.step
import io.qameta.allure.*
import io.restassured.RestAssured.given
import io.restassured.filter.cookie.CookieFilter
import io.restassured.http.ContentType
import org.hamcrest.Matchers.*
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import rest.RestAssuredSpecs
import java.util.*


@Owner("Андрей Усков")
@Feature("Произвольный функционал для примера")
//наследуем от базового класса методы работы с отдельными LogEntity, а не листенер
//1. Для каждого запроса отдельно создаём и указываем имя записи через specs.commonRequestSpecification(newLogEntity("name"))
//2. В данном случае логи отображаются в Allure в секции Tear down, а не Test body, т.к. аттачатся не из листенера, а из метода @AfterMethod
class IndividualLoggingRestTest: IndividualLoggingBaseRestTest() {
    private lateinit var specs: RestAssuredSpecs
    private lateinit var api: String

    @BeforeClass(alwaysRun = true, description = "Инициализация дополнительных переменных")
    fun initVars2() {
        api = "/auth/realms/bank/protocol/openid-connect/token"
        specs = RestAssuredSpecs(null, config.keycloakBaseUri(), api) //TODO уточнить, есть ли swagger
    }

    @Test(description = "Пример теста (индивидуальное логирование для каждого запроса)", groups = ["rest", "smoke"])
    @Issue("https://jira.bcc.kz/browse/QA-811")
    @Description("Пример теста на RestAssured с индивидуальным логированием в Allure для каждого запроса в одном тесте")
    @Severity(SeverityLevel.NORMAL)
    fun firstTest() {
        val cookieFilter = CookieFilter()
        val clientID = "dbp-channels-bcc-web"
        lateinit var token: String

        step("Вызов API $api (action = SIGN, authType = PASS)") {
            token =
            given().
                    spec(specs.commonRequestSpecification(newLogEntity("1. action = SIGN, authType = PASS"))).
                    filter(cookieFilter).
                    contentType(ContentType.URLENC).
                    param("grant_type", "password").
                    param("client_id", clientID).
                    param("login", config.userLogin()).
                    param("cell1", config.userPass()).
                    param("action", "SIGN").
                    param("authType", "PASS").
            `when`().
                    post(api).
            then().
                    spec(specs.commonResponseSpecification()).
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("device_id", not(emptyOrNullString())).
                    body("success", equalTo(true)).
                    body("verified", equalTo(false)).
                    body("token", matchesPattern("[-a-z0-9]+")).
            extract().
                    jsonPath().getString("token")
        }

        step("Вызов API $api (action = SIGN, authType = TOKEN)") {
            given().
                    spec(specs.commonRequestSpecification(newLogEntity("2. action = SIGN, authType = TOKEN"))).
                    filter(cookieFilter).
                    contentType(ContentType.URLENC).
                    param("grant_type", "password").
                    param("client_id", clientID).
                    param("token", token).
                    param("verify_code", "1234").
                    param("action", "SIGN").
                    param("authType", "TOKEN").
            `when`().
                    post(api).
            then().
                    spec(specs.commonResponseSpecification()).
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("success", equalTo(true)).
                    body("verified", equalTo(true))
        }

        step("Вызов API $api (action = CONNECT)") {
            given().
                    spec(specs.commonRequestSpecification(newLogEntity("3. action = CONNECT"))).
                    filter(cookieFilter).
                    contentType(ContentType.URLENC).
                    param("grant_type", "password").
                    param("client_id", clientID).
                    param("action", "CONNECT").
            `when`().
                    post(api).
            then().
                    spec(specs.commonResponseSpecification()).
                    statusCode(200).
                    contentType(ContentType.JSON).
                    body("access_token", not(emptyOrNullString())).
                    body("refresh_token", not(emptyOrNullString()))
        }
    }
}