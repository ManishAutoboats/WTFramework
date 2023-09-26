package com.salmon.test.models.api.salesorder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GiftMessage {
    int gift_message_id;
    int customer_id;
    String sender;
    String recipient;
    String message;
    ExtensionAttributes extension_attributes;
}
