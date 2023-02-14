package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "site")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Company company;
    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "nation")
    private String nation;
    @Column(name = "head_quarter")
    private Boolean headQuarter;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return name + " " + phoneNumber + " " + email + " " + headQuarter;
    }
}
