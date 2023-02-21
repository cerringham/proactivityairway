package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findByDepartureAndArrival(Long departure, Long arrival);
}
