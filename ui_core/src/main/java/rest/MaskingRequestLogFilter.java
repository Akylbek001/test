package rest;

import common.config.rest.RestConfig;
import common.config.rest.RestConfigProvider;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.io.PrintStream;
import java.util.*;

public class MaskingRequestLogFilter implements Filter {
    private static final String MASK = "[ BLACKLISTED ]";
    private RestConfig config = RestConfigProvider.get();
    private PrintStream printStream;
    private List<String> requestParams;
    private List<String> formParams;
    private List<String> jsonBodyKeys;
    private Set<String> headers;
    private Map<String, String> replacedParams;
    private Map<String, String> replacedFormParams;
    private String replacedJsonBody = "";

    public MaskingRequestLogFilter(PrintStream printStream) {
        this.printStream = printStream;
        requestParams = Arrays.asList(config.maskRequestParams().split(","));
        formParams = Arrays.asList(config.maskFormParams().split(","));
        jsonBodyKeys = Arrays.asList(config.maskRequestJsonBodyKeys().split(","));
        var blacklistedHeaders = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        if(config.maskRequestHeaders().length() > 0) {
            blacklistedHeaders.addAll(Set.of(config.maskRequestHeaders().split(",")));
        }
        headers = blacklistedHeaders;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        maskAllSecrets(requestSpec);
        RequestPrinter.print(requestSpec, requestSpec.getMethod(), requestSpec.getURI(), LogDetail.ALL, headers, printStream, true);
        unmaskAllSecrets(requestSpec);
        return ctx.next(requestSpec, responseSpec);
    }

    private void maskAllSecrets(FilterableRequestSpecification requestSpec) {
        maskParams(requestSpec);
        maskFormParams(requestSpec);
        maskJsonBodyKeys(requestSpec);
    }

    private void unmaskAllSecrets(FilterableRequestSpecification requestSpec) {
        unmaskParams(requestSpec);
        unmaskFormParams(requestSpec);
        unmaskJsonBodyKeys(requestSpec);
    }

    private void maskParams(FilterableRequestSpecification requestSpec) {
        replacedParams = new HashMap<>();
        requestParams.forEach(param -> {
            if(requestSpec.getRequestParams().containsKey(param)) {
                var originalValue = requestSpec.getRequestParams().get(param);
                replacedParams.put(param, originalValue);
                requestSpec.removeParam(param);
                requestSpec.param(param, MASK);
            }
        });
    }

    private void unmaskParams(FilterableRequestSpecification requestSpec) {
        replacedParams.keySet().forEach(key -> {
            requestSpec.removeParam(key);
            requestSpec.param(key, replacedParams.get(key));
        });
    }

    private void maskFormParams(FilterableRequestSpecification requestSpec) {
        replacedFormParams = new HashMap<>();
        formParams.forEach(param -> {
            if(requestSpec.getFormParams().containsKey(param)) {
                var originalValue = requestSpec.getFormParams().get(param);
                replacedFormParams.put(param, originalValue);
                requestSpec.removeFormParam(param);
                requestSpec.formParam(param, MASK);
            }
        });
    }

    private void unmaskFormParams(FilterableRequestSpecification requestSpec) {
        replacedFormParams.keySet().forEach(key -> {
            requestSpec.removeFormParam(key);
            requestSpec.formParam(key, replacedFormParams.get(key));
        });
    }

    private void maskJsonBodyKeys(FilterableRequestSpecification requestSpec) {
        if(requestSpec.getBody() != null) {
            replacedJsonBody = requestSpec.getBody().toString();
            String maskedBody = replacedJsonBody;
            for(String key : jsonBodyKeys) {
                maskedBody = maskedBody.replaceAll("\"" + key + "\" *: *\"[^\"]+\"", "\"" + key + "\":\"" + MASK + "\"");
            }
            requestSpec.body(maskedBody);
        }
    }

    private void unmaskJsonBodyKeys(FilterableRequestSpecification requestSpec) {
        if(replacedJsonBody.length() > 0) {
            requestSpec.body(replacedJsonBody);
        }
    }
}
