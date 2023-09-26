package com.salmon.test.models.api.newsletter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscriber {
    int subscriber_id;
    String subscriber_email;
    int subscriber_status;
    String firstname;
    String lastname;
    int customer_id;
    int store_id;
}
