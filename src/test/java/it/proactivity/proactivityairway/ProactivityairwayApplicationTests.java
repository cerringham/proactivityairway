package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.repository.TicketRepository;
import it.proactivity.proactivityairway.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
	void getEmployeeByTaskTest() {
		List<Employee> employeeList = employeeRepository.getEmployeeByTask("Pilota");
		assertTrue(employeeList.size() == 3);
		assertNotNull(employeeList);
	}

	@Test
	void getCdaEmployeeTest() {
		List<Employee> employeeList = employeeRepository.getCdaEmployee();
		assertTrue(employeeList.size() == 3);
		assertFalse(employeeList.isEmpty());
	}

	@Test
	void getTotalNumberOfTicketsFromArrivalAirportTest() {
		Map<Integer, Long> map = ticketService.getTotalNumberOfTicketsFromArrivalAirport();
		assertFalse(map.isEmpty());
	}
	@Test
	void getTotalNumberOfTicketsFromDepartureAirportTest() {
		Map<Integer, Long> map = ticketService.getTotalNumberOfTicketsFromDepartureAirport();
		assertFalse(map.isEmpty());
	}


}
