package com.salmon.test.page_objects.gui.models;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutPageDeliveryAddressModel {

    String firstName;
    String lastName;
    Address address;

    public CheckoutPageDeliveryAddressModel withDefaultValues() {
        RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build().withDefaultValues();
        RegistrationPageModel.PersonalInfo personalInfo = registrationPageModel.getPersonalInfo();

        return CheckoutPageDeliveryAddressModel.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .address(withAddress())
                .build();
    }

    private Address withAddress() {
        return Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).getDefaultAddress();
    }
}
