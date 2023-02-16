package it.proactivity.proactivityairway.utility;

import org.apache.commons.lang3.StringUtils;
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

    public Boolean validateCustomerId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Customer id can't be null");
        }
        return true;
    }

    public Boolean validateDeparture(String departure) {
        if (StringUtils.isEmpty(departure)) {
            throw new IllegalArgumentException("Departure can't be null or empty");
        }

        return !StringUtils.isNumeric(departure);
    }

    public Boolean validateArrival(String arrival) {
        if (StringUtils.isEmpty(arrival)) {
            throw new IllegalArgumentException("Arrival can't be null or empty");
        }

        return !StringUtils.isNumeric(arrival);
    }
}
