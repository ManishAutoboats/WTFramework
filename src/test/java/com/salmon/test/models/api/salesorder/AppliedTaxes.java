package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppliedTaxes {
    String code;
    String title;
    int percent;
    int amount;
    int base_amount;
    ExtensionAttributes extension_attributes;

}
