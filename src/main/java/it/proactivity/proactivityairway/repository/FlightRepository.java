package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.flightDate >= :fromDate AND f.flightDate <= :toDate")
    List<Flight> findFlightFromAndToDate(@Param("fromDate") LocalDate parseFrom, @Param("toDate") LocalDate parseTo);
}
