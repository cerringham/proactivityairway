package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
