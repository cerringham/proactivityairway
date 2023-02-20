package it.proactivity.proactivityairway.service;


import it.proactivity.proactivityairway.builder.FlightBuilder;
import it.proactivity.proactivityairway.model.*;
import it.proactivity.proactivityairway.model.dto.FlightWithDateDto;
import it.proactivity.proactivityairway.repository.*;
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
import java.time.ZoneId;
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
    @Autowired
    FleetRepository fleetRepository;


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

    public ResponseEntity insertFlight(FlightWithDateDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dto can't be null");
        }
        flightValidator.validateDeparture(dto.getDepartureAirport());
        flightValidator.validateArrival(dto.getArrivalAirport());
        flightValidator.validateAirplaneModel(dto.getAirplaneModel());
        Optional<Route> route = findRouteByDepartureAndArrival(dto.getDepartureAirport(), dto.getArrivalAirport());
        if (route.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Fleet> fleet = fleetRepository.findByAirplaneDescription(dto.getAirplaneModel());
        if (fleet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Flight flight = createFlight(route.get(), fleet.get(), dto.getDate(), dto.getDepartureTime(), dto.getArrivalTime());
        flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Flight createFlight(Route route, Fleet fleet, LocalDate date, String departureTime, String arrivalTime) {
        String cestsTimeZone = "Europe/Rome";   // CEST
        LocalDate cestLocalDate = date.atStartOfDay(ZoneId.of(cestsTimeZone)).toLocalDate();

        Optional<Airport> departureAirport = airportRepository.findById(route.getDeparture());
        if (departureAirport.isEmpty()) {
            throw new IllegalArgumentException("Departure not found");
        }

        Optional<Airport> arrivalAirport = airportRepository.findById(route.getArrival());
        if (arrivalAirport.isEmpty()) {
            throw new IllegalArgumentException("Arrival not found");
        }

        if (departureAirport.get().getName().equals("JFK") && arrivalAirport.get().getName().equals("Tokio-Haneda Airport")) {
            if (fleet.getAirplaneDescription().equals("Boeing 787")) {
                Flight flight = FlightBuilder.newBuilder(cestLocalDate)
                        .fleet(fleet)
                        .route(route)
                        .arrivalTime(arrivalTime)
                        .departureTime(departureTime)
                        .build();
                return flight;
            }else {
                throw new IllegalArgumentException("You can't use other fleet for this flight");
            }
        }

        Flight flight = FlightBuilder.newBuilder(cestLocalDate)
                .fleet(fleet)
                .route(route)
                .arrivalTime(arrivalTime)
                .departureTime(departureTime)
                .build();

        return flight;
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
