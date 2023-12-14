package tests

import inlines.step
import io.qameta.allure.*
import org.testng.annotations.Test

@Owner("Андрей Усков")
@Feature("Произвольный функционал для примера")
class KotlinManualTests {
    @Test(description = "Пример неавтоматизированного теста", groups = ["manual"])
    @Issue("https://jira.bcc.kz/browse/QA-734")
    @Description("Тест не содержит реальной автоматизации, служит лишь как пример ведения ручного теста до его автоматизации. Запись на Kotlin.")
    @Severity(SeverityLevel.NORMAL)
    fun notAutomatedTest() {
        step("Простой шаг")
        step("Составной шаг") {
            step("Иди туда, не знаю куда")
            step("Принеси то, не знаю что")
            step("И проверь, что получилось")
        }
        step("Проверка результата") {
            step("Проверка 1")
            step("Проверка 2")
        }
    }
}