
package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtensionAttributes {
    String option_id;
    String option_value;
    FileInfo fileInfo;
    List<CustomOptions> custom_options;
    List<BundleOptions> bundle_options;
    List<ConfigurableItemOptions> configurable_item_options;
    DownloadableOptions downloadable_option;
    GiftMessage giftMessage;
    List<String> vertex_tax_codes;
    List<String> invoice_text_codes;
    List<String> tax_codes;
    String colissimo_product_code;
    String colissimo_network_code;
    String colissimo_pickup_id;
    CheckoutFields checkout_fields;
    VaultPaymentToken vault_payment_token;
    String tokenbase_id;
    List<Rates> rates;
    String entity_id;
    String entity_type;
    List<ShippingAssignments> shipping_assignments;
    List<PaymentAdditionalInfo> payment_additional_info;
    List<AppliedTaxes> applied_taxes;
    List<ItemAppliedTaxes> item_applied_taxes;
//    boolean converting_from_quote;
}
