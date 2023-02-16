package it.proactivity.proactivityairway.model.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String name;

    private String surname;

    private String street;

    private String city;

    private String nation;

    private String email;

    private String phoneNumber;

    private String gender;

    private String dateOfBirth;

    private Boolean isHandicap;

    private String passport;

    private String identityCard;

    private String vaccinate;
}
