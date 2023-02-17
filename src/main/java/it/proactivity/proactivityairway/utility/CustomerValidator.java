package it.proactivity.proactivityairway.utility;

import com.sun.jdi.LongValue;
import it.proactivity.proactivityairway.model.dto.CustomerDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {
    public static Boolean validateEmail(String email) {
        if (StringUtils.isEmpty(email))
            return false;
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    public static Boolean validNameAndSurname(String nameOrSurname) {
        if (StringUtils.isEmpty(nameOrSurname)) {
            return false;
        }
        return StringUtils.isAlphaSpace(nameOrSurname);
    }

    public static Boolean validPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return false;
        }
        if (phoneNumber.startsWith("+")) {
            return true;
        }
        if (StringUtils.isNumeric(phoneNumber)) {
            return true;
        }
        return false;
    }

    public static Boolean validateId(String id) {
        if (StringUtils.isEmpty(id)) {
            return false;
        }
        try {
            Long.parseLong(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean validateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return false;
        }
        if (validNameAndSurname(customerDto.getName()) &&
                validateEmail(customerDto.getEmail()) &&
                validPhoneNumber(customerDto.getPhoneNumber())) {
            return true;
        }
        return false;
    }
}
