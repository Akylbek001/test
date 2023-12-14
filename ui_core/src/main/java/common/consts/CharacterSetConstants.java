package common.consts;

public final class CharacterSetConstants {
    public static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvqxyz";
    public static final String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String KAZAKH_ALPHABET = "АӘБВГҒДЕЁЖЗИЙКҚЛМНҢОӨПРСТУҰҮФХҺЦЧШЩЪЫІЬЭЮЯаәбвгғдеёжзийкқлмнңоөпрстуұүфхһцчшщъыіьэюя";
    public static final String NUMERIC = "0123456789";
    public static final String SPEC_CHARS = ",./?'|][{}()-_=+*&^:;#@$%\"";
    public static final String ALL_CHARS = ENGLISH_ALPHABET + NUMERIC + SPEC_CHARS;
    public static final String ALL_CHARS_AND_RUSSIAN = ENGLISH_ALPHABET + NUMERIC + SPEC_CHARS+RUSSIAN_ALPHABET;
}
