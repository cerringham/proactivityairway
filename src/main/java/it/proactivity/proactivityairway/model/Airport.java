package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "international_code")
    private String internationalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "nation")
    private String nation;

    @Override
    public String toString() {
        return name + " " + city + " " + nation;
    }
}


