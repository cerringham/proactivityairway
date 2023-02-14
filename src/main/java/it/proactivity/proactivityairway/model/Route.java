package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "route")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure")
    private Long departure;

    @Column(name = "arrival")
    private Long arrival;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private List<Flight> flightList;

    @Override
    public String toString() {
        return id + " Departure : " + departure + " \n Arrival : " + arrival;
    }
}


