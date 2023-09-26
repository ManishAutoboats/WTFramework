package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    String is_customer_notified;
    String comment;
    int is_visible_on_front;
    String parent_id;
    String entity_id;
    String created_at;
}
