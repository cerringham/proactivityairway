package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.dto.BuyTicketDto;
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

    @GetMapping("/get-flights-from-to-date")
    public ResponseEntity<List<Flight>> getFlightsFromAndToDate(@RequestParam String from, @RequestParam String to) {
        return flightService.findFlightsFromAndToDate(from, to);
    }



    @GetMapping("/get-flights-list-from-customerId-departure-arrival")
    public ResponseEntity<List<FlightDto>> getFlightListFromCustomerIdDepartureAndArrival(@RequestBody BuyTicketDto dto) {
        return flightService.getFlightListFromCustomerIdDepartureAndArrival(dto);
    }




}
