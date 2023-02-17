package it.proactivity.proactivityairway.controller;


import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.model.dto.TicketDto;
import it.proactivity.proactivityairway.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/insert-customer")
    public ResponseEntity insertNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.insertNewCustomer(customerDto);
    }

    @GetMapping(value = "/get-past-reservations")
    public ResponseEntity<List<TicketDto>>  getPastReservationsFromCustomer(@RequestParam String customerId) {
        return customerService.getPastReservationsFromCustomer(customerId);
    }
}
