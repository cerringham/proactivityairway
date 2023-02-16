package it.proactivity.proactivityairway.utility;

import it.proactivity.proactivityairway.model.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

//- inserimento di.un nuovo Employee con le seguenti validazioni:
//controllo della email (usando il metodo della libreria Apache Commons)
//nome e cognome devono contenere solo caratteri alfabetici e spazi (i doppi nomi o i doppi cognomi sono accettati)
//controllo che il numero di telefono contenga solo cifre e il carattere + per il prefisso internazionale (non convertite
// il "+" in 00) n.b. dovete creare voi un metodo
//(ad esempio +0987453 è valido, +89f454 non è valido, +909 89898 non è valido)
//- la ral deve essere un numero con la virgola, deve essere maggiore di 15mila, i valori decimali si fermano alla seconda
//cifra (esempio 15698,67 è valido 13000,73 non è valido, 19888,567 non è valido) verificate se i decimali sono da passare
// con la , o col il .
@Component
public class EmployeeValidator {

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

    public static Boolean validateRal(Float ral) {
        if (ral == null) {
            return false;
        }
        if (ral <= 15000.00) {
            return false;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String ralString = decimalFormat.format(ral);
        Float rounded = Float.parseFloat(ralString.replace(',', '.'));
        if (ral.equals(rounded) || ralString.split(",")[1].length() > 2) {
            return false;
        }
        return true;
    }

    public static Boolean validateEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return false;
        }
        if (validNameAndSurname(employeeDto.getName())) {
            return true;
        }
        if (validateEmail(employeeDto.getEmail())) {
            return true;
        }
        if (validateRal(employeeDto.getRal())) {
            return true;
        }
        return true;
    }

}
