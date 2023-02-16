package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Fleet;

public class FleetBuilder {

    private String airplaneDescription;

    private Integer numberOfSeat;

    private Integer availability;

    private FleetBuilder(String airplaneDescription) {
        this.airplaneDescription = airplaneDescription;
    }

    public static FleetBuilder newBuilder(String airplaneDescription) {
        return new FleetBuilder(airplaneDescription);
    }

    public FleetBuilder numberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
        return this;
    }

    public FleetBuilder availability(Integer availability) {
        this.availability = availability;
        return this;
    }

    public Fleet build() {
        return new Fleet(airplaneDescription, numberOfSeat, availability);
    }
}
