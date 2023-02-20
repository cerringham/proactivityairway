package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;


import java.time.LocalDate;

public class FlightBuilder {

    private Route route;

    private Fleet fleet;

    private String departureTime;

    private String arrivalTime;

    private LocalDate flightDate;

    private FlightBuilder(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public static FlightBuilder newBuilder(LocalDate flightDate) {
        return new FlightBuilder(flightDate);
    }

    public FlightBuilder route(Route route) {
        this.route = route;
        return this;
    }

    public FlightBuilder fleet(Fleet fleet) {
        this.fleet = fleet;
        return this;
    }

    public FlightBuilder departureTime(String departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public  FlightBuilder arrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public Flight build() {
        return new Flight(flightDate, route, fleet, departureTime, arrivalTime);
    }
}
