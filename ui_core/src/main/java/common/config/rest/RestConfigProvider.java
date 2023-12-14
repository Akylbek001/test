package common.config.rest;

import common.config.ConfigProvider;
import org.aeonbits.owner.ConfigFactory;

public final class RestConfigProvider implements ConfigProvider<RestConfig> {

    private static RestConfig config;

    private RestConfigProvider() {
        checkInstantiation();
    }

    public static RestConfig get() {
        if (config == null) {
            config = ConfigFactory.create(RestConfig.class, System.getProperties());
        }

        return config;
    }

    @Override
    public RestConfig getConfig() {
        return get();
    }
}