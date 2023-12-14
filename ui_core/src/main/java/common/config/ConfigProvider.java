package common.config;

public interface ConfigProvider<T> {

    T getConfig();

    default void checkInstantiation() {
        throw new IllegalStateException(this.getClass().getSimpleName() + " shouldn't be instantiated");
    }
}
