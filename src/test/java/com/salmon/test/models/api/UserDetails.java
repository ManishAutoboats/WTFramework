package com.salmon.test.models.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails {
     String username;
     String password;

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
