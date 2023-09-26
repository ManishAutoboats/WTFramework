package com.salmon.test.models.database;


import com.salmon.test.models.api.customer.CustomAttributes;
import com.salmon.test.models.api.customer.ExtensionAttributes;
import com.salmon.test.models.api.customer.Region;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressesDB {
    int entity_id;
    String customer_id;
    Region regionInfo;
    int region_id;
    String country_id;
    String street;
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
