package it.proactivity.proactivityairway.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FlightWithDateDto extends FlightDto{

   private LocalDate date;

   private String airplaneModel;

    private String departureTime;

    private String arrivalTime;

   public FlightWithDateDto(LocalDate date, String departureAirport, String arrivalAirport, String airplaneModel,
                            String departureTime, String arrivalTime) {
       super(departureAirport, arrivalAirport);
       this.date = date;
       this.airplaneModel = airplaneModel;
       this.departureTime = departureTime;
       this.arrivalTime = arrivalTime;
   }

}
