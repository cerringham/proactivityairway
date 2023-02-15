package it.proactivity.proactivityairway;


import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import it.proactivity.proactivityairway.model.dto.FleetDto;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.repository.FleetRepository;
import it.proactivity.proactivityairway.repository.FlightRepository;
import it.proactivity.proactivityairway.repository.TaskRepository;
import it.proactivity.proactivityairway.service.EmployeeService;
import it.proactivity.proactivityairway.service.FleetService;
import it.proactivity.proactivityairway.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
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
    FlightRepository flightRepository;
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
        FleetDto fleetDto = new FleetDto("Boeing 777",3);

        Optional<Fleet> fleetBeforeInsert = fleetRepository.findByAirplaneDescription("Boeing 777");
        assertTrue(fleetBeforeInsert.get().getAvailability() == 2);

        fleetService.insertFleet(fleetDto);

        Optional<Fleet> fleetAfterInsert = fleetRepository.findByAirplaneDescription("Boeing 777");
        assertTrue(fleetAfterInsert.get().getAvailability() == 3);

    }

    @Test
    void insertFleetNullAirplaneModelNegativeTest() {
        FleetDto fleetDto = new FleetDto(null,3);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fleetService.insertFleet(fleetDto);
        });

        String message = "airplaneModel can't be null";
        assertEquals(message,exception.getMessage());
    }

    @Test
    void insertFleetAirplaneModelNotFoundResponseTest() {
        FleetDto fleetDto = new FleetDto("Boeing 727",3);

        ResponseEntity responseEntity = fleetService.insertFleet(fleetDto);

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.NOT_FOUND);

        assertEquals(expectedResponse.getStatusCode(),responseEntity.getStatusCode());
    }

    @Test
    void insertFleetAvailabilityBadRequestResponseTest() {
        FleetDto fleetDto = new FleetDto("Boeing 777",0);

        ResponseEntity responseEntity = fleetService.insertFleet(fleetDto);

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse.getStatusCode(),responseEntity.getStatusCode());
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
        assertEquals(message,exception.getMessage());
    }

    @Test
    void deleteFleetAirplaneModelBadRequestResponseTest() {


        ResponseEntity responseEntity = fleetService.deleteFleetFromAirplaneModel("boeing 727");

        ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse.getStatusCode(),responseEntity.getStatusCode());
    }


    @Test
    void insertEmployeePositiveTest() {
        EmployeeDto employeeDto = new EmployeeDto("Luigi","Cerrato","luigi@luigi.it",
                "1990-03-11",70000.50f,"tecnico");
        Long numberOfEmployeeBeforeInsert = employeeRepository.findAll().stream().count();

        ResponseEntity responseEntity = employeeService.insertEmployee(employeeDto);
       // ResponseEntity expectedResponse = new ResponseEntity(HttpStatus.OK);

        Long numberOfEmployeeAfterInsert = employeeRepository.findAll().stream().count();

        assertTrue(numberOfEmployeeBeforeInsert < numberOfEmployeeAfterInsert);
       // assertEquals(expectedResponse, responseEntity.getStatusCode());
    }

    @Test
    void insertEmployeeNullNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto(null,"Costanzo","costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptyNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("","Costanzo","costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongNameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio11","Costanzo","costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Wrong name and surname";

        assertEquals(message, exception.getMessage());
    }


    @Test
    void insertEmployeeNullSurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio",null,"costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptySurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","","costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Name and surname can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongSurnameNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo!!","costanzo@costanzo.it",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Wrong name and surname";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeNullEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo",null,
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeEmptyEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo","",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email can't be null or empty";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongEmailNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo","costanzo@costanzo.it1",
                "1987-03-09",50000.50f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Email is wrong";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeNullRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo","costanzo@costanzo.it",
                "1987-03-09",null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "Ral can't be null";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeLowerRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo","costanzo@costanzo.it",
                "1987-03-09",11000f);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.insertEmployee(employeeDto);
        });

        String message = "The ral can't be lower than 15k";

        assertEquals(message, exception.getMessage());
    }

    @Test
    void insertEmployeeWrongFormatRalNegativeTest() {
        EmployeeDto employeeDto = new EmployeeDto("Maurizio","Costanzo","costanzo@costanzo.it",
                "1987-03-09",19000.1237777f);

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
        List<Long> idList = Arrays.asList(13l,11l);
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
    void test() {
        List<Flight> flightList = flightRepository.findFlightFromAndToDate(LocalDate.of(2023,1,12),
                LocalDate.of(2023,9,11));

       System.out.println(flightList.size());
        flightList.stream().forEach(System.out::println);
   }





}
