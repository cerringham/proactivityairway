package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT t FROM Ticket t INNER JOIN t.flight f WHERE t.customer.id = ?1 AND f.flightDate < ?2")
    List<Ticket> getPastTicketsFromCustomer(Long customerId, LocalDate date);

}
