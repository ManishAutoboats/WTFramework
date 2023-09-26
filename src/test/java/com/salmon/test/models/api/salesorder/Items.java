package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Items {
    String additional_data;
    int amount_refunded;
    String applied_rule_ids;
    int base_amount_refunded;
    int base_cost;
    int base_discount_amount;
    int base_discount_invoiced;
    int base_discount_refunded;
    int base_discount_tax_compensation_amount;
    int base_discount_tax_compensation_invoiced;
    int base_discount_tax_compensation_refunded;
    double base_original_price;
    double base_price;
    double base_price_incl_tax;
    int base_row_invoiced;
    double base_row_total;
    double base_row_total_incl_tax;
    int base_tax_amount;
    int base_tax_before_discount;
    int base_tax_invoiced;
    int base_tax_refunded;
    int base_weee_tax_applied_amount;
    int base_weee_tax_applied_row_amnt;
    int base_weee_tax_disposition;
    int base_weee_tax_row_disposition;
    String created_at;
    String description;
    int discount_amount;
    int discount_invoiced;
    int discount_percent;
    int discount_refunded;
    int event_id;
    String ext_order_item_id;
    int free_shipping;
    int gw_base_price;
    int gw_base_price_invoiced;
    int gw_base_price_refunded;
    int gw_base_tax_amount;
    int gw_base_tax_amount_invoiced;
    int gw_base_tax_amount_refunded;
    int gw_id;
    int gw_price;
    int gw_price_invoiced;
    int gw_price_refunded;
    int gw_tax_amount;
    int gw_tax_amount_invoiced;
    int gw_tax_amount_refunded;
    int discount_tax_compensation_amount;
    int discount_tax_compensation_canceled;
    int discount_tax_compensation_invoiced;
    int discount_tax_compensation_refunded;
    int is_qty_decimal;
    int is_virtual;
    int item_id;
    int locked_do_invoice;
    int locked_do_ship;
    String name;
    int no_discount;
    int order_id;
    double original_price;
    int parent_item_id;
    double price;
    double price_incl_tax;
    int product_id;
    String product_type;
    int qty_backordered;
    int qty_canceled;
    int qty_invoiced;
    int qty_ordered;
    int qty_refunded;
    int qty_returned;
    int qty_shipped;
    int quote_item_id;
    int row_invoiced;
    double row_total;
    double row_total_incl_tax;
    int row_weight;
    String sku;
    int store_id;
    int tax_amount;
    int tax_before_discount;
    int tax_canceled;
    int tax_invoiced;
    int tax_percent;
    int tax_refunded;
    String updated_at;
    String weee_tax_applied;
    int weee_tax_applied_amount;
    int weee_tax_applied_row_amount;
    int weee_tax_disposition;
    int weee_tax_row_disposition;
    int weight;
    ProductOption product_option;
    Items parent_item;
}