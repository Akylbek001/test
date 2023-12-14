package common.utils;

import common.consts.CharacterSetConstants;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomUtils {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String specialCharacters = "\\.[]{}()<>*+=!?^$|";
    private static final String numericCharacters = "0123456789";

    public static String randomCyrillic(int count) {
        String name = "";
        for (int i = 0; i < count; i++) {
            String cyrillicChars = CharacterSetConstants.RUSSIAN_ALPHABET;
            char c = cyrillicChars.charAt(RANDOM.nextInt(cyrillicChars.length()));
            name+=c;
        }
        return name;
    }

    public static String randomLatin(int count) {
        String name = "";
        for (int i = 0; i < count; i++) {
            String latinChars = CharacterSetConstants.ENGLISH_ALPHABET;
            char c = latinChars.charAt(RANDOM.nextInt(latinChars.length()));
            name+=c;
        }
        return name;
    }

    public static String randomKazakh(int count) {
        String name = "";
        for (int i = 0; i < count; i++) {
            String kazakhChars = CharacterSetConstants.KAZAKH_ALPHABET;
            char c = kazakhChars.charAt(RANDOM.nextInt(kazakhChars.length()));
            name+=c;
        }
        return name;
    }

    public static String randomSpecial(int count) {
        String name = "";
        for (int i = 0; i < count; i++) {
            char c = specialCharacters.charAt(RANDOM.nextInt(specialCharacters.length()));
            name+=c;
        }
        return name;
    }

    public static String randomNumeric(int count) {
        String name = "";
        for (int i = 0; i < count; i++) {
            char c = numericCharacters.charAt(RANDOM.nextInt(numericCharacters.length()));
            name+=c;
        }
        return name;
    }

    public static int randomAmountFromTo (int min, int max) {
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        return random_int;
    }

    public static List<String> getNumbersFromString(String string) {
        List<String> listNumbers = new ArrayList<>();
        Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?+(.[0-9]+)?");
        Matcher matcher=pat.matcher(string);
        while (matcher.find()) {
            listNumbers.add(matcher.group());
        };
        return listNumbers;
    }

    public static int getRandIntBetween(int min, int max) {
        return org.apache.commons.lang3.RandomUtils.nextInt(min, max);
    }

    public static <T> T getRandListElem(List<T> list) {
        Random rand = new Random();
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Collection must be defined and contains at least one element");
        }
        int randIndex = rand.nextInt(list.size());
        return list.get(randIndex);
    }


}


