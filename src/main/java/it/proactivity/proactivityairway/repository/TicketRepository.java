package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
