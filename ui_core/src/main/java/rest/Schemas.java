package rest;


import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Класс для работы с файлами, получающимися автоматическим преобразованием из файла формата Open API.
// Например, из спецификации https://m-dev.bcc.kz/mortgage/swagger/v1/swagger.json получен файл your_module_for_test_development/src/main/resources/schemas/schemas.json.
// Пример команды конвертации: openapi2schema -i swagger.json -p > schemas.json
// Подробнее про утилиту см. https://github.com/mikunn/openapi2schema.
public class Schemas {

    public static String getAPIRequestBodySchema(String filePath, String api, String method) {
        try {
            JSONObject methodSchemas = getMethodSchemas(filePath, api, method);
            JSONObject requestBodySchema = (JSONObject) methodSchemas.get("body");
            return requestBodySchema.toJSONString();
        } catch (NullPointerException e) {
            throw new RuntimeException("Could not find JSON schema for API '" + api + "',  method '" + method + "'.", e);
        }
    }

    public static String getAPIResponseSchema(String filePath, String api, String method, String responseCode) {
        try {
            JSONObject methodSchemas = getMethodSchemas(filePath, api, method);
            JSONObject responseSchemas = (JSONObject) methodSchemas.get("responses");
            JSONObject codeSchema = (JSONObject) responseSchemas.get(responseCode);
            return codeSchema.toJSONString();
        } catch (NullPointerException e) {
            throw new RuntimeException("Could not find JSON schema for API '" + api + "',  method '" + method + "', code '" + responseCode + "'.", e);
        }
    }

    private static JSONObject getMethodSchemas(String filePath, String api, String method) {
        String schemas;
        try {
            schemas = FileUtils.readFileToString(
                    new File(filePath),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject schemasJsonObject = (JSONObject) JSONValue.parse(schemas);
        JSONObject APISchemas = (JSONObject) schemasJsonObject.get(api);
        return (JSONObject) APISchemas.get(method);
    }

}
