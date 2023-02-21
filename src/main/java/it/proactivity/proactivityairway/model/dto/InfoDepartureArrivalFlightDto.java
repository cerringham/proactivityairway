package it.proactivity.proactivityairway.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoDepartureArrivalFlightDto {

    private String departureAirport;
    private String departureDate;
    private String departureTime;
    private String arrivalAirport;
    private String arrivalDate;
    private String arrivalTime;
    private String flightDuration;

    private String delay;

    public InfoDepartureArrivalFlightDto(String departureAirport, String departureDate, String departureTime,
                                         String arrivalAirport, String arrivalDate, String arrivalTime, String flightDuration) {
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalAirport = arrivalAirport;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
    }

    @Override
    public String toString() {
        return departureAirport + " - " + arrivalAirport + "\n"
                + departureTime + " - " + arrivalTime + "\n" +
                departureDate + " - " + arrivalDate + "\n" +
                flightDuration + "\n"
                + "delay : " + delay;
    }
}
