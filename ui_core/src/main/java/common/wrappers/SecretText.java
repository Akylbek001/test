package common.wrappers;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class SecretText {
    private final static Set<String> secrets = Collections.synchronizedSet(new HashSet<>());

    @Getter
    private final String secretText;

    public SecretText(String text) {
        this.secretText = text;
        secrets.add(text);
    }

    public static boolean isSecret(String text) {
        boolean result;
        synchronized(secrets) {
            result = secrets.stream().anyMatch(secret -> secret.equals(text));
        }
        return result;
    }

    public static String mask(String text) {
        if(text == null) {
            return "";
        }

        return "*".repeat(text.length());
    }

    public static String maskIfSecret(String text) {
        if(isSecret(text)) {
            return mask(text);
        } else {
            return text;
        }
    }

    @Override
    public String toString() {
        return mask(secretText);
    }
}
