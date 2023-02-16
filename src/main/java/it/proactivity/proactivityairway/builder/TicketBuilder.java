package it.proactivity.proactivityairway.builder;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Flight;
import it.proactivity.proactivityairway.model.Ticket;

public class TicketBuilder {

    private Flight flight;

    private Customer customer;

    private String seatCode;

    private TicketBuilder(Flight flight) {
        this.flight = flight;
    }

    public static TicketBuilder newBuilder(Flight flight) {
        return new TicketBuilder(flight);
    }

    public TicketBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public TicketBuilder seatCode(String seatCode) {
        this.seatCode = seatCode;
        return this;
    }

    public Ticket build() {
        return new Ticket(flight, customer, seatCode);
    }
}
