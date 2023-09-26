package com.salmon.test.models.api.iss.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RetrieveQuoteResponse {
    String sku;
    int qty;
    String name;
    ErrorMessage messages;
    String message;
}
