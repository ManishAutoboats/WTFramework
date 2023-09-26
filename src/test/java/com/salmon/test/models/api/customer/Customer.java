package com.salmon.test.models.api.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    int id;
    int group_id;
    String default_billing;
    String default_shipping;
    String confirmation;
    String created_at;
    String updated_at;
    String created_in;
    String dob;
    String email;
    String firstname;
    String lastname;
    String middlename;
    String prefix;
    String suffix;
    String gender;
    String store_id;
    String taxvat;
    String website_id;
    String document_value;
    List<Addresses> addresses;
    int disable_auto_group_change;
    ExtensionAttributes extension_attributes;
    List<CustomAttributes> custom_attributes;
}
