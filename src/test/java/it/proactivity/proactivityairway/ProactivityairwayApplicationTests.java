package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class ProactivityairwayApplicationTests {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TicketService ticketService;

    @Test
    void contextLoads() {
    }

    @Test
    void findEmployeeFromTaskTest() {
        List<Employee> employeeList = employeeRepository.findEmployeeFromTask("hostess");
        employeeList.stream().forEach(System.out::println);
    }


    @Test
    void findCdaEmployeeTest() {
        List<Employee> employeeList = employeeRepository.findCdaEmployee();
        employeeList.stream().forEach(System.out::println);
    }


    @Test
    void countTicketFromDepartureAirportTest() {

        Map<Integer, Long> totalOfDepartureTicket = ticketService.findNumberOfTicketForDepartureAirport();
        for (Map.Entry<Integer, Long> entry : totalOfDepartureTicket.entrySet()) {
            System.out.println("Key :" + entry.getKey() + " " + "Value :" + entry.getValue());
        }

    }

    @Test
    void countTicketFromArrivalAirportTest() {

        Map<Integer, Long> totalOfDepartureTicket = ticketService.findNumberOfTicketForArrivalAirport();
        for (Map.Entry<Integer, Long> entry : totalOfDepartureTicket.entrySet()) {
            System.out.println("Key :" + entry.getKey() + " " + "Value :" + entry.getValue());
        }

    }
}
