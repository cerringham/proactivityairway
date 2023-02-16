package it.proactivity.proactivityairway.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Component
public class ParsingUtility {
    public static LocalDate parseStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }
}
