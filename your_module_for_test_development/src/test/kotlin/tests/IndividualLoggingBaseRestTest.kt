package tests

import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import rest.LogEntity

abstract class IndividualLoggingBaseRestTest: BaseRestTest() {
    private lateinit var logEntities: ThreadLocal<ArrayList<LogEntity>>

    @BeforeMethod(description = "Инициализация переменных логирования")
    fun initLogVars() {
        logEntities = ThreadLocal()
        logEntities.set(ArrayList())
    }

    @AfterMethod(description = "Логи")
    fun attachLogs() {
        logEntities.get().forEach { it.attachToAllure() }
    }

    protected fun newLogEntity(name: String): LogEntity {
        val logEntity = LogEntity(name)
        logEntities.get().add(logEntity)
        return logEntity
    }

}