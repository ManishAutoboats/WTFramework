package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.epok.EpokCheckoutPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class EpokCheckoutPageSteps {
	

	EpokCheckoutPage epokCheckoutPage;

	public EpokCheckoutPageSteps(EpokCheckoutPage epokCheckoutPage) {
		this.epokCheckoutPage = epokCheckoutPage;
	}

	@And("^Epok user select Card Payment option$")
	public void epok_Select_Card_Payment_Option() {
		epokCheckoutPage.selectCardPaymentMethod();

	}

	@And("^Epok user select credit card as payment option$")
	public void epok_Select_Credit_Card_As_Payment_Option() {
		epokCheckoutPage.selectCreditCardCheckbox();

	}

	@And("^Epok user select debit card as payment option$")
	public void epok_Select_Debit_Card_As_Payment_Option() {
		epokCheckoutPage.selectDebitCardCheckbox();
	}

	@And("^user select the Master card as type$")
	public void epok_Select_Master_Card_As_Type() {
		epokCheckoutPage.selectMasterCardTypeCheckbox();
	}
	@And("^user select the Visa card as type$")
	public void user_Select_Visa_Card_As_Type() {
		epokCheckoutPage.selectVisaCardTypeCheckbox();
	}

	@And("^user fills Master or Visa card information$")
	public void user_Fills_Master_Card_Or_Visa_Information() throws InterruptedException {
		epokCheckoutPage.enterValidMasterOrVisaCardDetailsAndSubmit();
	}

	@And("^Epok user click on saved card type option checkbox$")
	public void epok_User_Click_Saved_Card_Type_Option_Checkbox() {
		epokCheckoutPage.selectSavedCardTypeCheckbox();

	}

	@And("^select users saved card checkbox$")
	public void epok_User_Select_User_Saved_Card_Checkbox() {
		epokCheckoutPage.selectUsersSavedCard();
	}

	@And("^enter saved card CVV number$")
	public void enter_Saved_Card_CVV_Number() {
		epokCheckoutPage.enterUserSavedCardCVVNumber();
	}

	@And("^Epok check alternative payment order option$")
	public void epokcheckAlternativePaymentOrderOption() {
		epokCheckoutPage.selectOtherPaymentMethodCheckbox();
	}

	@And("^Epok Check paypal option$")
	public void checkPaypalOption() throws Throwable {
		try {
			Thread.sleep(3000);
			epokCheckoutPage.selectPayPalCheckbox();
		} catch (Exception e) {
			Thread.sleep(3000);
			epokCheckoutPage.selectPayPalCheckbox();
		}
	}

	@And("^Epok select agree to terms and conditions$")
	public void tickAgreeToTermsAndConditions() {
		//epokCheckoutPage.selectTermsAndConditionCheckbox();
	}

	@And("^Epok select agree to terms and conditions for card payment$")
	public void epok_Select_Terms_Conditions_For_Card_Payment() {
		//epokCheckoutPage.selectTermsAndCondtionsCard();

	}

	@And("^Epok select place order$")
	public void selectPlaceOrder() throws Throwable {
		Thread.sleep(3000);
		epokCheckoutPage.selectBuyNowButtonPalPal();
		Thread.sleep(15000);
	}

	@And("^Epok select place order from Card$")
	public void selectPlaceOrderFromCard() throws Throwable {
		Thread.sleep(3000);
		epokCheckoutPage.selectBuyNowButtonFromCard();
		Thread.sleep(15000);
	}

	@And("^select PayPal payment method$")
	public void select_PayPal_Payment_Method() {
		epokCheckoutPage.selectPayPalCheckbox();
	}

	@And("^Epok switch address checkbox$")
	public void epok_Switch_Address_Checkbox() {
		epokCheckoutPage.selectSwitchAddressCheckbox();
	}

	@And("^Epok paypal page - click continue$")
	public void paypalPageClickContinue() {
		epokCheckoutPage.waitForExpectedElement(By.cssSelector("input#PMMakePayment")).click();
	}

	@And("^Epok Payment page details confirmed$")
	public void paymentPageDetailsConfirmed() {
		assertTrue(epokCheckoutPage.isOrderSummaryPresent());
	}
	@And("^Epok user apply the discount coupon '(.*)'")
	public void epok_User_Apply_Discount_Coupon(String couponCode) {
		epokCheckoutPage.clickOnCouponCodeLink();
		epokCheckoutPage.enterText(epokCheckoutPage.couponCodeInputField,UrlBuilder.getMessage(couponCode));
		epokCheckoutPage.ClickOnApplyDiscountButton();
	}

	@And("^Epok invalid promocode error '(.*)' message displayed$")
	public void epok_Promocode_Error_Message(String expectedErrorMessage) {
	assertEquals(epokCheckoutPage.invalidPromoCodeError(),UrlBuilder.getMessage(expectedErrorMessage));
		
	}
	@And("^Epok promocode error '(.*)' message displayed$")
	public void epok_Promocode_Sucess_Message(String expectedErrorMessage) {
		assertEquals(epokCheckoutPage.validPromoCodeSucessMsg(),Props.getProp(expectedErrorMessage));
	}
}
