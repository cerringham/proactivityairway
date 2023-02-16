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

    public Boolean validateAirplaneModel(String airplaneModel) {
        if (!validateNameOfFleet(airplaneModel)) {
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
}
