package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.dto.CustomerDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public Boolean validateCustomerDto(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("Customer Dto can'y be null");
        }
        if (!validateCustomerNameAndSurname(customerDto.getName(), customerDto.getSurname())) {
            return false;
        }

        if (!validatePassport(customerDto.getPassport())) {
            return false;
        }

        if (!validateCity(customerDto.getCity())) {
            return false;
        }

        if (!validateGender(customerDto.getGender())) {
            return false;
        }

        if (!validateStreet(customerDto.getStreet())) {
            return false;
        }

        if (!validateNation(customerDto.getNation())) {
            return false;
        }

        if (!validateCustomerEmail(customerDto.getEmail())) {
            return false;
        }

        if (!validatePhoneNumber(customerDto.getPhoneNumber())) {
            return false;
        }

        if (!validateVaccinate(customerDto.getVaccinate())) {
            return false;
        }

        if (!validateIdentityCard(customerDto.getIdentityCard())) {
            return false;
        }

        validateIsHandicap(customerDto.getIsHandicap());
        return true;
    }


    private Boolean validateCustomerNameAndSurname(String name, String surname) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(surname)) {
            throw new IllegalArgumentException("Name and surname can't be null or empty");
        }
        if (StringUtils.isAlphaSpace(name) && StringUtils.isAlphaSpace(surname)) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean validateCustomerEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("Email can't be null or empty");
        }
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }


    private Boolean validatePhoneNumber(String phoneNumber) {
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

    private Boolean validateStreet(String street) {
        if (StringUtils.isEmpty(street)) {
            throw new IllegalArgumentException("Street can't be null or empty");
        }
        return StringUtils.isAlphanumericSpace(street);
    }

    private Boolean validateCity(String city) {
        if (StringUtils.isEmpty(city)) {
            throw new IllegalArgumentException("City can't be null or empty");
        }

        return StringUtils.isAlpha(city);
    }

    private Boolean validateNation(String nation) {
        if (StringUtils.isEmpty(nation)) {
            throw new IllegalArgumentException("Nation can't be null or empty");
        }
        if (nation.length() == 3 && StringUtils.isAlpha(nation)) {
            return true;
        }else {
            return false;
        }
    }

    private Boolean validateGender(String gender) {
        if (StringUtils.isEmpty(gender)) {
            throw new IllegalArgumentException("Gender can't be null or empty");
        }

        if (StringUtils.isAlpha(gender) && (gender.equalsIgnoreCase("male") ||
                gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"))) {
            return true;
        }else {
            return false;
        }
    }

    private Boolean validateIsHandicap(Boolean isHandicap) {
        if (isHandicap == null) {
            throw new IllegalArgumentException("IsHandicap can't be null");
        }
        return true;
    }

    private Boolean validatePassport(String passport) {
        if (StringUtils.isEmpty(passport)) {
            throw new IllegalArgumentException("Passport can't be null or empty");
        }
        return StringUtils.isAlphanumeric(passport);
    }

    private Boolean validateIdentityCard(String identityCard) {
        if (StringUtils.isEmpty(identityCard)) {
            throw new IllegalArgumentException("identityCard can't be null or empty");
        }
        return StringUtils.isAlphanumeric(identityCard);
    }

    private Boolean validateVaccinate(String vaccinate) {
        if (StringUtils.isEmpty(vaccinate)) {
            throw new IllegalArgumentException("Vaccinate can't be null or empty");
        }

        return !StringUtils.isNumeric(vaccinate);
    }

    public Boolean validateId(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        return true;
    }
}
