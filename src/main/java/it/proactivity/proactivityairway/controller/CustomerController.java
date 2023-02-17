package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.model.dto.CustomerDto;
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

    @GetMapping(value = "/get-ticket-list-from-customer")
    public ResponseEntity<List<Ticket>>  getAllTicketsFromCustomer(@RequestParam Long customerId) {
        return customerService.getAllTicketsFromCustomer(customerId);
    }
}
