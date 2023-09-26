package com.salmon.test.models.api.iss.responses;

import com.salmon.test.models.api.product.ExtensionAttributes;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOption {
    ExtensionAttributes extension_attributes;
}

