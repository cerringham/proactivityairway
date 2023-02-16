package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

}
