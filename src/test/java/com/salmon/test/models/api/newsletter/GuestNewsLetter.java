package com.salmon.test.models.api.newsletter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuestNewsLetter {
     int entity_id;
     int subscriber_id;
     String firstname;
     String lastname;
}
