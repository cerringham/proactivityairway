package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.Airport;

import java.time.ZoneId;

public class ZoneIdUtility {

    public static ZoneId getZoneId(Airport departure) {
        if (departure.getCity().equals("MILANO")) {
            return ZoneId.of("Europe/Rome");
        } else if (departure.getCity().equals("New York") || departure.getCity().equals("Miami") ||
                departure.getCity().equals("Philadelphia") || departure.getCity().equals("Boston")) {
            return ZoneId.of("America/New_York");
        } else if (departure.getCity().equals("Los Angeles")) {
            return ZoneId.of("America/Los_Angeles");
        } else if (departure.getCity().equals("Tokio")) {
            return ZoneId.of("Asia/Tokyo");
        }else {
            throw new IllegalStateException("Zone not found");
        }

    }
}
