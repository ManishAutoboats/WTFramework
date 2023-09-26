package com.salmon.test.page_objects.gui.models;

import com.salmon.test.page_objects.gui.constants.Locale;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.salmon.test.framework.helpers.UrlBuilder.getLocale;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class NewsLetterPageModel {

    String firstName;
    String lastName;
    String phoneNumber;
    DateOfBirth dob;
    String email;
    String bankId;

    @Data
    @Builder
    @FieldDefaults(level = PRIVATE)
    public static class DateOfBirth {
        String date;
        String month;
        String year;
    }

    public NewsLetterPageModel withDefaultValues() {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        RegistrationPageModel.PersonalInfo personalInfo = registrationPageModel.getPersonalInfo();

        return NewsLetterPageModel.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .phoneNumber(registrationPageModel.getAddress().getPhoneNumber())
                .dob(withDateOfBirth(personalInfo.getDob()))
                .email(registrationPageModel.getSignInInfo().getEmail())
                .bankId("197506032916")
                .build();
    }

    public static DateOfBirth withDateOfBirth(String dob) {
        Map<String, String> dobFields = getDOBFieldsFromDOB(dob);
        return DateOfBirth.builder()
                .date(dobFields.get("dob_date"))
                .month(dobFields.get("dob_month"))
                .year(dobFields.get("dob_year"))
                .build();
    }

    private static Map<String, String> getDOBFieldsFromDOB(String dob) {
        List<String> dobFields;

        String[] strings;
        switch (Locale.valueOf(getLocale().toUpperCase())){
            case GLODE:
                strings = dob.split(".");
                break;
            case LYFTSE:
                strings = dob.split("-");
                break;
            default:
                strings = dob.split("/");
        }

        dobFields = Arrays.stream(strings).collect(Collectors.toList());

        Map<String, String> map = new HashMap<>();
        map.put("dob_date", dobFields.get(0));
        map.put("dob_month", dobFields.get(1));
        map.put("dob_year", dobFields.get(2));

        return map;
    }
}
