package it.proactivity.proactivityairway.utility;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParsingUtility {

    public static LocalDate parseStringToLocalDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("date can't be null or empty");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return parsedDate;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String parseDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            String formattedDate = date.format(formatter);
            return formattedDate;
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static String parseTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            String formattedTime = time.format(formatter);
            return formattedTime;
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
