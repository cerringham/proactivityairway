package it.proactivity.proactivityairway.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Getter
@Setter
@NoArgsConstructor
public class FlightWithDateDto extends FlightDto{

   private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

   private String airplaneModel;


   public FlightWithDateDto(LocalDateTime departureDate, LocalDateTime arrivalDate, String departureAirport,
                            String arrivalAirport, String airplaneModel) {
       super(departureAirport, arrivalAirport);
       this.departureDate = departureDate;
       this.airplaneModel = airplaneModel;
       this.arrivalDate = arrivalDate;

   }

}
