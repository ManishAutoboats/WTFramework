package com.salmon.test.models.api.salesorder;

import com.salmon.test.models.api.orderfulfilment.Arguments;
import com.salmon.test.models.api.orderfulfilment.ExtensionAttributes;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceOptions {
    Boolean capture;
    Boolean notify;
    List<InvoiceItems> items;
    Boolean appendComment;
    InvoiceComment comment;
    Arguments arguments;

    @Builder
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class InvoiceItems {
        ExtensionAttributes extension_attributes;
        int order_item_id;
        int qty;
    }

    @Builder
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class InvoiceComment {
        ExtensionAttributes extensionAttributes;
        String comment;
        int is_visible_on_front;
    }

    public static InvoiceOptions withDefaultInvoiceOptions(Map<Integer, Integer> orderItemIdQtyMap) {
        return InvoiceOptions.builder()
                .capture(true)
                .notify(true)
                .items(withDefaultInvoiceItems(orderItemIdQtyMap))
                .appendComment(true)
                .comment(withDefaultInvoiceComment())
                .arguments(Arguments.builder()
                        .extension_attributes(ExtensionAttributes.builder().build())
                        .build())
                .build();
    }

    private static InvoiceComment withDefaultInvoiceComment() {
        return InvoiceComment.builder()
                .extensionAttributes(ExtensionAttributes.builder().build())
                .comment("TestInvoiceComment")
                .is_visible_on_front(1)
                .build();
    }

    private static List<InvoiceItems> withDefaultInvoiceItems(Map<Integer, Integer> orderItemIdQtyMap) {
        List<InvoiceItems> invoiceItemsList = new ArrayList<>();

        orderItemIdQtyMap.forEach((key, value) -> {
            InvoiceItems invoiceItems = InvoiceItems.builder()
                    .extension_attributes(ExtensionAttributes.builder().build())
                    .order_item_id(key)
                    .qty(value)
                    .build();

            invoiceItemsList.add(invoiceItems);
        });

        return invoiceItemsList;
    }
}
