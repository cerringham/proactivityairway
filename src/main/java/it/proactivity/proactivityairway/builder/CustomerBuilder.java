package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Customer;

import java.time.LocalDate;

public class CustomerBuilder {

    private String name;

    private String surname;

    private String street;

    private String city;

    private String nation;

    private String email;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;

    private Boolean isHandicap;

    private String passport;

    private String identityCard;

    private String vaccinate;

    private CustomerBuilder(String name) {
        this.name = name;
    }

    public static CustomerBuilder newBuilder(String name) {
        return new CustomerBuilder(name);
    }

    public CustomerBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public CustomerBuilder email(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerBuilder street(String street) {
        this.street = street;
        return this;
    }

    public CustomerBuilder city(String city) {
        this.city = city;
        return this;
    }

    public CustomerBuilder nation(String nation) {
        this.nation = nation;
        return this;
    }

    public CustomerBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public CustomerBuilder handicap(Boolean isHandicap) {
        this.isHandicap = isHandicap;
        return this;
    }

    public CustomerBuilder passport(String passport) {
        this.passport = passport;
        return this;
    }

    public CustomerBuilder identityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    public CustomerBuilder vaccinate(String vaccinate) {
        this.vaccinate = vaccinate;
        return this;
    }

    public Customer build() {
        return new Customer(name, surname, street, city, nation, email, phoneNumber, gender, dateOfBirth, isHandicap,
                passport, identityCard, vaccinate);
    }
}
