package it.proactivity.proactivityairway.service;


import it.proactivity.proactivityairway.model.Airport;
import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;
import it.proactivity.proactivityairway.model.dto.BuyTicketDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.repository.AirportRepository;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.repository.FlightRepository;
import it.proactivity.proactivityairway.repository.RouteRepository;
import it.proactivity.proactivityairway.utility.FlightValidator;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightValidator flightValidator;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
     RouteRepository routeRepository;

    public ResponseEntity<List<Flight>> findFlightsFromAndToDate(String from, String to) {

        LocalDate parseFrom = ParsingUtility.parseStringToLocalDate(from);
        LocalDate parseTo = ParsingUtility.parseStringToLocalDate(to);

        if (!flightValidator.validateFromAndToDate(parseFrom, parseTo)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Flight> flightList = flightRepository.findFlightFromAndToDate(parseFrom, parseTo);

        if (flightList.size() == 0) {
            return ResponseEntity.ok(flightList);
        }
        File file = createFile("Flight_info.txt");
        writeFlightInformationInFile(file, flightList);
        return ResponseEntity.ok(flightList);
    }



    public ResponseEntity<List<FlightDto>> getFlightListFromCustomerIdDepartureAndArrival(BuyTicketDto dto) {
        if (dto == null) {
            throw new IllegalStateException("Dto can't be null or empty");
        }
        flightValidator.validateCustomerId(dto.getCustomerId());
        flightValidator.validateDeparture(dto.getDeparture());
        flightValidator.validateArrival(dto.getArrival());
        Optional<Customer> customer = customerRepository.findById(dto.getCustomerId());
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Route> route = findRouteByDepartureAndArrival(dto.getDeparture(), dto.getArrival());
        if (route.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Flight> flightList = route.get().getFlightList();
        List<FlightDto> dtoList = flightList.stream()
                .map(f -> {
                    Airport departure = airportRepository.findById(f.getRoute().getDeparture()).get();
                    Airport arrival = airportRepository.findById(f.getRoute().getArrival()).get();
                    return new FlightDto(f.getId(), departure.getName(), arrival.getName());
                }).toList();

        return ResponseEntity.ok(dtoList);
    }

    private Optional<Route> findRouteByDepartureAndArrival(String departure, String arrival) {

        Optional<Airport> departureAirport = airportRepository.findByName(departure);
        if (departureAirport.isEmpty()) {
            throw new IllegalArgumentException("Departure not found");
        }

        Optional<Airport> arrivalAirport = airportRepository.findByName(arrival);
        if (arrivalAirport.isEmpty()) {
            throw new IllegalArgumentException("Arrival not found");
        }

        Optional<Route> route = routeRepository.findByDepartureAndArrival(departureAirport.get().getId(),
                arrivalAirport.get().getId());

        return route;
    }





    private File createFile(String fileName) {
        File file = new File(fileName);

        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException("Something went wrong");
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong");
        }
        return file;
    }

    private void writeFlightInformationInFile(File file, List<Flight> flightList) {

        try {
            FileWriter fileWriter = new FileWriter(file);
            flightList.stream()
                    .forEach(f -> {
                        Optional<Airport> departure = airportRepository.findById(f.getRoute().getDeparture());
                        Optional<Airport> arrival = airportRepository.findById(f.getRoute().getArrival());
                        Integer seatsLeft = f.getFleet().getNumberOfSeat() - f.getTicketList().size();
                        try {
                            fileWriter.write(f.getId() + " " + departure.get().getName() + " "
                                    + arrival.get().getName() + " " + f.getFlightDate() + " "
                                    + f.getFleet().getAirplaneDescription() + " " + f.getTicketList().size() + " "
                                    + seatsLeft + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            fileWriter.close();
        } catch (IOException e) {
            throw new IllegalStateException("There is a problem with the file");
        }
    }


}
