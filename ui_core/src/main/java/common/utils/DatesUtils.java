package common.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.HashMap;
import java.util.Map;

public class DatesUtils {
    private static DateTimeFormatter formats = DateTimeFormatter.ofPattern("dd.MM.YYYY");

    private DatesUtils() {}

    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();

        return date.format(formats);
    }

    public static String getCurrentMonth() {
        LocalDate date = LocalDate.now();
        return date.getMonth().toString();
    }

    public static Integer getCurrentYear() {
        LocalDate date = LocalDate.now();
        return date.getYear();
    }

    public static String getFirstDayOfWeek() {
        LocalDate date = LocalDate.now();
        return date.with(DayOfWeek.MONDAY).format(formats);
    }

    public String customDate(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        return date.format(formats);
    }

    public static String futureDay(int countDays) {
        LocalDate date = LocalDate.now().plusDays(countDays);
        return date.format(formats);
    }

    public static String tomorrow() {
        LocalDate date = LocalDate.now().plusDays(1);
        return date.format(formats);
    }

    public static String yesterday() {
        LocalDate date = LocalDate.now().minusDays(1);
        return date.format(formats);
    }

    public String pastDate(int randInt) {
        LocalDate date = LocalDate.now()
                .minusYears(randInt)
                .minusMonths(randInt)
                .minusDays(randInt);

        return date.format(formats);
    }
    public String getNumberByMonth(){
        return createMonthMap().get(getCurrentMonth());
    }
    private Map<String, String> createMonthMap() {
        Map<String, String> map = new HashMap<>();
        map.put("JANUARY", "01");
        map.put("FEBRUARY", "02");
        map.put("MARCH", "03");
        map.put("APRIL", "04");
        map.put("MAY", "05");
        map.put("JUNE", "06");
        map.put("JULY", "07");
        map.put("AUGUST", "08");
        map.put("SEPTEMBER", "09");
        map.put("OCTOBER", "10");
        map.put("NOVEMBER", "11");
        map.put("DECEMBER", "12");
        return map;
    }
}