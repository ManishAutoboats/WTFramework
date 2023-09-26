package com.salmon.test.models.api.iss.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    String entity_id;
    String outlet_id;
    String area;
    String site;
    String status;
    String storeviews;
}
