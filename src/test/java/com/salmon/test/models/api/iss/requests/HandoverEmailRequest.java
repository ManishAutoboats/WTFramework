package com.salmon.test.models.api.iss.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HandoverEmailRequest {
    String email;
    String quote_id;
    String outlet_id;
    String staff_id;
}
