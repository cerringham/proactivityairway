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

    public Boolean validateAirplaneModel(String airplaneModel) {
        if (StringUtils.isEmpty(airplaneModel)) {
            throw new IllegalArgumentException("airplaneModel can't be null");
        }
        String[] array = airplaneModel.split(" ");
        if (array[0].equalsIgnoreCase("Boeing")) {
            if (StringUtils.isNumeric(array[1]) && array[1].length() == 3) {
                return true;
            } else {
                return false;
            }
        }

        if (array[0].equalsIgnoreCase("Airbus") && array[1].length() == 4) {
            if (StringUtils.isAlpha(array[1].substring(0, 1)) && StringUtils.isNumeric(array[1].substring(1, 4))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
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
