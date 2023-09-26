package com.salmon.test.page_objects.gui.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.salmon.test.framework.helpers.UrlBuilder.getMessage;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceRegistrationPageModel {

    String postCode;
    String myGloNumber;
    String deviceType;
    String deviceColour;
    String whereDidYouBuyGlo;
    String couponCode;

    public DeviceRegistrationPageModel withDefaultValues() {
        return DeviceRegistrationPageModel.builder()
                .postCode(getMessage("postcode.key"))
                .myGloNumber(getMessage("deviceRegMyGloNUmber.key"))
                .deviceType(getMessage("devRegDeviceType.key"))
                .deviceColour(getMessage("devRegDeviceColour.key"))
                .whereDidYouBuyGlo(getMessage("devRegWhereDidYouBuyGlo.key"))
                .build();
    }
}
