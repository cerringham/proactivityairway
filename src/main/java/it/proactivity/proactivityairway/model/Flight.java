package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "flight")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", route=" + route +
                ", fleet=" + fleet +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", flightDate=" + flightDate +
                ", ticketList=" + ticketList +
                '}';
    }
}
