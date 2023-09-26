package com.salmon.test.models.api.orderfulfilment;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockUpdate {
    List<InventoryItems> sourceItems;
}
