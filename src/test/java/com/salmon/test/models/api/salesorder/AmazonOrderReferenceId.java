package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AmazonOrderReferenceId {
    String amazon_order_reference_id;
    int order_id;
}
