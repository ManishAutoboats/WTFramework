package com.salmon.test.models.api.salesorder;

import com.salmon.test.enums.Store;
import com.salmon.test.framework.helpers.UrlBuilder;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalesOrderBuilder {

    SalesOrder entity;

    public SalesOrderBuilder createNewSalesOrder(int customerID, String email) {
        SalesOrder salesOrder = SalesOrder.builder()
                .adjustment_negative(2)
                .adjustment_positive(0)
                .base_adjustment_negative(3)
                .base_adjustment_positive(0)
                .base_currency_code("GBP")
                .base_discount_amount(0)
                .base_discount_invoiced(0)
                .base_discount_refunded(0)
                .base_grand_total(34.99)
                .base_discount_tax_compensation_amount(0)
                .base_discount_tax_compensation_invoiced(0)
                .base_discount_tax_compensation_refunded(0)
                .base_shipping_amount(5)
                .base_shipping_discount_amount(0)
                .base_shipping_discount_tax_compensation_amnt(0)
                .base_shipping_incl_tax(5)
                .base_shipping_refunded(0)
                .base_shipping_tax_amount(0)
                .base_shipping_tax_refunded(0)
                .base_subtotal(29.99)
                .base_subtotal_incl_tax(29.99)
                .base_tax_amount(0)
                .base_tax_invoiced(0)
                .base_tax_refunded(0)
                .base_total_due(0)
                .base_total_invoiced_cost(0)
                .base_total_paid(34.99)
                .base_to_global_rate(1)
                .base_to_order_rate(1)
                .billing_address_id(2)
                .customer_email(email)
                .customer_firstname("Chandra")
                .customer_lastname("Reddy")
                .customer_gender(1)
                .customer_group_id(1)
                .customer_id(customerID)
                .customer_is_guest(0)
                .customer_note_notify(1)
                .discount_amount(0)
                .discount_invoiced(0)
                .discount_refunded(0)
                .email_sent(1)
                .global_currency_code("GBP")
                .grand_total(34.99)
                .discount_tax_compensation_amount(0)
                .discount_tax_compensation_invoiced(0)
                .discount_tax_compensation_refunded(1)
                .is_virtual(0)
                .order_currency_code("GBP")
                .quote_id(11)
                .remote_ip("10.10.0.52")
                .shipping_amount(5)
                .shipping_description("Flat Rate - Fixed")
                .shipping_discount_amount(0)
                .shipping_discount_tax_compensation_amount(0)
                .shipping_refunded(0)
                .shipping_tax_amount(0)
                .shipping_tax_refunded(0)
                .state("new")
                .status("pending")
                .store_currency_code("GBP")
                .store_id(Integer.parseInt(Store.valueOf(UrlBuilder.storeCode).getStoreId()))
                .store_name("Vype UK\\nVype\\nEnglish")
                .store_to_base_rate(0)
                .store_to_order_rate(0)
                .subtotal(29.99)
                .subtotal_incl_tax(29.99)
                .tax_amount(0)
                .tax_invoiced(0)
                .tax_refunded(0)
                .total_due(0)
                .total_item_count(1)
                .total_paid(34.99)
                .total_qty_ordered(1)
                .weight(2)
                .items(buildOrderItems())
                .billing_address(buildAddress("billing", email))
                .payment(buildPaymentDetails())
                .extension_attributes(buildExtensionAttributes(email))
                .build();

        return SalesOrderBuilder.builder().entity(salesOrder).build();
    }

    private List<Items> buildOrderItems() {
        Items items = Items.builder()
                .base_original_price(29.99)
                .base_price(29.99)
                .base_price_incl_tax(29.99)
                .base_row_total(29.99)
                .base_row_total_incl_tax(29.99)
                .base_tax_amount(0)
                .base_tax_invoiced(0)
                .base_tax_refunded(0)
                .discount_amount(0)
                .discount_invoiced(0)
                .discount_percent(0)
                .is_qty_decimal(0)
                .name("Vype ePen 3 Iced Blackcurrant Cartridges")
                .original_price(5.49)
                .price(5.49)
                .price_incl_tax(5.49)
                .product_id(438)
                //.product_type("configurable")
                .product_type("simple")
                .qty_ordered(1)
                .quote_item_id(135)
                .row_total(29.99)
                .row_total_incl_tax(29.99)
                .row_weight(2)
                //.sku("VYPE-EPEN3-ICED-B")
                .sku("10086527")
                .store_id(Integer.parseInt(Store.valueOf(UrlBuilder.storeCode).getStoreId()))
                .weight(2)
                .product_option(buildProductOptions())
                .build();
        switch (UrlBuilder.storeCode) {
            case "vype_co_es_es":
            case "vuse_co_es_es":
            case "vuse_mx_es_es":
                items.setSku("vype-epen3-device");
                break;
        }
        return Arrays.asList(items);
    }

    private ProductOption buildProductOptions() {
        ProductOption productOption = new ProductOption();
        ExtensionAttributes extensionAttributes = new ExtensionAttributes();
        List<ConfigurableItemOptions> configurableItemOptions = Arrays.asList(ConfigurableItemOptions.builder()
                .option_id("197")
                .option_value(26)
                .build());
        extensionAttributes.setConfigurable_item_options(configurableItemOptions);
        productOption.setExtension_attributes(extensionAttributes);
        return productOption;

    }

    private Address buildAddress(String addressType, String email) {
        return Address.builder()
                .address_type(addressType)
                .city("London")
                .country_id("GB")
                .customer_address_id(64042)
                .email(email)
                .firstname("Chandra")
                .lastname("Reddy")
                .parent_id(56849)
                .postcode("WD17 1JJ")
                .street(Arrays.asList("64 watford"))
                .telephone("07956 012210")
                .build();
    }

    private Payment buildPaymentDetails() {
       return Payment.builder()
                .amount_ordered(34.99)
                .amount_paid(34.99)
                .base_amount_ordered(34.99)
                .base_amount_paid(34.99)
                .base_shipping_amount(5)
                .base_shipping_captured(5)
                .cc_exp_year("0")
                .cc_last4(null)
                .cc_ss_start_month("0")
                .cc_ss_start_year("0")
                .method("checkmo")
                .parent_id(56849)
                .shipping_amount(5)
                .shipping_captured(5)
                .shipping_refunded(0)
                .build();
    }

    private ExtensionAttributes buildExtensionAttributes(String email) {
        return ExtensionAttributes.builder()
                .shipping_assignments(Arrays.asList(buildShippingAssignments(email)))
                .payment_additional_info(Arrays.asList(buildPaymentAdditionalInfo()))
                .build();
    }

    private ShippingAssignments buildShippingAssignments(String email) {
       Total total =  Total.builder()
                .base_shipping_amount(5)
                .base_shipping_discount_amount(0)
                .base_shipping_discount_tax_compensation_amnt(0)
                .base_shipping_incl_tax(5)
                .shipping_amount(5)
                .shipping_incl_tax(5)
                .build();

       Shipping shipping = Shipping.builder()
                .address(buildAddress("shipping", email))
                .method("flatrate_flatrate")
                .total(total)
                .build();

       return ShippingAssignments.builder()
               .shipping(shipping)
               .items(buildOrderItems())
               .build();

    }

    private PaymentAdditionalInfo buildPaymentAdditionalInfo() {
        return PaymentAdditionalInfo.builder()
                .key("method_title")
                .value("Check / Money order")
                .build();
    }
}
