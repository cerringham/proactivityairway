package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.Airport;
import it.proactivity.proactivityairway.model.Fleet;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Route;
import it.proactivity.proactivityairway.repository.AirportRepository;
import it.proactivity.proactivityairway.repository.FleetRepository;
import it.proactivity.proactivityairway.repository.RouteRepository;
import it.proactivity.proactivityairway.service.FleetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FlightUtility {

    @Autowired
    AirportRepository airportRepository;
    @Autowired
    RouteRepository routeRepository;

    public Boolean validateFlightRoute(String departureAirport, String arrivalAirport) {
        if (StringUtils.isEmpty(departureAirport) || StringUtils.isEmpty(arrivalAirport)) {
            return false;
        }
        Optional<Airport> departure = airportRepository.findByName(departureAirport);
        Optional<Airport> arrival = airportRepository.findByName(arrivalAirport);
        if (departure == null || arrival == null) {
            return false;
        }
        Optional<Route> route = routeRepository.findByDepartureAndArrival(departure.get().getId(), arrival.get().getId());
        if (!route.isPresent()) {
            return false;
        }
        return true;
    }

    public Boolean checkSpecialRoute(String departure, String arrival) {
        if (departure == null || arrival == null) {
            return false;
        }
        if (departure.equalsIgnoreCase("JFK") && arrival.equalsIgnoreCase("Tokio-Haneda Airport")) {
            return true;
        }
        return false;
    }
}
