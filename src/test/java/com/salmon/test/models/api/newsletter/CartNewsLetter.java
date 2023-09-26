package com.salmon.test.models.api.newsletter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartNewsLetter {
    NewsletterSubscription newsletterSubscription;

    public CartNewsLetter(NewsletterSubscription newsletterSubscription) {
        this.newsletterSubscription = newsletterSubscription;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class NewsletterSubscription {
        String subscribe;
    }
}
