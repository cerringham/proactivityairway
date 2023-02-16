package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Ticket;
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

    public Map<Integer, Long> getTotalNumberOfTicketsFromArrivalAirport() {
        List<Object[]> list = ticketRepository.getTotalNumberOfTicketsFromArrivalAirport();
        Map<Integer, Long> map = list.stream().collect(Collectors.toMap(
                n -> (Integer) n[0],
                n -> (Long) n[1]));
        return map;
    }

    public Map<Integer, Long> getTotalNumberOfTicketsFromDepartureAirport() {
        List<Object[]> list = ticketRepository.getTotalNumberOfTicketsFromDepartureAirport();
        Map<Integer, Long> map = list.stream().collect(Collectors.toMap(
                n -> (Integer) n[0],
                n -> (Long) n[1]));
        return map;
    }

    public List<Ticket>
}
