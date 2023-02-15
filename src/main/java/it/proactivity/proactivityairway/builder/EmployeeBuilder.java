package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Employee;
import it.proactivity.proactivityairway.model.Task;

import java.time.LocalDate;

public class EmployeeBuilder {
    private String name;

    private String surname;

    private String email;

    private LocalDate dateOfBirth;

    private Float ral;

    private Task task;

    private EmployeeBuilder(String name) {
        this.name = name;
    }

    public static EmployeeBuilder newBuilder(String name) {
        return new EmployeeBuilder(name);
    }

    public EmployeeBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public EmployeeBuilder ral(Float ral) {
        this.ral = ral;
        return this;
    }

    public EmployeeBuilder task(Task task) {
        this.task = task;
        return this;
    }

    public Employee build() {

        Employee employee = new Employee(name, surname, email, dateOfBirth, ral, task);
        return employee;
    }
}
