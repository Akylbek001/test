package tests

import inlines.*
import org.testng.annotations.Listeners
import rest.RestAssuredListener

@Listeners(RestAssuredListener::class)
abstract class SimpleLoggingBaseRestTest: BaseRestTest() {}