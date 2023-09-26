package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemAppliedTaxes {
    String type;
    int item_id;
    int associated_item_id;
    List<AppliedTaxes> appliedTaxes;
    ExtensionAttributes extension_attributes;
}
