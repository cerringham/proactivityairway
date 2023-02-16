package it.proactivity.proactivityairway.model.dto;

import it.proactivity.proactivityairway.model.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String surname;
    private String street;
    private String city;
    private String nation;
    private String email;
    private String phoneNumber;
    private String gender;
    private String dateOfBirthday;
    private Boolean isHandicap;
    private String passport;
    private String identityCard;
    private String vaccinate;
    private List<Ticket> ticketList;

    public CustomerDto(String name, String surname, String street, String city, String nation, String email,
                       String phoneNumber, String gender, String dateOfBirthday, Boolean isHandicap, String passport,
                       String identityCard, String vaccinate, List<Ticket> ticketList) {
        this.name = name;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.nation = nation;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirthday = dateOfBirthday;
        this.isHandicap = isHandicap;
        this.passport = passport;
        this.identityCard = identityCard;
        this.vaccinate = vaccinate;
        this.ticketList = ticketList;
    }
}
