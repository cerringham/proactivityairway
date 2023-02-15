package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
//lista di tutti gli Employee, il servizio deve anche (oltre a creare e ritornare la lista) creare un file dove
// scriviamo in ogni riga i seguenti dettagli: id, nome, cognome, email, nome della mansione, ral
//il file deve contenere i dati ordinati per ral crescente (ascendente)
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<?> deleteEmployeeById(Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteEmployeesByList(List<Long> idList) {
        if (idList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        employeeRepository.deleteAllById(idList);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<EmployeeDto> addNewEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Employee employee = createEmployee(employeeDto);
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }

    public static Employee createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setRal(employeeDto.getRal());
        employee.setTask(employeeDto.getTask());
        return employee;
    }

    public ResponseEntity<?> listOfEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        File file = new File("employeeList.txt");
        if (file.exists()) {
            file.delete();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            employeeList.sort(Comparator.comparing(Employee::getRal));
            for (Employee e : employeeList) {
                fileWriter.write(e.toString() + "\n");
            }
            fileWriter.close();
            return ResponseEntity.ok().build();
        }catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
