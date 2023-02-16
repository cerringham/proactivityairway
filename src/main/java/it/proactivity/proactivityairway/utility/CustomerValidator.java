package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.dto.CustomerDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

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

    public static Boolean validateCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return false;
        }
        if (validNameAndSurname(customerDto.getName())) {
            return true;
        }
        if (validNameAndSurname(customerDto.getSurname())) {
            return true;
        }
        if (validateEmail(customerDto.getEmail())) {
            return true;
        }
        if (validPhoneNumber(customerDto.getPhoneNumber())) {
            return true;
        }
        return true;
    }
}
