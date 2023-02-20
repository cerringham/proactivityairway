package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.BuyTicketDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.model.dto.FlightIdDto;
import it.proactivity.proactivityairway.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/buy-flight-step1")
    public ResponseEntity<List<FlightDto>> buyFlightStep1(@RequestBody BuyTicketDto dto) {
        return ticketService.buyFlightStep1(dto);
    }

    @GetMapping("/buy-flight-step2/{customerId}")
    public ResponseEntity buyFlightStep2(@RequestBody FlightIdDto flightIdDto, @PathVariable Long customerId,
                                         @RequestParam String seat) {
        return ticketService.buyFlightStep2(flightIdDto, customerId, seat);
    }
}
