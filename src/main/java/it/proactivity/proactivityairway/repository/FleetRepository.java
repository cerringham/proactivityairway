package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {
    Optional<Fleet> findByAirplaneDescription(String airplaneDescription);
}
