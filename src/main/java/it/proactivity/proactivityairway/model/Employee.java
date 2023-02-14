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

    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirth;

    @Column(name = "ral")
    private Float ral;

    @ManyToOne
    private Task task;

    @Override
    public String toString() {
        return name + " " + surname + " " + task + " " + ral;
    }
}

