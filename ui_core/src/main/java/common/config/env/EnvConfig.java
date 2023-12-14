package common.config.env;

import org.aeonbits.owner.Config;
@Config.Sources({"classpath:environment.properties"})
public interface EnvConfig extends Config{
    @Key("run")
    String run();

    @Key("selenoid.url")
    String selenoidUrl();

    @Key("browser")
    String browser();
}
