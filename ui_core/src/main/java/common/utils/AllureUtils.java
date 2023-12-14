package common.utils;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class AllureUtils {

    private AllureUtils() {}

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachPng(String decription, byte[] bytes) {
        return bytes;
    }

    @Attachment(value = "Logs", type = "text/html")
    public static byte[] attachFile(File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            log.error("Failed to load log file: ", e);
        }

        return new byte[0];
    }

    @Attachment(value = "{0}")
    public static byte[] attachFromStream(String description, ByteArrayOutputStream stream) {
        return convertToByteArray(stream);
    }

    private static byte[] convertToByteArray(ByteArrayOutputStream logStream) {
        byte[] array = logStream.toByteArray();
        logStream.reset();
        return array;
    }
}