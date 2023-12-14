package common.config.rest;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:rest.properties"})
public interface RestConfig extends Config {
    @Key("base.uri")
    String baseUri();

    @Key("base.uri.keycloak")
    String keycloakBaseUri();

    @Key("json.schemas.filepath")
    String jsonSchemasFilePath();

    @Key("logs.mask.enabled")
    Boolean maskSecrets();

    @Key("logs.console.requests")
    Boolean logRequestsToConsole();

    @Key("logs.console.responses")
    Boolean logResponsesToConsole();

    @Key("logs.mask.request.requestParams")
    @DefaultValue("")
    String maskRequestParams();

    @Key("logs.mask.request.formParams")
    @DefaultValue("")
    String maskFormParams();

    @Key("logs.mask.request.headers")
    @DefaultValue("")
    String maskRequestHeaders();

    @Key("logs.mask.request.body.jsonKeys")
    @DefaultValue("")
    String maskRequestJsonBodyKeys();

    @Key("logs.mask.response.headers")
    @DefaultValue("")
    String maskResponseHeaders();

    @Key("logs.mask.response.body.jsonKeys")
    @DefaultValue("")
    String maskResponseJsonBodyKeys();

    @Key("user.login")
    String userLogin();

    @Key("user.pass")
    String userPass();
}

