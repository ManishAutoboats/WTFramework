package com.salmon.test.models.api.iss.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem {
    String sku;
    String qty;
    String quote_id;
    Product_option product_option;
}
