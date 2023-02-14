package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r INNER JOIN FETCH r.flightList")
    List<Route> findRouteWithFlightList();



}
