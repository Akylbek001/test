package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Owner("Алибек Акылбеков")
@Feature("Произвольный функционал для примера")
public class ManualTests {
    @Test(description = "Пример неавтоматизированного теста", groups = {"manual"})
    @Issue("https://jira.bcc.kz/browse/QA-734")
    @Description("Тест не содержит реальной автоматизации, служит лишь как пример ведения ручного теста до его автоматизации. Запись на Java.")
    @Severity(SeverityLevel.NORMAL)
    public void notAutomatedTest() {
        step("Простой шаг");
        step("Составной шаг", () -> {
            step("Иди туда, не знаю куда");
            step("Принеси то, не знаю что");
            step("И проверь, что получилось");
        });
        step("Проверка результата", () -> {
            step("Проверка 1");
            step("Проверка 2");
        });
    }
}
