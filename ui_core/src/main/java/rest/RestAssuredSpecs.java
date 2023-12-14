package rest;

import common.config.rest.RestConfig;
import common.config.rest.RestConfigProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.PrintStream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class RestAssuredSpecs {

    private RestConfig config = RestConfigProvider.get();
    private String schemasFilePath;
    private String baseUri;
    private String API;

    public RestAssuredSpecs(String API) {
        this.schemasFilePath = config.jsonSchemasFilePath();
        this.baseUri = config.baseUri();
        this.API = API;
    }

    public RestAssuredSpecs(String schemasFilePath, String baseUri, String API) {
        this.schemasFilePath = schemasFilePath;
        this.baseUri = baseUri;
        this.API = API;
    }

    public RequestSpecification commonRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUri);
        if(config.logRequestsToConsole()) {
            builder.addFilter(Filters.requestLoggingFilter(System.out));
        }
        if(config.logResponsesToConsole()) {
            builder.addFilter(Filters.responseLoggingFilter(System.out));
        }
        return builder.build();
    }

    //на случай, если аттач логов в Allure через листенер не подходит и каждому тесту нужны отдельные потоки вывода
    public RequestSpecification commonRequestSpecification(LogEntity logEntity) {
        return commonRequestSpecification().
                        filter(Filters.requestLoggingFilter(new PrintStream(logEntity.getRequest(), true))).
                        filter(Filters.responseLoggingFilter(new PrintStream(logEntity.getResponse(), true)));

    }

    public ResponseSpecification commonResponseSpecification() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        return builder.build();
    }

    public RequestSpecification jsonRequestWithoutBodySpecification() {
        return commonRequestSpecification().contentType(ContentType.JSON);
    }

    public RequestSpecification jsonRequestWithoutBodySpecification(LogEntity logEntity) {
        return commonRequestSpecification(logEntity).contentType(ContentType.JSON);
    }

    public RequestSpecification jsonRequestSpecification(String body) {
        return jsonRequestWithoutBodySpecification().body(body);
    }

    public RequestSpecification jsonRequestSpecification(String body, LogEntity logEntity) {
        return jsonRequestWithoutBodySpecification(logEntity).body(body);
    }

    public ResponseSpecification allowedGetMethodSpec() {
        return allowedMethodsSpec("GET");
    }

    public ResponseSpecification allowedPutMethodSpec() {
        return allowedMethodsSpec("PUT");
    }

    public ResponseSpecification allowedPostMethodSpec() {
        return allowedMethodsSpec("POST");
    }

    public ResponseSpecification allowedGetPostMethodsSpec() {
        return allowedMethodsSpec("GET, POST");
    }

    public ResponseSpecification allowedDeleteGetPatchMethodsSpec() {
        return allowedMethodsSpec("DELETE, GET, PATCH");
    }

    public ResponseSpecification allowedMethodsSpec(String methods) {
        return commonResponseSpecification().
                statusCode(405).
                header("Allow", methods);
    }

    public ResponseSpecification correctJsonResponseSpecification(String method) {
        return commonResponseSpecification().
                statusCode(200).
                contentType(ContentType.JSON).
                body(matchesJsonSchema(Schemas.getAPIResponseSchema(schemasFilePath, API, method, "200")));
    }

    public ResponseSpecification errorJsonResponseSpecification(String method) {
        return commonResponseSpecification().
                statusCode(400).
                contentType(ContentType.JSON).
                body(matchesJsonSchema(Schemas.getAPIResponseSchema(schemasFilePath, API, method, "400")));
    }

    public ResponseSpecification notFoundResponseSpecification() {
        return commonResponseSpecification().statusCode(404);
    }


}
