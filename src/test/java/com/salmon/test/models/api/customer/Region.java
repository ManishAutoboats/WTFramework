package com.salmon.test.models.api.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Region {
    String region_code;
    String region;
    int region_id;
    List<ExtensionAttributes> extension_attributes;
}
