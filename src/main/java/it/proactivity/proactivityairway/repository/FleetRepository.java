package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {

    @Query(value = "SELECT DISTINCT airplane_description FROM fleet", nativeQuery = true)
    List<String> getFleetNames();

    @Query(value = "SELECT f FROM Fleet f WHERE f.airplane_description = ?1")
    Fleet getFleetWhitAvailability(String model);

}
