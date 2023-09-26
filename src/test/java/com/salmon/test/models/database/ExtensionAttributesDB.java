package com.salmon.test.models.database;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExtensionAttributesDB {
    int data_handling_consent;
    int market_research_consent;
    int email_consent;
    int telephone_consent;
    int email_optout;
    int telephone_optout;
    int initial_load;
    String company;
    /*TODO: except subscriber_status all other fields may not be correct.
    *  Need to check*/
    String subscriber_status;
    String amazon_id;
    String vertex_customer_code;
}
