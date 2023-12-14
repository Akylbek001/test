package driver;

import common.utils.AllureUtils;
import helpers.*;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Manages WebDriver
 */

public class DriverManager {

    private static final int DEFAULT_IMPL_WAIT_SEC = 15; //TODO может, в конфиг вытащить?
    private static final int DEF_DELAY = 600; //FIXME не используется

    @Getter
    private WebDriver driver;

    @Getter
    private WebDriverWait driverWait;

    @Getter
    private Button button;

    @Getter
    private DropDown dropDown;

    @Getter
    private ElementsAttributes elAttr;

    @Getter
    private Input input;

    @Getter
    private Move move;

    @Getter
    private JavascriptExecutor js;

    public DriverManager(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_IMPL_WAIT_SEC));
        this.js = (JavascriptExecutor) driver;
        this.button = new Button(driver, driverWait);
        this.dropDown = new DropDown(); //TODO тут драйвер получается через InitDriver.initDriver() - если так можно, то зачем драйвер в параметре этого конструктора?
        this.elAttr = new ElementsAttributes();
        this.input = new Input(); //TODO в Input есть ссылка на DriverManager - почему бы ей тут не присвоить this?
        this.move = new Move();
    }

    //TODO а почему только статик? Если у нас уже есть готовый объект DriverManager, то удобнее было бы позвать метод от экземпляра без передачи параметра
    public static void takeScreen(WebDriver driver) {
        if (driver == null) {
            return;
        }

        var bytes = ((EventFiringWebDriver) driver).getScreenshotAs(OutputType.BYTES);
        AllureUtils.attachPng("Screenshoot", bytes);
    }
}