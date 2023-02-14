package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE LOWER(e.task.name) = LOWER(?1)")
    List<Employee> findCdaEmployee(String cda);
}
