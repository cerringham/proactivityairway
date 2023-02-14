package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

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
}
