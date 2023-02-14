package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends JpaRepository<Fleet,Long> {
}
