package com.salmon.test.models.api.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    String sku;
    String name;
    int attribute_set_id;
    double price;
    int status;
    int visibility;
    String type_id;
    String weight;
    ExtensionAttributes extension_attributes;
}
