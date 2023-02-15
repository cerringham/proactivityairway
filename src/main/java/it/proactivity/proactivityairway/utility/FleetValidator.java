package it.proactivity.proactivityairway.utility;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class FleetValidator {

    public Boolean validateNameOfFleet(String airplaneModel) {
        if (StringUtils.isEmpty(airplaneModel)) {
            throw new IllegalArgumentException("airplaneModel can't be null");
        }
        return true;
    }

    public Boolean validateAvailability(Integer availability) {
        if (availability <= 0) {
            return false;
        }
        return true;
    }


}
