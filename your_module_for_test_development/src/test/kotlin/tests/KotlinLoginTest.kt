//package tests
//
//import common.utils.WaitUtils
//import inlines.step
//import io.qameta.allure.*
//import org.testng.annotations.BeforeMethod
//import org.testng.annotations.Test
//
//
////используем BaseTest на Java
//@Owner("Андрей Усков")
//@Feature("Функционал логина")
//class KotlinLoginTest : BaseTest() {
//    @BeforeMethod(alwaysRun = true, description = "Подготовка браузера")
//    fun setUpMethod() {
//        brManager.clearCache()
//        navigation.gotoLoginPage()
//        WaitUtils.wait(1)
//    }
//
//    @Test(description = "Простая проверка логина с помощью OTP", groups = ["automated"]) //this is just example of test
//    @Issue("https://jira.bcc.kz/browse/QA-734")
//    @Description("Вариант теста логина на основой странице на Kotlin с базовым тестом на Java")
//    @Severity(SeverityLevel.BLOCKER)
//    fun kotlinTestLoginWithJavaBaseTest() {
//        step("Логин на основной странице") {
//            loginSteps.login(config.userLogin(), config.userPass())
//            WaitUtils.wait(2) // you can remove this
//        }
//    }
//
//}