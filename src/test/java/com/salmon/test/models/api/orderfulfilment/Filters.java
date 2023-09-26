package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Filters {
    String field;
    String value;
    String condition_type;
}
