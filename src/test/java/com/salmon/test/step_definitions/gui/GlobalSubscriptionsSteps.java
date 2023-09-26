package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.page_objects.gui.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.eclipse.jetty.client.AuthenticationProtocolHandler.LOG;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class GlobalSubscriptionsSteps {

    private PDP pdp;
    private HomePage homepage;
    BATHelper batHelper;
    private SearchResult searchResult;
    private PaymentPage paymentPage;
    private RegistrationPage registrationPage;
    private GlobalSubscriptionsPage globalSubscriptionsPage;
    private boolean userBuyingNonSubItems = false;

    public GlobalSubscriptionsSteps(PDP pdp, HomePage homepage, BATHelper batHelper, SearchResult searchResult, PaymentPage paymentPage, RegistrationPage registrationPage,
                                    GlobalSubscriptionsPage globalSubscriptionsPage) {
        this.pdp = pdp;
        this.homepage=homepage;
        this.batHelper=batHelper;
        this.searchResult=searchResult;
        this.paymentPage=paymentPage;
        this.registrationPage=registrationPage;
        this.globalSubscriptionsPage=globalSubscriptionsPage;
    }

    @And("^user adds subscription item '(.*)' to the basket by quantity '(.*)'$")
    public void userAddsSubscriptionItemSubscriptionItemTermKeyToTheBasket(String searchTerm, String qty) throws InterruptedException {
        pdp.performSearch(searchTerm);
        //if(UrlBuilder.isMobile()){
        //globalSubscriptionsPage.waitForURLToContain("catalogsearch/result");}
        if(globalSubscriptionsPage.doesURLContain("catalogsearch/result"))
            searchResult.selectSearchResult();
        globalSubscriptionsPage.clickGlobalSubsOption();
        pdp.selectProductStrengthOrColour();
        homepage.enterItemQuantityPDP(qty);
        if(!UrlBuilder.getLocale().equals("vusefr")||((UrlBuilder.getLocale().equals("vusefr")&&!SessionInfo.scenarioName.contains("Cart limits display error message for Max quantity")))){
            pdp.clickAddToCartButton();
        }
    }

    @And("^user navigates to '(.*)' and changes qty to '(.*)'$")
    public void userNavigatesToAndChangesQTYTo(String searchTerm, String qty) throws InterruptedException {
        pdp.performSearch(searchTerm);
        //if(UrlBuilder.isMobile()){
            //globalSubscriptionsPage.waitForURLToContain("catalogsearch/result");}
        if(globalSubscriptionsPage.doesURLContain("catalogsearch/result"))
            searchResult.selectSearchResult();
        globalSubscriptionsPage.clickGlobalSubsOption();
        pdp.selectProductStrengthOrColour();
        homepage.enterItemQuantityPDP(qty);
    }



    @And("^Credit or Debit Cards \\(SubscribePro\\) option is chosen$")
    public void creditOrDebitCardsSubscribeProOptionIsChosen() {
        globalSubscriptionsPage.selectGlobalSubPaymentOptions();
    }

    @And("^Checkout is selected with appropriate card details entered '(.*)'$")
    public void checkoutIsSelectedWithAppropriateCardDetailsEntered(String cardNo) throws Exception {
        globalSubscriptionsPage.enterValidMasterCardDetailsAndSubmitSubscribePro(cardNo);
    }

    @Then("^'(.*)' function is working as intended \\(Native\\)$")
    public void pauseFunctionIsWorkingAsIntendedNative(String cta) throws InterruptedException{
        globalSubscriptionsPage.myAccountSubscriptionFunctionalityNativeVer(cta);
    }

    @Then("^User is able to update address for a subscription \\(Native\\)$")
    public void userIsAbleToUpdateAddressForASubscriptionNative() throws InterruptedException {
        globalSubscriptionsPage.updateAddressGlobalSubsNativeVer();
    }

    @Then("^assert Subscribe and Save Option on PDP page$")
    public void assertSubscribeAndSaveOptionOnPDPPage() {
        switch (UrlBuilder.getLocale()) {
            case "lyftse":
                assertTrue(pdp.waitForExpectedElement(globalSubscriptionsPage.GLOBAL_SUBS_OPTION_PDPSE, 5).isDisplayed());
                break;
            case "fr":
            case "vusefr":
            case "vusede":
                pdp.waitForPresenceOfElement(globalSubscriptionsPage.GLOBAL_SUBS_OPTION_PDPVUSE);
                assertTrue(pdp.isElementDisplayedBySeconds(globalSubscriptionsPage.GLOBAL_SUBS_OPTION_PDPVUSE,10));
                break;
        }
    }

    @When("^Subscribe and save Option is selected$")
    public void subscribeAndSaveOptionIsSelected() {
        globalSubscriptionsPage.clickGlobalSubsOption();
    }

    @And("^Interval is selected for subscription product$")
    public void intervalIsSelectedForSubscriptionProduct() {
        globalSubscriptionsPage.selectIntervalFromDropDownGlobalSubs(1);
    }

    @And("^User adds '(.*)' to the basket$")
    public void userAddsSearchTermKeyToTheBasket(String searchTerm) throws InterruptedException {
        batHelper.searchAddAProductToBasket(searchTerm);
    }

    @Then("^'(.*)' Message is displayed in PDP$")
    public void errorMessageIsDisplayedInPDP(String errorMessageKey) {
        String errorMessage = UrlBuilder.getMessage(errorMessageKey).replaceAll("[^A-Za-z0-9]","");
        globalSubscriptionsPage.globalSubsErrorAssert(errorMessage);
    }

    private void dateCalculator(ArrayList<String> totalTitlesArrayList){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,1);
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
        String estimatedDate = UrlBuilder.getMessage("SubtotalExpectedDeliveryDate.key")+timeStamp;
        totalTitlesArrayList.add(estimatedDate);
    }

    @Then("^cart has appropriate '(.*)' applied to cart$")
    public void cartHasAppropriateDiscountAppliedToCart(String discount) throws InterruptedException {
        String totalTitles = UrlBuilder.getMessage("SubsTotalsCartPage.key");
        String[] totalTitlesList = totalTitles.split(",");
        ArrayList<String> totalTitlesArrayList = new ArrayList<>(Arrays.asList(totalTitlesList));
        switch (UrlBuilder.getLocale()){
            case "vusede":
                dateCalculator(totalTitlesArrayList);
                if(discount.equals("no discount")){
                    totalTitlesArrayList.add(UrlBuilder.getMessage("SubTotalShipping.key"));
                }else{
                    totalTitlesArrayList.add(UrlBuilder.getMessage("SubTotalFreeShipping.key"));
                }
                break;
            case "fr":
            case "vusefr":
//                if(discount.equals("no discount")){//Remove if there is no ongoing promotion
//                    totalTitlesArrayList.add(UrlBuilder.getMessage("PromoDiscouont.key"));
//                }
                dateCalculator(totalTitlesArrayList);
                break;
            case "vuseco":
            case "vypeit":
            case "vuseit":
            case "vuseza":
                dateCalculator(totalTitlesArrayList);
                break;
        }
        if(!discount.equalsIgnoreCase("no discount")){
            String capDiscount = discount.substring(0, 1).toUpperCase() + discount.substring(1);
            if (discount.equalsIgnoreCase("accessory")) {
                totalTitlesArrayList.add(UrlBuilder.getMessage(capDiscount + "DiscountTitle.key"));
                totalTitlesArrayList.add(UrlBuilder.getMessage("BronzeDiscountTitle.key"));
            } else if (UrlBuilder.getLocale().equalsIgnoreCase("vusefr")) {
                //The account has been removed since RT83.
//                totalTitlesArrayList.add(UrlBuilder.getMessage(capDiscount + "DiscountTitleFR.key"));
                totalTitlesArrayList.add(UrlBuilder.getMessage(capDiscount + "DiscountTitle.key"));
            } else {
                totalTitlesArrayList.add(UrlBuilder.getMessage(capDiscount + "DiscountTitle.key"));
            }
        }
        globalSubscriptionsPage.verifyBasketSectionWithItemDetailsTableContent(totalTitlesArrayList);
    }

    @Then("^cart has the correct discount when appropriate quantity is added to cart$")
    public void cartHasTheCorrectDiscountWhenAppropriateQuantityIsAddedToCart(DataTable table) throws Throwable{
        List<List<String>> cartItems = table.raw();
        int totalItemQty = 0;
        for (List<String> cartItem : cartItems) {
            totalItemQty = totalItemQty + Integer.parseInt(cartItem.get(0));
            pdp.performSearch("SubscriptionItemTerm.key");
            if (globalSubscriptionsPage.doesURLContain("catalogsearch/result")) {
                searchResult.selectSearchResult();
            }
            globalSubscriptionsPage.clickGlobalSubsOption();
            pdp.selectProductStrengthOrColour();
            homepage.enterItemQuantityPDP(cartItem.get(0));
            pdp.clickAddToCartButton();
            homepage.confirmMiniBasketDisplayedAmountOf(String.valueOf(totalItemQty));
            if (UrlBuilder.isDesktop()) {
                homepage.clickOnBasketIcon();
            }
            homepage.usersClicksOnTheLogoutTextLink("viewBasketText.key");
            LOG.info(cartItem.get(1) + " Discount being checked");
            cartHasAppropriateDiscountAppliedToCart(cartItem.get(1));
        }
    }

    @And("^user selects to buy items as non subscription items$")
    public void userSelectsToBuyItemsAsNonSubscriptionItems() {
        userBuyingNonSubItems = true;
        homepage.selectOrderNonSubsCTA();
    }


    @Then("^Subscription item has banner in Cart page$")
    public void subscriptionItemHasBannerInCartPage() {
        switch (UrlBuilder.getLocale()) {
            case "fr":
            case "vusefr":
            case "vuseco":
            case "vuseza":
                break;
            case "lyftse":
                assertTrue(homepage.waitForExpectedElement(globalSubscriptionsPage.GLOBAL_SUBS_CART_ITEM_BANNERSE).isDisplayed());
                break;
        }
    }

    @Then("^Subscription item has chosen interval period$")
    public void subscriptionItemHasChosenIntervalPeriod() {
        globalSubscriptionsPage.assertIntervalMatchesGlobalSubs("Every 2 Months");
    }

    @Then("^Subscription Banner is no longer displayed$")
    public void subscriptionBannerIsNoLongerDisplayed() {
        assertTrue(homepage.invisibilityOfElementLocated(globalSubscriptionsPage.GLOBAL_SUBS_CART_ITEM_BANNER));
    }

    @When("^user clicks on Immediate Purchase option for product$")
    public void userClicksOnImmediatePurchaseOptionForProduct() {
        globalSubscriptionsPage.selectImmediatePurchaseOptionCart();
    }

    @When("^user click on subscribe and save option for product$")
    public void userClickOnSubscribeAndSaveOptionForProduct() {
        globalSubscriptionsPage.selectSubscribeAndSaveOptionCart();
    }

    @And("^user adds accessory to cart through add accessory CTA$")
    public void userAddsAccessoryToCartThroughAddAccessoryCTA() {
        globalSubscriptionsPage.addSubsAccessoryViaCart();
    }

    @And("^user (increases|decreases) the quantity of the selected item by '(\\d+)'$")
    public void userIncreasesTheQuantityOfTheSelectedItemBy(String incdec, int qty) throws Throwable{
        if(incdec.contains("increase")){
            globalSubscriptionsPage.increaseQuantityOfItemInCart(qty);
        }else{
            globalSubscriptionsPage.decreaseQuantityOfItemInCart(qty);
        }
    }

    @Then("^error message '(.*)' is displayed to user after add to cart$")
    public void errorMessageIsDisplayedToUser(String errorMessage) {
        globalSubscriptionsPage.checkErrorMessage(errorMessage);
    }

    @Then("^customer clicks the cancel button against a subscription$")
    public void userClickOnCancelSubscription() {
        globalSubscriptionsPage.clickCancelSubscription();
    }


    @Then("^display the cancellation survey$")
    public void verifyCancellationSurveyDisplay() {
        globalSubscriptionsPage.verifyCancellationSurveyDisplay();
    }

    @And("^display choice of reason drop down$")
    public void assertDropDown() {
        globalSubscriptionsPage.checkDropown();
    }

    @And("^do not also display a free text comment box on the survey$")
    public void assertTextAreaNotDisplayed() {
        globalSubscriptionsPage.checkTextAreaNotDisplayed();
    }
    @And("^user click on one time purchase$")
    public void userClickOnOneTimePurchase() {
        globalSubscriptionsPage.clickOneTimePurchase();
    }

    @And("^user click on subscribe all$")
    public void userclickonSubscribeAll() {
        globalSubscriptionsPage.clickSubscribeAll();
    }

    @And("^two options display$")
    public void userTwoOptions() {
        globalSubscriptionsPage.viewSubsOption();
    }

    @And("^select make all one time purchase$")
    public void userclickallonetimepurchase() {
        globalSubscriptionsPage.clickAllOneTimePurchase();
    }

    @And("^subscription product change to one time purchase$")
    public void checkProductChangeToOneTimePurchase() {
        globalSubscriptionsPage.viewOneTimePurchaseSelected();
    }

    @And("^select checkout subscription first$")
    public void userSelectCheckoutSubscriptionFirst() {
        globalSubscriptionsPage.clickSubscriptioFfirst();
    }

    @And("^verify only subscription product is added for payment$")
    public void userVerifyOnlySubscriptionProductIsAddedForPayment() {
        globalSubscriptionsPage.assertSubscriptionProductAdded();
    }

    @And("^verify subscription message is displayed$")
    public void userVerifySubscriptionMessageIsDisplayed() {
        globalSubscriptionsPage.assertSubMessageDisplayed();
    }

    @And("^verify subscription message is not displayed$")
    public void userVerifySubscriptionMessageIsNotDisplayed() {
        globalSubscriptionsPage.assertSubMessageNotDisplayed();
    }

    @And("^toggle to one time product to subscription$")
    public void userToggleToOneTimeProductToSubscription() {
        globalSubscriptionsPage.toggleToSubscription();
    }


    @Then("^min or max subscription message is displayed$")
    public void minOrMaxSubscriptionMessageIsDisplayed() {
        assertTrue(globalSubscriptionsPage.isSubMessagePresent());
    }

    @Then("^Subscription text of packages are displayed on page$")
    public void SubscriptionPageIsDisplayed() {
        pdp.verifySubscriptionPage();
    }

    @Then("^information icon beside the price on the subscription toggle bar is displayed$")
    public void informationIconBesideThePriceOnTheSubscriptionToggleBarIsDisplayed() {
        assertTrue(globalSubscriptionsPage.isIIconPresent());
    }

    @When("^user checks the current tier of latest subscription$")
    public void userChecksTheCurrentTierOfLatestSubscription() {
        globalSubscriptionsPage.setCurrentTier();
    }

    @And("^tier discount has been (promoted|demoted)$")
    public void tierDiscountHasBeenPromoted(String modifier) throws InterruptedException{
        globalSubscriptionsPage.getUpdatedSubscriptionTier(modifier);
    }

    @And("^User can add new card to be used as a subscription$")
    public void userCanAddNewCardToBeUsedAsASubscription() throws InterruptedException{
        globalSubscriptionsPage.addCardFromSubscriptionPage();
    }

    @Then("^user cancels the active subscription$")
    public void userCancelsTheActiveSubscription() {
        globalSubscriptionsPage.clickCancleSubscriptionArrowIcon();
        globalSubscriptionsPage.clickStopSubscriptionButton();
    }

    @Then("^restart button isn't displayed$")
    public void restartButtonIsnTDisplayed() {
        assertFalse(globalSubscriptionsPage.isRestartButtonPresent());
    }

    @Then("^the most recent product is removed from the cart$")
    public void theMostRecentProductIsRemovedFromTheCart() {
        globalSubscriptionsPage.removeSpecificItemFromCart(2);
    }

    @Then("^user clicks on Learn More button under My Account Subscriptions tab and assert CTA$")
    public void userClicksOnLearnMoreButtonUnderMyAccountSubscriptionsAndAssertCTA() {
        globalSubscriptionsPage.clickOnLearnMoreButtonUnderMyAccountSubscriptionsAndAssertCTA();
    }

    @Then("^User is able to update address for a subscription using loqate$")
    public void userIsAbleToUpdateAddressForSubscriptionUsingLoqate() throws InterruptedException {
        globalSubscriptionsPage.updateAddressUsingLoqateForGlobalSubs();
    }

    @Then("^select change address or payment method checkbox and then click on Update Address button$")
    public void selectChangeAddressOrPaymentMethodCheckboxAndThenClickUpdateAddressButton() throws InterruptedException {
        globalSubscriptionsPage.selectChangeAddressOrPaymentMethodCheckboxAndThenClickUpdateAddressButton();
    }

    @Then("^user can (add|remove) another product (to|from) the subscription$")
    public void userCanAddAnotherProductToTheSubscription(String modifier, String x) {
        globalSubscriptionsPage.modifySubscriptionProducts(modifier);
    }

    @Then("^user can (increase|decrease) existing product in subscription by '(\\d+)'$")
    public void userCanIncreaseExistingProductInSubscriptionBy(String incdec, int qty) throws InterruptedException{
        globalSubscriptionsPage.modifyExistingSubscriptionProduct(incdec,qty);
    }

    @Then("^user can (add|remove) a new product (to|from) the subscription$")
    public void userCanAddANewProductToTheSubscription(String addremove, String tofrom) {
        globalSubscriptionsPage.addRemoveNewSubscriptionProducts(addremove);
    }

    @And("^error message '(.*)' is displayed for given promocode$")
    public void errorMessageSpecificInvalidPromoCodeErrorKeyIsDisplayedForGivenPromocode(String errorMessage) {
        globalSubscriptionsPage.checkErrorMessageForPromoCode(errorMessage);
    }

    @Then("^verify subscription page is displayed$")
    public void verifySubscriptionPageIsDisplayed() {
       assertTrue(batHelper.isElementDisplayedBySeconds(globalSubscriptionsPage.SUBSCRIPTION_ACCOUNT_PAGE,10));
    }
}
