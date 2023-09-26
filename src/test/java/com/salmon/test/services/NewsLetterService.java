package com.salmon.test.services;

import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.enums.ServiceEndPoints;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.BatDBHelper;
import com.salmon.test.models.api.newsletter.CartNewsLetter;
import com.salmon.test.models.api.newsletter.Subscriber;
import com.salmon.test.models.api.newsletter.SubscriberNewsLetter;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class NewsLetterService extends ApiHelper {

    private BatDBHelper batDBHelper;

    public NewsLetterService(BatDBHelper batDBHelper) {
        this.batDBHelper = batDBHelper;
    }

    public Response getGuestUserDetails(String subscriberId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GUEST_NEWS_LETTER.getUrl(), subscriberId);
    }

    public Response getGuestUserDetailsWithOutAuthToken(String subscriberId) {
        return givenConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.GUEST_NEWS_LETTER.getUrl(), subscriberId);
    }

    public Response getSubscriberUserDetails(String subscriberId) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.SUBSCRIBER_NEWS_LETTER.getUrl(), subscriberId);
    }

    public Response getSubscriberUserDetailsWithOutAuthToken(String subscriberId) {
        return givenConfig()
                .when()
                .log().all()
                .get(ServiceEndPoints.SUBSCRIBER_NEWS_LETTER.getUrl(), subscriberId);
    }

    public Response updateSubscriberUserDetails(String subscriberId, SubscriberNewsLetter.Subscriber userDetails) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(gson().toJson(new SubscriberNewsLetter(userDetails)))
                .put(ServiceEndPoints.SUBSCRIBER_NEWS_LETTER.getUrl(), subscriberId);
    }

    public Response updateGuestCartNewsLetterSubscription(String cartId, CartNewsLetter.NewsletterSubscription newsletterSubscription) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(gson().toJson(new CartNewsLetter(newsletterSubscription)))
                .put(ServiceEndPoints.CART_GUEST_NEWS_LETTER.getUrl(), cartId);
    }

    public Response updateSubscriberCartNewsLetterSubscription(CartNewsLetter.NewsletterSubscription newsletterSubscription) {
        return givenBatApiConfig()
                .when()
                .log().all()
                .body(gson().toJson(new CartNewsLetter(newsletterSubscription)))
                .put(ServiceEndPoints.CART_SUBSCRIBER_NEWS_LETTER.getUrl());
    }

    public List<Subscriber> getSubscriberDetails(String subscriberId) {
        return  batDBHelper.executeQueryToObject(Subscriber.class,
                DatabaseQueries.GET_SUBSCRIBER_BY_ID.getUrl().replace("?", subscriberId)); }

    public List<Subscriber> getRegisteredSubscriber(String subscriberId) {
        return  batDBHelper.executeQueryToObject(Subscriber.class,
                DatabaseQueries.GET_Registered_SUBSCRIBER_BY_ID.getUrl().replace("?", subscriberId)); }

    public List<Subscriber> getMinSubscriber() {
        return  batDBHelper.executeQueryToObject(Subscriber.class,
                DatabaseQueries.GET_MIN_SUBSCRIBER_ID.getUrl()); }

}
