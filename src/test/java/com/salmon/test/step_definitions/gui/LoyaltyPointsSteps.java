package com.salmon.test.step_definitions.gui;

import com.salmon.test.page_objects.gui.LoyaltyPointsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class LoyaltyPointsSteps {

    private LoyaltyPointsPage loyaltyPointsPage;

    public LoyaltyPointsSteps(LoyaltyPointsPage loyaltyPointsPage) {
        this.loyaltyPointsPage = loyaltyPointsPage;
    }

    @Then("^assert Loyalty points awarded for the order$")
    public void assertLoyaltyPointsAwardedForTheOrder() {
        loyaltyPointsPage.verifyLoyaltyPointsForOrder();
    }

    @Then("^user should be able to see the option to redeem the loyalty points$")
    public void userShouldBeAbleToSeeTheOptionToRedeemTheLoyaltyPoints() {
        loyaltyPointsPage.verifyLoyaltyPointsRedeemOptionInCheckoutPage();
    }

    @And("^assert Total Charges are updated after redeeming the loyalty points$")
    public void assertTotalChargesAreUpdatedAfterRedeemingTheLoyaltyPoints() {
        loyaltyPointsPage.verifyTotalPriceAfterRedeemingLoyaltyPoints();
    }

    @And("^click on loyalty points link$")
    public void clickOnLoyaltyPointsLink() {
        loyaltyPointsPage.clickLoyaltyPointsLink();
    }

    @Then("^verify loyalty points awarded for referring the friend$")
    public void verifyLoyaltyPointsAwardedForReferringTheFriend() {
        loyaltyPointsPage.verifyLoyaltyPointsAwardedForReferringAFriend();
        System.out.println();
    }
}
