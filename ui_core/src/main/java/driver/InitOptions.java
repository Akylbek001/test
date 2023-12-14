package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

/**
 * Initialization options to start browser with selected parameters
 */
public final class InitOptions {
    private static ChromeOptions optChrome = new ChromeOptions();

    /**
     * Use headless mode on CI/CD
     * to avoid case when workaround the absence of real display on machines with no display hardware
     * and no physical input devices
     */
    protected static ChromeOptions defChromeOpt() {
        HashMap<String, Object> prefsOne = new HashMap<>();
        prefsOne.put("profile.default_content_settings.popups", 0);
        prefsOne.put("download.default_directory", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "downloadFiles");
        prefsOne.put("download.Prompt_for_download", false);
        prefsOne.put("download.directory_upgrade", true);
        prefsOne.put("safebrowsing.enabled", false);
        prefsOne.put("download.extensions_to_open", "application/xlsx");
        prefsOne.put("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, application/xls, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        prefsOne.put("profile.default_content_setting_values.notifications", 1);
        prefsOne.put("plugins.always_open_pdf_externally", true);
        prefsOne.put("pdfjs.disabled", true);
        prefsOne.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"}
        );

        optChrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        optChrome.addArguments("--ignore-certificate-errors");
        optChrome.addArguments("--use-fake-ui-for-media-stream");
        optChrome.addArguments("--disable-user-media-security");
        optChrome.addArguments("--disable-web-security");
        optChrome.addArguments("--reduce-security-for-testing");
        optChrome.addArguments("--use-fake-device-for-media-stream");
        optChrome.addArguments("--allow-file-access-from-files");

        optChrome.addArguments("--start-maximized");
        optChrome.addArguments("--no-sandbox");
        optChrome.addArguments("--disable-gpu");
//        optChrome.addArguments("--incognito");
        //optChrome.addArguments("--disable-popup-blocking");
        optChrome.addArguments("--safebrowsing-disable-download-protection");
        optChrome.addArguments("--safebrowsing-disable-extension-blacklist");
        optChrome.addArguments("--kiosk-printing");
        optChrome.addArguments("--disable-extensions");
        optChrome.setExperimentalOption("prefs", prefsOne);
        optChrome.addArguments("--disable-infobars");
        //optChrome.addArguments("--enable-infobars");
        return optChrome;
    }
}
