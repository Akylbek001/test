package common.config.env;

import common.config.ConfigProvider;
import common.config.app.AppConfig;
import org.aeonbits.owner.ConfigFactory;

public class EnvConfigProvider implements ConfigProvider<EnvConfig> {
    private static EnvConfig config;

    private EnvConfigProvider(){
        checkInstantiation();
    }

    public static EnvConfig get() {
        if (config == null) {
            config = ConfigFactory.create(EnvConfig.class, System.getProperties());
        }

        return config;
    }

    @Override
    public EnvConfig getConfig() {
        return get();
    }
}
