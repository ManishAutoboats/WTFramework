package com.salmon.test.models.api.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtensionAttributes {
    int data_handling_consent;
    int market_research_consent;
    int email_consent;
    int telephone_consent;
    int email_optout;
    int telephone_optout;
    int initial_load;
    String company;
    String is_subscribed;
    String amazon_id;
    String vertex_customer_code;
    String last_login_at;
}
