package com.salmon.test.models.database;

import com.salmon.test.models.api.customer.CustomAttributes;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDB {
    int entity_id;
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
    List<AddressesDB> addresses;
    int disable_auto_group_change;
    ExtensionAttributesDB extension_attributes;
    List<CustomAttributes> custom_attributes;
}
