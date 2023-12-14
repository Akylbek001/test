//package tests
//
//import common.utils.WaitUtils
//import inlines.step
//import io.qameta.allure.*
//import org.testng.annotations.BeforeMethod
//import org.testng.annotations.Test
//
////используем BaseTest на Kotlin
//@Owner("Андрей Усков")
//@Feature("Функционал логина")
//class KotlinLoginTest2 : KotlinBaseTest() {
//    @BeforeMethod(alwaysRun = true, description = "Подготовка браузера")
//    fun setUpMethod() {
//        brManager.clearCache()
//        navigation.gotoLoginPage()
//        WaitUtils.wait(1)
//    }
//
//    @Test(description = "Простая проверка логина с помощью OTP", groups = ["automated"])
//    @Issue("https://jira.bcc.kz/browse/QA-734")
//    @Description("Вариант теста логина на основой странице на Kotlin, в т.ч. с базовым тестом на Kotlin")
//    @Severity(SeverityLevel.BLOCKER)
//    fun kotlinTestLoginWithKotlinBaseTest() {
//        step("Логин на основной странице") {
//            loginSteps.login(config.userLogin(), config.userPass())
//            WaitUtils.wait(2)
//        }
//    }
//
//}