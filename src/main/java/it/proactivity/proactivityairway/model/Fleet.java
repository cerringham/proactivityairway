package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "fleet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Fleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airplane_description")
    private String airplaneDescription;

    @Column(name = "number_of_seat")
    private Integer numberOfSeat;

    @Column(name = "availability")
    private Integer availability;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fleet_id")
    private List<Flight> flightList;

    @Override
    public String toString() {
        return airplaneDescription + " " + numberOfSeat;
    }
}

