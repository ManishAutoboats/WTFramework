package com.salmon.test.models.api.iss.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendToCustomerResponse {
    String status;
    String id;
}
