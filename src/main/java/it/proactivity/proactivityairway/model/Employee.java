package it.proactivity.proactivityairway.model;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "ral")
    private Float ral;

    @ManyToOne
    private Task task;

    public Employee(String name, String surname, String email, LocalDate dateOfBirth, Float ral, Task task) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.ral = ral;
        this.task = task;
    }
    @Override
    public String toString() {
        return name + " " + surname + " " + task + " " + ral;
    }
}

