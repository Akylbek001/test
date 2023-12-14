package rest;

import common.config.rest.RestConfig;
import common.config.rest.RestConfigProvider;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MaskingResponseLogFilter implements Filter {
    private static final String MASK = "[ BLACKLISTED ]";
    private RestConfig config = RestConfigProvider.get();
    private PrintStream printStream;
    private Set<String> headers;
    private List<String> jsonBodyKeys;

    public MaskingResponseLogFilter(PrintStream printStream) {
        this.printStream = printStream;
        config.maskResponseHeaders().split(",");
        var blacklistedHeaders = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if(config.maskResponseHeaders().length() > 0) {
            blacklistedHeaders.addAll(Set.of(config.maskResponseHeaders().split(",")));
        }
        headers = blacklistedHeaders;
        jsonBodyKeys = Arrays.asList(config.maskResponseJsonBodyKeys().split(","));
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        var maskedResponse = maskJsonBodyKeys(response);
        ResponsePrinter.print(maskedResponse, maskedResponse, printStream, LogDetail.ALL, true, headers);
        return response;
    }

    private Response maskJsonBodyKeys(Response response) {
        if(response.getBody() != null && jsonBodyKeys.size() > 0) {
            String maskedBody = response.getBody().asPrettyString();
            for(String key : jsonBodyKeys) {
                maskedBody = maskedBody.replaceAll("\"" + key + "\" *: *\"[^\"]+\"", "\"" + key + "\":\"" + MASK + "\"");
            }
            Response maskedResponse = (new ResponseBuilder()).clone(response).setBody(maskedBody).build();
            ((RestAssuredResponseImpl)maskedResponse).setHasExpectations(true);
            return maskedResponse;
        } else {
            return response;
        }
    }

}
