package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    String account_status;
    String additional_data;
    List<String> additional_information;
    String address_status;
    int amount_authorized;
    int amount_canceled;
    double amount_ordered;
    double amount_paid;
    double amount_refunded;
    int anet_trans_method;
    int base_amount_authorized;
    int base_amount_canceled;
    double base_amount_ordered;
    double base_amount_paid;
    int base_amount_paid_online;
    int base_amount_refunded;
    int base_amount_refunded_online;
    int base_shipping_amount;
    int base_shipping_captured;
    int base_shipping_refunded;
    String cc_approval;
    String cc_avs_status;
    String cc_cid_status;
    String cc_debug_request_body;
    String cc_debug_response_body;
    String cc_debug_response_serialized;
    String cc_exp_month;
    String cc_exp_year;
    String cc_last4;
    String cc_number_enc;
    String cc_owner;
    String cc_secure_verify;
    String cc_ss_issue;
    String cc_ss_start_month;
    String cc_ss_start_year;
    String cc_status;
    String cc_status_description;
    String cc_trans_id;
    String cc_type;
    String echeck_account_name;
    String echeck_account_type;
    String echeck_bank_name;
    String echeck_routing_number;
    String echeck_type;
    int entity_id;
    String last_trans_id;
    String method;
    int parent_id;
    String po_number;
    String protection_eligibility;
    int quote_payment_id;
    int shipping_amount;
    int shipping_captured;
    int shipping_refunded;
}
