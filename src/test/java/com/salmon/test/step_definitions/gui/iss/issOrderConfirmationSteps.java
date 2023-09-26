package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.admin.CustomerPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.iss.IssOrderConfirmationPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class issOrderConfirmationSteps {

    private IssOrderConfirmationPage issOrderConfirmationPage;
    private CustomerPage customerPage;
    private ScenarioContext scenarioContext;

    public issOrderConfirmationSteps(IssOrderConfirmationPage issOrderConfirmationPage, CustomerPage customerPage, ScenarioContext scenarioContext) {
        this.issOrderConfirmationPage = issOrderConfirmationPage;
        this.customerPage = customerPage;
        this.scenarioContext = scenarioContext;
    }

    @And("^that future payments will be done automatically is displayed$")
    public void thatFuturePaymentsWillBeDoneAutomaticallyIsDisplayed() {
        assertThat(issOrderConfirmationPage.futurePaymentsDisplayed())
                .as("ERROR: future payments message not displayed on order confirmation page").isTrue();
    }

    @And("^the order completion page informs that the first payment has been completed$")
    public void theOrderCompletionPageInformsThatTheFirstPaymentHasBeenCompleted() {
        assertThat(issOrderConfirmationPage.firstPaymentMessageDisplayed())
                .as("ERROR: first payment message not displayed").isTrue();
    }

    @And("^that an order confirmation email has been sent$")
    public void thatAnOrderConfirmationEmailHasBeenSent() {
        assertThat(issOrderConfirmationPage.orderConfirmationEmailSentDisplayed())
                .as("ERROR: email confirmation message not displayed on order confirmation page").isTrue();
    }

    @And("^the go to my account CTA is displayed$")
    public void theGoToMyAccountCTAIsDisplayed() {
        assertThat(issOrderConfirmationPage.goToMyAccountButtonDisplayed())
                .as("ERROR: Go to my account CTA not displayed").isTrue();
    }

    @And("^the return to home CTA is displayed$")
    public void theReturnToHomeCTAIsDisplayed() {
        assertThat(issOrderConfirmationPage.returnToHomeLinkDisplayed())
                .as("ERROR: Return to home link not displayed").isTrue();
    }

    @When("^the customer clicks on go to my account CTA$")
    public void theCustomerClicksOnGoToMyAccountCTA() {
        issOrderConfirmationPage.clickOnGoToMyAccount();
    }

    @Then("^he navigates to the My Account Page$")
    public void heNavigatesToTheMyAccountPage() {
        
    }

    @When("^the customer clicks on the Return to Home CTA$")
    public void theCustomerClicksOnTheReturnToHomeCTA() {
        issOrderConfirmationPage.clickOnReturnToHome();
    }

    @Then("^he navigates to the home page$")
    public void heNavigatesToTheHomePage() {
        assertThat(issOrderConfirmationPage.isOnHomePage())
                .as("ERROR: did not return to home page").isTrue();
    }

    @And("^ensure that in admin age verified is set to \"([^\"]*)\"$")
    public void ensureThatInAdminAgeVerifiedIsSetTo(boolean ageVerified) throws Throwable {
        customerPage.loginMagentoAdminAndSetAgeVerified(ageVerified, scenarioContext.getContext(Context.NEW_USER_EMAIL_ID).toString());
    }

    @And("^ensure that in admin age verified is set to \"([^\"]*)\" for user \"([^\"]*)\"$")
    public void ensureThatInAdminAgeVerifiedIsSetToForUser(boolean ageVerified, String user) throws Throwable {
        customerPage.loginMagentoAdminAndSetAgeVerified(ageVerified, UrlBuilder.getMessage(user));
    }
}
