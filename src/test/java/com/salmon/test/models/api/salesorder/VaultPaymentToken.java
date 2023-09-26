package com.salmon.test.models.api.salesorder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaultPaymentToken {
    int entity_id;
    int customer_id;
    String public_hash;
    String payment_method_code;
    String type;
    String created_at;
    String expires_at;
    String gateway_token;
    String token_details;
    boolean is_active;
    boolean is_visible;
}
