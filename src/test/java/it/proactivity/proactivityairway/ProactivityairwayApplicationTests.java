package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.service.CustomerService;
import it.proactivity.proactivityairway.service.FleetService;
import it.proactivity.proactivityairway.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
class ProactivityairwayApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TicketService ticketService;

	@Autowired
	FleetService fleetService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerRepository customerRepository;

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

	@Test
	void deleteEmployeeByIdTest() {
		employeeRepository.deleteById(12l);
	}

	@Test
	void insertNewCustomerTest() {
		CustomerDto customerDto = new CustomerDto("Vero", "Zun", "via", "Milano", "ITA", "verozun@gmail.com",
				"+2222222", "female", "2000-06-22", false, "PASSPORT35785T", "IDENTYTY898", "blabla");
		customerService.insertNewCustomer(customerDto);
		List<Customer> customerList = customerRepository.findAll();
		assertTrue(customerList.size() == 6);
	}

}
