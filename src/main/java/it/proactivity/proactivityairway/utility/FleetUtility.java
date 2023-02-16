package it.proactivity.proactivityairway.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class FleetUtility {

    public static Boolean validateFleetName(String airplaneModel) {
        if (StringUtils.isEmpty(airplaneModel)) {
            return false;
        }
        String[] parts = airplaneModel.split(" ");
        String model = parts[0];
        if (!model.equals("Boeing") && !model.equals("Airbus")) {
            return false;
        }
        String serialNumber = parts[1];
        if (model.equals("Boeing")) {
            if (!serialNumber.matches("\\d{3}")) {
                return false;
            }
        }else if (model.equals("Airbus")) {
            if (!serialNumber.matches("[A-Z]\\d{3}")) {
                return false;
            }
        }
        return true;
    }
}
