package com.salmon.test.models.api.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtensionAttributes {
   List<CategoryLinks> category_links;
   StockItem stock_item;
}
