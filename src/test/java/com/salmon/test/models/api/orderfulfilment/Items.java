package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Items {
    String entity_id;
    String parent_id;
    String name;
    String price;
    String product_id;
    String sku;
    String weight;
    String order_item_id;
    int qty;
}
