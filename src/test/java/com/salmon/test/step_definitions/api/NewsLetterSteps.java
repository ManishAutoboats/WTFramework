package com.salmon.test.step_definitions.api;

import com.salmon.test.enums.DatabaseQueries;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.api.newsletter.CartNewsLetter;
import com.salmon.test.models.api.newsletter.GuestNewsLetter;
import com.salmon.test.models.api.newsletter.Subscriber;
import com.salmon.test.models.api.newsletter.SubscriberNewsLetter;
import com.salmon.test.services.NewsLetterService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

public class NewsLetterSteps {
    private static final Logger LOG = LoggerFactory.getLogger(NewsLetterSteps.class);
    private NewsLetterService newsLetterService;
    private Response response;
    private String subscriber;
    private String customerID;

    public NewsLetterSteps(NewsLetterService newsLetterService) {
        this.newsLetterService = newsLetterService;
    }

    @When("^I get guest details for news letter with the subscriber id (.*)$")
    public void i_get_guest_details_for_news_letter_with_the_subscriber_id(String subscriberId) {
        subscriber = subscriberId;
        response = newsLetterService.getGuestUserDetails(subscriberId);
    }

    @Then("^News letter response status code is (.*)$")
    public void news_letter_response_status_code_is(int status) {
        assertThat(response.getStatusCode()).isEqualTo(status);
    }

    @Then("^Guest news letter response should contain (valid|invalid) user details$")
    public void guest_news_letter_response_should_contain_user_details(String type) {
//        Expected subscriber details for the given subscriber Id
        List<Subscriber> expectedSubscriberList = newsLetterService.getSubscriberDetails(subscriber);
//        Actual subscriber details for the given subscriber Id
        GuestNewsLetter guestNewsLetter = ApiHelper.convertResponseToPojo(response, GuestNewsLetter.class);
        if ("valid".equals(type)) {
            assertThat(guestNewsLetter.getSubscriber_id()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_id).findFirst().orElse(0));
            assertThat(guestNewsLetter.getFirstname()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getFirstname).findFirst().orElse(""));
            assertThat(guestNewsLetter.getLastname()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getLastname).findFirst().orElse(""));
        } else if ("invalid".equals(type)) {
            assertThat(guestNewsLetter.getFirstname()).isNull();
            assertThat(guestNewsLetter.getLastname()).isNull();
        }
    }

    @Then("^Subscriber news letter response should contain (valid|invalid) user details$")
    public void subscriber_news_letter_response_should_contain_user_details(String type) {
//        Expected subscriber details for the given subscriber Id
        List<Subscriber> expectedSubscriberList =  newsLetterService.getSubscriberDetails(subscriber);
//        Actual subscriber details for the given subscriber Id
        SubscriberNewsLetter.Subscriber actualSubscriber = ApiHelper.convertResponseToPojo(response, SubscriberNewsLetter.Subscriber.class);
        if("valid".equals(type)) {
            assertThat(actualSubscriber.getFirst_name()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getFirstname).findFirst().orElse(""));
            assertThat(actualSubscriber.getLast_name()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getLastname).findFirst().orElse(""));
            assertThat(actualSubscriber.getEmail()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_email).findFirst().orElse(""));
            assertThat(actualSubscriber.getSubscriber_id()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_id).findFirst().orElse(0));
        } else if ("invalid".equals(type)) {
            assertThat(actualSubscriber.getFirst_name()).isNull();
            assertThat(actualSubscriber.getLast_name()).isNull();
        }
    }

    @Then("^Registered subscriber news letter response should contain (valid|invalid) user details$")
    public void registered_subscriber_news_letter_response_should_contain_user_details(String type) {
//        Expected subscriber details for the given subscriber Id
        List<Subscriber> expectedSubscriberList =  newsLetterService.getRegisteredSubscriber(customerID);
//        Actual subscriber details for the given subscriber Id
        SubscriberNewsLetter.Subscriber actualSubscriber = ApiHelper.convertResponseToPojo(response, SubscriberNewsLetter.Subscriber.class);
        if("valid".equals(type)) {
            assertThat(actualSubscriber.getFirst_name()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getFirstname).findFirst().orElse(""));
            assertThat(actualSubscriber.getLast_name()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getLastname).findFirst().orElse(""));
            assertThat(actualSubscriber.getEmail()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_email).findFirst().orElse(""));
            assertThat(actualSubscriber.getSubscriber_id()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_id).findFirst().orElse(0));
            assertThat(actualSubscriber.getStatus()).isEqualTo(expectedSubscriberList.stream().map(Subscriber::getSubscriber_status).findFirst().orElse(0));
        } else if ("invalid".equals(type)) {
            assertThat(actualSubscriber.getFirst_name()).isNull();
            assertThat(actualSubscriber.getLast_name()).isNull();
        }
    }

    @When("^I get guest details for news letter with out auth token for the subscriber id (.*)$")
    public void iGetGuestDetailsForNewsLetterWithOutAuthTokenForTheGuestId(String subscriberId) {
        response = newsLetterService.getGuestUserDetailsWithOutAuthToken(subscriberId);
    }

    @When("^I get subscriber details for news letter with out auth token for the subscriber id (.*)$")
    public void iGetSubscriberDetailsForNewsLetterWithOutAuthTokenForTheGuestId(String subscriberId) {
        response = newsLetterService.getSubscriberUserDetailsWithOutAuthToken(subscriberId);
    }

    @And("^Error message is \"(.*)\"$")
    public void errorMessageIs(String errorMsg) {
        errorMsg= Props.getLocalMessage(errorMsg);
        assertThat(from(response.asString()).get("message").toString()).isEqualTo(errorMsg);
    }

    @When("^I get subscriber details for news letter with the subscriber id (.*)$")
    public void i_get_subscriber_details_for_news_letter_with_the_subscriber_id(String subscriberId) {
        subscriber = subscriberId;
        response = newsLetterService.getSubscriberUserDetails(subscriberId);
        response.prettyPrint();
    }

    @When("^I get subscriber details for news letter with the existing subscriber id$")
    public void i_get_subscriber_details_for_news_letter_with_the_existing_subscriber_id() {
        List<Subscriber> subscriberList =  newsLetterService.getMinSubscriber();
        subscriber = subscriberList.stream().map(Subscriber::getSubscriber_id).findFirst().orElse(0).toString();
        customerID = subscriberList.stream().map(Subscriber::getCustomer_id).findFirst().orElse(0).toString();
        response = newsLetterService.getSubscriberUserDetails(subscriber);
        response.prettyPrint();
    }

    @When("^I update subscriber details for news letter with the subscriber id (.*)$")
    public void i_update_subscriber_details_for_news_letter_with_the_subscriber_id(String subscriberId, List<SubscriberNewsLetter.Subscriber> userDetails) throws Exception {
        subscriber = subscriberId;
        response = newsLetterService.updateSubscriberUserDetails(subscriberId, userDetails.stream().findFirst().orElseThrow(() -> new Exception("Subscriber details not found!")));
    }

    @When("^I update subscriber details for news letter with the existing subscriber id$")
    public void i_update_subscriber_details_for_news_letter_with_the_subscriber_id(List<SubscriberNewsLetter.Subscriber> userDetails) throws Exception {
        List<Subscriber> subscriberList =  newsLetterService.getMinSubscriber();
        subscriber = subscriberList.stream().map(Subscriber::getSubscriber_id).findFirst().orElse(0).toString();
        customerID = subscriberList.stream().map(Subscriber::getCustomer_id).findFirst().orElse(0).toString();
        response = newsLetterService.updateSubscriberUserDetails(subscriber, userDetails.stream().findFirst().orElseThrow(() -> new Exception("Subscriber details not found!")));
        response.prettyPrint();
    }

    @When("^I update cart guest news letter with the cart id (.*)$")
    public void iUpdateCartGuestNewsLetterWithTheCartIdCartId(String cartId, List<CartNewsLetter.NewsletterSubscription> subscriptionList) throws Exception {
        response = newsLetterService.updateGuestCartNewsLetterSubscription(cartId, subscriptionList.stream().findFirst().orElseThrow(() -> new Exception("Cart guest details not found!")));
    }

    @When("^I update cart subscriber news letter subscription$")
    public void iUpdateCartSubscriberNewsLetterSubscription(List<CartNewsLetter.NewsletterSubscription> subscriptionList) throws Exception {
        response = newsLetterService.updateSubscriberCartNewsLetterSubscription(subscriptionList.stream().findFirst().orElseThrow(() -> new Exception("Cart Subscriber details not found!")));
    }
}
