package com.salmon.test.models.api.iss.responses;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffLoginResponse {
    String token;
    String quote_id;
    String message;
    Parameters parameters;
}

