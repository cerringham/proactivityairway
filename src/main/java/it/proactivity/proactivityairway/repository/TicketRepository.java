package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT r.departure, COUNT(t.id) " +
            "FROM ticket t " +
            "INNER JOIN flight f " +
            "ON t.flight_id = f.id " +
            "INNER JOIN route r " +
            "ON  f.route_id = r.id " +
            "GROUP BY r.departure",
            nativeQuery = true)
    List<Object[]> countTicketsByDepartureAirport();

    @Query(value = "SELECT r.arrival, COUNT(t.id) " +
            "FROM ticket t " +
            "INNER JOIN flight f " +
            "ON t.flight_id = f.id " +
            "INNER JOIN route r " +
            "ON  f.route_id = r.id " +
            "GROUP BY r.arrival",
            nativeQuery = true)
    List<Object[]> countTicketsByArrivalAirport();
}
