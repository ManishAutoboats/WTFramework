package com.salmon.test.models.api.iss.requests;


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
public class StaffLoginRequest {
    String username;
    String password;
    String outlet_id;
}
