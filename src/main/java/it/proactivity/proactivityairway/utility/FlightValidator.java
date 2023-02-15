package it.proactivity.proactivityairway.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FlightValidator {

    public Boolean validateFromAndToDate(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return false;
        }
        if (from.isAfter(to)) {
            return false;
        }
        return true;
    }
}
