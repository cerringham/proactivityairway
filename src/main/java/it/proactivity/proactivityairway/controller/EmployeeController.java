package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import it.proactivity.proactivityairway.service.EmployeeService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity deleteEmployeeById(@RequestParam Long id) {
        return employeeService.deleteEmployeeById(id);
    }

    @DeleteMapping(value = "/delete-by-list")
    public ResponseEntity deleteEmployeesByList(@RequestBody List<Long> idList) {
        return employeeService.deleteEmployeesByList(idList);
    }

    @PostMapping(value = "/insert-employee")
    public ResponseEntity insertEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addNewEmployee(employeeDto);
    }

    @GetMapping(value = "/employee-list")
    public ResponseEntity createEmployeeList() {
        return employeeService.listOfEmployee();
    }


}
