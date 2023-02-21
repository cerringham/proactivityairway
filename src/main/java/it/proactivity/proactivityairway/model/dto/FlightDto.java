package it.proactivity.proactivityairway.model.dto;
import it.proactivity.proactivityairway.model.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class FlightDto {

    private Long id;
    private String route;
    private String fleet;
    private String departureTime;
    private String arrivalTime;
    private LocalDate flightDate;
    private List<Ticket> ticketList;

    public FlightDto(String route, String fleet, String departureTime, String arrivalTime, LocalDate flightDate, List<Ticket> ticketList) {
        this.route = route;
        this.fleet = fleet;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDate = flightDate;
        this.ticketList = ticketList;
    }

    public FlightDto(Long id, String departureTime, String arrivalTime) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}
