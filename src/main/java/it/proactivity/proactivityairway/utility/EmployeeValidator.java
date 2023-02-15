package it.proactivity.proactivityairway.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

//- inserimento di.un nuovo Employee con le seguenti validazioni:
//controllo della email (usando il metodo della libreria Apache Commons)
//nome e cognome devono contenere solo caratteri alfabetici e spazi (i doppi nomi o i doppi cognomi sono accettati)
//controllo che il numero di telefono contenga solo cifre e il carattere + per il prefisso internazionale (non convertite
// il "+" in 00) n.b. dovete creare voi un metodo
//(ad esempio +0987453 è valido, +89f454 non è valido, +909 89898 non è valido)
//- la ral deve essere un numero con la virgola, deve essere maggiore di 15mila, i valori decimali si fermano alla seconda
//cifra (esempio 15698,67 è valido 13000,73 non è valido, 19888,567 non è valido) verificate se i decimali sono da passare
// con la , o col il .
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

}
