package it.proactivity.proactivityairway.model.dto;

import it.proactivity.proactivityairway.model.Flight;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FleetDto {
    private Long id;
    private String airplaneModel;
    private Integer numberOfSeat;
    private Integer availability;
    private List<Flight> flightList;

    public FleetDto (String airplaneModel, Integer availability) {
        this.airplaneModel = airplaneModel;
        this.availability = availability;
    }

    public FleetDto(Long id, String airplaneModel, Integer numberOfSeat, Integer availability, List<Flight> flightList) {
        this.id = id;
        this.airplaneModel = airplaneModel;
        this.numberOfSeat = numberOfSeat;
        this.availability = availability;
        this.flightList = flightList;
    }

}
