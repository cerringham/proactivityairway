package it.proactivity.proactivityairway.utility;

import java.time.LocalDate;

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
