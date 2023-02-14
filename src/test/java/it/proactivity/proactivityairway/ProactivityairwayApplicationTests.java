package it.proactivity.proactivityairway;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;
import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ProactivityairwayApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RouteRepository routeRepository;
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
	void findTicketListFromDepartureAirportTest() {
		List<Route> routes = routeRepository.findRouteWithFlightList();
		List<List<Flight>> flightList =routes.stream()
				.map(r -> r.getFlightList())
				.toList();

		List<List<Ticket>> ticketList = flightList.stream()
				.flatMap(List::stream)
				.map(Flight::getTicketList)
				.toList();
	}



}
