package inlines

import org.slf4j.LoggerFactory
import org.slf4j.Logger

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}