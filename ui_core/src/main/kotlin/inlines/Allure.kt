package inlines

import io.qameta.allure.Allure
import io.qameta.allure.model.Status

fun <T> step(name: String, lambda: () -> T) {
    class Help : Allure.ThrowableRunnable<T> {
        override fun run() = lambda()
    }
    Allure.step(name, Help())
}

fun step(name: String) {
    Allure.step(name)
}

fun step(name: String, status: Status) {
    Allure.step(name, status)
}