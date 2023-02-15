package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class EmployeeValidator {

    public Boolean validateEmployeeNameAndSurname(String name, String surname) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)) {
            throw new IllegalArgumentException("Name and surname can't be null or empty");
        }
        if (StringUtils.isAlphaSpace(name) && StringUtils.isAlphaSpace(surname)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean validateEmployeeEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }


    public Boolean validateEmployeePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber can't be null or empty");
        }
        if (StringUtils.startsWith(phoneNumber, "+")) {
            phoneNumber = phoneNumber.replace("+", "");
        }
        if (StringUtils.isNumeric(phoneNumber)) {
            return true;
        }
        return false;
    }

    public boolean validateEmployeeRal(Float ral) {
        if (ral == null) {
            throw new IllegalArgumentException("Ral can't be null");
        }
        if (ral < 15000.00) {
            throw new IllegalArgumentException("The ral can't be lower than 15k");
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String formattedInput = decimalFormat.format(ral);
        Float roundedInput = Float.parseFloat(formattedInput.replace(',', '.'));

        if (!ral.equals(roundedInput)) {
            return false;
        }
        return true;
    }

    public Boolean validateEmployeeDto(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("Employee dto can't be null");
        }

        if (!validateEmployeeNameAndSurname(employeeDto.getName(), employeeDto.getSurname())) {
            throw new IllegalArgumentException("Wrong name and surname");
        }

        if (!validateEmployeeEmail(employeeDto.getEmail())) {
            throw new IllegalArgumentException("Email is wrong");
        }

        if (!validateEmployeeRal(employeeDto.getRal())) {
            throw new IllegalArgumentException("Ral is not acceptable");
        }

        return true;
    }
}
