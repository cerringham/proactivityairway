package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
