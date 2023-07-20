package HeXA.MealU_HeXA_Project.api.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String toFormat(LocalDate date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }
    public static LocalDate parseToDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
}
