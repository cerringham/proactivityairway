package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Airport;
import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.Route;
import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.repository.AirportRepository;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.repository.RouteRepository;
import it.proactivity.proactivityairway.service.CustomerService;
import it.proactivity.proactivityairway.service.FleetService;
import it.proactivity.proactivityairway.service.TicketService;
import it.proactivity.proactivityairway.utility.FlightUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

	@Autowired
	AirportRepository airportRepository;

	@Autowired
	FlightUtility flightUtility;

	@Autowired
	RouteRepository routeRepository;

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

	/*@Test
	void insertNewCustomerTest() {
		CustomerDto customerDto = new CustomerDto("Vero", "Zun", "via", "Milano", "ITA", "verozun@gmail.com",
				"+2222222", "female", "2000-06-22", false, "PASSPORT35785T", "IDENTYTY898", "blabla");
		customerService.insertNewCustomer(customerDto);
		List<Customer> customerList = customerRepository.findAll();
		assertTrue(customerList.size() == 6);
	}*/

	@Test
	void getPastTicketsFromCustomerTest() {
		List<Ticket> ticketList = customerRepository.getPastTicketsFromCustomer(3l, LocalDate.now());
		assertTrue(ticketList.size() != 0);
		assertTrue(ticketList.get(0).getCustomer().getId() == 3);
	}

	@Test
	void findByNameTest() {
		Optional<Airport> airport = airportRepository.findByName("Milano Malpensa");
		assertTrue(airport.isPresent());
		assertNotNull(airport);
	}

	/*@Test
	void validateFlightRouteTest() {
		Boolean route = flightUtility.validateFlightRoute("Milano Malpensa", "Logan Airport");
		assertTrue(route);
	}*/

	@Test
	void findByDepartureAndArrivalTest() {
		Optional<Route> route = routeRepository.findByDepartureAndArrival(1l, 2l);
		assertTrue(route.isPresent());
		Optional<Route> route2 = routeRepository.findByDepartureAndArrival(2l, 1l);
		assertFalse(route2.isPresent());
	}

	@Test
	void findByNameAirportTest() {
		Optional<Airport> airport = airportRepository.findByName("milano malpensa");
		assertTrue(airport.isPresent());
	}
}
