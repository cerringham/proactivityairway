package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.dto.BuyTicketDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.model.dto.FlightIdDto;
import it.proactivity.proactivityairway.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/get-flights-from-to-date")
    public ResponseEntity<List<Flight>> getFlightsFromAndToDate(@RequestParam String from, @RequestParam String to) {
        return flightService.findFlightsFromAndToDate(from, to);
    }


    @GetMapping("/buy-flight-step1")
    public ResponseEntity<List<FlightDto>> buyFlightStep1(@RequestBody BuyTicketDto dto) {
        return flightService.buyFlightStep1(dto);
    }

    @GetMapping("/buy-flight-step2/{customerId}")
    public ResponseEntity buyFlightStep2(@RequestBody FlightIdDto flightIdDto, @PathVariable Long customerId,
                                         @RequestParam String seat) {
        return flightService.buyFlightStep2(flightIdDto, customerId, seat);
    }
}
