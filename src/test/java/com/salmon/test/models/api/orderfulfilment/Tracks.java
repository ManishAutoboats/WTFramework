package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tracks {
    String order_id;
    String weight;
    String qty;
    String description;
    String track_number;
    String title;
    String carrier_code;
    String created_at;
    String entity_id;
    String parent_id;
    String updated_at;
}
