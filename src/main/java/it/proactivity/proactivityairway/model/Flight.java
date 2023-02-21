package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private List<Ticket> ticketList;

    public Flight(LocalDate departureDate, LocalDate arrivalDate, Route route, Fleet fleet, LocalTime departureTime,
                  LocalTime arrivalTime) {
        this.departureDate = departureDate;
        this.route = route;
        this.fleet = fleet;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.ticketList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return route + "\n" + fleet + "\n" + departureTime + "-" + arrivalTime + " " + departureDate;
    }
}

