package it.proactivity.proactivityairway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto extends FlightIdDto {

    private String departureAirport;

    private String arrivalAirport;

    public FlightDto(Long id, String departureAirport, String arrivalAirport) {
        super(id);
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }
}
