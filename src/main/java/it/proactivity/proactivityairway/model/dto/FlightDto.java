package it.proactivity.proactivityairway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDto {

    private Long flightId;

    private String departure;

    private String arrival;
}
