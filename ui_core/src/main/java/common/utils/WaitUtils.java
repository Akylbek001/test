package common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public final class WaitUtils {

    //TODO я бы поменял название на просто "wait", т.к. с текущим названием может показаться, что ты устанавливаешь какое-то свойство, а не встаёшь на паузу
    public static void wait(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage());
        }
    }
}
