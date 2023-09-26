package com.salmon.test.models.api.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YourChoiceCustomer {
    String username;
    String password;
    boolean yourChoiceLogin;
}
