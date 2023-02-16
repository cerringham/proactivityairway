package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.model.dto.TicketDto;
import it.proactivity.proactivityairway.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/insert-customer")
    public ResponseEntity insertCustomer(CustomerDto customerDto) {
        return customerService.insertCustomer(customerDto);
    }

    @GetMapping("/get-ticket-list/{id}")
    public ResponseEntity<List<TicketDto>> getTicketListFromCustomerId(@PathVariable Long id) {
        return customerService.getTicketListFromCustomerId(id);
    }


}
