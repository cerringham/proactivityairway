package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Long> {

    Fleet findByAirplaneDescription(String airplaneDescription);

}
