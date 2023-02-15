package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.builder.EmployeeBuilder;
import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.Task;
import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import it.proactivity.proactivityairway.repository.EmployeeRepository;
import it.proactivity.proactivityairway.repository.TaskRepository;
import it.proactivity.proactivityairway.utility.EmployeeValidator;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeValidator employeeValidator;

    public ResponseEntity insertEmployee(EmployeeDto employeeDto) {
        if (!employeeValidator.validateEmployeeDto(employeeDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Employee employee = createEmployee(employeeDto);
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteEmployeeFromId(Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity deleteByIdList(List<Long> idList) {
        if (idList == null) {
            throw new IllegalArgumentException("Id list can't be null");
        }
        employeeRepository.deleteAllById(idList);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<EmployeeDto>> getAllEmployeesOrderedByRalAsc() {
        List<Employee> employees = employeeRepository.findByOrderByRalAsc();

        File file = createFile("employee_info.txt");
        writeEmployeesInformationInFile(file, employees);

        List<EmployeeDto> employeeDtos = employees.stream()

                .map(e -> new EmployeeDto(e.getName(), e.getSurname(), e.getEmail(),
                        ParsingUtility.parseDateToString(e.getDateOfBirth()), e.getRal(), e.getTask().getName()))
                .toList();
        return ResponseEntity.ok(employeeDtos);
    }

    private Employee createEmployee(EmployeeDto employeeDto) {
        LocalDate date = ParsingUtility.parseStringToLocalDate(employeeDto.getDateOfBirth());
        if (date == null) {
            throw new IllegalArgumentException("The date is wrong");
        }

        Optional<Task> task = taskRepository.findByName(employeeDto.getTaskName());
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task does not exist");
        }
        Employee employee = EmployeeBuilder.newBuilder(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .email(employeeDto.getEmail())
                .dateOfBirth(date)
                .ral(employeeDto.getRal())
                .task(task.get())
                .build();

        return employee;
    }

    private File createFile(String fileName) {
        File file = new File(fileName);

        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IllegalStateException("Something went wrong");
            }
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong");
        }
        return file;
    }

    private void writeEmployeesInformationInFile(File file, List<Employee> employeeList) {

        try {
            FileWriter fileWriter = new FileWriter(file);
            employeeList.stream()
                    .forEach(e -> {

                        try {
                            fileWriter.write(e.getId() + " " + e.getName() + " " + e.getSurname() + " " + e.getEmail() + " "
                                    + e.getTask().getName() + " " + e.getRal() + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            fileWriter.close();
        } catch (IOException e) {
            throw new IllegalStateException("There is a problem with the file");
        }
    }
}
