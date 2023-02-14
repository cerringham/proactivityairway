package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Flight flight;

    @Column(name = "seat_code")
    private String seatCode;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", customer=" + customer +
                ", flight=" + flight +
                ", seatCode='" + seatCode + '\'' +
                '}';
    }
}
