package com.salmon.test.models.api.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBuilder {
    Product product;

    public ProductBuilder newProduct() {
        return ProductBuilder.builder()
                .product(buildProduct())
                .build();
    }

    private Product buildProduct() {
        return Product.builder()
                .sku("MS-Champ-S")
                .name("Champ Tee Small")
                .attribute_set_id(9)
                .price(25)
                .status(1)
                .visibility(1)
                .type_id("simple")
                .weight("0.5")
                .extension_attributes(buildExtensionAttributes())
                .build();
    }

    private ExtensionAttributes buildExtensionAttributes() {
        return ExtensionAttributes.builder()
                .category_links(buildCategoryLinks())
                .stock_item(buildStockItem())
                .build();
    }

    private List<CategoryLinks> buildCategoryLinks() {
        return Arrays.asList(CategoryLinks.builder().category_id("11").position("0").build(),
                CategoryLinks.builder().category_id("13").position("1").build());
    }

    private StockItem buildStockItem() {
        return StockItem.builder()
                .qty("1")
                .is_in_stock("yes")
                .build();
    }
}
