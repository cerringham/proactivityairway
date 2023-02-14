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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Fleet fleet;

    @ManyToOne
    private Customer customer;

    @Column(name = "seat_code")
    private String seatCode;



    @Override
    public String toString() {
        return customer.getName() + " " + customer.getSurname() + " " + seatCode;
    }
}
