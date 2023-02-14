package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "route")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
        return "Route{" +
                "id=" + id +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", flightList=" + flightList +
                '}';
    }
}
