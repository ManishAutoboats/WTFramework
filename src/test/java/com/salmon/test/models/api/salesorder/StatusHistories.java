package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusHistories {
    String comment;
    String created_at;
    int entity_id;
    String entity_name;
    String is_customer_notified;
    int is_visible_on_front;
    int parent_id;
    String status;
}
