package rest;

import common.config.rest.RestConfig;
import common.config.rest.RestConfigProvider;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.io.PrintStream;

public class Filters {
    private static RestConfig config = RestConfigProvider.get();

    public static Filter requestLoggingFilter(PrintStream printStream) {
        if(config.maskSecrets()) {
            return new MaskingRequestLogFilter(printStream);
        } else {
            return new RequestLoggingFilter(printStream);
        }
    }

    public static Filter responseLoggingFilter(PrintStream printStream) {
        if(config.maskSecrets()) {
            return new MaskingResponseLogFilter(printStream);
        } else {
            return new ResponseLoggingFilter(printStream);
        }
    }

}
