package it.proactivity.proactivityairway.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private String departureTime;

    private String arrivalTime;

    private String flightDate;

    @Override
    public String toString() {
        return departureTime + " " + arrivalTime + " " + flightDate;
    }
}
