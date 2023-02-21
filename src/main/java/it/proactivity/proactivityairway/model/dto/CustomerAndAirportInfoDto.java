package it.proactivity.proactivityairway.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerAndAirportInfoDto {
    private Long customerId;
    private String departureAirport;
    private String arrivalAirport;

    public CustomerAndAirportInfoDto(Long customerId, String departureAirport, String arrivalAirport) {
        this.customerId = customerId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }
}
