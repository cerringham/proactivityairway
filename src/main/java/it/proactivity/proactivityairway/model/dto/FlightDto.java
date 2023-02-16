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

    private String departure;

    private String arrival;

    public FlightDto(Long id, String departure, String arrival) {
        super(id);
        this.departure = departure;
        this.arrival = arrival;
    }
}
