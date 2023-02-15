package it.proactivity.proactivityairway.model.dto;

import it.proactivity.proactivityairway.model.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Float ral;
    private Task task;

    public EmployeeDto(String name, String surname, LocalDate dateOfBirth, Float ral, Task task) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.ral = ral;
        this.task = task;
    }
}
