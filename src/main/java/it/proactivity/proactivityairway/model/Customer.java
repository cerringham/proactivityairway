package it.proactivity.proactivityairway.model;

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

    @Column(name = "date_of_birthday")
    private String dateOfBirthday;

    @Column(name = "handicap")
    private Boolean isHandicap;

    @Column(name = "passport")
    private String passport;

    @Column(name = "identity_card")
    private String identityCard;

    @Column(name = "vaccinations")
    private String vaccinate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Ticket> ticketList;


}
