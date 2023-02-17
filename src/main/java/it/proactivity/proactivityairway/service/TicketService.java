package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.model.dto.TicketDto;
import it.proactivity.proactivityairway.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//- lista delle prenotazioni passate: un servizio che prende in input un id di un Customer e che ritorna tutti i
// biglietti acquistati dal Customer con quell'id che hanno una data antecedente a quella odierna
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


}
