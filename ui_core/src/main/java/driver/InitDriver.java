package driver;

import common.config.env.EnvConfig;
import common.config.env.EnvConfigProvider;
import common.utils.OSValidation;
import events.EventLog;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


/**
 * Driver initialization
 */
@Slf4j
public class InitDriver {
    private static WebDriver driver;
    public static boolean needReInit = false;
    public static EventFiringWebDriver eventDriver;
    private static EventLog eventLog = new EventLog();
    private static EnvConfig config = EnvConfigProvider.get();


    private InitDriver() {}

    public static WebDriver initDriver() {
        if (eventDriver == null || needReInit) {
            createDriver(config.run());
            needReInit = false;
        }
        return eventDriver;
    }

    public static void needReInit() {
        needReInit = true;
    }
    /**
     * Driver initialization
     */
    private static void createDriver(String run) {
        if (run.equals("local")) {
            if (OSValidation.isMac()) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            } else if (OSValidation.isWindows()) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            }

            driver = new ChromeDriver(InitOptions.defChromeOpt());
            eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(eventLog);
            log.info("Chrome browser instantiated");
        } else if (run.equals("remote")) {
            ChromeOptions chromeOptions = InitOptions.defChromeOpt();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            capabilities.setBrowserName("chrome");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            capabilities.setCapability("enableLog", true);

            try {
                driver = new RemoteWebDriver(new URL(config.selenoidUrl()), chromeOptions);
                eventDriver = new EventFiringWebDriver(driver);
                eventDriver.register(eventLog);
                log.info("Remote browser instantiated");
            } catch (Exception e) {
                log.error("Failed to create remote driver: " + e.getMessage());
            }
        }
    }
}
