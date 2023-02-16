package it.proactivity.proactivityairway.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private Customer customer;

    @Column(name = "seat_code")
    private String seatCode;

    public Ticket(Flight flight, Customer customer, String seatCode) {
        this.flight = flight;
        this.customer = customer;
        this.seatCode = seatCode;
    }


    @Override
    public String toString() {
        return customer.getName() + " " + customer.getSurname() + " " + seatCode;
    }
}
