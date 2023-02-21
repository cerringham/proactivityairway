package it.proactivity.proactivityairway.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomDto {
    private Long customerId;
    private String departureAirport;
    private String arrivalAirport;

    public CustomDto(Long customerId, String departureAirport, String arrivalAirport) {
        this.customerId = customerId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }
}
