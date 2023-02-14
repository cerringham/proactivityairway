package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//native sql
//- totale dei biglietti per ogni aeroporto di partenza
//- totale dei biglietti per ogni aeroporto di destinazione
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
//route ticket flight
    @Query(value = "SELECT r.arrival, COUNT(t.id) FROM ticket t INNER JOIN flight f ON t.flight_id = f.id INNER JOIN " +
            "route r ON  f.route_id = r.id GROUP BY r.arrival", nativeQuery = true)
    List<Object[]> getTotalNumberOfTicketsFromArrivalAirport();

    @Query(value = "SELECT r.departure, COUNT(t.id) FROM ticket t INNER JOIN flight f ON t.flight_id = f.id INNER JOIN " +
            "route r ON  f.route_id = r.id GROUP BY r.departure\n", nativeQuery = true)
    List<Object[]> getTotalNumberOfTicketsFromDepartureAirport();
}
