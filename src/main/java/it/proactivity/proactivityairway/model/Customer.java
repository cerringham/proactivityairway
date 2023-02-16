package it.proactivity.proactivityairway.model;

import it.proactivity.proactivityairway.builder.CustomerBuilder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "nation")
    private String nation;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "handicap")
    private Boolean isHandicap;

    @Column(name = "passport")
    private String passport;

    @Column(name = "identity_card")
    private String identityCard;

    @Column(name = "vaccinations")
    private String vaccinate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loyalty_program_id", referencedColumnName = "id")
    private LoyaltyProgram loyaltyProgram;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Ticket> ticketList;

    public Customer(String name, String surname, String street, String city, String nation, String email,
                    String phoneNumber, String gender, LocalDate dateOfBirth, Boolean isHandicap, String passport,
                    String identityCard, String vaccinate) {

        this.name = name;
        this.surname = surname;
        this.street = street;
        this.city = city;
        this.nation = nation;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.isHandicap = isHandicap;
        this.passport = passport;
        this.identityCard = identityCard;
        this.vaccinate = vaccinate;
    }


    @Override
    public String toString() {
        return name + " " + surname + " " + gender + " " + passport + " " + isHandicap + " " + vaccinate;
    }
}
