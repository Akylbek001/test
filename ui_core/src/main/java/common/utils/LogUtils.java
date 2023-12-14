package common.utils;

import org.apache.log4j.FileAppender;
import org.apache.log4j.PatternLayout;

import java.io.File;

import static org.apache.log4j.Logger.getRootLogger;

public class LogUtils {

    private static final PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} [%p] - %m%n");
    private static final String LOG_DIR = "./target/logs";
    private static final String ENCODING = "UTF-8";

    protected LogUtils() {
        //Utility class
    }

    public static File attachFileAppender(String fileName) {
        FileAppender apiLog = new FileAppender();
        File logFile = new File(LOG_DIR,
                (fileName + ".log"));
        apiLog.setName(logFile.getName());
        apiLog.setFile(logFile.getAbsolutePath());
        apiLog.setEncoding(ENCODING);
        apiLog.setLayout(layout);
        apiLog.setImmediateFlush(true);
        apiLog.activateOptions();
        getRootLogger().addAppender(apiLog);
        return logFile;
    }

    public static void detachAppender(File file) {
        getRootLogger().removeAppender(file.getName());
    }
}
