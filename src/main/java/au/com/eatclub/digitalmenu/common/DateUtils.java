package au.com.eatclub.digitalmenu.common;

import java.time.LocalDate;

public class DateUtils {

    private DateUtils() {}

    public static boolean isFirstDateBetweenOtherTwoDates(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
