package common.config.app;

import common.config.ConfigProvider;
import org.aeonbits.owner.ConfigFactory;
public final class AppConfigProvider implements ConfigProvider<AppConfig> {

    private static AppConfig config;

    private AppConfigProvider() {
        checkInstantiation();
    }

    public static AppConfig get() {
        if (config == null) {
            config = ConfigFactory.create(AppConfig.class, System.getProperties());
        }

        return config;
    }

    @Override
    public AppConfig getConfig() {
        return get();
    }
}