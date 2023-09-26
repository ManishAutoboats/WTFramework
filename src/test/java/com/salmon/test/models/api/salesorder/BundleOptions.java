package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BundleOptions {
    int option_id;
    int option_qty;
    List<Integer> option_selections;
    ExtensionAttributes extension_attributes;
}
