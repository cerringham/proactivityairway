package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.builder.CustomerBuilder;
import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.model.dto.TicketDto;
import it.proactivity.proactivityairway.predicate.CustomerPredicate;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.utility.CustomerValidator;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerValidator customerValidator;

    @Autowired
    CustomerPredicate customerPredicate;

    public ResponseEntity insertCustomer(CustomerDto customerDto) {

        if (!customerValidator.validateCustomerDto(customerDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Customer customer = createCustomer(customerDto);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    public ResponseEntity<List<TicketDto>> getTicketListFromCustomerId(Long id) {
        customerValidator.validateId(id);
        Optional<Customer> customer = customerRepository.findByIdAndFetchListOfTicket(id);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Ticket> ticketList = customer.get().getTicketList();
        if (ticketList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<TicketDto> dtoList = ticketList.stream()
                .filter(t -> customerPredicate.dateBeforeToday(t.getFlight().getFlightDate()))
                .map(t -> new TicketDto(t.getFlight().getDepartureTime(),t.getFlight().getArrivalTime(),
                        ParsingUtility.parseDateToString(t.getFlight().getFlightDate())))
                .toList();
        return ResponseEntity.ok(dtoList);
    }



    private Customer createCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Customer dto can't be null");
        }

        LocalDate parseDate = ParsingUtility.parseStringToLocalDate(customerDto.getDateOfBirth());
        if (parseDate == null) {
            throw new IllegalArgumentException("Date of birth can't be null");
        }

        Customer customer = CustomerBuilder.newBuilder(customerDto.getName())
                .surname(customerDto.getSurname())
                .street(customerDto.getStreet())
                .city(customerDto.getCity().toUpperCase())
                .nation(customerDto.getNation().toUpperCase())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .gender(customerDto.getGender())
                .dateOfBirth(parseDate)
                .handicap(customerDto.getIsHandicap())
                .passport(customerDto.getPassport())
                .identityCard(customerDto.getIdentityCard())
                .vaccinate(customerDto.getVaccinate())
                .build();
        return customer;
    }


}
