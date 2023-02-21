package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT a FROM Airport a WHERE LOWER(a.name) = ?1")
    Optional<Airport> findByName(String name);
}
