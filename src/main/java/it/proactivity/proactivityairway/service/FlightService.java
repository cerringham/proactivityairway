package it.proactivity.proactivityairway.service;


import it.proactivity.proactivityairway.builder.FlightBuilder;
import it.proactivity.proactivityairway.model.*;
import it.proactivity.proactivityairway.model.dto.FlightWithDateDto;
import it.proactivity.proactivityairway.model.dto.InfoDepartureArrivalFlightDto;
import it.proactivity.proactivityairway.repository.*;
import it.proactivity.proactivityairway.utility.FlightValidator;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import it.proactivity.proactivityairway.utility.ZoneIdUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
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

        Flight flight = createFlight(route.get(), fleet.get(), dto.getDepartureDate(), dto.getArrivalDate());
        flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    public ResponseEntity<List<InfoDepartureArrivalFlightDto>> getDepartureArrivalInfoForAllFlight() {

        List<Flight> flightList = flightRepository.findAll();

        List<InfoDepartureArrivalFlightDto> dtoList = flightList.stream()
                .map(f -> {
                    Route route = f.getRoute();
                    Optional<Airport> departureAirport = airportRepository.findById(route.getDeparture());
                    Optional<Airport> arrivalAirport = airportRepository.findById(route.getArrival());
                    Long duration = Duration.between(f.getDepartureTime(), f.getArrivalTime()).toMillis();

                    Double hour = duration / 3600000.0;

                    String departureDate = ParsingUtility.parseDateToString(f.getDepartureDate());
                    String departureTime = ParsingUtility.parseTimeToString(f.getDepartureTime());
                    String arrivalDate = ParsingUtility.parseDateToString(f.getArrivalDate());
                    String arrivalTime = ParsingUtility.parseTimeToString(f.getArrivalTime());
                    return new InfoDepartureArrivalFlightDto(departureAirport.get().getName(), departureDate, departureTime,
                            arrivalAirport.get().getName(), arrivalDate, arrivalTime, String.valueOf(hour));
                }).toList();

        return ResponseEntity.ok(dtoList);

    }

    public ResponseEntity<List<InfoDepartureArrivalFlightDto>> getAllFlightWithDelay() {
        List<Flight> flightList = flightRepository.findAll();

        List<InfoDepartureArrivalFlightDto> dtoList = flightList.stream()
                .map(f -> {
                    Integer random = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);
                    Route route = f.getRoute();
                    Optional<Airport> departureAirport = airportRepository.findById(route.getDeparture());
                    Optional<Airport> arrivalAirport = airportRepository.findById(route.getArrival());
                    String departureDate = ParsingUtility.parseDateToString(f.getDepartureDate());
                    String departureTime = ParsingUtility.parseTimeToString(f.getDepartureTime());
                    String arrivalDate = ParsingUtility.parseDateToString(f.getArrivalDate());
                    String arrivalTime = ParsingUtility.parseTimeToString(f.getArrivalTime());
                    Long duration = Duration.between(f.getDepartureTime(), f.getArrivalTime()).toMillis();
                    Double hour = duration / 3600000.0;
                    if (random <= 3) {
                        return new InfoDepartureArrivalFlightDto(departureAirport.get().getName(), departureDate, departureTime,
                                arrivalAirport.get().getName(), arrivalDate, arrivalTime, String.valueOf(hour));
                    } else {

                        InfoDepartureArrivalFlightDto dto = new InfoDepartureArrivalFlightDto(departureAirport.get().getName(),
                                departureDate, departureTime, arrivalAirport.get().getName(), arrivalDate, arrivalTime,
                                String.valueOf(hour));

                        dto.setDelay(Duration.between(f.getArrivalTime(), LocalTime.now()).toString());
                        return dto;
                    }
                }).toList();
        return ResponseEntity.ok(dtoList);
    }

    private Flight createFlight(Route route, Fleet fleet, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {


        Optional<Airport> departureAirport = airportRepository.findById(route.getDeparture());
        if (departureAirport.isEmpty()) {
            throw new IllegalArgumentException("Departure not found");
        }

        ZoneId departureZone = ZoneIdUtility.getZoneId(departureAirport.get());
        ZonedDateTime zoneDepartureDateTime = departureDateTime.atZone(departureZone);
        LocalTime departureTime = zoneDepartureDateTime.toLocalTime();

        Optional<Airport> arrivalAirport = airportRepository.findById(route.getArrival());
        if (arrivalAirport.isEmpty()) {
            throw new IllegalArgumentException("Arrival not found");
        }

        ZoneId arrivalZone = ZoneIdUtility.getZoneId(arrivalAirport.get());
        ZonedDateTime zoneArrivalDateTime = arrivalDateTime.atZone(arrivalZone);
        LocalTime arrivalTime = zoneArrivalDateTime.toLocalTime();

        Flight flight = FlightBuilder.newBuilder(departureDateTime.toLocalDate())
                .arrivalDate(arrivalDateTime.toLocalDate())
                .arrivalTime(arrivalTime)
                .departureTime(departureTime)
                .fleet(fleet)
                .route(route)
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

        Optional<Route> route = routeRepository.findByDepartureAndArrivalForFlight(departureAirport.get().getId(),
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
                                    + arrival.get().getName() + " " + f.getDepartureDate() + " "
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
