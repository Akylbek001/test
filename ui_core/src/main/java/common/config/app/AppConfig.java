package common.config.app;

import org.aeonbits.owner.Config;
@Config.Sources({"classpath:application.properties"})
public interface AppConfig extends Config {
    @Key("run")
    String run();

    @Key("base.url")
    String baseUrl();

    @Key("product.name")
    String productName();
}
