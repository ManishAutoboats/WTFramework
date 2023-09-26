package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionPoint {
    int recipient_address_id;
    String collection_point_id;
    String name;
    String country;
    String region;
    String postcode;
    String city;
    List<String> street;
}
