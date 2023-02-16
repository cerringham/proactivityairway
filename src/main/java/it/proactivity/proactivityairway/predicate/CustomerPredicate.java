package it.proactivity.proactivityairway.predicate;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CustomerPredicate {

    public Boolean dateBeforeToday(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            return true;
        }else {
            return false;
        }
    }

}
