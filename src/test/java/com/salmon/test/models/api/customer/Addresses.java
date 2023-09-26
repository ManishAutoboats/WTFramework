package com.salmon.test.models.api.customer;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Addresses {
    int id;
    int customer_id;
    Region region;
    int region_id;
    String country_id;
    List<String> street;
    String company;
    String telephone;
    String fax;
    String postcode;
    String city;
    String firstname;
    String lastname;
    String middlename;
    String prefix;
    String suffix;
    String vat_id;
    String default_shipping;
    String default_billing;
    ExtensionAttributes extension_attributes;
    List<CustomAttributes> custom_attributes;
}
