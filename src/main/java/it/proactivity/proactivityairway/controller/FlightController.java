package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.dto.CustomDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping(value = "/from-to-list")
    public ResponseEntity getFlightsFromToList (@RequestParam String from,@RequestParam String to) {
        return flightService.getFlightsFromToList(from, to);
    }

    @GetMapping(value = "/buy-ticket")
    public ResponseEntity<List<FlightDto>> getFlightListToBuy(@RequestBody CustomDto customDto) {
        return flightService.getFlightListToBuy(customDto);
    }
}
