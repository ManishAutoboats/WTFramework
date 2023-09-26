package com.salmon.test.models.cucumber.glo.myaccount;

import lombok.Data;

@Data
public class AddressModel {
    String company;
    String addressLine;
    String town;
    String postCode;
    String country;
    String addressType;
}
