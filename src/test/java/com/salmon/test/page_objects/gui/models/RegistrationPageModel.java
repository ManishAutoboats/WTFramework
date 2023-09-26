package com.salmon.test.page_objects.gui.models;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.CodiceFiscaleGenerator;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.constants.Locale;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class RegistrationPageModel {

    PersonalInfo personalInfo;
    Address address;
    SignInInfo signInInfo;

    @Data
    @Builder
    @FieldDefaults(level = PRIVATE)
    public static class PersonalInfo {
        String firstName;
        String lastName;
        String dob;
        String gender;
        String fiscalCode;
    }

    @Data
    @Builder
    @FieldDefaults(level = PRIVATE)
    public static class Address {
        String streetAndHouseNumber;
        String houseNumber;
        String city;
        String postcode;
        String country;
        String phoneNumber;
        String UserID;
    }

    @Data
    @Builder
    @FieldDefaults(level = PRIVATE)
    public static class SignInInfo {
        String email;
        String password;
        String confirmPassword;
    }

    public RegistrationPageModel withDefaultValues() {
        return RegistrationPageModel.builder()
                .personalInfo(withPersonalInfo())
                .address(withAddress())
                .signInInfo(withSignIninfo())
                .build();
    }

    public RegistrationPageModel withInvalidValues() {
        return RegistrationPageModel.builder()
            .personalInfo(withInvalidPersonalInfo())
            .signInInfo(withInvalidSignIninfo())
            .build();
    }

    private PersonalInfo withPersonalInfo() {
        String dob="";
        switch (UrlBuilder.getLocale()) {
            case "velopl":
            case "vusede":
                dob = UrlBuilder.getMessage("ValidDOB.key");
                return PersonalInfo.builder()
                        .firstName(random(6, ALPHABETS))
                        .lastName(random(6, ALPHABETS))
                        .dob(dob)
                        .gender(UrlBuilder.getMessage("Gender.key"))
                        .build();
            default:
                dob = UrlBuilder.getMessage("validDOB.key");
                return PersonalInfo.builder()
                        .firstName(random(6, ALPHABETS))
                        .lastName(random(6, ALPHABETS))
                        .dob(dob)
                        .gender(UrlBuilder.getMessage("Gender.key"))
                        .fiscalCode(getValidFiscalCode(dob))
                        .build();
        }
    }

    private PersonalInfo withInvalidPersonalInfo() {
        switch (UrlBuilder.getLocale()) {
            case "vuseza":
            case "vusedk":
            case "vusede":
                return PersonalInfo.builder()
                    .firstName(UrlBuilder.getMessage("InvalidUserFirstName.key"))
                    .lastName(UrlBuilder.getMessage("InvalidUserLastName.key"))
                    .dob(UrlBuilder.getMessage("UnderAgeDob.key"))
                    .gender("")
                    .build();
            default://TODO: implement in the future
                return null;
        }
    }


    public String getValidFiscalCode(String dob) {
        DateTimeFormatter formatter;
        switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
            case GLODE:
            case VELODE:
                formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                break;
            case LYFTSE:
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                break;
            default:
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }
        LocalDate date = LocalDate.parse(dob, formatter);
        CodiceFiscaleGenerator fcg = new CodiceFiscaleGenerator(date, true);
        return fcg.getFiscalCode();
    }

    private Address withAddress() {
        return Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).getDefaultAddress();
    }

    private SignInInfo withSignIninfo() {
        return SignInInfo.builder()
                .email(RandomGenerator.randomEmailAddress(6))
                .password(UrlBuilder.getMessage("loginValidPassword.key"))
                .confirmPassword(UrlBuilder.getMessage("loginValidPassword.key"))
                .build();
    }
    private SignInInfo withInvalidSignIninfo() {
        return SignInInfo.builder()
            .email(UrlBuilder.getMessage("loginInvalidEmail.key"))
            .password(UrlBuilder.getMessage("loginInvalidPassword.key"))
            .confirmPassword(UrlBuilder.getMessage("loginInvalidPassword.key") + "0")
            .build();
    }
}
