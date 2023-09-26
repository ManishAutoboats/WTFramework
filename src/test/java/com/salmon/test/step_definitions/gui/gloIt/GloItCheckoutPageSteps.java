package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.gloIt.GloItCheckoutPage;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItProductListPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.page_objects.gui.constants.Context.COUPON_PRODUCT_URL_ISVALID;
import static org.testng.Assert.assertFalse;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.testng.AssertJUnit.assertTrue;

public class GloItCheckoutPageSteps {
	GloItCheckoutPage gloItCheckoutPage;
	private PaymentPage paymentPage;
	private GloItHomePage gloItHomePage;
	private AddNewAddressPage addNewAddressPage;
	private SoftAssertions softAssertions;
	private static final Logger LOG = LoggerFactory.getLogger(GloItCheckoutPageSteps.class);
	private ScenarioContext scenarioContext;
	private OrderSuccessPage orderSuccessPage;
	private GloItProductListPage gloItProductListPage;
	private HomePage homePage;
	private PLP plp;
	private BasketPage basketPage;

	public GloItCheckoutPageSteps(GloItCheckoutPage gloItCheckoutPage, PaymentPage paymentPage,
								  GloItHomePage gloItHomePage, AddNewAddressPage addNewAddressPage,
								  SoftAssertions softAssertions, ScenarioContext scenarioContext,
								  OrderSuccessPage orderSuccessPage, GloItProductListPage gloItProductListPage,
								  HomePage homePage, PLP plp, BasketPage basketPage) {
		this.gloItCheckoutPage = gloItCheckoutPage;
		this.paymentPage=paymentPage;
		this.gloItHomePage= gloItHomePage;
		this.addNewAddressPage = addNewAddressPage;
		this.softAssertions = softAssertions;
		this.scenarioContext = scenarioContext;
		this.orderSuccessPage = orderSuccessPage;
		this.gloItProductListPage=gloItProductListPage;
		this.homePage=homePage;
		this.plp = plp;
		this.basketPage=basketPage;
	}

	@And("^user select Card Payment option$")
	public void Select_Card_Payment_Option() {
		gloItCheckoutPage.selectCardPaymentMethod();
	}

	@And("^user select credit card as payment option$")
	public void glo_Select_Credit_Card_As_Payment_Option() {
		gloItCheckoutPage.selectCreditCardCheckbox();
	}

	@And("^user select PayU as payment option$")
	public void glo_Select_PayU_As_Payment_Option() {
		gloItCheckoutPage.selectAndPurchaseThroughPayU_PAYMENT();
	}

	@And("^user select debit card as payment option$")
	public void epok_Select_Debit_Card_As_Payment_Option() {
		gloItCheckoutPage.selectDebitCardCheckbox();
	}

	@And("^user click on saved card type option checkbox$")
	public void epok_User_Click_Saved_Card_Type_Option_Checkbox() throws InterruptedException {
		gloItCheckoutPage.selectSavedCardTypeCheckbox();
	}

	@And("^select users first saved card checkbox$")
	public void Select_User_First_Saved_Card_Checkbox() {
		gloItCheckoutPage.selectUsersSavedCard();
	}

	@And("^ check alternative payment order option$")
	public void epokcheckAlternativePaymentOrderOption() {
		gloItCheckoutPage.selectOtherPaymentMethodCheckbox();
	}

	@And("^Glo user fills Master or Visa card information$")
	public void Glo_user_Fills_Master_Card_Or_Visa_Information() throws InterruptedException {
		gloItCheckoutPage.enterValidMasterOrVisaCardDetailsAndSubmit();
	}

	@And("^ Check paypal option$")
	public void checkPaypalOption() throws Throwable {
		try {
			Thread.sleep(3000);
			LOG.info("\n ****** Selecting Paypay option");
			gloItCheckoutPage.selectPayPalCheckbox();
		} catch (Exception e) {
			LOG.info("Couldn't select paypal option, trying again after 3 seconds wait");
			Thread.sleep(3000);
			gloItCheckoutPage.selectPayPalCheckbox();
		}
	}

	@And("^select agree to terms and conditions$")
	public void tickAgreeToTermsAndConditions()  {
		gloItCheckoutPage.selectTermsAndConditionCheckbox();
	}

	@And("^select agree to terms and conditions for card payment$")
	public void Select_Terms_Conditions_For_Card_Payment() {
		gloItCheckoutPage.selectTermsAndCondtionsCard();

	}

	@And("^select place order from Card$")
	public void selectPlaceOrderFromCard() throws Throwable {
		Thread.sleep(3000);
		gloItCheckoutPage.selectBuyNowButtonFromCard();
		paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),15);
		paymentPage.waitForPageLoad();
	}

	@And("^switch address checkbox$")
	public void epok_Switch_Address_Checkbox() {
		gloItCheckoutPage.selectSwitchAddressCheckbox();

	}

	@And("^user apply the discount coupon '(.*)'")
	public void user_Apply_Discount_Coupon(String couponCode) {
		switch(UrlBuilder.getLocale()) {
			case "pl":
				if (gloItCheckoutPage.isElementDisplayedBySeconds(gloItCheckoutPage.couponCodeCancelButton, 5))
					gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.couponCodeCancelButton);
				gloItCheckoutPage.waitForAjaxElementNotToBePresent(gloItCheckoutPage.getWebDriver(), 10);
				gloItCheckoutPage.enterText(gloItCheckoutPage.couponCodeInputField, UrlBuilder.getMessage(couponCode));
				gloItCheckoutPage.ClickOnApplyDiscountButton();
				break;
			case "kz":
				if (paymentPage.getWebDriver().findElements(paymentPage.CANCEL_DISCOUNT_BUTTON_GLODE).size() > 0) {
					paymentPage.clickUsingJS(paymentPage.CANCEL_DISCOUNT_BUTTON_GLODE);
				}
				gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.appyDiscountButton, 15);
				gloItCheckoutPage.enterText(gloItCheckoutPage.couponCodeInputField, UrlBuilder.getMessage(couponCode));
				gloItCheckoutPage.ClickOnApplyDiscountButton();
				break;
			case "vuseuk":
			case "vusefr":
			case "vusede":
			case "vuseit":
				homePage.closeVuseAlertIfPresent();
				basketPage.waitForAjaxElementNotToBePresent(getWebDriver(),5);
				basketPage.enterText(basketPage.DISCOUNT_FIELD, UrlBuilder.getMessage(couponCode));
				basketPage.waitForExpectedElement(basketPage.APPLY_DISCOUNT,5).click();
				basketPage.jsScrollElementInCenter(basketPage.waitForExpectedElement(basketPage.APPLY_DISCOUNT));
				break;
			case "lyftse":
				basketPage.waitForAjaxElementNotToBePresent(getWebDriver(),5);
				basketPage.enterText(basketPage.DISCOUNT_FIELD_LYFTSE, UrlBuilder.getMessage(couponCode));
				basketPage.waitForExpectedElement(basketPage.APPLY_DISCOUNT_LYFTSE).click();
				break;
			case "glode":
				gloItCheckoutPage.enterText(gloItCheckoutPage.couponCodeInputField, UrlBuilder.getMessage(couponCode));
				gloItCheckoutPage.ClickOnApplyDiscountButton();
				break;
			default:
				gloItCheckoutPage.clickOnCouponCodeLink();
				gloItCheckoutPage.ClickOnRemoveDiscountButton();
				gloItCheckoutPage.enterText(gloItCheckoutPage.couponCodeInputField, UrlBuilder.getMessage(couponCode));
				gloItCheckoutPage.ClickOnApplyDiscountButton();
		}
	}

	@And("^promocode '(.*)' message displayed$")
	public void promocode_Sucess_Message(String expectedErrorMessage) throws Throwable {
		Thread.sleep(5000);
		assertTrue((gloItCheckoutPage.validPromoCodeSucessMsg().contains(UrlBuilder.getMessage(expectedErrorMessage))));
	}

	@And("^promocode '(.*)' error message displayed$")
	public void promocode_error_Message(String expectedErrorMessage) {
		String actualErrorText=gloItCheckoutPage.invalidPromoCodeError();
		if(actualErrorText!=null)
			assertTrue((actualErrorText.contains(UrlBuilder.getMessage(expectedErrorMessage))));
		else
			assertTrue(getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedErrorMessage)));
		}

	@And("^promocode invalidPromoCodeError.key error message displayed$")
	public void promocode_error_Message() {
		gloItCheckoutPage.promocode_error_Message();
	}

	@And("Select COD CheckBOX")
  	public void selectCODCheckBox(){
		gloItCheckoutPage.cashOnDeliveryCheckBox();

	}
	@And("^Fill Guest checkout detail form$")
	public void fillGuestCheckoutDetailForm() {
		gloItCheckoutPage.fillGuestCheckoutDetailForm();
	}

	@When("^user add the new shipping address with NIP-length-(.*)$")
	public void userAddTheNewShippingAddressWithNIPLength(String nip) {
		gloItCheckoutPage.populateAddressFields();
		addNewAddressPage.populateTaxIDFieldWith(nip);
		gloItCheckoutPage.clickSaveShippingAddressButton();
	}

	@When("^user attempts to add shipping address as with following NIP Then assert error message$")
	public void userAttemptsToAddShippingAddressAsWithFollowingNIPThenAssertErrorMessage(List<Map<String, String>> nipMap) {
		gloItCheckoutPage.populateAddressFields();
		nipMap.forEach(map -> {
			addNewAddressPage.populateTaxIDFieldWith(map.get("pattern"));
			gloItCheckoutPage.clickSaveShippingAddressButton();
			String expectedMessage = UrlBuilder.getMessage(map.get("expected"));
			softAssertions.assertThat(addNewAddressPage.getWebDriver().getPageSource().contains(expectedMessage))
					.as("Expected Message: "+ expectedMessage + " :is not displayed in the page")
					.isTrue();
		});
		softAssertions.assertAll();
	}

	@And("^enter a value for resale code$")
	public void enterAValueForResaleCode() {
		paymentPage.enterResaleCode();
	}

	@And("^assert Newsletter Subscription option not displayed for Free Trial Checkout$")
	public void assertNewsletterSubscriptionOptionNotDisplayedOnFreeTrialCheckout() {
		assertTrue(gloItCheckoutPage.invisibilityOfElementLocated(gloItHomePage.NEWSLETTER_SUBSCRIPTION_CHECKBOX_CHECKOUT));
	}

	@And("^assert Newsletter Subscription option not selected by default$")
	public void assertNewsletterSubscriptionOptionNotSelectedByDefault() {
		assertTrue(!gloItCheckoutPage.waitForExpectedElement(gloItHomePage.NEWSLETTER_SUBSCRIPTION_CHECKBOX_CHECKOUT,10).isSelected());
	}

	@And("^user clicks Newsletter Subscription option on Checkout$")
	public void userClicksNewsletterSubscriptionOptionOnCheckout() {
		gloItCheckoutPage.userClicksNewsletterSubscriptionOptionOnCheckout();
	}

	@And("^assert message '(.*)' is displayed in header$")
	public void assertMessageIsDisplayedInHeader(String strExpectedText){
		switch (UrlBuilder.getLocale()){
			case "pl":
				gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.FREE_GIFT_MESSAGE);
				assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.FREE_GIFT_MESSAGE,20).getText().contains(UrlBuilder.getMessage(strExpectedText)));
		    break;
			default:
				assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.HEADER_PROMO_MESSAGE,10).getText().contains(UrlBuilder.getMessage(strExpectedText)));
		}
	}

	@And("^add two free gifts to cart and assert user is not allowed to checkout$")
	public void addTwoFreeGiftsToCartAndAssertUserIsNotAllowedToCheckout() {
		//gloItHomePage.addFreeGiftPacksToMiniBasket(2);
		gloItHomePage.waitForMiniBasketToLoadAndProceedToCheckout();
		assertTrue(gloItCheckoutPage.doesURLContain("/checkout/cart/"));
	}

	@And("^add two free gifts to cart and assert user is allowed to checkout$")
	public void addTwoFreeGiftsToCartAndAssertUserIsAllowedToCheckout() throws InterruptedException {
		gloItCheckoutPage.addTwoFreeGiftsToCartAndAssertUserIsAllowedToCheckout();
	}

	@And("^add one free consumable pack and assert user is allowed to checkout$")
	public void addFreeConsumablePackToCartAndAssertUserIsAllowedToCheckout() {
		gloItCheckoutPage.addFreeConsumablePackToCartAndAssertUserIsAllowedToCheckout();
	}

	@And("^add one free consumable pack and assert user is not allowed to checkout$")
	public void addFreeConsumablePackToCartAndAssertUserIsNotAllowedToCheckout() {
		gloItCheckoutPage.addFreeConsumablePackToCartAndAssertUserIsNotAllowedToCheckout();
	}

	@And("^assert Free gift item is not displayed$")
	public void assertFreeGiftItemIsNotDisplayed() {
		gloItCheckoutPage.assertFreeGiftItemIsNotDisplayed();
	}

	@And("^click on proceed to checkout for guest user$")
	public void clickOnProceedToCheckoutButtonForGuestUser() {
		gloItCheckoutPage.clickOnProceedToCheckoutButtonForGuestUser();
	}

	@And("^user select payu mbank payment option$")
	public void userClickOnPayuMbankPymentOption() {
		gloItCheckoutPage.userClickOnPayuMbankPymentOption();
	}

	@And("^user select payu blik payment option$")
	public void userClickOnPayuBlikPymentOption() {
		gloItCheckoutPage.userClickOnPayuBlikPymentOption();
	}

	@And("^Customer makes payu payment with \"([^\"]*)\"$")
	public void customerMakesAPayuPymentWith(String paymentType) throws Throwable {
		switch (paymentType) {
			case "payu":
				gloItCheckoutPage.selectAndPurchaseThroughPayU_PAYMENT();
				break;
			case "COD":
				gloItCheckoutPage.cashOnDeliveryCheckBox();
				break;
			case "blik":
				gloItCheckoutPage.selectPayU_blikPAYMENT();
				gloItCheckoutPage.userClickOnSubmitBlikBankSimulator();
				//gloItCheckoutPage.userClickOnLogoutBankSimulator();
				break;
			case "mbank":
				gloItCheckoutPage.selectPayU_mbankPAYMENT();
				gloItCheckoutPage.userClickOnSubmitBankSimulator();
				break;
			case "visa":
				gloItCheckoutPage.selectPayU_PAYMENT();
				gloItCheckoutPage.payThroughPayU_PAYMENT();
				break;
			case "pekao":
				gloItCheckoutPage.selectPekao_PAYMENT();
				gloItCheckoutPage.userClickOnSubmitBankSimulator();
				break;
			case "ipko":
				gloItCheckoutPage.selectIpko_PAYMENT();
				gloItCheckoutPage.userClickOnSubmitBankSimulator();
				break;
			case "Pay gate card":
			paymentPage.clickOnPaygatePaymentRadioButton();
			paymentPage.paymentCardZA();
				break;
			case "Sid Secure EFT":
					paymentPage.clickOnPaygatePaymentRadioButton();
					paymentPage.clickPlaceOrder();
					paymentPage.sidPayment();
					break;
		}
		orderSuccessPage.eyesCheckOrderSuccessPage();
	}

	@And("^assert loader is displayed$")
	public void assertLoaderIsDisplayed() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.LOADER_GLOPL, 20).isDisplayed());
	}

	@And("^assert the applied discount code '(.*)' is still present in basket$")
	public void assertAppliedDiscountCodeIsPresentInBasketAfterLogin(String strVoucher) {
		gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.VOUCHER_CODE_APPLIED,15);
		for (WebElement ele : gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.VOUCHER_CODE_APPLIED)) {
			if (ele.getText().toLowerCase().contains("off") || ele.getText().toLowerCase().contains("TEST")){
				assertTrue(ele.getText().toLowerCase().contains(UrlBuilder.getMessage(strVoucher).toLowerCase()));
				break;}
		}
	}

	@And("^launch URL '(.*)' with coupon and product SKU and assert it returns success$")
	public void launchUrlWithCouponAndProductSKUAndAssertReturnsSuccess(String strURL) throws Throwable {
		boolean blnSuccess=false;
		gloItCheckoutPage.getWebDriver().navigate().to(gloItCheckoutPage.getCurrentUrl()+UrlBuilder.getMessage(strURL));
		blnSuccess=gloItCheckoutPage.verifyURLStatus(gloItCheckoutPage.getCurrentUrl()) && gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.VOUCHER_APPLIED_SUCCESS_MSG).size()>0;
		scenarioContext.setContext(COUPON_PRODUCT_URL_ISVALID,blnSuccess);
		if(blnSuccess) {
			assertTrue(gloItCheckoutPage.getCurrentUrl().equalsIgnoreCase(UrlBuilder.getUrl()+"/"));
			assertTrue(gloItCheckoutPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage("couponAppliedSuccessMsg.key")));
			assertTrue(gloItCheckoutPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage("productAddedToCartMsg.key")));
			gloItCheckoutPage.assertTrueWithCustomError("1",gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.GLO_BASKET_QTY,10).getText());
		}
	}

	@And("^user navigate to mini cart as a guest user with coupon '(.*)' applied and place order via URL$")
	public void userNavigatesToMiniCartWithCouponAppliedAndPlaceOrder(String strCoupon) throws Throwable {
		Boolean blnCouponValid = (Boolean) scenarioContext.getContext(COUPON_PRODUCT_URL_ISVALID);
		if(blnCouponValid) {
			gloItCheckoutPage.userNavigatesToMiniCartWithCouponAppliedAndCheckout(strCoupon);
			customerMakesAPayuPymentWith("visa");
			gloItCheckoutPage.GloIt_assert_ThankYou_Is_Displayed("thankYouMessageHeading.key");
		}
	}

	@And("^gloJp user navigates to checkout page$")
	public void userNavigatesToCheckoutPage() {
		//gloItCheckoutPage.navigateToCheckout();
		homePage.proceedToCheckoutButton();
	}

	@And("^verify Terms and Conditions is present$")
	public void verifyTermsAndConditionsIsPresent() {
		Assert.assertTrue(gloItCheckoutPage.isTermsAndConditionsBlockPresent());
	}

	@And("^verify title for terms and conditions$")
	public void verifyTitleForTermsAndConditions() {
		gloItCheckoutPage.verifyTermsAndConditionsTitle();
	}

	@And("^verify title for Glo contacts$")
	public void verifyTitleForGloContacts() {
		Assert.assertTrue(gloItCheckoutPage.isGloContactsBlockPresent());
		//gloItCheckoutPage.verifyGloContactsTitle();
	}

	@And("^verify title for Velo contacts$")
	public void verifyTitleForVeloContacts() {
		Assert.assertTrue(gloItCheckoutPage.isVeloContactsBlockPresent());
		//gloItCheckoutPage.verifyVeloContactsTitle();
	}

	@And("^verify Place Order button is enabled$")
	public void verifyPlaceOrderButtonIsEnabled() {
		Assert.assertTrue(gloItCheckoutPage.placeOrderButtonIsEnabled());
		gloItCheckoutPage.getActualGrandTotal();
		gloItCheckoutPage.getSubTotalForDiscount();
	}

	@And("^user clicks on place order button$")
	public void userClicksOnPlaceOrderButton() {
		if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
			paymentPage.eyesCheckCheckoutPage();
		}
		gloItCheckoutPage.clickCODOnPlaceOrder();
	}

	@And("^user clicks on COD payment method$")
	public void userClicksOnCODPaymentMethod() {
		gloItCheckoutPage.clickCODPaymentOption();
	}

	@And("^user clicks on Card payment method$")
	public void userClicksOnCardPaymentMethod() {
		gloItCheckoutPage.clickCardPaymentOption();
		gloItCheckoutPage.getActualGrandTotal();
	}

	@And("^enters Credit card details$")
	public void entersCreditCardDetails() {
		gloItCheckoutPage.enterCreditCardDetails();
	}

	@And("^tick agree to terms and conditions for card payment$")
	public void tickAgreeToTermsAndConditionsForCardPayment() {
		gloItCheckoutPage.clickTermsAndConditions();
	}

	@Then("^Order Summary is displayed$")
	public void orderSummaryIsDisplayed() {
		Assert.assertTrue(gloItCheckoutPage.isOrderSummaryDisplayed());
	}

	@And("^total cart value and number of items is displayed$")
	public void totalCartValueAndNumberOfItemsIsDisplayed() {
		gloItCheckoutPage.totalCartValueAndItemsCount();
	}

	@And("^user clicks on plus icon and product list is displayed$")
	public void userClicksOnPlusIconAndProductListIsDisplayed() {
		gloItCheckoutPage.verifyProductListIsDisplayed();
		gloItCheckoutPage.getCheckoutProductDetails();
		gloItCheckoutPage.getProductsCountOnCheckout();
	}

	@And("^verify name of each item is dsiplayed$")
	public void verifyNameOfEachItemIsDsiplayed() {
		Assert.assertTrue(gloItCheckoutPage.isProductNameDisplayed());
	}

	@And("^verify quantity of each item in cart is dsiplayed$")
	public void verifyQuantityOfEachItemInCartIsDsiplayed() {
		Assert.assertTrue(gloItCheckoutPage.isProductQtyDisplayed());
	}

	@And("^is dsiplayed price of each item is dsiplayed$")
	public void isDsiplayedPriceOfEachItemIsDsiplayed() {
		Assert.assertTrue(gloItCheckoutPage.isProductPriceDisplayed());
	}

	@And("^verify products added$")
	public void verifyProductsAdded() {
		gloItCheckoutPage.assertProductCountOnCheckout();
		gloItCheckoutPage.assertProductsAddedWithCheckout();
	}

	@And("^verify grand total on checkout page$")
	public void verifyGrandTotalOnCheckoutPage() {
		gloItCheckoutPage.assertOrderSummaryTotalWithCheckoutGrandTotal();
	}

	@And("^user selects courier delivery option$")
	public void userSelectsCourierDeliveryOption() {
		gloItCheckoutPage.selectCourierDeliveryOption();
		gloItCheckoutPage.getCourierDeliveryFeeOnCheckout();
	}

	@And("^user clicks on Card place order button$")
	public void userClicksOnCardPlaceOrderButton() {
		gloItCheckoutPage.getActualGrandTotal();
		gloItCheckoutPage.clickOnCardPlaceOrder();
	}

	@And("^verify Card Place Order button is enabled$")
	public void verifyCardPlaceOrderButtonIsEnabled() {
		Assert.assertTrue(gloItCheckoutPage.placeCardOrderButtonIsEnabled());
	}

	@And("^user selects Standard delivery option$")
	public void userSelectsStandardDeliveryOption() {
		gloItCheckoutPage.selectStandardDeliveryOption();
	}

	@And("^enters invalid Credit card details$")
	public void entersInvalidCreditCardDetails() {
		gloItCheckoutPage.enterInvalidCreditCardDetails();
	}

	@And("^verify discount code field is present$")
	public void verifyDiscountCodeFieldIsPresent() {
		homePage.waitForAjaxElementNotToBePresent(getWebDriver(),20);
		Assert.assertTrue(gloItCheckoutPage.applyDiscountCodeModulePresent());
	}

	@And("^enter valid discount code and click apply$")
	public void enterValidDiscountCodeAndClickApply() {
		gloItCheckoutPage.enterValidDiscountCode();
		gloItCheckoutPage.ClickOnApplyDiscountButton();
	}

	@And("^assert success message for coupon is displayed$")
	public void assertSuccessMessageForCouponIsDisplayed() {
		Assert.assertTrue(gloItCheckoutPage.successMessageIsDisplayed());
		homePage.assertTrueWithCustomError(UrlBuilder.getMessage("discountCodeSuccess.key"),homePage.waitForExpectedElement(GloItProductListPage.SUCCESS_MESSAGE).getText());
	}

	@And("^assert remove discount coupon button is available$")
	public void assertRemoveDiscountCouponButtonIsAvailable() {
		Assert.assertTrue(gloItCheckoutPage.removeCouponCodeOption());
	}

	@And("^enter invalid discount code and click apply$")
	public void enterInvalidDiscountCodeAndClickApply() {
		gloItCheckoutPage.enterInValidDiscountCode();
		gloItCheckoutPage.ClickOnApplyDiscountButton();
	}

	@And("^assert error message for coupon is displayed$")
	public void assertErrorMessageForCouponIsDisplayed() {
		Assert.assertTrue(gloItCheckoutPage.couponCodeErrorMessageIsDisplayed());
		homePage.assertTrueWithCustomError(UrlBuilder.getMessage("discountCodeError.key"),gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.ERROR_MESSAGE).getText());
	}

	@And("^remove existing coupon and add new coupon code$")
	public void removeExistingCouponAndAddNewCouponCode() {
		gloItCheckoutPage.removeCouponCode();
		gloItCheckoutPage.enterValidDiscountCode();
		gloItCheckoutPage.ClickOnApplyDiscountButton();
	}

	@And("^assert the order summary is updated$")
	public void assertTheOrderSummaryIsUpdated() {
		gloItCheckoutPage.assertOrderSummaryTotal();
	}

	@And("^verify associated fee is displayed in the Order summary$")
	public void verifyAssociatedFeeIsDisplayedInTheOrderSummary() {
		gloItCheckoutPage.CODFeeIsDisplayed();
		gloItCheckoutPage.getCODFeeOnCheckout();
	}

	@And("^order summary prices breakdown is displayed$")
	public void orderSummaryPricesBreakdownIsDisplayed() {
		Assert.assertTrue(gloItCheckoutPage.orderSummaryPricesBreakdownIsDisplayed());
	}

	@And("^discount heading for Deductions is visible$")
	public void discountHeadingForDeductionsIsVisible() {
		Assert.assertTrue(gloItCheckoutPage.discountHeadingIsDisplayed());
		gloItCheckoutPage.verifyDiscountHeading();
	}

	@And("^a note saying promotion are not eligible for sticks/Velo$")
	public void aNoteSayingPromotionAreNotEligibleForSticksVelo() {
		gloItCheckoutPage.verifyPromotionNoteDisplayed();
	}

	@And("^a note saying tax is included is visible$")
	public void aNoteSayingTaxIsIncludedIsVisible() {
		gloItCheckoutPage.verifyTaxIncludedNote();
	}

	@And("^delivery fee and total amount is visible$")
	public void deliveryFeeAndTotalAmountIsVisible() {
		softAssertions.assertThat(gloItCheckoutPage.deliveryFeeIsDisplayed());
		softAssertions.assertThat(gloItCheckoutPage.totalAmountInOrderSummaryIsDisplayed());
		softAssertions.assertAll();
	}

	@And("^user clicks on the tile and navigates to that page$")
	public void userClicksOnTheTileAndNavigatesToThatPage(DataTable table) {
		gloItProductListPage.addProductsToBasket(table);
		if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
			plp.eyesCheckPlpPage();
		}
	}

/*	@And("^assert '(.*)' is displayed (GLO)$")
	public void assertShippingAddressKeyIsDisplayed(String strExpectedText) {
		gloItCheckoutPage.assertShippingAddressHeader(strExpectedText);
		gloItCheckoutPage.assertShippingAddressIsDisplayed();
	}*/

	@And("^assert payment methods section is present$")
	public void assertPaymentMethodsSectionIsPresent() {
		Assert.assertTrue(gloItCheckoutPage.assertPaymentMethodsIsDisplayed());
	}

	@And("^assert fee is displayed for COD payment$")
	public void assertFeeIsDisplayedForCODPayment() {
		Assert.assertTrue(gloItCheckoutPage.assertFeeIsDisplayedForCODpayment());
	}

	@And("^assert update address link$")
	public void assertUpdateAddressLink() {
		Assert.assertTrue(gloItCheckoutPage.assertUpdateAddressLinkPresent());
		gloItCheckoutPage.clickOnUpdateAddressLink();
	}

	@And("^user clicks on '(.*)' method$")
	public void userClicksOnPaymentMethod(String paymentMethod) {
		try {
			homePage.clickByElementByQueryJSExecutor(By.id(UrlBuilder.getMessage(paymentMethod)));
		} catch (Exception e) {
			LOG.info("Couldn't click due to : " + e.getMessage());
		}
	}

	@And("^assert error message displayed for Delivery date time$")
	public void assertErrorMessageDisplayedForDeliveryDateTime() {
		Assert.assertTrue(gloItCheckoutPage.isSelectDeliveryDateTimeErrorDisplayed());
	}

	@And("^user selects delivery date and time$")
	public void userSelectsDeliveryDateAndTime() throws ParseException {
		gloItCheckoutPage.selectDeliveryDateTime();
	}

	@And("^verify select delivery date and time fields are displayed$")
	public void verifySelectDeliveryDateAndTimeFieldsAreDisplayed() {
		softAssertions.assertThat(gloItCheckoutPage.isSelectTimeDisplayed());
		softAssertions.assertAll();
	}

	@And("^user clicks on collapse icon$")
	public void userClicksOnCollapseIcon() {
		gloItCheckoutPage.collapseProductList();
	}

	@And("^assert product name, price,image and quantity is displayed for each product$")
	public void assertProductNamePriceImageAndQuantityIsDisplayedForEachProduct() {
		gloItCheckoutPage.isNamePriceQuantityDisplayed();
	}


	@And("^assert fee is displayed for courier delivery$")
	public void assertFeeIsDisplayedForCourierDelivery() {
		Assert.assertTrue(gloItCheckoutPage.assertCourierDeliveryFeeIsDisplayed());
	}

	@And("^enters Credit card details '(.*)' '(.*)' '(.*)' '(.*)'$")
	public void entersCreditCardDetailsCardNumberExpiryDateCvv(String cardNumber, String expiryMonth, String expiryYear, String cvv) {
		String card_number = (UrlBuilder.getMessage(cardNumber));
		String Cvv = (UrlBuilder.getMessage(cvv));
		String expiry_month = (UrlBuilder.getMessage(expiryMonth));
		String expiry_year = (UrlBuilder.getMessage(expiryYear));
		gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.CARD_NUMBER, 10).sendKeys(card_number);
		gloItCheckoutPage.selectValueFromDropDownByby(expiry_month, gloItCheckoutPage.MONTH_DD);
		gloItCheckoutPage.selectValueFromDropDownByby(expiry_year, gloItCheckoutPage.YEAR_DD);
		gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.CVV_NUMBER, 10).sendKeys(Cvv);
	}

	@And("^assert '(.*)' '(.*)' '(.*)' message displayed for invalid card details$")
	public void assertErrorMessageDisplayedForInvalidCardDetails(String expectedCardText, String expectedCvvText, String expectedDateError) {
		try {
			if ("insufficientFundsCardError_text.key".equals(expectedCardText)) {
				softAssertions.assertThat(getWebDriver().findElement(gloItCheckoutPage.insufficientCardError).isDisplayed());
				homePage.assertTrueWithCustomError((UrlBuilder.getMessage(expectedCardText)), getWebDriver().findElement(gloItCheckoutPage.insufficientCardError).getText());
			} else {
				softAssertions.assertThat(homePage.waitForExpectedElement(gloItCheckoutPage.cardError).isDisplayed());
				softAssertions.assertThat(homePage.waitForExpectedElement(gloItCheckoutPage.cvvError).isDisplayed());
				homePage.assertTrueWithCustomError((UrlBuilder.getMessage(expectedCardText)), getWebDriver().findElement(gloItCheckoutPage.cardError).getText());
				homePage.assertTrueWithCustomError((UrlBuilder.getMessage(expectedCvvText)), getWebDriver().findElement(gloItCheckoutPage.cvvError).getText());
				homePage.assertTrueWithCustomError((UrlBuilder.getMessage(expectedDateError)), getWebDriver().findElement(gloItCheckoutPage.expiryDateError).getText());
				homePage.assertTrueWithCustomError((UrlBuilder.getMessage("blankYearError_text.key")), getWebDriver().findElement(gloItCheckoutPage.expiryYearError).getText());
			}
			softAssertions.assertAll();
		} catch (Exception e) {
			LOG.info("Exception is ->" + e);
		}
	}

	@And("^user clicks on add to cart button$")
	public void userClicksOnAddToCartButton() {
		gloItProductListPage.clickOnAddToCartButton();
	}

	@And("^assert prices for products added$")
	public void assertPricesForProductsAdded() {
		gloItCheckoutPage.assertProductsAddedWithCheckout();
		gloItCheckoutPage.assertProductPricesWithCategoryTotal();
	}

	@And("^assert price and quantity for updated product$")
	public void assertPriceAndQuantityForUpdatedProduct() {
		gloItCheckoutPage.assertUpdatedPriceOnCheckout();
		gloItCheckoutPage.assertUpdatedQuantityOnCheckout();
	}

	@And("^user clicks on cancel on next page$")
	public void userClicksOnCancelOnNextPage() {
		gloItCheckoutPage.cancelPayment();
	}

	@And("^verify user is redirected to '(.*)' page$")
	public void verifyUserIsRedirectedToCheckoutKeyPage(String key) {
		try {
			homePage.urlToContainInSeconds("/shop/checkout",50);
		}catch(TimeoutException te){
			homePage.urlToContainInSeconds("/shop/checkout",50);
		}
		homePage.assertTrueWithCustomError((UrlBuilder.getMessage(key)),homePage.getCurrentPageTitle());
	}

	@And("^assert courier fee amount$")
	public void assertCourierFeeAmount() {
		gloItCheckoutPage.getCourierDeliveryFeeOnCheckout();
		gloItCheckoutPage.assertCourierDeliveryAmount();
	}

	@And("^assert COD fee amount on checkout page$")
	public void assertCODFeeAmountOnCheckoutPage() {
		gloItCheckoutPage.assertCODFeeAmount();
	}

	@And("^update address$")
	public void updateAddress() {
		addNewAddressPage.populateAllAddressFieldsExceptForFirstAndLastName();
		addNewAddressPage.pressSaveAddressButton();
	}

	@And("^user signout$")
	public void userSignout() {
		switch (UrlBuilder.getLocale()){
			case "glojp" :
				homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 5);
				homePage.waitForItemToBeClickableAndClick(By.linkText(UrlBuilder.getMessage("signOut.key")), 10);
				break;
			default:
				homePage.waitForAjaxElementNotToBePresent(getWebDriver(), 10);
				homePage.waitForItemToBeClickableAndClick(By.linkText("Sign Out"), 10);
		}

	}

	@And("^verify courier delivery option is selected$")
	public void verifyCourierDeliveryOptionIsSelected() {
		gloItCheckoutPage.isCourierDeliveryOptionSelected();
	}

	@And("^verify address contains updated text$")
	public void verifyAddressContainsUpdatedText() {
		gloItProductListPage.waitForLoaderToDisappear();
		gloItCheckoutPage.verifyUpdatedAddress();
	}

	@And("^assert terms and conditions text for payment$")
	public void assertTermsAndConditionsTextForPayment() {
		gloItCheckoutPage.clickCODPaymentOption();
		homePage.assertTrueWithCustomError(UrlBuilder.getMessage("termsAndConditionsText.key"), homePage.getTextFor(PaymentPage.TermsAndCondtionCheckBoxJP));
		gloItCheckoutPage.clickCardPaymentOption();
		homePage.assertTrueWithCustomError(UrlBuilder.getMessage("termsAndConditionsText.key"), homePage.getTextFor(gloItCheckoutPage.TERMS_AND_CONDITIONS_TEXT));
	}

	@And("^assert that '(.*)' and '(.*)' is displayed$")
	public void assertThatDiscountHeadingKeyAndDiscountNoteKeyIsDisplayed(String strExpectedText, String strExpectedtext1) {
		gloItCheckoutPage.assertDiscountHeadingAndNote(strExpectedText, strExpectedtext1);
	}

	@And("^assert cvv hint for card payment$")
	public void assertCvvHintForCardPayment() {
		gloItCheckoutPage.assertCvvHint();
	}

	@And("^assert payment methods section is not displayed$")
	public void assertPaymentMethodsSectionIsNotDisplayed() {
		assertFalse(gloItCheckoutPage.assertPaymentMethodsIsDisplayed());
	}

	@And("^assert order summary section is not displayed$")
	public void assertOrderSummarySectionIsNotDisplayed() {
		assertFalse(gloItCheckoutPage.orderSummaryPricesBreakdownIsDisplayed());
	}

	@And("^address2 is not available for user$")
	public void addressIsNotAvailableForUser() {
		gloItCheckoutPage.ocrAddressIsNotAvailable();
	}

	@And("^verify Place Order button is disabled$")
	public void verifyPlaceOrderButtonIsDisabled() {
		gloItProductListPage.waitForLoaderToDisappear();
		Assert.assertFalse(gloItCheckoutPage.placeOrderButtonIsEnabled(),"User is not able to place order");
	}

	@And("^assert update address link is present for address2$")
	public void assertUpdateAddressLinkIsPresentForAddress() {
		Assert.assertTrue(gloItCheckoutPage.assertUpdateAddress2LinkPresent());
	}

	@And("^user selects fluid delivery address$")
	public void userSelectsFluidDeliveryAddress() {
		gloItCheckoutPage.userSelectsFluidDeliveryAddress();
	}

	@And("^verify shipping address1 is non selectable$")
	public void verifyShippingAddressIsNonSelectable() {
		gloItProductListPage.waitForLoaderToDisappear();
		Assert.assertFalse(gloItCheckoutPage.fluidAddressIsEnabled());	}

	@And("^assert OCR delivery address error is displayed$")
	public void assertOCRDeliveryAddressErrorIsDisplayed() {
		assertTrue(gloItCheckoutPage.ocrAddressErrorIsDisplayed());
	}

	@And("^user clicks on update address link for address2$")
	public void userClicksOnUpdateAddressLinkForAddress() {
		gloItCheckoutPage.clickOnUpdateAddress2Link();
	}

	@And("^updates shipping address2$")
	public void updatesShippingAddress() throws IOException, InterruptedException {
		gloItCheckoutPage.updateShippingAddress2WithValidDetails();
	}

	@And("^address2 is pending verification for the user$")
	public void addressIsPendingVerificationForTheUser() {
		gloItCheckoutPage.ocrAddressVerificationStatusIsPending();
	}

	@And("^assert OCR static message is displayed$")
	public void assertOCRStaticMessageIsDisplayed() {
		assertTrue(gloItCheckoutPage.ocrStaticMessageIsDisplayed());
	}

	@And("^user selects verified delivery address$")
	public void userSelectsVerifiedDeliveryAddress() {
		gloItCheckoutPage.userSelectsOcrDeliveryAddress();
	}

	@And("^assert address2 status is verified$")
	public void assertAddressStatusIsVerified() throws IOException, InterruptedException {
		gloItCheckoutPage.ocrAddressVerificationStatusIsVerified();
	}

	@And("^assert address2 is reflected in address1$")
	public void assertAddressIsReflectedInAddress() {
		homePage.waitForAjaxElementNotToBePresent(getWebDriver(),30);
		gloItCheckoutPage.getFluidAddress();
		homePage.assertTrueWithCustomError(GloItCheckoutPage.shippingAddress2[0],GloItCheckoutPage.shippingAddress1[0]);
	}

	@And("^address is not saved for user$")
	public void addressIsNotSavedForUser() {
		gloItCheckoutPage.noAddressSaved();
	}

	@And("^assert update address link is present for address1$")
	public void assertUpdateAddressLinkIsPresentForAddress1() {
		Assert.assertTrue(gloItCheckoutPage.assertUpdateAddressLinkPresent());
	}

	@And("^updates shipping address2 with invalid details$")
	public void updatesShippingAddressWithInvalidDetails() {
		gloItCheckoutPage.updateShippingAddress2WithInvalidDetails();
	}

	@And("^verify shipping address2 is non selectable$")
	public void verifyShippingAddress2IsNonSelectable() {
		Assert.assertFalse(gloItCheckoutPage.ocrAddressIsEnabled());
	}

	@And("^user enters more than '(.*)' characters in Street field and assert error message '(.*)'$")
	public void userEntersMoreThanCharactersLimitInStreetFieldOnCheckoutPage(String strLimit,String strErrorMessage){
	if(gloItCheckoutPage.doesURLContain("checkout")){
		gloItCheckoutPage.enterDataAndWait(paymentPage.STREET_ADDRESS_FIELD_CHECKOUT,random(Integer.valueOf(strLimit),ALPHABETS));
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.STREET_ADDRESS_MAX_LIMIT_ERROR).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
	else{
		gloItCheckoutPage.enterDataAndWait(addNewAddressPage.STREET_ADDRESS_LINE_1_INPUT,random(Integer.valueOf(strLimit),ALPHABETS));
		gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
		assertTrue(gloItCheckoutPage.waitForExpectedElement(addNewAddressPage.Street1Error,5).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
	}

	@And("^user enters more than '(.*)' characters in House number field and assert error message '(.*)'$")
	public void userEntersMoreThanCharactersLimitInHouseNumberFieldOnCheckoutPage(String strLimit,String strErrorMessage){
	if(gloItCheckoutPage.doesURLContain("checkout")){
		gloItCheckoutPage.enterDataAndWait(paymentPage.STREET_ADDRESS2_FIELD,random(Integer.valueOf(strLimit),ALPHABETS));
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.STREET_ADDRESS_MAX_LIMIT_ERROR).get(1).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
	else{
		gloItCheckoutPage.enterDataAndWait(addNewAddressPage.streetAddressLine2Input,random(Integer.valueOf(strLimit),ALPHABETS));
		gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
		assertTrue(gloItCheckoutPage.waitForExpectedElement(addNewAddressPage.STREET2_ERROR,5).getText().contains(UrlBuilder.getMessage(strErrorMessage))); }
	}

	@And("^user enters more than '(.*)' characters in Apartment number field and assert error message '(.*)'$")
	public void userEntersMoreThanCharactersLimitInApartmentNumberFieldOnCheckoutPage(String strLimit,String strErrorMessage){
	if(gloItCheckoutPage.doesURLContain("checkout")){
		gloItCheckoutPage.enterDataAndWait(gloItCheckoutPage.STREET_ADDRESS_FIELD3_CHECKOUT,random(Integer.valueOf(strLimit),ALPHABETS));
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.STREET_ADDRESS_MAX_LIMIT_ERROR).get(2).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
	else{
		gloItCheckoutPage.enterDataAndWait(addNewAddressPage.streetAddressLine3Input,random(Integer.valueOf(strLimit),ALPHABETS));
		gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
		assertTrue(gloItCheckoutPage.waitForExpectedElement(addNewAddressPage.STREET3_ERROR,5).getText().contains(UrlBuilder.getMessage(strErrorMessage))); }
	}

	@And("^user creates a new address on Checkout page - GloPL$")
	public void userCreatesNewAddressOnCheckoutPageForGloPL() {
		gloItCheckoutPage.userCreatesNewAddressOnCheckoutPageForGloPL();
	}

	@And("assert COD option is present")
	public void assertTheCODOptionIsPresent(){
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.CHECKBOX_COD,3).isDisplayed());
	}

	@And("^assert Mail Bin Delivery option is displayed$")
	public void assertMailBinDeliveryOptionIsDisplayed() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.MAIL_BIN_DELIVERY_METHOD).isDisplayed());
	}

	@And("^assert courier delivery option is not displayed$")
	public void assertCourierDeliveryOptionIsNotDisplayed() {
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.COURIER_DELIVERY_METHOD).size()==0);
	}

	@And("^assert select delivery date and time fields are not displayed$")
	public void assertSelectDeliveryDateAndTimeFieldsAreNotDisplayed() {
		try{
			assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.TIME).isEnabled()); }
		catch(Exception ex){
			LOG.info("Select Delivery Date and Time is not displayed when Mail Bin conditions are met.");}
	}

	@And("^assert COD payment method is not displayed$")
	public void assertCODPaymentMethodIsNotDisplayed() {
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.COD_PAYMENT).size()==0);
	}

	@And("^assert only GMO Card payment method is displayed$")
	public void assertCardGMOPaymentMethodIsDisplayed() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.GMO_PAYMENT_METHOD).isDisplayed());
	}

	@And("^user clicks on the '(.*)' product category,navigate to PDP and add quantity '(.*)' to cart$")
	public void clicksOnProductTileNavigateToPDPAndAddQuantityToCart(String strProdCategory,String strQuantity) {
		gloItProductListPage.clickOnProductTileNavigateToPDPAndAddQuantityToCart(strProdCategory,strQuantity);
	}

	@And("^assert Mail Bin Delivery option is not displayed$")
	public void assertMailBinDeliveryOptionNotDisplayed() {
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.MAIL_BIN_DELIVERY_METHOD).size()==0);
	}

	@And("^assert COD payment method is displayed$")
	public void assertCODPaymentMethodIsDisplayed() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.COD_PAYMENT).isDisplayed());
	}

	@And("^assert Courier Delivery option is displayed$")
	public void assertCourierDeliveryOptionIsDisplayed() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.COURIER_DELIVERY_METHOD)
				.isDisplayed());
	}
		@And("^user enters '(.*?)' numeric characters in Tax Number field and assert format$")

	public void userEntersCharactersLimitInTaxNumberFieldOnCheckoutPage(String strCharacters) {
		gloItCheckoutPage.userEntersCharactersLimitInTaxNumberFieldOnCheckoutPage(strCharacters);
	}

	@And("^user clears Tax Number data as the field is optional$")
	public void userClearsTaxNumberDataAsFieldIsOptional() {
		gloItCheckoutPage.userClearsTaxNumberDataAsFieldIsOptional();
	}

	@And("^user enters '(.*?)' letters in Tax Number field and assert error message '(.*?)'$")
	public void userEntersLettersInTaxNumberFieldOnCheckoutPageAndAssertError(String strLimit,String strErrorMessage){
		gloItCheckoutPage.userEntersLettersInTaxNumberFieldOnCheckoutPageAndAssertError(strLimit,strErrorMessage);
	}

	@And("^user enters more than '(.*?)' characters in Tax Number field and assert field accepts '(.*)' digits as format$")
	public void userEntersMoreThanLimitCharactersInInTaxNumberFieldOnCheckoutPageAndAssertFormat(String strInput,String strLimit) {
		gloItCheckoutPage
				.userEntersMoreThanLimitCharactersInInTaxNumberFieldOnCheckoutPageAndAssertFormat(strInput,
						strLimit);
	}
	@And("^verify mail bin delivery option is selected$")
	public void verifyMailBinDeliveryOptionIsSelected() {
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.MAIL_BIN_DELIVERY_METHOD,20).isSelected());
	}

	@And("^user clicks on GMO Card payment method when mail bin conditions are met$")
	public void userClicksOnGMOPaymentMethodWhenMailBinConditionsAreMet() {
		gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.GMO_PAYMENT_METHOD,10);
		gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.GMO_PAYMENT_METHOD);
	}

	@Then("^free pack is visible at header$")
	public void freePackIsVisibleAtHeader() {
		assertTrue(gloItCheckoutPage.isElementDisplayedBySeconds(GloItCheckoutPage.FREE_PACK,5));
	}

	@Then("^free pack pop up is displayed$")
	public void freePackPopupIsVisibleAtHeader() {
		gloItCheckoutPage.waitForExpectedElement(GloItCheckoutPage.FREE_PACK_POPUP,10);
		assertTrue(gloItCheckoutPage.isElementDisplayedBySeconds(GloItCheckoutPage.FREE_PACK_POPUP,5));
	}

	@And("^assert world pay page is displayed$")
	public void assertWorldPayPageIsDisplayed() {
		assertTrue(gloItCheckoutPage.isWorldPayPage());
	}

	@And("^assert Fast Address Finder field is not displayed$")
	public void assertFastAddressFinderFieldIsNotDisplayed() {
		assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.FAST_FINDER_ADDRESS_FIELD).size()==0);
	}

	@And("^user enters string values in House number field and assert error message '(.*)'$")
	public void userEntersStringValuesInHouseNumberFieldOnCheckoutPage(String strErrorMessage){
		if(gloItCheckoutPage.doesURLContain("checkout")){
			gloItCheckoutPage.enterDataAndWait(paymentPage.STREET_ADDRESS2_FIELD,random(10,ALPHABETS));
			assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.STREET_ADDRESS_MAX_LIMIT_ERROR).get(1).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
		else{
			gloItCheckoutPage.enterDataAndWait(addNewAddressPage.streetAddressLine2Input,random(10,ALPHABETS));
			gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
			assertTrue(gloItCheckoutPage.waitForExpectedElement(addNewAddressPage.STREET2_ERROR,5).getText().contains(UrlBuilder.getMessage(strErrorMessage))); }
	}

	@And("^user enters string values in Apartment number field and assert error message '(.*)'$")
	public void userEntersStringValuesInApartmentNumberFieldOnCheckoutPage(String strErrorMessage){
		if(gloItCheckoutPage.doesURLContain("checkout")){
			gloItCheckoutPage.enterDataAndWait(gloItCheckoutPage.STREET_ADDRESS_FIELD3_CHECKOUT,random(10,ALPHABETS));
			assertTrue(gloItCheckoutPage.getWebDriver().findElements(gloItCheckoutPage.STREET_ADDRESS_MAX_LIMIT_ERROR).get(2).getText().contains(UrlBuilder.getMessage(strErrorMessage)));}
		else{
			gloItCheckoutPage.enterDataAndWait(addNewAddressPage.streetAddressLine3Input,random(10,ALPHABETS));
			gloItCheckoutPage.clickByElementByQueryJSExecutor(gloItCheckoutPage.USE_THIS_ADDRESS_BUTTON_PL);
			assertTrue(gloItCheckoutPage.waitForExpectedElement(addNewAddressPage.STREET3_ERROR,5).getText().contains(UrlBuilder.getMessage(strErrorMessage))); }
	}

	@And("^user enters '(.*)' characters in City field and assert error message '(.*)'$")
	public void userEntersCharactersLimitInCityField(String strLimit,String strErrorMessage){
		addNewAddressPage.userEntersCharactersLimitInCityField(strLimit,strErrorMessage);
	}

	@And("^assert error message '(.*)' displayed on top of Checkout page$")
	public void assertBlockedPhoneNumberErrorMessageOnTopOfCheckoutPage(String strErrorMessage){
		assertTrue(gloItCheckoutPage.waitForExpectedElement(gloItCheckoutPage.BILLING_ADDRESS_RESTRICTION_ERROR).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
	}

	@And("^assert error message '(.*)' displayed in place of Delivery Methods$")
	public void assertBlockedPhoneNumberErrorMessageInPlaceOfDeliveryMethods(String strErrorMessage){
		assertTrue(gloItCheckoutPage.waitForExpectedElement(paymentPage.ADDRESS_RESTRICTION_ERROR_MESSAGE).getText().contains(UrlBuilder.getMessage(strErrorMessage)));
	}

	@And("^user select PayU as payment option, select TCs and click Place Order$")
	public void userSelectsPayUPaymentOptionSelectTCsAndClickPlaceOrder(){
		gloItCheckoutPage.selectPayU_PAYMENT();
	}
}
