package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;


import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class FlightBuilder {

    private Route route;

    private Fleet fleet;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private LocalDate departureDate;

    private LocalDate arrivalDate;


    private FlightBuilder(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public static FlightBuilder newBuilder(LocalDate departureDate) {
        return new FlightBuilder(departureDate);
    }

    public FlightBuilder route(Route route) {
        this.route = route;
        return this;
    }

    public FlightBuilder fleet(Fleet fleet) {
        this.fleet = fleet;
        return this;
    }

    public FlightBuilder departureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
        return this;
    }

    public  FlightBuilder arrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        return this;
    }

    public FlightBuilder arrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public Flight build() {
        return new Flight(departureDate, arrivalDate, route, fleet, departureTime, arrivalTime);
    }
}
