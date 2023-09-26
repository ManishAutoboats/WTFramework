package com.salmon.test.models.api.iss.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddToCartResponse {
    String item_id;
    String sku;
    String qty;
    String name;
    String price;
    String product_type;
    String quote_id;
    ProductOption product_option;
}
