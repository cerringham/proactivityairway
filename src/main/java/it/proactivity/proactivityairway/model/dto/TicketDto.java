package it.proactivity.proactivityairway.model.dto;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Flight;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TicketDto {
    private Long id;
    private String customerId;
    private String flightId;
    private String seatCode;

    public TicketDto(String customerId, String flightId, String seatCode) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.seatCode = seatCode;
    }
}
