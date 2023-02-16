package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f INNER JOIN FETCH f.ticketList WHERE f.flightDate >= :from AND f.flightDate <= :to")
    List<Flight> findFlightFromTo(LocalDate from, LocalDate to);
}
