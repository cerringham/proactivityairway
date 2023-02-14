package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
