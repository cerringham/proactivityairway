package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c INNER JOIN FETCH c.ticketList WHERE c.id = ?1")
    public Optional<Customer> findByIdAndFetchListOfTicket(Long id);
}
