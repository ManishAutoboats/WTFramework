package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.website.EngravingAttribute;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.*;

public class BasketPageSteps {

    private BasketPage basketPage;
    private SubscriptionsPage subscriptionsPage;
    private SearchResult searchResult;
    private PDP pdp;
    private HomePage homePage;
    private ScenarioContext scenarioContext;

    public BasketPageSteps(BasketPage basketPage, SubscriptionsPage subscriptionsPage,
                           SearchResult searchResult, PDP pdp, HomePage homePage, ScenarioContext scenarioContext) {
        this.basketPage = basketPage;
        this.subscriptionsPage = subscriptionsPage;
        this.searchResult = searchResult;
        this.pdp = pdp;
        this.homePage = homePage;
        this.scenarioContext = scenarioContext;
    }

    @And("^assert summary total changes when item's quantity is updated$")
    public void summaryTotalUpdated(){
        basketPage.summaryTotalUpdated();
    }

    @And("^user asserts basket is empty$")
    public void basketEmptyMessage(){
        basketPage.basketEmptyMessage();
    }

    @And("^user asserts product count is matching the subtotal count$")
    public void subTotalAndProductsCount(){
        basketPage.subTotalAndProductsCount();
    }

    @And("^assert summary section and total is matching in basket page$")
    public void summaryTotalCount() {
        basketPage.summaryTotalCount();
    }

    @And("^assert summary section and total is matching in basket page without code$")
    public void summaryTotalCountWithoutCode(){
        basketPage.summaryTotalCountWithoutCode();
    }

    @And("^users clicks on the Cancel cta for coupon code$")
    public void cancelCoupon(){
        basketPage.cancelCoupon();
    }

    @And("enter the coupon code '(.*)'")
    public void couponCode(String code){
        basketPage.couponCode(code);
    }


    @And("^verify if subscription is selected$")
    public void subscriptionIsSelected() {
        basketPage.subscriptionIsSelected();
    }

    @And("^verify if subscription modal is displayed with '(.*)' error message$")
    public void subscriptionModalIsPresent(String text){
        basketPage.subscriptionModalIsPresent(text);
    }

    @And("^verify if subscription cta's are displayed$")
    public void subscriptionCtaIsDisplayed(DataTable table){
        basketPage.subscriptionCtaIsDisplayed(table);
    }

    @And("^users clicks on the '(.*)' cta and assert it is applied$")
    public void selectCta(String text){
        basketPage.selectCta(text);
    }

    @And("^click on one time purchase for first product$")
    public void selectForFirstProduct(){
        basketPage.selectForFirstProduct();
    }

    @And("^click on '(.*)' subscription option and assert it is selected$")
    public void selectSubscriptionOption(String text){
        basketPage.selectSubscriptionOption(text);
    }

    @And("^update the subscription to '(.*)'$")
    public void subscriptionBronze(String text){
        basketPage.subscriptionBronze(text);
    }

    @And("^update the quantity to '(.*)'$")
    public void bronzeQuantity(String text){
        basketPage.bronzeQuantity(text);
    }

    @And("^user clicks on the View Basket cta$")
    public void viewBasketCta() throws InterruptedException {
        basketPage.viewBasketCta();
    }

    @And("^user select product add to basket and view basket$")
    public void selectProductAddAndViewBasket() throws InterruptedException {
        searchResult.selectFirstBuyableProduct_Pl();
        pdp.eyesCheckPdpPage();
        subscriptionsPage.clickAddToBasket();
        basketPage.viewBasketCta();
        if (!Locale.valueOf(UrlBuilder.getLocale().toUpperCase()).equals(Locale.PL)) {
            homePage.eyesCheckMiniCart();
            basketPage.viewBasketCta();
        }
    }

    @And("^user clicks on the basket View shopping cart and proceed to checkout$")
    public void viewShoppingCartAndProceedCheckout(){
        basketPage.viewShoppingCartAndProceedCheckout();
    }

    @Then("^assert view cart page displays the engraving details$")
    public void assertMiniCartBasketDisplaysTheEngravingDetails() {
        basketPage.engravingDetailsConfirmationOnViewcartPage();
        if(pdp.engravingAttributeList != null){
            Set<EngravingAttribute> actualResult= new HashSet<>(basketPage.getEngravingAttributesOnViewcartPage());
            Set<EngravingAttribute> expectedResult = new HashSet<>(pdp.engravingAttributeList);
            assertEquals(expectedResult, actualResult);
        }
    }

  @And("^add promotion '(.*)' at basket page$")
  public void addPromotionAtBasketPage(String promoCode) {
    basketPage.addVusePromoCode(promoCode);
  }

    @And("^remove the promotion$")
    public void removePromotion() {
        basketPage.removeVusePromo();
    }

    @Then("^assert quantity selector is displayed on Mini Basket$")
    public void assertQtySelectorDisplayedOnMiniBasket() throws Throwable {
        if(basketPage.doesURLContain("/checkout/cart/"))
            assertTrue(basketPage.waitForExpectedElement(basketPage.QTY_SELECTOR_MINI_BASKET)!=null);
        else
            assertTrue(basketPage.waitForExpectedElement(basketPage.QTY_SELECTOR_MINI_CART)!=null);
    }

    @Then("^assert if user is able to input any value '(.*)' in mini basket Qty field$")
    public void assertUserIsAbleToInputAnyValueInMiniBasketQtyField(String strQty) throws Throwable {
        basketPage.assertUserIsAbleToInputAnyValueInMiniBasketQtyField(strQty);
    }

    @Then("^user clicks on plus button on MiniBasket qty selector and assert increase to '(.*)'$")
    public void userClicksOnPlusButtonOnMiniBasketQtySelectorToIncreaseQty(String strIncreaseQty) throws Throwable {
        basketPage.clickToActionOnQtySelectorToUpdateMiniBasketQty(basketPage.QTY_SELECTOR_PLUS_BUTTON_BASKET,strIncreaseQty, basketPage.QTY_SELECTOR_PLUS_BUTTON_MINI_CART);
    }

    @Then("^user clicks on minus button on MiniBasket qty selector and assert decrease to '(.*)'$")
    public void userClicksOnMinusButtonOnMiniBasketQtySelectorToDecreaseQty(String strDecreaseQty) throws Throwable {
        basketPage.clickToActionOnQtySelectorToUpdateMiniBasketQty(basketPage.QTY_SELECTOR_MINUS_BUTTON_BASKET,strDecreaseQty, basketPage.QTY_SELECTOR_MINUS_BUTTON_MINI_CART);
    }

    @And("verify the remove sku functionality on '(.*)'$")
    public void verifyRemoveSkuFunctionality(String cart) throws InterruptedException {
        basketPage.verifyRmoveSkuFunctionality(cart);
    }

    @Then("^user clicks on minus button on (?:mini cart|basket page) and assert pop-up to confirm product removal appears$")
    public void userClicksOnMinusButtonOnMiniCartAndAssertConfirmProductRemovalPopUp() throws Throwable {
        basketPage.clickOnMinusButtonOnMiniCartQtySelector();
        if(basketPage.doesURLContain("/checkout/cart/"))
            assertTrue(basketPage.getWebDriver().findElements(basketPage.PRODUCT_REMOVAL_CONFIRM_POPUP_CART).size()>0);
        else
            assertTrue(basketPage.waitForExpectedElement(basketPage.PRODUCT_REMOVAL_CONFIRM_POPUP).isDisplayed());
    }

    @And("^user increase the quantity of the selected item by '(\\d+)'$")
    public void userIncreasesTheQuantityOfTheSelectedItemBy(String qty) throws Throwable{
            basketPage.enterItemQuantityMiniCart(qty);
    }

    @And("^assert user click accept button to close exceeds the maximum qty notification window$")
    public void userClickAcceptBtnCloseExceedsMaxQtyWindow(){
        basketPage.userClickAcceptBtnCloseExceedsMaxQtyWindow();
    }

    @And("^user increases the quantity of product into '(\\d+)' using dropdown menu in basket page$")
    public void userIncreasesTheQuantityUsingDropdownInBasketPage(String qty) throws Throwable{
        basketPage.selectItemQuantityUsingDropdownInBasketPage(qty);
    }

    @And("^user clicks on Cancel button from confirm removal pop up and assert pop up is closed$")
    public void userClicksOnCancelButtonFromConfirmRemovalPopUpAndAssertPopUpClosed() throws Throwable {
        basketPage.clickOnCancelButtonFromConfirmRemovalPopUp();
        basketPage.waitForElementToAppearAndDisappear(basketPage.LOADER,5,5);
        switch (UrlBuilder.getLocale()) {
            case "vuseuk":
            case "vusefr":
            case "vusede":
            case "glode":
            case "kz":
                if(basketPage.doesURLContain("/checkout/cart")) {
                    assertTrue(!basketPage.getWebDriver().findElements(basketPage.PRODUCT_REMOVAL_CONFIRM_POPUP).get(0).isDisplayed());
                }
                else{
                    assertTrue(basketPage.getWebDriver().findElements(basketPage.PRODUCT_REMOVAL_CONFIRM_POPUP).size() == 0);
                }
                break;
            case "vuseit":
            case "vuseza":
            case "vuseco":
                assertTrue(!basketPage.isRemovalConfirmPopupPresent());
                break;
            default:
                assertTrue(!basketPage.getWebDriver().findElement(basketPage.PRODUCT_REMOVAL_CONFIRM_POPUP).isDisplayed());
        }
    }

    @And("^user clicks on Remove button from confirm removal pop up and assert basket gets empty$")
    public void userClicksOnRemoveButtonFromConfirmRemovalPopUpAndAssertBasketIsEmpty() throws Throwable {
        basketPage.clickOnRemoveButtonFromConfirmRemovalPopUp();
        assertTrue(basketPage.getWebDriver().findElements(basketPage.BAKSET_EMPTY_STATUS).size() > 0);
    }

    @And("^assert free gift is added at top right on the basket page$")
    public void assertFreeGiftIsAddedAtTopRightOnTheBasketPage() throws Throwable {
        if(basketPage.waitForExpectedElement(basketPage.FREE_GIFT_LABEL).getText().contains("FREE")){
            basketPage.waitForExpectedElement(basketPage.PRODUCTS_IN_BASKET,10);
            assertTrue(basketPage.getWebDriver().findElements(basketPage.PRODUCTS_IN_BASKET).get(0).findElement(basketPage.FREE_GIFT_LABEL).isDisplayed());
        }
    }

    @And("^assert navigation to (?:cart|home|account details) page with '(.*)' and response status code as 200$")
    public void assertNavigationToPageWithURLAndResponseStatusCode200(String strURL){
       assertTrue(basketPage.waitForURLToContain(UrlBuilder.getMessage(strURL)));
       basketPage.restAssuredAssertStatusCode(200);
    }

    @And("^assert text is displayed in English after switching language$")
    public void assertTextDisplayedInEnglishAfterSwitchingLanguage(){
        assertTrue(basketPage.waitForExpectedElement(basketPage.LYFT_EN_LANGUAGE).isDisplayed());
        assertTrue(basketPage.getWebDriver().findElements(basketPage.LYFT_SV_LANGUAGE).size()==0);
    }

    @And("^assert text is displayed in Swedish after switching language$")
    public void assertTextDisplayedInSwedishAfterSwitchingLanguage(){
        assertTrue(basketPage.waitForExpectedElement(basketPage.LYFT_SV_LANGUAGE).isDisplayed());
        assertTrue(basketPage.getWebDriver().findElements(basketPage.LYFT_EN_LANGUAGE).size()==0);
    }


    @Then("^verify product cost include taxes$")
    public void userVerifyProductCostIncludeTaxes() {
        basketPage.verifyProductCostIncludeTaxes();
    }

    @Then("^assert user get subscription discount$")
    public void userGetSubscriptionDiscount() {
        basketPage.verifySubscriptionDiscount();
    }

    @Then("^assert user subscription discount not display$")
    public void userSubscriptionDiscountNotDisplay() {
        basketPage.verifySubscriptionDiscountNotDisplay();
    }

    @And("^each item in the basket has a name quantity image price and remove button$")
    public void eachItemInTheBasketHasANameQuantityImagePriceAndRemoveButton() {
        basketPage.validateBasketItemComponents();
    }

    @And("^user click on mini cart wrapper button in basket page$")
    public void userClickOnMinicartWrapperButton() {
        basketPage.clickMiniBasketInCart();
    }

    @And("^assert mini cart pop-up displays in basket page$")
    public void assertMiniCartDisplay() {
        basketPage.assertMiniCartDisplay();
    }

    @And("^confirm that the heading \"([^\"]*)\" is displayed$")
    public void confirmThatTheHeadingIsDisplayed(String basketHeadingText) throws Throwable {
        String expectedHeading;
        if (basketPage.itemsInBasket() > 1) {
             expectedHeading = UrlBuilder.getMessage(basketHeadingText).replace("%QTY%", String.valueOf(basketPage.itemsInBasket()));
        } else {
            expectedHeading = UrlBuilder.getMessage(basketHeadingText.replace("-", "Singular-")).replace("%QTY%", String.valueOf(basketPage.itemsInBasket()));
        }
        basketPage.validateBasketHeading(expectedHeading);
    }

    @And("^i click on 'X' button next to any product to remove the product$")
    public void iClickOnXButtonNextToAnyProductToRemoveTheProduct() {
        basketPage.clickOnRemoveProduct();
    }

    @Then("^I see a pop-up on the screen with text \"([^\"]*)\" asking me to confirm removal of product from my basket$")
    public void iSeeAPopUpOnTheScreenWithTextAskingMeToConfirmRemovalOfProductFromMyBasket(String expectedPopupText) throws Throwable {
        basketPage.IseePopupAskingConfirmation(UrlBuilder.getMessage(expectedPopupText));
    }

    @And("^there is a Cancel and Remove CTA$")
    public void thereIsACancelAndRemoveCTA() {
        basketPage.thereIsCancelAndRemove();
    }

    @And("^If i click cancel i go back to my basket$")
    public void ifIClickCancelIGoBackToMyBasket() {
        String expectedHeading;
        basketPage.clickCancelAndCheckOnBasketPage();
        int itemsInBasket = basketPage.itemsInBasket();
        if (itemsInBasket == 1) {
            expectedHeading = UrlBuilder.getMessage("basketHeadingTextSingular-" + scenarioContext.getContext(Context.LANGUAGE)).replace("%QTY%", String.valueOf(basketPage.itemsInBasket()));
        } else {
            expectedHeading = UrlBuilder.getMessage("basketHeadingText-" + scenarioContext.getContext(Context.LANGUAGE)).replace("%QTY%", String.valueOf(basketPage.itemsInBasket()));
        }
        basketPage.validateBasketHeading(expectedHeading);
    }

    @Then("^I see message \"([^\"]*)\" informing that my basket is empty$")
    public void iSeeMessageInformingThatMyBasketIsEmpty(String expectedEmptyBasketMessage) throws Throwable {
        basketPage.validateEmptyBasketMessage(UrlBuilder.getMessage(expectedEmptyBasketMessage));
    }

    @And("^there is a 'Start Shopping' CTA$")
    public void thereIsAStartShoppingCTA() {
        basketPage.validateThereIsAStartShoppingCta();
    }

    @And("^this CTA redirects me to web shop homepage$")
    public void thisCTARedirectsMeToWebShopHomepage() {
        basketPage.clickStartShoppingAndValidateRedirect();
    }

    @And("^the user can see the tax calculation amount$")
    public void theUserCanSeeTheTaxCalculationAmount() {
        String expectedTaxLabel = UrlBuilder.getMessage("basketTaxString");
        basketPage.canSeeTaxCalculationAmount(expectedTaxLabel);
    }

    @And("^the user can see the total price including VAT labelled as \"([^\"]*)\"$")
    public void theUserCanSeeTheTotalPriceIncludingVATLabelledAs(String expectedPriceLabel) throws Throwable {
        String priceLabel = UrlBuilder.getMessage(expectedPriceLabel);
        basketPage.canSeeTotalPrice(priceLabel);
    }

    @And("^the user can see the proceed to checkout CTA with button text \"([^\"]*)\"$")
    public void theUserCanSeeTheProceedToCheckoutCTAWithButtonText(String checkoutCtaText) throws Throwable {
        String expectedButtonText = UrlBuilder.getMessage(checkoutCtaText);
        basketPage.canSeeProceedToCheckoutCta(expectedButtonText);
    }

    @And("^all products are automatically added to the basket$")
    public void allProductsAreAutomaticallyAddedToTheBasket() {
        basketPage.checkAllOrderAgainProductsAddedToBasket();
    }
    @Then("^verify combos discount is applied$")
    public void verifyComboDiscountApplied() {
        basketPage.assetComboDiscountApplied();
    }

    @Then("^apply discount code and verify coupon applied$")
    public void userApplyDiscountCodeForReferralCoupon() {
        basketPage.applyReferalCouponCode();
    }

    @And("^increases the quantity of selected product item by '(\\d+)'$")
    public void userIncreasesTheQuantityOfTheSelectedProductBy(int qty) throws Throwable{
        basketPage.increaseQuantityOfItemInCart(qty);
    }

    @And("^fetch the product price$")
    public void verifyProductPrice() {
        basketPage.verifyProductPrice();
    }

    @And("^user add one product and get flavours discount$")
    public void userGetFlavoursDiscount() {
        basketPage.userGetFlavourDiscount();
    }

    @And("^product added in basket$")
    public void userAddedProductInBasket() {
        assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.SUCCESS_MESSAGE,5));
        switch (UrlBuilder.getLocale()) {
            case "glode":
            case "it":
            case "pl":
                break;
            default:
                assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.PRODUCT_BASKET,2));
        }
    }

    @And("^user click on Start your shopping button$")
    public void userClickStartYourShoppingButton() {
        basketPage.clickStartYourShoppingButton();
    }

    @Then("^assert user is navigate to homepage$")
    public void userNavigateToHomepage() {
        assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.GLO_DE_HOMEPAGE,5));
    }

    @And("^added product is visible in basket$")
    public void addedProductInBasketIsVisible() {
        assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.SUCCESS_MESSAGE,5));
        assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.PRODUCT_IN_BASKET,5));
    }
    @And("^add to cart is not visible for loyalty products$")
    public void addToCartIsNotVisible() {
        assertFalse(basketPage.isElementDisplayedBySeconds(basketPage.ADD_TOCART_LOYALTY,5));
    }

    @Then("^confirm basket displayed (.*) tiles$")
    public void confirmBasketDisplayedAmountOfTiles(int basketQty) throws Throwable {
        basketPage.confirmBasketDisplayedAmountOfTiles(basketQty);
    }

    @And("^user selects maximum quantity available in Quantity dropdown on (?:mini cart|mini basket)$")
    public void userSelectsMaximumQuantityFromQtyDropdownOnMiniCartAndBasket() {
        basketPage.selectMaxQuantityFromQtyDropdownOnMiniCartAndBasket();
    }

    @And("^assert Qty dropdown control switches to free text entry and update CTA is populated$")
    public void assertQtyDropdownControlSwitchesToFreeTextEntryAndUpdateCTAPopulated() {
        if(basketPage.doesURLContain("/checkout/cart/")){
            assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.INPUT_QTY_MINI_BASKET,5));
            assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.QTY_UPDATE_LINK_BASKET_PAGE,5));}
        else {
            assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.INPUT_QTY_MINI_CART, 5));
            assertTrue(basketPage.isElementDisplayedBySeconds(basketPage.QTY_UPDATE_LINK_MINI_CART, 5));
        }
    }

    @And("user enters quantity '(.*)' in the mini-cart text-field and update$")
    public void userEntersQuantityInMiniCartTextFieldAndUpdate(String strQuantity) throws InterruptedException {
        basketPage.enterQuantityInMiniCartTextFieldAndUpdate(strQuantity);
    }

    @And("^assert Qty free text field switches back to a dropdown when qty is less than max value$")
    public void assertQtyFreeTextFieldSwitchesBackToDropdownWhenQtyIsLessThanMaxValue() {
       basketPage.assertQtyFreeTextFieldSwitchesBackToDropdownWhenQtyIsLessThanMaxValue();
    }

    @And("^assert Qty field continues to display as a free text field when qty is more than max value$")
    public void assertQtyFieldContinuesToDisplayAsTextFieldWhenQtyIsMoreThanMaxValue() {
        basketPage.assertQtyFieldContinuesToDisplayAsTextFieldWhenQtyIsMoreThanMaxValue();
    }

    @And("^assert quantity exceeded error message '(.*)' is displayed in basket page$")
    public void assertErrorMessageIsDisplayedInBasket(String strErrorMsg) {
        assertTrue(basketPage.waitForExpectedElement(basketPage.QUANTITY_EXCEEDED_MESSAGE).getText().contains(UrlBuilder.getMessage(strErrorMsg)));
    }

    @And("^assert quantity number is '(\\d+)' in  title$")
    public void assertQuantityNumberIsInTitle(int quantityNumber) {
        assertTrue(basketPage.getQuantityNumInTitle()==quantityNumber);
    }

    @And("^assert Cross-sell products section at cart page$")
    public void assertCrossSellProductSectionAtCartPage() {
       assertTrue(basketPage.waitForExpectedElement(basketPage.RELATED_PRODUCT_ADD_TO_CART_PL).isDisplayed());
    }

    @And("^assert Related Products section at PDP$")
    public void assertRelatedProductSectionAtPDP() {
        assertTrue(basketPage.waitForExpectedElement(basketPage.RELATED_PRODUCT_ADD_TO_CART).isDisplayed());
    }

    @And("^user add '(.*)' into the cart and verify item is added$")
    public void userAddRelatedProduct(String product) {
        basketPage.addRelatedProductIntoCart(product);
    }

    @And("^user click on related product image and verify product pdp is opened$")
    public void userClickRelatedProductImageAndVerifyPDP() {
        basketPage.userClickRelatedProductImageAndVerifyPDP();
    }

    @And("^Qty is not editable$")
    public void qtyIsNotEditable() {
        basketPage.isQTYEditable();
    }

    @And("^user clicks on choose your device on basket page$")
    public void userClicksOnChooseYourDeviceOnBasketPage() {
        basketPage.clickChooseYourDevice();
    }

    @Then("^user adds free device from a popup$")
    public void userAddsFreeDeviceFromAPopup() {
        basketPage.chooseFreeDeviceFromPopUp();
    }

    @Then("^empty basket from cart page$")
    public void emptyBasket() {
        homePage.emptyBasket();
    }

    @And("^assert mini cart basket displays the total price of added product$")
    public void assertMiniCartBasketDisplaysTheTotalPriceOfAddedProduct() {
        basketPage.checkMiniBasketPLP();
    }

    @And("^assert Cross-sell products section at minicart is displayed$")
    public void assertCrossSellProductIsDisplayedAtMinicart() {
        assertTrue(basketPage.getWebDriver().findElements(basketPage.CROSS_SELL_PRODUCT_MINICART).size()>0);
        assertTrue(basketPage.getWebDriver().findElements(basketPage.CROSS_SELL_PRODUCT_MINICART_ADD_TO_CART).size()>0);
    }

    @And("^user select cross-sell product and verify PDP of the selected product is opened$")
    public void selectCrossSellProductAndVerifyPDPOpened() {
        basketPage.selectCrossSellProductAndVerifyPDPOpened();
    }

    @And("^user click on add to cart cta of cross sell product and verify product is added into the cart$")
    public void clickAddToCartCTAAndVerifyProductIsAddedIntoCart() {
        basketPage.clickAddToCartCTAAndVerifyProductIsAddedIntoCart();
    }

    @Then("^verify the product and price added into the cart$")
    public void verifyProductAndPriceAddedInCart() {
        basketPage.verifyProductAndPriceAddedInCart();
    }

    @Then("^verify the product and price according to increase in product count$")
    public void verifyProductPriceAccordingToIncreaseInProductCount() {
        basketPage.verifyProductPriceAccordingToIncreaseInProductCount();
    }

    @Then("^increase product quantity and verify pricing update accordingly$")
    public void increaseProductQuantityAndVerifyPricingUpdate() {
        basketPage.increaseProductQuantityAndVerifyPricingUpdate();
    }
}
