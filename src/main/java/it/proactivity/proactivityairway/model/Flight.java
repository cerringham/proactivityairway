package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Fleet fleet;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "flight_date")
    private LocalDate flightDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private List<Ticket> ticketList;

    public Flight(LocalDate flightDate, Route route, Fleet fleet, String departureTime, String arrivalTime) {
        this.flightDate = flightDate;
        this.route = route;
        this.fleet = fleet;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return route + "\n" + fleet + "\n" + departureTime + "-" + arrivalTime + " " + flightDate;
    }
}

