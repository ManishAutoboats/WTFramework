package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.SubscriptionProPage;
import com.salmon.test.step_definitions.gui.admin.CatalogPageSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import static org.testng.Assert.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SubscriptionProSteps {
    private SubscriptionProPage subscriptionProPage;
    private CatalogPageSteps catalogPageSteps;
    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionProSteps.class);

    public SubscriptionProSteps(
            SubscriptionProPage subscriptionProPage,
            CatalogPageSteps catalogPageSteps
    ) {
        this.subscriptionProPage = subscriptionProPage;
        this.catalogPageSteps = catalogPageSteps;
    }

    @When("^user clicks on the subscription block$")
    public void clickOnSubscriptionBlock() {
        subscriptionProPage.openSubscriptionBlock();
    }

    @Then("^assert subscription block is displayed$")
    public void assertSubscriptionBlockDisplayed() {
        assertTrue(subscriptionProPage.isSubscriptionBlockPresent());
    }

    @Then("^assert subscription label on product is displayed$")
    public void assertSubscriptionProductLabelDisplayed() {
        assertTrue(subscriptionProPage.isSubscriptionLabelOnProductPresent());
    }

    @Given("^user navigates to a product page with subscription$")
    public void navigateSubscriptionProduct() {
        String productPath = (StringUtils.isEmpty(catalogPageSteps.getProductUrlPath())) ?
            UrlBuilder.getMessage("subscribeProProductPath") :
            catalogPageSteps.getProductUrlPath();

        subscriptionProPage.selectSubscriptionProduct(productPath);
    }

    @Given("^user navigates to the product page with '(.*)' href$")
    public void navigateProductWithHref(String pathKey) {
        String productPath = UrlBuilder.getMessage(pathKey);
        subscriptionProPage.selectProductWithHref(productPath);
    }

    @And("^user clicks on Subscribe and Save$")
    public void clickOnSubscribeAndSave() throws Throwable {
        subscriptionProPage.selectSubscribeAndSave();
        subscriptionProPage.isSubscribeAndSaveChecked();
    }

    @When("^user updates the subscription quantity with '(.*)'$")
    public void clickOnSubscribeAndSave(String qty) {
        subscriptionProPage.updateSubscrQtyInProductPage(qty);
    }

    @And("^user clicks on Skip Delivery '(.*)' times$")
    public void skipDelivery(String times) {
        subscriptionProPage.skipDelivery(Integer.parseInt(times));
    }

    @And("^user clicks on One Time Purchase$")
    public void clickOnOneTimePurchase() {
        subscriptionProPage.selectOneTimePurchase();
        subscriptionProPage.isOneTimePurchaseChecked();
    }

    @And("^user cancels the latest subscription$")
    public void cancelLatestSubscription() {
        subscriptionProPage.cancelSubscription();
    }

    @And("^user saves the new modified address$")
    public void saveDeliveryAddress() {
        subscriptionProPage.saveAddress();
    }
    @And("^user removes the first item from subscription$")
    public void removeLatestItemFromSubscription() {
        subscriptionProPage.removeFirstItemSubscription();
    }

    @And("^user clicks on modify delivery address button$")
    public void modifyDeliveryAddress() {
        subscriptionProPage.clickModifyDeliveryAddress();
    }

    @And("^user clicks on add a new subscription button$")
    public void addANewSubscription() {
        subscriptionProPage.clickAddANewSubscriptionBtn();
    }

    @And("^user adds a new product to subscription in quantity '(.*)'$")
    public void fillinSubscriptionForm(String qty) {
        subscriptionProPage.fillAndSubmitSubscriptionForm(qty);
    }

    @And("^user selects next delivery date '(.*)' days into the future$")
    public void selectNextDeliverySubscription(String days) {
        subscriptionProPage.selectNewDeliveryDate(days);
    }

    @And("^user accepts paying the fee$")
    public void acceptPayingTheFee() {
        subscriptionProPage.waitAndAcceptFeeFromPopup();
    }

    @And("^assert subscription container is displayed$")
    public void assertPdpSubscriptionContainer() {
        assertTrue(subscriptionProPage.isSubscriptionContainerPresent());
        assertEquals(2, subscriptionProPage.pdpSubscriptionContainerChoices().size());
    }

    @And("^assert One Time Purchase is checked by default$")
    public void assertPdpOneTimePurchaseChecked() {
        assertTrue(subscriptionProPage.isOneTimePurchaseChecked());
    }

    @And("^assert current product can be found '(.*)' times in cart$")
    public void assertSubscribedProductsInCart(Integer times) {
        Integer productsInCart = subscriptionProPage.currentProductInCart().size();
        Assert.assertEquals(
            times,
            productsInCart,
            "ERROR: Product cannot be found in cart " + times + " times."
        );
    }

    @And("^assert One Time Purchase label is displayed in cart$")
    public void assertOneTimePurchaseLblCart() {
        assertTrue(subscriptionProPage.isOneTimeLblVisibleInCart());
    }

    @And("^assert Subscription label is displayed in cart$")
    public void assertSubscriptionLblCart() {
        assertTrue(subscriptionProPage.isSubscriptionLblVisibleInCart());
    }

    @And("^assert minimum quantity for subscription error is displayed$")
    public void assertMinQtyErrorSubscription() {
        String errorTxt = (subscriptionProPage.doesURLContain("customer")) ?
                subscriptionProPage.subscriptionMyAccountError().getText() :
                subscriptionProPage.subscriptionCheckoutError().getText();

        assertTrue(errorTxt.contains(UrlBuilder.getMessage("subscribeProErrorMsgMinimumQuantity.key")));
    }

    @Then("^assert skip delivery button displayed$")
    public void assertSkipBtnExistSubscription() {
        assertTrue(subscriptionProPage.isElementPresentByby(subscriptionProPage.skipDeliveryButton));
    }
    @And("^assert skip delivery button not displayed$")
    public void assertSkipBtnNotExistSubscription() {
        assertTrue(subscriptionProPage.invisibilityOfElementLocated(subscriptionProPage.skipDeliveryButton));
    }

    @And("^assert subscription discount is displayed$")
    public void assertSubscriptionDiscountDisplayed() {
        assertTrue(subscriptionProPage.discountIsDisplayed());
    }

    @And("^assert subscription discount text is different$")
    public void assertSubscriptionDiscountTxtDifferent() {
        assertTrue(subscriptionProPage.isDiscountTextDifferent());
    }

    @And("^assert subscription discount is not displayed$")
    public void assertSubscriptionDiscountNotDisplayed() {
        assertFalse(subscriptionProPage.discountIsDisplayed());
    }

    @And("^assert maximum quantity for subscription error is displayed$")
    public void assertMaxQtyErrorSubscription() {
        String errorTxt;
        if(subscriptionProPage.doesURLContain("catalog")){
            errorTxt = subscriptionProPage.subscriptionPDPError().getText();
        }
        else if(subscriptionProPage.doesURLContain("customer")){
            errorTxt = subscriptionProPage.subscriptionMyAccountError().getText();
        }
        else{
            errorTxt = subscriptionProPage.subscriptionCheckoutError().getText();
        }

        assertTrue(errorTxt.contains(UrlBuilder.getMessage("subscribeProErrorMsgMaximumQuantity.key")));
    }

    @Then("^assert subscription block has content$")
    public void assertSubscriptionBlockHasContent() {
        assertTrue(subscriptionProPage.subscriptionBlockTitle().length() > 1);
        assertTrue(subscriptionProPage.subscriptionBlockOptions().size() > 1);
    }

    @And("^assert subscription doesn't exist$")
    public void assertSubscriptionNotExists() {
        assertFalse(subscriptionProPage.subscriptionExists());
    }

    @And("^assert subscription exist under My subscriptions$")
    public void assertSubscriptionExists() {
        assertTrue(subscriptionProPage.subscriptionExists());
    }

    @And("^assert the discount is applied for '(.*)' subscription$")
    public void assertDiscountForSubscriptionExists(String subscrTypeKey) {
        subscriptionProPage.waitForLoaderToDisapear();
        String discountText = subscriptionProPage.waitForExpectedElement(subscriptionProPage.SUBS_PRO_DISCOUNT_TEXT).getText();
        switch(subscrTypeKey){
            case "silver":
                assertTrue(discountText.contains(UrlBuilder.getMessage("subscriptionTypeSilver.key")));
                break;
            case "gold":
                assertTrue(discountText.contains(UrlBuilder.getMessage("subscriptionTypeGold.key")));
                break;
            case "platinum":
                assertTrue(discountText.contains(UrlBuilder.getMessage("subscriptionTypePlatinum.key")));
                break;
            default:
                LOG.info("Discount not found");
        }
    }

    @And("^assert the free device is applied for subscription$")
    public void assertFreeDeviceExists() {
        assertTrue(subscriptionProPage.isFreeDeviceDisplayed());
    }

    @Then("^assert user is not allowed to have any other subscriptions$")
    public void assertNoMoreSubscriptions() {
        assertTrue(subscriptionProPage.isErrorMessagePresent());
    }

    @And("^assert '(.*)' products in subscription exist under My Subscriptions$")
    public void assertNumberOfSubscriptionsExists(Integer expectedNo) {
        Assert.assertEquals(subscriptionProPage.numberOfExistingProductSubscriptions(), expectedNo);
    }

    @And("^assert delivery address changed$")
    public void assertDeliveryAddressChanged() {
        LOG.info(subscriptionProPage.waitForExpectedElement(subscriptionProPage.deliveryAddressContainer).getText());
        LOG.info(subscriptionProPage.currentAddressName);
        assertNotEquals(
                subscriptionProPage.waitForExpectedElement(subscriptionProPage.deliveryAddressContainer).getText(),
                subscriptionProPage.currentAddressName
        );
    }

    @And("^assert subscription was updated$")
    public void assertSubscriptionUpdated() {
        assertTrue(
                subscriptionProPage.isElementPresentByby(
                        subscriptionProPage.successMsgUpdate
                )
        );
    }

    @And("^assert subscription was delayed for '(.*)' months$")
    public void assertSubscriptionDelayed(String numberOfMonths) {
        subscriptionProPage.refreshBrowser();
        assertTrue(
                subscriptionProPage.subscriptionExists(Integer.parseInt(numberOfMonths))
        );
    }

    @And("^assert subscription was succesfully added$")
    public void assertSubscriptionWasAdded() {
        assertTrue(
                subscriptionProPage.isElementPresent(
                        subscriptionProPage.successTopMsg
                )
        );
    }

    @And("^assert '(.*)' subscriptions present in page$")
    public void assertCountSubscriptionsInPage(String numberOfSubscr) {
        assertTrue(subscriptionProPage
            .subscriptionsByDeliveryDate(1)
            .size() == (Integer.parseInt(numberOfSubscr))
        );
    }

    @And("^assert '(.*)' number of products can be found in checkout$")
    public void assertCountProductsInCheckout(String ExpectedNumberOfProducts) {
        assertTrue(subscriptionProPage.productsInCheckout(Integer.parseInt(ExpectedNumberOfProducts)));
    }

    @And("^user increases the product quantity for Subscriptions if error appears$")
    public void userIncreasesProductQtyIfSubsErrorAppearsInCart() {
        subscriptionProPage.userIncreasesProductQtyIfSubsErrorAppearsInCart();
    }

    @Then("^assert '(.*)' message is presented on Modify Subscription module$")
    public void assertValidationMessageIsPresentedOnModifySubscriptionModule(String messageType){
        if ("below 5 validation".equalsIgnoreCase(messageType)) {
            assertTrue(subscriptionProPage.getValidateMessageOnModifySubscriptionModule().contains(UrlBuilder.getMessage("validationMinMessages.key")));
        } else {
            assertTrue(subscriptionProPage.getValidateMessageOnModifySubscriptionModule().contains(UrlBuilder.getMessage("validationMaxMessages.key")));
        }
    }
}
