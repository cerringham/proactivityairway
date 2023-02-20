package it.proactivity.proactivityairway.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator {

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

    public Boolean validateFlightId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Flight id can't be null");
        }
        return true;
    }

    public Boolean validateSeat(String seat) {
        if (StringUtils.isEmpty(seat)) {
            throw new IllegalArgumentException("Seat can't be null or empty");
        }
        return true;
    }
}
