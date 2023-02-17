package it.proactivity.proactivityairway.service;

import it.proactivity.proactivityairway.model.Customer;
import it.proactivity.proactivityairway.model.Ticket;
import it.proactivity.proactivityairway.model.dto.CustomerDto;
import it.proactivity.proactivityairway.repository.CustomerRepository;
import it.proactivity.proactivityairway.utility.CustomerValidator;
import it.proactivity.proactivityairway.utility.ParsingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//inserimento di un Customer, controllando che il numero di telefono contenga solo cifre e il carattere + per il
// prefisso internazionale (non convertite il "+" in 00) n.b. dovete creare voi un metodo
//(ad esempio +0987453 è valido, +89f454 non è valido, +909 89898 non è valido)
//rimangono validi i controlli per email, nome e cognome già fatti per l'Employee
@Service
public class CustomerService {
    @Autowired
    static
    ParsingUtility parsingUtility;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerValidator customerValidator;

    public ResponseEntity insertNewCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return ResponseEntity.badRequest().build();
        }
        if (customerValidator.validateCustomer(customerDto)) {
            Customer customer = createCustomer(customerDto);
            customerRepository.save(customer);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    public static Customer createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setStreet(customerDto.getStreet());
        customer.setCity(customerDto.getCity());
        customer.setNation(customerDto.getNation());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setGender(customerDto.getGender());
        customer.setDateOfBirthday(parsingUtility.parseStringToLocalDate(customerDto.getDateOfBirthday()));
        customer.setIsHandicap(customerDto.getIsHandicap());
        customer.setPassport(customerDto.getPassport());
        customer.setIdentityCard(customerDto.getIdentityCard());
        customer.setVaccinations(customerDto.getVaccinations());
        return customer;
    }

    public ResponseEntity<List<Ticket>> getAllTicketsFromCustomer(Long customerId) {
        if (customerId == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Ticket> customerTicketList = customer.get().getTicketList();
        if (customerTicketList.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        for (Ticket t : customerTicketList) {
            if (t.getFlight().getFlightDate().isBefore(LocalDate.now())) {
                t.getFlight();
            }
            return null;
        }
        return ResponseEntity.ok(customerTicketList);
    }
}
