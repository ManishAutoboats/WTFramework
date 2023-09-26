package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShipmentTracking {
    String billing_address_id;
    String created_at;
    String customer_id;
    String email_sent;
    String entity_id;
    String increment_id;
    String shipping_address_id;
    String store_id;
    String total_qty;
    String updated_at;
    List<String> packages;
    String order_id;
    List<Items> items;
    List<Comment> comments;
    List<Tracks> tracks;
    ExtensionAttributes extension_attributes;
}
