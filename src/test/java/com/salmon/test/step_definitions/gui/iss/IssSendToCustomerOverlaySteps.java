package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.iss.IssHomePage;
import com.salmon.test.page_objects.gui.iss.IssSendToCustomerOverlayPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class IssSendToCustomerOverlaySteps {

    private IssSendToCustomerOverlayPage issSendToCustomerOverlayPage;
    private IssHomePage issHomePage;
    private ScenarioContext scenarioContext;
    public IssSendToCustomerOverlaySteps(IssSendToCustomerOverlayPage issSendToCustomerOverlayPage, IssHomePage issHomePage, ScenarioContext scenarioContext) {
        this.issSendToCustomerOverlayPage = issSendToCustomerOverlayPage;
        this.issHomePage = issHomePage;
        this.scenarioContext = scenarioContext;
    }

    @Then("^the send to customer overlay is opened$")
    public void theSendToCustomerOverlayIsOpened() {
        assertThat(issSendToCustomerOverlayPage.isSendToCustomerOverlayDisplayed())
                .as("ERROR: send to customer overlay page is not displayed").isTrue();
    }

    @And("^it has a consent with radio button$")
    public void itHasAConsentWithRadioButton() {
        assertThat(issSendToCustomerOverlayPage.isConsentWithRadioDisplayed())
                .as("ERROR: consent with radio button not displayed").isTrue();
    }

    @And("^an explanation why the address is being collected$")
    public void anExplanationWhyTheAddressIsBeingCollected() {
        assertThat(issSendToCustomerOverlayPage.isConsentTextDisplayed())
                .as("ERROR: consent text is not displayed").isTrue();
    }

    @And("^an email address field is displayed$")
    public void anEmailAddressFieldIsDisplayed() {
        assertThat(issSendToCustomerOverlayPage.isEmailAddressTextFieldDisplayed())
                .as("ERROR: email address field not displayed").isTrue();
    }

    @And("^a submit button which should be disabled initially$")
    public void aSubmitButtonWhichShouldBeDisabledInitially() {
        assertThat(issSendToCustomerOverlayPage.isSubmitButtonDisplayed())
                .as("ERROR: submit button is not displayed").isTrue();
        assertThat(issSendToCustomerOverlayPage.IsSubmitButtonDisabled())
                .as("ERROR: submit button is not disabled").isTrue();
    }

    @Then("^the colleague selects the consent radio button$")
    public void theColleagueSelectsTheConsentRadioButton() {
        issSendToCustomerOverlayPage.clickconsentRadioButton();
    }

    @And("^enters the email address of the customer$")
    public void entersTheEmailAddressOfTheCustomer() {
        issSendToCustomerOverlayPage.enterEmailAddress(UrlBuilder.getMessage("issCustomer"));
    }

    @Then("^the submit CTA gets enabled$")
    public void theSubmitCTAGetsEnabled() {
        assertThat(issSendToCustomerOverlayPage.submitCtaIsEnabled())
                .as("ERROR: submit CTA is not enabled").isTrue();
    }

    @And("^colleague clicks on the submit CTA$")
    public void colleageClicksOnTheSubmitCTA() {
        issSendToCustomerOverlayPage.clickSubmitButton();
    }

    @Then("^a success message is reported$")
    public void aSuccessMessageIsReported() {
        assertThat(issSendToCustomerOverlayPage.isSuccessFeedbackGiven())
                .as("ERROR: success message not displayed").isTrue();
    }

    @And("^a CTA finish subscription is displayed$")
    public void aCTAFinishSubscriptionIsDisplayed() {
        assertThat(issSendToCustomerOverlayPage.isFinishSubscriptionButtonDisplayed())
                .as("ERROR: finish subscription button not diplayed").isTrue();
    }

    @When("^he clicks on the finish subscription CTA$")
    public void heClicksOnTheFinishSubscriptionCTA() {
        issSendToCustomerOverlayPage.clickFinishSubscription();
    }

    @Then("^he should navigate back to the challenge (\\d+) screen$")
    public void heShouldNavigateBackToTheChallengeScreen(int arg0) {
        assertThat(issHomePage.isOnChallengeScreen())
                .as("ERROR: did not return to challenge screen").isTrue();
    }

    @Then("^they receive an error message explaining email is required$")
    public void theyReceiveAnErrorMessageExplainingEmailIsRequired() {
        assertThat(issSendToCustomerOverlayPage.isEmailErrorReceived())
                .as("ERROR: incorrect error response when no email supplied").isTrue();
    }

    @And("^enters an invalid email address$")
    public void entersAnInvalidEmailAddress() {
        issSendToCustomerOverlayPage.enterEmailAddress(UrlBuilder.getMessage("loginInvalidEmail.key"));
    }


    @And("^enters the email address of the new customer$")
    public void entersTheEmailAddressOfTheNewCustomer() {
        issSendToCustomerOverlayPage.enterEmailAddress(scenarioContext.getContext(Context.NEW_USER_EMAIL_ID).toString());
    }

    @And("^a success message is returned$")
    public void aSuccessMessageIsReturned() {
        assertThat(issSendToCustomerOverlayPage.isEmailSuccessReceived())
                .as("ERROR: incorrect success response when valid email supplied").isTrue();
    }

}
