package it.proactivity.proactivityairway.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String name;

    private String surname;

    private String email;

    private String dateOfBirth;

    private Float ral;

    private String taskName;

    public EmployeeDto(String name, String surname, String email, String dateOfBirth, Float ral) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.ral = ral;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + email + " " + dateOfBirth + " " + ral;
    }
}
