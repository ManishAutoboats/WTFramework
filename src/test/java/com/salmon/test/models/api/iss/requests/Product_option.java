package com.salmon.test.models.api.iss.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product_option {
    Extension_attributes extension_attributes;
}
