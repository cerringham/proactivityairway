package it.proactivity.proactivityairway.repository;

import it.proactivity.proactivityairway.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//- ricerca dei dipendenti per ruolo
//- ricerca dei dipendenti che fanno parte del consiglio di amministrazione
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.task.name = ?1")
    List<Employee> getEmployeeByTask(String task);

    @Query("SELECT e FROM Employee e WHERE e.task.name = 'C.D.A'")
    List<Employee> getCdaEmployee();
}
