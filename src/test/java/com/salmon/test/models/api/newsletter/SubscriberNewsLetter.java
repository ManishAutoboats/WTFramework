package com.salmon.test.models.api.newsletter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriberNewsLetter {
    Subscriber subscriber;

    public SubscriberNewsLetter(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Subscriber {
        int subscriber_id;
        String email;
        int status;
        String first_name;
        String last_name;
        int store_my_data;
        int email_marketing;
        int mob_marketing;
        String event_timestamp;
        int customer_id;
        int guest_details_id;
        int is_modi_consumer;
        String dob;
        String telephone;
        String source;
    }
}
