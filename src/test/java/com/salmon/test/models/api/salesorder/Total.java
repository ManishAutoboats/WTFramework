package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Total {
    int base_shipping_amount;
    int base_shipping_canceled;
    int base_shipping_discount_amount;
    int base_shipping_discount_tax_compensation_amnt;
    int base_shipping_incl_tax;
    int base_shipping_invoiced;
    int base_shipping_refunded;
    int base_shipping_tax_amount;
    int base_shipping_tax_refunded;
    int shipping_amount;
    int shipping_canceled;
    int shipping_discount_amount;
    int shipping_discount_tax_compensation_amount;
    int shipping_incl_tax;
    int shipping_invoiced;
    int shipping_refunded;
    int shipping_tax_amount;
    int shipping_tax_refunded;
    ExtensionAttributes extension_attributes;
}
