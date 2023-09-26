package com.salmon.test.models.api.iss.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Extension_attributes {
    List<Configurable_item_option> configurable_item_options;
}
