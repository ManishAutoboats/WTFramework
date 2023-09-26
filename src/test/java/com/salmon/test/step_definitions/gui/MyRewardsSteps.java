package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MyRewardsSteps extends PageObject {

    private MyRewardsPage myRewardsPage;
    public MyRewardsSteps(MyRewardsPage myRewardsPage) {
        this.myRewardsPage = myRewardsPage;
    }

    @And("^assert default status for Loyalty is '(.*)'$")
    public void assertDefaultStatusForLoyaltyIsExplorerTermKey(String expectedText) {
        myRewardsPage.ValidateDefaultStatusIsExplorer(expectedText);
    }

    @And("^User Sign Up for Loyalty program$")
    public void userSignUpForLoyaltyProgram() throws InterruptedException {
        myRewardsPage.SignUpForLoyalty();
    }

    @And("^assert '(.*)' section is present$")
    public void assertClubVorteileTermKeySectionIsPresent(String expectedText) {
        assertThat(myRewardsPage.fetchClubVorteileHeaderText().contains(UrlBuilder.getMessage(expectedText)));
    }

    @And("^Link option is present with sections$")
    public void assertMainTermKeySectionsIsPresent(DataTable dataTable) {
        myRewardsPage.validateMainDashboardOptions(dataTable);
    }

    @And("^assert enter club button is display to click$")
    public void assertEnterClubButtonIsPresent() {
        assertTrue(myRewardsPage.validateEnterClubButton());
    }

    @And("^user switch into insider club frame$")
    public void switchIntoInsiderClubFrame() {
        myRewardsPage.switchToLoyaltyiFrame();
    }

    @And("^user can see insider club credit available$")
    public void assertInsiderCreditAvailable() {
        assertThat(myRewardsPage.validateInsiderClubInCheckout()).isEqualTo(Float.valueOf(UrlBuilder.getMessage("availableCoin.key")));
    }

    @And("^I can see credit balance '(.*)' is displayed in the dashboard$")
    public void assertInsiderCreditAmountIsAvailable(String creditAmount) {
        waitForAjaxElementNotToBePresent(webDriver,10);
        assertEquals(creditAmount, myRewardsPage.getAvailableCreditInDashboard());
    }

    @And("^user enter '(.*)' credit to use from insider club$")
    public void enterCreditToUse(String credit) {
        myRewardsPage.enterClubCredit(credit);
    }

    @And("^assert total order value '(.*)' is displayed$")
    public void verifyTotalInSummary(String value) {
        waitForAjaxElementNotToBePresent(webDriver,10);
        assertEquals(value, myRewardsPage.verifyTotalInSummary());
    }

    @And("^assert applied credit amount is deducted from item price$")
    public void assertDeductedAmount() {
        assertThat(myRewardsPage.verifyDeductedAmountOfPrice()).isEqualTo(myRewardsPage.verifyDeductAmountInSummary());
    }

    @And("^assert applied credit amount '(.*)' is deducted from item price in Cart$")
    public void assertDeductedAmountInCart(String amount) {
        myRewardsPage.verifyDeductAmountInCartSummary(amount);
    }

    @And("^assert user has paid for whole order$")
    public void assertDeductedWholeAmount() {
        assertThat(myRewardsPage.verifyDeductedWholeAmountOfPrice()).isEqualTo(0.0f);
    }

    @And("^assert user has paid for whole order in Cart$")
    public void assertDeductedWholeAmountInCart() {
        assertThat(waitForExpectedElement(myRewardsPage.TOTAL_IN_CART).getText().equals("€ 0,00"));
    }

    @And("^an error message will be shown$")
    public void assertErrorForSpendMoreCredit() {
        myRewardsPage.verifyErrorLoyaltyCredit();
    }

    @And("^delete insider club credit amount from summary$")
    public void deleteLoyaltyCreditAmount() {
        myRewardsPage.deleteDeductedCredit();
    }

    @And("^delete insider club credit amount from Cart summary$")
    public void deleteLoyaltyCreditAmountFromCart() {
        myRewardsPage.deleteDeductedCreditCart();
    }

    @And("^assert success message is displayed for removing credit in Cart$")
    public void verifySuccessMsgInCart() {
        myRewardsPage.verifySuccessMsgCartRemoveCredit();
    }

    @And("^user sent to Loyalty Insiders Club landing page$")
    public void verifyUserIsInInsiderClubPage() {
        waitForAjaxElementNotToBePresent(getWebDriver(),10);
        myRewardsPage.verifyInsiderClubPageLoaded();
    }

    @And("^user sent to Loyalty Insiders Club T&C page$")
    public void verifyUserIsInInsiderClubTCPage() {
        myRewardsPage.verifyInsiderClubTCPageLoaded();
    }

    @And("^assert '(.*)' section is available$")
    public void assertVipVorteileTermKeySectionIsPresent(String expectedText) {
        assertThat(myRewardsPage.fetchVipVorteileHeaderText().equals(UrlBuilder.getMessage(expectedText)));
    }

    @And("^click on 'Badges'$")
    public void clickOnBadges() {
        myRewardsPage.clickOnBadgesButton();
    }

    @Then("^assert '(.*)' label$")
    public void assertThatEpenEpodTileIsEnabled(String expectedText) {
        assertThat(myRewardsPage.fetchEpodEpenTileLabel().equals(UrlBuilder.getMessage(expectedText)));
    }

    @Then("^verify '(.*)' badges is enabled$")
    public void verifyBagesIsEnabled(String items) {
        switch (items) {
            case "2":
                assertEquals(myRewardsPage.fetch2ItemTileStatus(), "block");
                assertEquals(myRewardsPage.fetch2ItemTileText(), "PROBIERT");
                break;
            case "5":
                assertEquals(myRewardsPage.fetch5ItemTileStatus(), "block");
                assertEquals(myRewardsPage.fetch5ItemTileText(), "PROBIERT");
                break;
            default:
                break;
        }
    }

    @Then("^assert message for a free skin and free capsule is displayed$")
    public void assertMessageForFreeSkinAndCapsuleIsDisplayed() {
        assertTrue(myRewardsPage.waitForExpectedElement(myRewardsPage.FREE_ITEMS_BLOCK,10).isDisplayed());
    }

    @Then("^assert discount is applied against both free capsule and free skin items for '(.*)' user$")
    public void assertDiscountAppliedForFreeCapsuleGiftItemInDiscountSection(String strTier) {
        myRewardsPage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
        if(strTier.equalsIgnoreCase("Explorer")){
            assertTrue(waitForExpectedElement(myRewardsPage.DISCOUNTS_SUMMARY_SECTION).getText().contains(UrlBuilder.getMessage("loyaltyePenPromoCodeExplorer.key")));
            assertTrue(waitForExpectedElement(myRewardsPage.DISCOUNTS_SUMMARY_SECTION).getText().contains(UrlBuilder.getMessage("loyaltyePodPromoCode.key")));
        }
        else if(strTier.equalsIgnoreCase("Challenger") || strTier.equalsIgnoreCase("Innovator")){
            assertTrue(waitForExpectedElement(myRewardsPage.DISCOUNTS_SUMMARY_SECTION).getText().contains(UrlBuilder.getMessage("loyaltyePodPromoCode.key")));
            assertTrue(waitForExpectedElement(myRewardsPage.DISCOUNTS_SUMMARY_SECTION).getText().contains(UrlBuilder.getMessage("loyaltyePenPromoCodeCI.key")));
        }
        List<WebElement> lstDiscountPrices=myRewardsPage.getWebDriver().findElements(myRewardsPage.DISCOUNT_AMOUNT);
        Float intDiscount1Price = (Float.valueOf(lstDiscountPrices.get(0).getText().split(" ")[0].replace(",", ".")));
        Float intDiscount2Price = (Float.valueOf(lstDiscountPrices.get(1).getText().split(" ")[0].replace(",", ".")));
        assertTrue(Math.signum(intDiscount1Price) == -1 && Math.signum(intDiscount2Price) == -1);
    }

    @Then("^assert user has been moved to '(.*)' tier$")
    public void assertUserHasBeenMovedToSpecifiedTier(String customerTier) {
        myRewardsPage.waitForExpectedElement(myRewardsPage.CUSTOMER_TIER_STATUS,10);
        getWebDriver().switchTo().frame(getWebDriver().findElement(myRewardsPage.CUSTOMER_TIER_FRAME));
        WebElement eleLoyalityTier=myRewardsPage.getWebDriver().findElements(myRewardsPage.CUSTOMER_TIER_STATUS).get(0);
        waitForTextToBe(getWebDriver(),10,myRewardsPage.CUSTOMER_TIER_STATUS,customerTier);
        assertTrue(eleLoyalityTier.getText().contains(customerTier));
        getWebDriver().switchTo().defaultContent();
    }

    @And("^assert first voucher loyalty pop up is not displayed for non loyalty user$")
    public void assertFirstVoucherLoyaltyPopUpNotDisplayedForNonLoyaltyUser(){
        myRewardsPage.assertFirstVoucherLoyaltyPopUpNotDisplayedForNonLoyaltyUser();
    }

    @Then("^non loyalty user registers for Loyalty on selecting the checkbox and clicking Register Now$")
    public void nonLoyaltyUserRegistersForLoyaltyOnSelectingRequiredCheckboxAndClickingRegisterNow() {
        myRewardsPage.nonLoyaltyUserRegisterForLoyaltyOnSelectingRequiredCheckAndClickingRegisterNow();
    }

    @And("^assert first voucher loyalty pop up is displayed for loyalty user$")
    public void assertFirstVoucherLoyaltyPopUpDisplayedForLoyaltyUser(){
        myRewardsPage.assertFirstVoucherLoyaltyPopUpDisplayedForLoyaltyUser();
    }

    @And("^user clicks on discount code button from Loyalty pop-up and redirected to Loyalty dashboard$")
    public void userClicksOnDiscountCodeButtonFromLoyaltyPopUpAndRedirectedToLoyaltyDashboard(){
        myRewardsPage.waitAndClickByElementByJSExecutor(myRewardsPage.TO_DISCOUNT_CODE_POP_OUT_BUTTON,10);
    }
}
