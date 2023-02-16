package it.proactivity.proactivityairway.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FleetDto {

    private String airplaneDescription;

    private Integer numberOfSeat;

    private Integer availability;

    public FleetDto(String airplaneDescription, Integer availability) {
        this.airplaneDescription = airplaneDescription;
        this.availability = availability;
    }
}
