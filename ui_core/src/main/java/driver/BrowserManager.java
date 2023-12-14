package driver;

import common.config.app.AppConfigProvider;
import common.utils.WaitUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;

public class BrowserManager {

    private static final String URL = AppConfigProvider.get().baseUrl();
    private WebDriver driver;
    private DriverManager drManager;

    public BrowserManager(WebDriver driver) {
        this.driver = driver;
        this.drManager = new DriverManager(driver);
    }

    public void closeTab() {
        int tabsCount = drManager.getDriver().getWindowHandles().size();
        drManager.getDriver().close();
        if(tabsCount == 1) {
            InitDriver.needReInit();
        }
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void getPage(String url) {
        drManager.getDriver().get(url);
    }

    //TODO по какому принципу в некоторых методах "driver.", а в других "drManager.getDriver()."?
    public String getCurrUrl() {
        return drManager.getDriver().getCurrentUrl();
    }

    public String getPageSrc() {
        return drManager.getDriver().getPageSource();
    }

    public void navigateTo(String url) {
        drManager.getDriver().navigate().to(url);
    }

    public void returnBack() {
        drManager.getDriver().navigate().back();
    }

    public void quit() {
        drManager.getDriver().quit();
        InitDriver.needReInit();
    }

    public void openNewTab() {
        //TODO не лучше ли просто позвать openNewTab(URL) ?
        driver.getWindowHandle(); //TODO а зачем тут getWindowHandle(), если этот хендл дальше не используется?
        drManager.getJs().executeScript(String.format("window.open(\"%s\");", URL)); //TODO зачем JS? Не проще driver.switchTo().newWindow(WindowType.TAB) и driver.get(URL)?
    }

    public void openNewTab(String url) {
        driver.getWindowHandle();
        drManager.getJs().executeScript(String.format("window.open(\"%s\");", url));
    }

    public void switchToAnotherTab(int tabNumber) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabNumber));
    }

    public void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        int lastTab = tabs.size();
        driver.switchTo().window(tabs.get(lastTab - 1));
    }

    public Cookie getCookie(String cookieName) {
        return drManager.getDriver().manage().getCookieNamed(cookieName);
    }

    //TODO релазиация только для хрома
    //несовместимо с опцией --incognito
    public void clearCache() {
        String handle = drManager.getDriver().getWindowHandle();
        drManager.getDriver().switchTo().newWindow(WindowType.TAB);
        drManager.getDriver().get("chrome://settings/clearBrowserData");
        WaitUtils.wait(1);
        WebElement clearData =  (WebElement) drManager.getJs().executeScript("return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section[section='privacy'] > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearBrowsingDataConfirm\")");
        drManager.getJs().executeScript("arguments[0].click();", clearData);
        WaitUtils.wait(1);
        drManager.getDriver().close();
        drManager.getDriver().switchTo().window(handle);
    }
}