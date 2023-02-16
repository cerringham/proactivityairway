package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Airport;
import it.proactivity.proactivityairway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r INNER JOIN FETCH r.flightList WHERE r.departure = ?1 AND r.arrival = ?2")
    Optional<Route> findByDepartureAndArrival(Long departure, Long arrival);
}
