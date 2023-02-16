package it.proactivity.proactivityairway;


import it.proactivity.proactivityairway.model.*;
import it.proactivity.proactivityairway.model.dto.*;
import it.proactivity.proactivityairway.repository.*;
import it.proactivity.proactivityairway.service.*;
import it.proactivity.proactivityairway.utility.CustomerValidator;
import it.proactivity.proactivityairway.utility.FleetValidator;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
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
    FleetRepository fleetRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    FlightService flightService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private RouteRepository routeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void findEmployeeFromTaskTest() {
        List<Employee> employeeList = employeeRepository.findEmployeesFromTask("hostess");
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

    @Test
    void insertFleetPositiveTest() {
        FleetDto fleetDto = new FleetDto("Boeing 777", 5);

        Optional<Fleet> fleetBeforeInsert = fleetRepository.findByAirplaneDescription("Boeing 777");
        assertTrue(fleetBeforeInsert.get().getAvailability() == 3);

        fleetService.insertFleet(fleetDto);

        Optional<Fleet> fleetAfterInsert = fleetRepository.findByAirplaneDescription("Boeing 777");
        assertTrue(fleetAfterInsert.get().getAvailability() == 5);

    }

    @Test
    void insertFleetNullAirplaneModelNegativeTest() {
        FleetDto fleetDto = new FleetDto(null, 3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.insertFleet(fleetDto);
        });

        String message = "airplaneModel can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertFleetAirplaneModelNotFoundResponseTest() {
        FleetDto fleetDto = new FleetDto("Boeing 727", 3);

        ResponseEntity responseEntity = fleetService.insertFleet(fleetDto);

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.NOT_FOUND);

        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void insertFleetAvailabilityBadRequestResponseTest() {
        FleetDto fleetDto = new FleetDto("Boeing 777", 0);

        ResponseEntity responseEntity = fleetService.insertFleet(fleetDto);

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }

    @Test
    void deleteFleetFromAirplaneModelPositiveTest() {
        Long numberOfFleetBeforeDelete = fleetRepository.findAll().stream().count();

        fleetService.deleteFleetFromAirplaneModel("Boeing 787");

        Long numberOfFleetAfterDelete = fleetRepository.findAll().stream().count();

        assertTrue(numberOfFleetBeforeDelete > numberOfFleetAfterDelete);
    }

    @Test
    void deleteFleetNullAirplaneModelNegativeTest() {


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.deleteFleetFromAirplaneModel(null);
        });

        String message = "airplaneModel can't be null";
        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteFleetAirplaneModelBadRequestResponseTest() {


        ResponseEntity responseEntity = fleetService.deleteFleetFromAirplaneModel("boeing 727");

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse.getStatusCode(), responseEntity.getStatusCode());
    }


    @Test
    void insertEmployeePositiveTest() {
        EmployeeDto employeeDto = new EmployeeDto("Luigi", "Cerrato", "luigi@luigi.it",
                "1990-03-11", 70000.50f, "tecnico");
        Long numberOfEmployeeBeforeInsert = employeeRepository.findAll().stream().count();

        ResponseEntity responseEntity = employeeService.insertEmployee(employeeDto);
        // ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);

        Long numberOfEmployeeAfterInsert = employeeRepository.findAll().stream().count();

        assertTrue(numberOfEmployeeBeforeInsert < numberOfEmployeeAfterInsert);
        // assertEquals(expectedResponse, responseEntity.getStatusCode());
    }

    @Test
    void insertEmployeeNullNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto(null, "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptyNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("", "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio11", "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Wrong name and surname";

        assertEquals(message, exception.getMessage());
    }


    @Test
    void insertEmployeeNullSurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", null, "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptySurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "", "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongSurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo!!", "costanzo@costanzo.it",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Wrong name and surname";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeNullEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", null,
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptyEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", "",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", "costanzo@costanzo.it1",
                "1987-03-09", 50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email is wrong";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeNullRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Ral can't be null";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeLowerRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", 11000f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "The ral can't be lower than 15k";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongFormatRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio", "Costanzo", "costanzo@costanzo.it",
                "1987-03-09", 19000.1237777f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Ral is not acceptable";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void deleteEmployeeFromId() {
        Long numberOfEmployeeBeforeDelete = employeeRepository.findAll().stream().count();

        ResponseEntity responseEntity = employeeService.deleteEmployeeFromId(14l);


        Long numberOfEmployeeAfterDelete = employeeRepository.findAll().stream().count();

        assertTrue(numberOfEmployeeBeforeDelete > numberOfEmployeeAfterDelete);

    }

    @Test
    void deleteEmployeesFromIdListTest() {
        List<Long> idList = Arrays.asList(13l, 11l);
        Long numberOfEmployeeBeforeDelete = employeeRepository.findAll().stream().count();
        ResponseEntity responseEntity = employeeService.deleteByIdList(idList);
        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);
        Long numberOfEmployeeAfterDelete = employeeRepository.findAll().stream().count();
        assertTrue(numberOfEmployeeBeforeDelete > numberOfEmployeeAfterDelete);
        assertEquals(responseEntity.getStatusCode(), expectedResponse);
    }

    @Test
    void getAllEmployeesOrderedByRalAscTest() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployeesOrderedByRalAsc().getBody();
        employeeDtos.stream()
                .forEach(System.out::println);

    }

    @Test
    void findFlightsFromAndToDatePositiveTest() {
        ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate("2023-01-12", "2023-09-11");


        List<Flight> flights = flightList.getBody();
        flights.stream().forEach(System.out::println);
    }

    @Test
    void findFlightsFromAndToDateFromaDateNullTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate(null, "2023-09-11");

        });

        String message = "date can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void findFlightsFromAndToDateFromaDateEmptyTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate("", "2023-09-11");

        });

        String message = "date can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void findFlightsFromAndToDateToDateNullTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate("2023-01-12", null);

        });

        String message = "date can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void findFlightsFromAndToDateToDateEmptyTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate("2023-01-12", "");

        });

        String message = "date can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void findFlightsFromAndToDateWrongFormatTest() {

        ResponseEntity<List<Flight>> flightList = flightService.findFlightsFromAndToDate("12-01-2023", "2023-09-11");
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assertEquals(flightList.getStatusCode(), responseEntity.getStatusCode());

    }

    @Test
    void insertNewFleetPositiveTest() {
        Long numberOfFleetBeforeInsert = fleetRepository.findAll().stream().count();
        FleetDto fleetDto = new FleetDto("Airbus a400",400,5);

        fleetService.insertFleet(fleetDto);

        Long numberOfFleetAfterInsert = fleetRepository.findAll().stream().count();

        assertTrue(numberOfFleetBeforeInsert < numberOfFleetAfterInsert);
    }

    @Test
    void insertNewFleetWrongAirplaneModelNegativeTest() {
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        FleetDto fleetDto = new FleetDto("Airbus a4a00",400,5);
        ResponseEntity response = fleetService.insertFleet(fleetDto);
        assertEquals(response.getStatusCode(),responseEntity.getStatusCode());
    }

    @Test
    void insertNewCustomerPositiveTest() {
        CustomerDto customerDto = new CustomerDto("Alessio", "Cassarino", "via pozzillo 19", "gela", "ita",
                "alessio@alessio.it", "+398763546278", "male", "1995-11-16",
                false, "hsgdj76", "hsjdnnd87", "covid 19, malaria");

        Long customerBeforeInsert = customerRepository.findAll().stream().count();

        customerService.insertCustomer(customerDto);

        Long customerAfterInsert = customerRepository.findAll().stream().count();

        assertTrue(customerBeforeInsert < customerAfterInsert);
    }

    @Test
    void getTicketListFromCustomerId() {
        ResponseEntity<List<TicketDto>> response = customerService.getTicketListFromCustomerId(3l);
        List<TicketDto> list = response.getBody();

        list.stream()
                .forEach(System.out::println);

    }

    @Test
    void getFlightDtoListFromCustomerIdDepartureAndArrivalAirportTest() {
        BuyTicketDto dto = new BuyTicketDto(1l, "Milano Malpensa", "JFK");
        ResponseEntity<List<FlightDto>> response = flightService.getFlightListFromCustomerIdDepartureAndArrival(dto);
        List<FlightDto> list = response.getBody();

       assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void test() {
        Optional<Airport> departureAirport = airportRepository.findByName("Milano Malpensa");
        Optional<Airport> arrivalAirport = airportRepository.findByName("JFK");
        Optional<Route> route = routeRepository.findByDepartureAndArrival(1,2);
        System.out.println(route.get());
    }


}
