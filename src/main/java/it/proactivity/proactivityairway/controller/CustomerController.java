package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/insert-customer")
    public ResponseEntity insertNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.insertNewCustomer(customerDto);
    }
}
