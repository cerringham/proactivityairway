package it.proactivity.proactivityairway.controller;

import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import it.proactivity.proactivityairway.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/insert-employee")
    public ResponseEntity insertEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.insertEmployee(employeeDto);
    }

    @GetMapping(value = "/delete-employee/{id}")
    public ResponseEntity deleteEmployeeFromId(@PathVariable Long id) {
        return employeeService.deleteEmployeeFromId(id);
    }

    @GetMapping(value = "/delete-employee-from-idList")
    public ResponseEntity deleteEmployeeFromIdList(@RequestBody List<Long> idList) {
        return employeeService.deleteByIdList(idList);
    }

    @GetMapping("/get-employees-ral-asc")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesOrderedByRalAsc() {
        return employeeService.getAllEmployeesOrderedByRalAsc();
    }

}
