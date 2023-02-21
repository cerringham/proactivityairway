package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Airport;
import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;
import it.proactivity.proactivityairway.model.dto.CustomerAndAirportInfoDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.repository.AirportRepository;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.repository.FlightRepository;
import it.proactivity.proactivityairway.repository.RouteRepository;
import it.proactivity.proactivityairway.utility.FlightUtility;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//servizio che prese in input due date ("from" e "to") fa i seguenti controlli:
//from deve essere antecedente a to altrimenti restituisco una ResponseEntity 400 (Bad Request)
//fa una ricerca dei voli che sono schedulati in quelle date
//se non ci sono voli restituisce una lista vuota
//se ci sono voli restituisce una lista dei voli e crea un file con le seguenti informazioni
//id del volo, nome aeroporto partenza, nome aeroporto di arrivo, data di partenza ordinata in modo ascendente,
// modello di aereo, numero di biglietti per quel volo, numero di posti ancora disponibili
@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    FlightUtility flightUtility;

    public ResponseEntity getFlightsFromToList(String from, String to) {
        if (from.isEmpty() || to.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LocalDate parsedFrom = ParsingUtility.parseStringToLocalDate(from);
        LocalDate parsedTo = ParsingUtility.parseStringToLocalDate(to);
        if (!validateDate(parsedFrom, parsedTo)) {
            return ResponseEntity.badRequest().build();
        }

        List<Flight> flightList = flightRepository.findFlightFromTo(parsedFrom, parsedTo);
        File file = new File("flightsList.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            flightList.sort(Comparator.comparing(Flight::getDepartureTime));
            flightList.stream()
                    .forEach(f -> {
                        Optional<Airport> departureAirport = airportRepository.findById(f.getRoute().getDeparture());
                        Optional<Airport> arrivalAirport = airportRepository.findById(f.getRoute().getArrival());
                        Integer leftSeats = f.getFleet().getNumberOfSeat() - f.getTicketList().size();
                        try {
                            fileWriter.write(f.getId() + " " + departureAirport.get().getName() + arrivalAirport.get().getName()
                                    + " " + f.getDepartureTime() + " " + f.getTicketList() + " " + leftSeats + "\n");

                        } catch (IOException e) {
                            throw new RuntimeException();
                        }
                    });
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return ResponseEntity.ok().build();
    }

    public static Boolean validateDate(LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            return false;
        }
        if (from.isBefore(to)) {
            return true;
        }
        return false;
    }

    public ResponseEntity<List<FlightDto>> buyTicketPartOne(CustomerAndAirportInfoDto customerAndAirportInfoDto) {
        if (customerAndAirportInfoDto.getCustomerId() == null ||
                StringUtils.isEmpty(customerAndAirportInfoDto.getDepartureAirport()) ||
                StringUtils.isEmpty(customerAndAirportInfoDto.getArrivalAirport())) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Customer> customer = customerRepository.findById(customerAndAirportInfoDto.getCustomerId());
        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Route> route = getRouteByDepartureAndArrival(customerAndAirportInfoDto.getDepartureAirport(),
                customerAndAirportInfoDto.getArrivalAirport());
        if (!route.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<Flight> flightList = route.get().getFlightList();
        List<FlightDto> dtoList = flightList.stream()
                .map(f -> new FlightDto(f.getId(), f.getDepartureTime(), f.getArrivalTime()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    public Optional<Route> getRouteByDepartureAndArrival(String departure, String arrival) {
        if (StringUtils.isEmpty(departure) || StringUtils.isEmpty(arrival)) {
            throw new IllegalArgumentException();
        }
        Optional<Airport> departureAirport = airportRepository.findByName(departure);
        Optional<Airport> arrivalAirport = airportRepository.findByName(arrival);
        if (departureAirport.isEmpty() || arrivalAirport.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Optional<Route> route = routeRepository.findByDepartureAndArrival(departureAirport.get().getId(),
                arrivalAirport.get().getId());
        return route;
    }
}
