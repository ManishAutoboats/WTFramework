package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    String address_type;
    String city;
    String company;
    String country_id;
    int customer_address_id;
    int customer_id;
    String email;
    int entity_id;
    String fax;
    String firstname;
    String lastname;
    String middlename;
    int parent_id;
    String postcode;
    String prefix;
    String region;
    String region_code;
    int region_id;
    List<String> street;
    String suffix;
    String telephone;
    String vat_id;
    int vat_is_valid;
    String vat_request_date;
    String vat_request_id;
    int vat_request_success;
    ExtensionAttributes extension_attributes;
}
