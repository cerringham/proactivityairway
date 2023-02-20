package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.builder.TicketBuilder;
import it.proactivity.proactivityairway.model.*;
import it.proactivity.proactivityairway.model.dto.BuyTicketDto;
import it.proactivity.proactivityairway.model.dto.FlightDto;
import it.proactivity.proactivityairway.model.dto.FlightIdDto;
import it.proactivity.proactivityairway.repository.*;
import it.proactivity.proactivityairway.utility.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AirportRepository airportRepository;

    @Autowired
    TicketValidator ticketValidator;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    FlightRepository flightRepository;

    public Map<Integer, Long> findNumberOfTicketForDepartureAirport() {

        List<Object[]> objects = ticketRepository.countTicketsByDepartureAirport();

        Map<Integer, Long> numberOfTicketForEveryDeparture = objects.stream()
                .collect(Collectors.toMap(
                        o -> (Integer) o[0],
                        o -> (Long) o[1]
                ));
        return numberOfTicketForEveryDeparture;
    }

    public Map<Integer, Long> findNumberOfTicketForArrivalAirport() {

        List<Object[]> objects = ticketRepository.countTicketsByArrivalAirport();

        Map<Integer, Long> numberOfTicketForEveryArrival = objects.stream()
                .collect(Collectors.toMap(
                        o -> (Integer) o[0],
                        o -> (Long) o[1]
                ));
        return numberOfTicketForEveryArrival;
    }

    public ResponseEntity<List<FlightDto>> buyFlightStep1(BuyTicketDto dto) {
        if (dto == null) {
            throw new IllegalStateException("Dto can't be null or empty");
        }
        ticketValidator.validateCustomerId(dto.getCustomerId());
        ticketValidator.validateDeparture(dto.getDeparture());
        ticketValidator.validateArrival(dto.getArrival());
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

    public ResponseEntity buyFlightStep2(FlightIdDto flightIdDto, Long customerId, String seat) {
        ticketValidator.validateFlightId(flightIdDto.getId());
        ticketValidator.validateCustomerId(customerId);
        ticketValidator.validateSeat(seat);

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<Flight> flight = flightRepository.findById(flightIdDto.getId());
        if (flight.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Ticket ticket = createTicket(flight.get(), customer.get(), seat);
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private Ticket createTicket(Flight flight, Customer customer, String s) {
        if (customer.getLoyaltyProgram().getPoint() < 1500) {
            customer.getLoyaltyProgram().setPoint(customer.getLoyaltyProgram().getPoint() + 100);
            customerRepository.save(customer);
        }
        if (customer.getLoyaltyProgram().getPoint() == 1500) {
            customer.getLoyaltyProgram().setPoint(0);
            customerRepository.save(customer);
        }
        Ticket ticket = TicketBuilder.newBuilder(flight)
                .customer(customer)
                .seatCode(s)
                .build();

        return ticket;
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
}
