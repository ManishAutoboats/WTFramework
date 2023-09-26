package com.salmon.test.step_definitions.gui.iss;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.iss.IssBasketOverlayPage;
import com.salmon.test.page_objects.gui.iss.IssSubscriptionDetailsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class IssBasketOverlaySteps {
    private IssBasketOverlayPage issBasketOverlayPage;
    private IssSubscriptionDetailsPage issSubscriptionDetailsPage;
    public IssBasketOverlaySteps (IssBasketOverlayPage issBasketOverlayPage, IssSubscriptionDetailsPage issSubscriptionDetailsPage) {
        this.issBasketOverlayPage = issBasketOverlayPage;
        this.issSubscriptionDetailsPage = issSubscriptionDetailsPage;
    }

    @When("^he clicks on the CTA SET UP SUBSCRIPTION$")
    public void heClicksOnTheCTASETUPSUBSCRIPTION() {
        issBasketOverlayPage.clickSetUpSubscription();
    }

    @And("^he clicks on the send to customer button$")
    public void heClicksOnTheSendToCustomerButton() {
        issBasketOverlayPage.clickSendToCustomer();
    }

    @And("^closes the basket overlay$")
    public void closesTheBasketOverlay() {
        issBasketOverlayPage.closeBasketOverlay();
    }

    @Then("^a message \"([^\"]*)\" displayed instructing a minimum quantity of \"([^\"]*)\" is required for a subscription$")
    public void aMessageDisplayedInstructingAMinimumQuantityOfIsRequiredForASubscription(String displayed, String minQty) throws Throwable {
        boolean expectDisplayed;
        boolean actualDisplayed;
        if (displayed.equalsIgnoreCase("is")) {
            expectDisplayed = true;
        } else {
            expectDisplayed = false;
        }
        actualDisplayed = issBasketOverlayPage.minQtyMessageDisplayed(UrlBuilder.getMessage("minQty"));
        assertThat(actualDisplayed == expectDisplayed)
                .as("ERROR: expected message to be displayed was "+expectDisplayed+" but I got "+actualDisplayed).isTrue();
    }

    @And("^the set up subscription button is \"([^\"]*)\"$")
    public void theSetUpDescriptionButtonIs(String expectedButtonState) throws Throwable {
        assertThat(issBasketOverlayPage.validateSetUpSubscriptionButtonIs(expectedButtonState))
                .as("ERROR: expected button state to be "+expectedButtonState+" but it wasn't").isTrue();
    }

    @When("^he adds further items to a total of \"([^\"]*)\"$")
    public void heAddsFurtherItemsToATotalOf(String requiredTotal) throws Throwable {
        int targetTotal = Integer.parseInt(UrlBuilder.getMessage("minQty"));
        issBasketOverlayPage.addUntilQuantityReached(targetTotal);
    }

    @And("^he is able to proceed to the subscription details page$")
    public void heIsAbleToProceedToTheSubscriptionDetailsPage() {
        issBasketOverlayPage.clickSetUpSubscription();
        assertThat(issSubscriptionDetailsPage.isSubscriptionDetailsHeadingdisplayed())
                .as("ERROR: did not arrive at the subscription details page").isTrue();
    }
}
