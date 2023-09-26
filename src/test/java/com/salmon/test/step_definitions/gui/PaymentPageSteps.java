package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.models.CheckoutPageDeliveryAddressModel;
import com.salmon.test.page_objects.gui.models.VisaDetail;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.PageObject.LOADER;
import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static com.salmon.test.page_objects.gui.constants.Context.PARCEL_SHOP_ADDRESS;
import static com.salmon.test.page_objects.gui.constants.Context.SHIPPING_THRESHOLD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PaymentPageSteps {

	private PaymentPage paymentPage;
	private BATHelper batHelper;
	private ScenarioContext scenarioContext;
	private AccountDashboardPage accountDashBoardPage;
	private OrderSuccessPage orderSuccessPage;
	private HomePage homePage;
	private AvalancheAccountDashboardPage avalancheAccountDashboardPage;
	private static final Logger LOG = LoggerFactory.getLogger(PaymentPageSteps.class);

	public PaymentPageSteps(TestContext testContext, PaymentPage paymentPage, BATHelper batHelper, OrderSuccessPage orderSuccessPage, HomePage homePage, AccountDashboardPage accountDashBoardPage) {
		scenarioContext = testContext.getScenarioContext();
		this.paymentPage = paymentPage;
		this.batHelper = batHelper;
		this.orderSuccessPage = orderSuccessPage;
		this.homePage = homePage;
		this.accountDashBoardPage = accountDashBoardPage;
		//this.avalancheAccountDashboardPage= avalancheAccountDashboardPage;
	}

	@And("^Payment page details confirmed$")
	public void paymentPageDetailsConfirmed() {
		paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 20);
		if(UrlBuilder.isMobile()) {
			paymentPage.refreshBrowser();
		}
		paymentPage.paymentPageDetailsConfirmed();
	}

	@And("^select continue with pods$")
	public void continuePods() {
		paymentPage.selectContinueWithPods();
	}

	@And("^Payment page - next page selected$")
	public void paymentPageNextPageSelected() {
		paymentPage.paymentClickNextButton();
	}

	@And("^Check money order option selected$")
	public void checkMoneyOrderOptionSelected() throws Throwable {
		Thread.sleep(2000);
		paymentPage.checkMoneyOrderOptionSelected();
		assertTrue(paymentPage.isBillingSameAsAddressTicked());
	}

	@And("^Credit Card - MasterCard option and press next$")
	public void creditCardMasterCardOrderOptionSelected() {
		paymentPage.MasterCardOrderOptionSelected();
	}

	@And("^Credit Card - VISA order option and press next$")
	public void creditCardVISACardOrderOptionSelected() throws Throwable {
		paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 10);
		paymentPage.VISACardOrderOptionSelected();
	}

	@And("^Credit Card - AMEX order option and press next$")
	public void creditCardAMEXOrderOptionAndPressNext() {
		paymentPage.AMEXCardOrderOptionSelected();
	}

	@And("^Credit/Debit Card - SubscribePro order option and press next$")
	public void creditDebitCardSubscribeProOptionAndPressNext() {
		paymentPage.subscribeProOrderOptionSelected();
	}

	@And("^Credit Card - AMEX order option selected and valid details entered$")
	public void creditCardAMEXOrderOptionSelectedAndValidDetailsEntered() {
		paymentPage.AMEXCardOrderOptionSelected();
		paymentPage.enterValidAMEXCardDetailsAndSubmit();
	}

	@And("^enter mastercard details$")
	public void enterMastercardDetails() throws Throwable {
		boolean localeIsMexican = paymentPage.doesURLContain("/mx/es/");
		boolean localeIsVypeIt = paymentPage.doesURLContain("/it/it/");

		if (localeIsMexican) {
			paymentPage.enterValidVISACardDetailsForMX(UrlBuilder.getMessage("cardHolderName.key"), UrlBuilder.getMessage("masterCreditCardNumber.key"), UrlBuilder.getMessage("MasterCardSecurityCode.key"));
		} else if (localeIsVypeIt && paymentPage.isElementPresent(paymentPage.cardNumberIframe)) {
			paymentPage.enterValidSubscribeProCardDetails();
		} else {
			paymentPage.enterValidMasterCardDetailsAndSubmit();
		}
	}

	@And("^enter visa card details$")
	public void enterVisacardDetails() throws Throwable {
		if (paymentPage.getWebDriver().getCurrentUrl().contains("/mx/es/")) {
			paymentPage.enterValidVISACardDetailsForMX(UrlBuilder.getMessage("cardHolderName.key"), UrlBuilder.getMessage("VisaCreditCardNumber.key"), UrlBuilder.getMessage("VisaSecurityCode.key"));
		} else {
			paymentPage.enterValidVISACardDetailsAndSubmit();
		}
		Thread.sleep(2000);
	}

	@And("^enter amex card details$")
	public void enterAmexCardDetails() throws Throwable {
		paymentPage.enterValidAmexCardDetailsAndSubmit();
		Thread.sleep(2000);
	}

	@And("^select place order$")
	public void selectPlaceOrder() {
		paymentPage.clickPlaceOrder();
		if (UrlBuilder.getLocale().equalsIgnoreCase("co"))
			paymentPage.waitForExpectedElement(paymentPage.BUTTON_CONTINUE_SHOPPING,20);
			paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 50);
	}

	@And("^user place the order$")
	public void userPlaceTheOrder() {
		paymentPage.clickTermsAndConditionsBox();
		paymentPage.waitForElementToAppearAndDisappear(LOADER,3,10);
		paymentPage.clickPlaceOrder();
		paymentPage.waitForElementToAppearAndDisappear(LOADER,5,30);

	}

	@And("^apply discount code module present$")
	public void applyDiscountCodeModulePresent() {
		assertTrue(paymentPage.applyDiscountCodeModulePresent());
	}

	@And("^apply discount '(.*)' to the discount module and assert success message$")
	public void applyDiscountRobToTheDiscountModuleAndAssertSuccessMessage(String discountCode) {
		paymentPage.applyDiscountCode(discountCode);
	}

	@And("^assert message of '(.*)' presented to user$")
	public void assertMessageOfYourCouponWasSuccessfullyApplied(String expectedPromotionMsg) throws Throwable {
		paymentPage.promotionMessage(expectedPromotionMsg);
	}

	@And("^tick agree to terms and conditions$")
	public void tickAgreeToTermsAndConditions() {
		if (!UrlBuilder.getLocale().equals("de")) {
			paymentPage.clickTermsAndConditionsBox();
		}

	}

	@And("^click on shipping method$")
	public void clickOnShippingMethod() {

		if (paymentPage.getCurrentUrl().contains("dk/da") || (paymentPage.getCurrentUrl().contains("nl/nl") || (paymentPage.getCurrentUrl().contains("fr/fr") || (paymentPage.getCurrentUrl().contains("mx/es") || (paymentPage.getCurrentUrl().contains("de/de"))))) || paymentPage.getCurrentUrl().contains("vype.non-prod.marketing.bat.net/it/it/") || paymentPage.getCurrentUrl().contains("se/sv") || paymentPage.getCurrentUrl().contains("co/es")) {
			LOG.info("Move to next step");
		} else if (paymentPage.waitForExpectedElement(By.cssSelector("div.checkout-shipping-method div.step-content form.form.methods-shipping table.table-checkout-shipping-method tbody:nth-child(2) tr.row:nth-child(1) td.col.col-method:nth-child(1) > input")).isSelected()) {
			LOG.info(" \n **********Radio button selected - NO ACTION NEEDED");
		} else {
			LOG.info("Expected button not selected");
			paymentPage.clickByElementByQueryJSExecutor(By.cssSelector("div.checkout-shipping-method div.step-content form.form.methods-shipping table.table-checkout-shipping-method tbody:nth-child(2) tr.row:nth-child(1) td.col.col-method:nth-child(1) > input"));
			paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 15);
		}
	}

	@And("^user choose shipping method$")
	public void userChooseShippingMethod() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
				paymentPage.choosePaymentMethod();
				break;
		}
	}

	@And("^remove promotion$")
	public void removePromotion() throws Throwable {
		paymentPage.removePromotion();
		Thread.sleep(3000);
	}

	@And("^Check wallets order option$")
	public void checkWalletsOrderOption() {
		paymentPage.waitForExpectedElement(By.cssSelector("div#checkout-payment-method-load div:nth-child(4) > div.payment-method-title.field.choice > label"), 10).click();
	}

	@And("^Check alternative payment order option$")
	public void checkAlternativePaymentOrderOption() {
		if (!paymentPage.getWebDriver().getCurrentUrl().contains("/mx/es/")) {
			paymentPage.waitForExpectedElement(By.cssSelector("input#worldpay_apm"), 60);
			paymentPage.clickByElementByQueryJSExecutor(By.cssSelector("input#worldpay_apm"));
		}
	}

	@And("^Check paypal option$")
	public void checkPaypalOption() throws Throwable {
		Thread.sleep(3000);
		LOG.info("\n ****** Selecting Paypay option");
		if (paymentPage.doesURLContain("/mx/es/")) {
			try {
				paymentPage.waitForExpectedElement(paymentPage.rdoPaypalExpressCheckout, 40).click();
			} catch (Exception e) {
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.rdoPaypalExpressCheckout);
			}
			Thread.sleep(2000);
			try {
				paymentPage.waitForExpectedElement(paymentPage.chkTermsConditionsPaypal, 5).click();
			} catch (Exception e) {
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.chkTermsConditionsPaypal);
			}
			Thread.sleep(2000);
		} else if (paymentPage.doesURLContain("de/de")) {
			paymentPage.clickByElementByQueryJSExecutor(By.cssSelector("form.form fieldset.fieldset.payment.items.ccard.worldpay_apm div.field.type.required:nth-child(1) > label:nth-child(2)"));
		} else if (UrlBuilder.getLocale().equals("fr")) {
			paymentPage.clickByElementByQueryJSExecutor(paymentPage.selectPaypalPaymentOption);
		}
		else if (paymentPage.doesURLContain("se/sv")||paymentPage.doesURLContain("it/it")) {
			paymentPage.selectPaypalRadioButton();
		} else {
			try {
				paymentPage.waitForExpectedElement(By.cssSelector("#worldpay_apm_cc_type_div > label"), 10);
				paymentPage.clickByElementByQueryJSExecutor(By.cssSelector("#worldpay_apm_cc_type_div > label"));
			} catch (Exception e) {
				LOG.info("Couldn't select paypal option, trying again after 3 seconds wait");
				Thread.sleep(3000);
				paymentPage.clickUsingJS(By.cssSelector("#apm_PAYPAL-EXPRESS"));
			}
		}
	}

	@And("^press validate payment button$")
	public void pressValidatePaymentButton() throws Throwable {
		paymentPage.pressValidatePaymentButton();
	}

	@And("^tick save card box$")
	public void tickSaveCardBox() {
		paymentPage.saveCard();
	}

	@And("^paypal page - click continue$")
	public void paypalPageClickContinue() {
		switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
			case MX:
				paymentPage.waitForExpectedElement(paymentPage.elePayPalPageHeader,50);
				break;
			case LYFTSE:
			case FR:
			case VUSEFR:
				paymentPage.waitForExpectedElement(paymentPage.PAYPAL_CONTINUE_BUTTON,60);
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.PAYPAL_CONTINUE_BUTTON);
				break;
			default:
		}
	}

	@And("^click use saved card$")
	public void clickUseSavedCard() throws Throwable {
		switch (UrlBuilder.getLocale()) {
			case "vypeit":
			case "vuseit":
				LOG.info("Handled separately for Vype IT");
				break;
			case "nl":
				paymentPage.waitForExpectedElement(By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(6)")).click();
				WebElement ele = paymentPage.waitForExpectedElement(paymentPage.btnSaveCard, 10);
				paymentPage.clickElementByQueryJSExecutor(ele);
				Thread.sleep(1000);
				paymentPage.waitForExpectedElement(By.cssSelector(".input-text.cvv.saved-cvv-number")).sendKeys("123");
				break;
			case "lyftse":
				paymentPage.clickOnSavedCardOption();
				Thread.sleep(1000);
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.rdoSavedCardLyft);
				Thread.sleep(1000);
				paymentPage.waitForExpectedElement(By.cssSelector(".input-text.cvv.saved-cvv-number")).sendKeys("123");
				break;
			case "de":
				paymentPage.waitForExpectedElement(By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(6)")).click();
				Thread.sleep(1000);
				if (paymentPage.getWebDriver().findElements(paymentPage.lstSavedCards).size() > 0) {
					int intNumberOfCards = paymentPage.getWebDriver().findElements(paymentPage.lstSavedCards).size();
					WebElement rdoRecentSavedCard = paymentPage.getWebDriver().findElements(paymentPage.lstSavedCards).get(intNumberOfCards - 1);
					paymentPage.clickUsingJS(rdoRecentSavedCard);
				} else {
					LOG.info("No Saved Cards available to select for Vypde DE.");
				}
				Thread.sleep(1000);
				paymentPage.waitForExpectedElement(By.cssSelector(".input-text.cvv.saved-cvv-number")).sendKeys("123");
				break;
			case "vusefr":
				paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),15);
				paymentPage.waitForExpectedElement(paymentPage.amexCardOption).click();
				paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),15);
				try {
					paymentPage.clickUsingJS(By.name("payment[token_to_use]"));
				} catch (Exception e) {
					paymentPage.clickUsingJS(By.cssSelector("#saved_card_3753"));
				}
				paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),15);
				paymentPage.waitClearAndEnterText(paymentPage.txtSavedCardCVV,"123");
				break;
			default:
				paymentPage.waitForExpectedElement(By.cssSelector("#worldpay_cc_cc_type_div > div > label:nth-child(6)")).click();
				Thread.sleep(1000);
				try {
					paymentPage.clickUsingJS(By.name("payment[token_to_use]")); // _3752")); //59")); //#saved_card_71"));
				} catch (Exception e) {
					paymentPage.clickUsingJS(By.cssSelector("#saved_card_3753"));
				}
				Thread.sleep(1000);
				paymentPage.waitForExpectedElement(By.cssSelector(".input-text.cvv.saved-cvv-number")).sendKeys("123");
		}
	}

	@And("^click Use Saved Cards - Vype IT$")
	public void clickUseSavedCardVypeItaly() throws Throwable {
		if (paymentPage.doesURLContain("vype.non-prod.marketing.bat.net/it/it/")||paymentPage.doesURLContain("vuse.non-prod.marketing.bat.net/it/it/")) {
			if (paymentPage.getWebDriver().findElements(paymentPage.rdoUseSavedCards).size() > 0) {
				paymentPage.clickElementByQueryJSExecutor(paymentPage.waitForExpectedElement(paymentPage.rdoUseSavedCards));
				paymentPage.clickElementByQueryJSExecutor(paymentPage.waitForExpectedElement(paymentPage.rdoSavedCard));
				paymentPage.enterDataAndWait(paymentPage.txtSavedCardCVV, UrlBuilder.getMessage("VisaSecurityCode.key"));
			} else if (paymentPage.getWebDriver().findElements(paymentPage.subscrSavedCardsLabel).size() > 0) {
				paymentPage.selectSubscribedProSavedCard();
			} else {
				paymentPage.clickElementByQueryJSExecutor(paymentPage.waitForExpectedElement(paymentPage.btnSaveCardVypeIT));
				enterMastercardDetails();
			}
		}
	}

	@And("^select Card Payment option then credit Card option$")
	public void selectCardPaymentOptionThenCreditCardOption() {
		paymentPage.SelectCardPaymentThenCreditCardOption();
	}

	@And("^click on New Address button$")
	public void clickOnNewAddressButton() throws Throwable {
		paymentPage.clickByElementByQueryJSExecutor(By.cssSelector("button.action.action-show-popup"));
		Thread.sleep(4000);
	}

	@And("^populate postcode input field$")
	public void populatePostcodeInputField() {
		paymentPage.getWebDriver().findElement(By.xpath("//*[@id=\"IHY7PC2\"]")).sendKeys("WD3 1HL");
	}

	@And("^assert billing and shipping are the same tick box present$")
	public void assertBillingAndShippingAreTheSameTickBoxPresent() {
		if (paymentPage.getWebDriver().getCurrentUrl().contains("de/de")) {
			assertTrue(paymentPage.waitForExpectedElement(paymentPage.billingSameAsShippingAddressCheckBox).isDisplayed());
		}
	}

	@And("^assert that one block summary div is displayed$")
	public void assertThatOneBlockSummaryDivIsDisplayed() {
		assertTrue(paymentPage.waitForExpectedElement(By.cssSelector(".opc-block-summary")).isDisplayed());
	}

	@And("^selectable shipping options displayed$")
	public void selectableShippingOptionsDisplayed() {
		switch (UrlBuilder.getLocale()) {
			case "dk":
				paymentPage.assertShippingMethodsOnCheckout();
				break;
			case "mx":
			case "glode":
				break;
			default:
				assertTrue(paymentPage.waitForExpectedElement(paymentPage.PAYMENTPAGE_SHIPPINGOPTIONS, 30).isDisplayed());
		}
	}

	@And("^select credit card radio box$")
	public void selectCreditCardRadioBox() {
		paymentPage.selectCreditCardOptions();
	}

	@And("^assert credit card information for presented to user$")
	public void assertCreditCardInformationForPresentedToUser() {
		switch (UrlBuilder.getLocale()) {
			case "pl":
				break;
			default:
				assertTrue(paymentPage.waitForExpectedElement(By.cssSelector(
						"#checkout-payment-method-load > div > div > div.payment-method._active > div.payment-method-content"))
						.isDisplayed());
		}
	}

	@And("^confirm that item contained cart page$")
	public void confirmThatItemContainedCartPage() {
		assertTrue(paymentPage.waitForExpectedElement(By.cssSelector("tbody.cart.item")).isDisplayed());
	}

	@And("^clear the item from the cart page$")
	public void clearTheItemFromTheCartPage() throws Throwable {
		if (paymentPage.getWebDriver().getCurrentUrl().contains("mx/es/")) {
			paymentPage.waitForExpectedElement(paymentPage.btnRemoveCartItem, 30).click();
			paymentPage.waitForExpectedElement(paymentPage.btnDeleteAction, 30).click();
			paymentPage.waitForExpectedElement(paymentPage.eleCartEmptyMessage, 30);
		} else {
			paymentPage.waitForExpectedElement(By.cssSelector("i.remove.material-icons")).click();
			Thread.sleep(2000);
			LOG.info("Confirm removing item from basket");

			try {
				paymentPage.waitForExpectedElement(By.cssSelector("#modal-content-1 > div > div > a")).click();
			} catch (Exception e) {
				paymentPage.waitForExpectedElement(By.cssSelector("#modal-content-2 > div > div > a")).click();
			}
		}
	}

	@And("^assert items are displayed within the summary plain$")
	public void assertItemSAreDisplayedWithinTheSummaryPlain() {
		if (UrlBuilder.getLocale().equals("uk")||(UrlBuilder.getLocale().equals("vuseuk"))) {
			assertTrue(paymentPage.waitForExpectedElement(By.cssSelector("div.opc-block-summary")).isDisplayed());
		} else {
			assertTrue(paymentPage.waitForExpectedElement(By.cssSelector(".block.items-in-cart.active")).isDisplayed());
		}
	}

	@And("^assert ship here button is displayed$")
	public void assertShipHereButtonIsDisplayed() {
		assertTrue(paymentPage.waitForExpectedElement(By.cssSelector("button.action.action-select-shipping-item"))
				.isDisplayed());
	}

	@And("^assert new address button is displayed$")
	public void assertNewAddressButtonIsDisplayed() {
		switch (UrlBuilder.getLocale()){
			case "fr":
			case "vusefr":
			case "velopl":
			case "velobe":
				try {
					paymentPage.waitForExpectedElement(paymentPage.ADD_NEW_ADDRESS_BUTTON).isDisplayed();
				} catch (Exception e) {
					paymentPage.clickChangeAddress();
					assertTrue(paymentPage.waitForExpectedElement(paymentPage.ADD_NEW_ADDRESS_SECONDARY).isDisplayed());
				}
			break;
			case "mx":
			case "vypeit":
			case "vuseit":
			case "vusemx":
				try {
					paymentPage.waitForExpectedElement(paymentPage.ADD_NEW_ADDRESS_BUTTON).isDisplayed();
				} catch (Exception e) {
					paymentPage.clickChangeAddress();
					assertTrue(paymentPage.waitForExpectedElement(paymentPage.ADD_NEW_ADDRESS_SECONDARY).isDisplayed());
				}
				break;
			default:
			assertTrue(paymentPage.waitForExpectedElement(paymentPage.ADD_NEW_ADDRESS_BUTTON).isDisplayed());
		}
	}

	@And("^click add new address button$")
	public void clickNewAddressButton() {
		paymentPage.clickNewAddressButton();
	}

	@And("^div cart summary pane is displayed$")
	public void divCartSummaryPaneIsDisplayed() {
		paymentPage.waitForExpectedElement(By.cssSelector("table.data.table.totals"), 60);
		assertTrue(paymentPage.waitForExpectedElement(By.cssSelector("div.cart-summary")).isDisplayed());
	}

	@And("^add new address details and confirm these are correctly added$")
	public void addNewAddressDetailsAndConfirmTheseAreCorrectlyAdded() throws Throwable {
		Thread.sleep(3000);
		String randomAddress = random(6, ALPHABETS);
		String randonCity = random(6, ALPHABETS);
		String postCode = "Wd3 1hl";
		paymentPage.waitForExpectedElement(By.name("street[0]")).sendKeys(randomAddress);
		paymentPage.waitForExpectedElement(By.name("city")).sendKeys(randonCity);
		paymentPage.waitForExpectedElement(By.name("postcode")).sendKeys(postCode);
		paymentPage.waitForExpectedElement(By.cssSelector("button.action.primary.action.save-address")).click();
		Thread.sleep(3000);
		Boolean expectedAddressPresent = paymentPage.getWebDriver().getPageSource().contains(randomAddress);
		Boolean expectedCityPreset = paymentPage.getWebDriver().getPageSource().contains(randonCity);
		assertTrue("Expected newly added Address details are not present", expectedAddressPresent);
		assertTrue("Expected newly added City details are not present", expectedCityPreset);
	}

	@And("^enter select package shop details$")
	public void enter_Select_Package_Shop_Details() throws Exception {
		if (paymentPage.doesURLContain("/dk/da") || paymentPage.doesURLContain("/se/sv/")) {
			paymentPage.enterSelectPackageShopDetails();
			Thread.sleep(10000);
		} else {
			LOG.info("Move to next steps");
		}
	}

	@And("^user clicks on Next button and place the order$")
	public void userClicksOnNextButtonAndPlaceTheOrder() {
		paymentPage.clickOnNextButtonAndPlaceOrder();
	}

	@And("^assert text of '(.*)' is displayed when Pay button is clicked without selecting Terms and Conditions$")
	public void verifyErrorOnNotSelectingTCs(String strExpectedText) throws Throwable {
		if (paymentPage.doesURLContain("/mx/es/")) {
			paymentPage.waitForExpectedElement(paymentPage.MXCreditCardOption, 120);
			paymentPage.selectCreditCardOptions();
			paymentPage.clickUsingJS(paymentPage.waitForExpectedElement(paymentPage.btnPay, 30));
			paymentPage.waitForExpectedElement(paymentPage.eleErrorTCsNotSelected, 30);
			assertEquals(UrlBuilder.getMessage(strExpectedText), paymentPage.waitForExpectedElement(paymentPage.eleErrorTCsNotSelected).getText());
		}
	}

	@And("^click on Continue on Paypal button on Checkout page$")
	public void clickContinueOnPaypal() {
		switch (UrlBuilder.getLocale()) {
			case "fr":
			case "uk":
			case "vuseuk":
            case "vypeit":
			case "vuseit":
				paymentPage.waitForExpectedElement(paymentPage.PAYPAL_CONTINUE_BUTTON, 50);
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.PAYPAL_CONTINUE_BUTTON);
				break;
			case "mx":
				try{
				paymentPage.waitForExpectedElement(paymentPage.btnContinueOnPaypal,50);
				paymentPage.clickByElementByQueryJSExecutor(paymentPage.btnContinueOnPaypal);
				paymentPage.waitForExpectedElement(paymentPage.txtPaypalEmail,80);
					} catch(Exception e) {
					LOG.info("Failed to click on Continue Paypal button due to exception: "+e.getMessage());
		 			}
				break;
			default:
		}
	  }
	 
	 @And("^user sign-in into Paypal account with username '(.*)' and password '(.*)'$")
	  public void signIntoPaypal(String strUserName,String strPassword) {
		 if (paymentPage.getWebDriver().getTitle().contains("Log in to your PayPal account")) {
			 if (paymentPage.getWebDriver().findElements(paymentPage.btnAcceptCookies).size() > 0)
				 paymentPage.waitForExpectedElement(paymentPage.btnAcceptCookies).click();
			 paymentPage.loginPayPal(UrlBuilder.getMessage(strUserName), UrlBuilder.getMessage(strPassword));
		 }
	 }

	 @And("^user selects agree to terms and conditions and clicks on Validate Payment on Review Order page$")
	 public void paypalValidatePayment() throws Throwable {
		  if(paymentPage.getWebDriver().getCurrentUrl().contains("/mx/es/")) {
			  try {
				  paymentPage.waitForExpectedElement(paymentPage.chkTermsConditionsReviewOrder,20).click();
			  } catch(Exception e) {
				  LOG.info("trying to click on Terms and Conditions checkbox using javascript");
				  paymentPage.clickByElementByQueryJSExecutor(paymentPage.chkTermsConditionsReviewOrder);
			  }
			  Thread.sleep(2000);
			  paymentPage.waitForExpectedElement(paymentPage.btnValidatePayment).click();
			  paymentPage.waitForExpectedElement(paymentPage.eleSuccessOrderTitle,80);
		  }
	  }
	 
	 @And("^assert updated Cart Totals under Order Summary after applying discount coupon code$")
	 public void assertUpdatedCartTotalsAfterApplyingDiscountCouponCode() {
		paymentPage.verifyOrderSummaryDetailsAfterDiscount();
	}

	@And("^click Save This Card checkbox$")
	public void clickSaveThisCardCheckbox() {
		paymentPage.saveCard();
	}

	@And("^tick agree to terms and conditions and select place order$")
	public void tickAgreeTCsAndSelectPlaceOrder() throws Throwable {
		tickAgreeToTermsAndConditions();
		selectPlaceOrder();
		paymentPage.waitForLoaderToDisapear();
	}

	@And("^select place order without tick TandC$")
	public void SelectPlaceOrder() throws Throwable {
		selectPlaceOrder();
		paymentPage.waitForLoaderToDisapear();
	}

	@And("^enter Package shop details and select Credit Card - VISA order option$")
	public void enterPackageShopDetailsAndSelectCreditCardVisaCardoption() throws Throwable {
		enter_Select_Package_Shop_Details();
		paymentPage.VISACardOrderOptionSelected();
	}


	@And("^user selects Items in Cart section under Order Overview section$")
	public void userSelectsItemsInCartSectionUnderOrderOverview() {
		paymentPage.userSelectsItemsInCartSectionUnderOrderOverview();
	}

	@And("assert same products details for LAB and Non-LAB products on Checkout$")
	public void assertSameProductDetailsForLABandNonLabProductsOnCheckout() {
		paymentPage.assertSameProductDetailsForLABandNonLabProductsOnCheckout();
	}

	@And("assert Strength attribute as '(.*)' appears for only LAB products on Checkout$")
	public void assertStrengthAttributeForOnlyLABProductsOnCheckout(String strExpectedStrength) {
		paymentPage.assertStrengthAttributeForOnlyLABProductsOnCheckout(strExpectedStrength);
	}

	@And("assert SKUs quantity for only bundle LAB products on Checkout$")
	public void assertSKUsQuantityAttributeForOnlyBundleLABProductsOnCheckout() {
		paymentPage.assertSKUsQuantityAttributeForOnlyBundleLABProductsOnCheckout();
	}

	@And("^select Debit Card option$")
	public void selectDebitCardOption() {
		if (UrlBuilder.getLocale().equals("uk")) {
			paymentPage.selectDebitCardOption();
		} else {
			paymentPage.selectDebitCardOptionCO();
		}
	}

	@And("^enter open pay PSE details$")
	public void enterOpenPayPSEDetails() {
		paymentPage.enterOpenPayDetails();
	}

	@And("^place Open Pay Order$")
	public void placeOpenPayOrder() {
		paymentPage.placeOpenPayOrder();
	}

	@And("click on Debit/Credit Card Payment Option$")
	public void clickODebitCreditCardPaymentOption() {
		paymentPage.selectCreditCardOptions();
	}

	@And("click on PayPal Payment Option$")
	public void clickOnPayPalPaymentOption() throws Throwable {
		checkPaypalOption();
	}

	@Then("assert Checkout Age Verification warning label$")
	public void assertCheckoutAgeVerificationWarning() {
		paymentPage.assertCheckoutAgeVerificationWarning();
	}

	@And("assert Terms and Conditions link in Age Verification Warning$")
	public void assertTermsAndConditionsLinkInAgeVerificationWarning() {
		paymentPage.assertTermsAndConditionsLinkInAgeVerificationWarning();
	}

	@And("assert Telephone Number field is a mandatory field$")
	public void assertTelephoneNumberFieldIsMandatory() {
		paymentPage.assertTelephoneNumberFieldIsMandatory();
	}

	@And("user clicks on Shop on Map button under GLS Shipping Method$")
	public void userClicksOnShowOnMapButtonUnderGLSShippingMethod() {
		paymentPage.clickOnShowOnMapButtonUnderGLSShippingMethod();
	}

	@And("assert GLS Pop-up is displayed with GLS map inside$")
	public void assertGLSPopUpMapIsDisplayed() {
		paymentPage.assertGLSPopUpMapIsDisplayed();
	}

	@And("user clicks on Continue button On GLS pop-up$")
	public void userClicksOnContinueButtonOnGLSPopUp() {
		paymentPage.clickOnContinueButtonOnGLSMapPopUp();
	}

	@And("user clicks on Close button on GLS pop-up$")
	public void userClicksOnCloseButtonOnGLSMapPopUp() {
		paymentPage.clickOnCloseButtonOnGLSMapPopUp();
	}

	@And("assert GLS pop-up has closed$")
	public void assertGLSPopUpMapHasClosed(){
		paymentPage.assertGLSPopUpMapHasClosed();
	}

	@And("^search and checkout product \"([^\"]*)\"$")
	public void userSearchesForAndChecksThisProductOut(String searchTerm) throws Throwable {
		batHelper.checkoutAproduct(searchTerm);
	}

	@And("^search and view basket for the product '(.*)'$")
	public void searchAndViewBasketForTheProduct(String searchTerm) throws Throwable {
		batHelper.searchAndViewBasketForTheProduct(searchTerm);
	}

	@And("^select a GLO product and view basket$")
	public void selectGLOProductAndViewBasket() throws Throwable {
		batHelper.selectGLOProductAndViewBasket();
	}

	@And("^select a GLO product and view basket as a guest user$")
	public void selectGLOProductAndViewBasketAsGuest() throws Throwable {
		batHelper.selectGLOProductAndViewBasketAsGuest();
	}

	@And("^assert Eco tax is applied for the product$")
	public void assertEcoTaxIsAppliedForTheProduct()  {
		paymentPage.assertEcoTaxIsAppliedForTheProduct();
	}

	@And("^fetch the Sub-Total, Total and Delivery charges for the product$")
	public void fetchSubTotalTotalDeliveryChargesForTheProduct()  {
		paymentPage.fetchTotalChargesForTheProduct();
	}

	@And("^assert Total Charges are updated after applying promo code$")
	public void assertTotalChargesForTheProductAfterPromoCodeApplied() {
		paymentPage.assertTotalChargesForTheProductAfterDiscount();
	}

	@And("^assert Total Charges are updated after applying promo code in Shopping Cart$")
	public void assertTotalChargesForTheProductAfterPromoCodeAppliedInShoppingCart() throws Throwable {
		paymentPage.assertTotalChargesForTheProductAfterDiscountInShoppingCart();
	}

	@And("^assert Total Charges are updated with respect to other charges after applying 50% promo code$")
	public void assertTotalChargesForTheProductAfter50PercentPromoCodeApplied(){
		paymentPage.assertTotalChargesForTheProductAfter50PercentDiscount();
	}

	@And("^assert Delivery Charges after applying promo code$")
	public void assertDeliveryChargesForTheProductAfterPromoCodeApplied( ) {
		paymentPage.assertDeliveryChargesForTheProductAfterDiscount();
	}

	@And("^assert Delivery Charges before applying promo code$")
	public void assertDeliveryChargesForTheProductBeforePromoCode( ) {
		paymentPage.assertDeliveryChargesForTheProductBeforeDiscount();
	}

	@And("^click on Cancel button on Add new Address pop-up$")
	public void clickOnCancelButtonOnAddNewAddressPopUp() {
		paymentPage.clickOnCancelButtonOnAddNewAddressPopUp();
	}

	@Then("^assert that the modal has a title$")
	public void assertThatTheModalHasATitle() {
		assertThat(paymentPage.newAddressModalHasTitle()).as("ERROR: modal does not contain a title").isTrue();
	}

	@And("^assert Age Verification checkbox under Card Details section$")
	public void assertAgeVerificationCheckBoxUnderCardDetailsSection() {
		paymentPage.assertAgeVerificationCheckBoxUnderCardDetails();
	}

	@And("^assert Terms and Conditions checkbox under Card Details section$")
	public void assertTermsConditionsCheckBoxUnderCardDetailsSection() {
		paymentPage.assertTermsConditionCheckBoxUnderCardDetails();
	}

	@And("^search and add item for the product '(.*)'$")
	public void searchAndAddItemForTheProduct(String searchTerm) {
		paymentPage.addPoductIntoCart(searchTerm);
	}


	@And("^user clicks on Include button to add a recycling bag SKU to the order$")
	public void userClicksOnIncludeButtonToAddRecyclingBagSKUToOrder() throws Throwable {
		paymentPage.clickOnIncludeButtonToAddRecyclingBagSKUToTheOrder();
	}

	@And("^assert '(.*)' section and '(.*)' CTA$")
	public void assertDropThePodSectionAndDropThePodLinkCTA(String strSection, String strLink) {
		paymentPage.assertDropThePodSectionAndDropThePodLinkCTA(strSection,strLink);
	}

	@And("^user de-selects 'My billing and shipping address are the same' checkbox$")
	public void userDeSelectsMyBillingShippingAddressIsSameCheckbox() {
		paymentPage.userDeSelectsMyBillingShippingAddressIsSameCheckbox();
	}

	@And("^assert content section by business regarding non-editable fields is not displayed$")
	public void assertViewNoticeSectionRegardingReadOnlyFieldsNotDisplayed() {
		paymentPage.assertViewNoticeSectionRegardingReadOnlyFieldsNotDisplayed();
	}

	@And("^assert First Name field isn't editable under Billing Address section$")
	public void assertFirstNameFieldIsNotEditableUnderCheckoutBillingAddress() {
		paymentPage.assertFirstNameFieldIsNotEditableUnderCheckoutBillingAddress();
	}

	@And("^assert Last Name field isn't editable under Billing Address section$")
	public void assertLastNameFieldIsNotEditableUnderCheckoutBillingAddress() {
		paymentPage.assertLastNameFieldIsNotEditableUnderCheckoutBillingAddress();
	}

	@And("^assert pre-populated first name with existing account under Billing Address$")
	public void assertPrePopulatedFirstNameWithExistingAccountUnderBillingAddress() {
		paymentPage.assertPrePopulatedFirstNameWithExistingAccountUnderBillingAddress();
	}

	@And("^assert pre-populated last name with existing account under Billing Address$")
	public void assertPrePopulatedLastNameWithExistingAccount() {
		paymentPage.assertPrePopulatedLastNameWithExistingAccountUnderBillingAddress();
	}

	@And("^search and add the product into the basket '(.*)'$")
	public void searchAndAddTheProductIntoTheBasket(String searchTerm) throws Throwable {
		batHelper.addProductIntoBasket(searchTerm);
	}

	@And("^confirm that the resale code field is present$")
	public void confirmThatTheResaleCodeFieldIsPresent() {
		paymentPage.checkResaleCodeFieldTitle();
	}

	@And("^click on paygate payment radio button$")
	public void clickOnPaygatePaymentRadioButton() {
		paymentPage.clickOnPaygatePaymentRadioButton();

	}

	@And("^proceed with sidSecurePayment$")
	public void sidSecurePayment() throws InterruptedException {
		paymentPage.sidPayment();
	}

	@And("^proceed with DPO Card payment$")
	public void paymentCardZA()  {
		paymentPage.paymentCardZA();
	}

	@And("^add new billing address at checkout$")
	public void addNewBillingAddressAtCheckout() {
			paymentPage.addNewBillingAddress();
		}

	@And("^complete the form and submit with (.*) which is not present in list of postcodes$")
	public void completeTheFormWithPostcodeNotPresentWithListOfPostcodes(String postcode) {
		CheckoutPageDeliveryAddressModel deliveryAddressModel = CheckoutPageDeliveryAddressModel.builder().build().withDefaultValues();
		deliveryAddressModel.getAddress().setPostcode(UrlBuilder.getMessage(postcode));
		paymentPage.completeNewDeliveryAddressFormAndSave(deliveryAddressModel);
	}

	@Then("^assert checkout-delivery-notice is present with list of postcodes$")
	public void assertCheckoutDeliveryNoticeIsPresentWithListOfPostcodes() {
		assertThat(paymentPage.isCheckoutDeliveryNoticeDisplayed()).isTrue();
	}

	@When("^user selects correct delivery address$")
	public void userSelectsCorrectDeliveryAddress() {
		paymentPage.clickOnDeliverHereButton();
	}

	@And("PaymentInfoBlock is displayed$")
	public boolean assertFreeTrialPaymentBlock() {
		return paymentPage.deviceTrialPaymentBlockPresent();
	}

	@And("PaymentInfoBlock is not displayed$")
	public void deviceTrialPaymentBlockIsNotDisplayed() {
		paymentPage.waitForExpectedElement(paymentPage.deviceTrialPaymentBlock,10);
		boolean paymentInfoBlockDisplayed = paymentPage.getWebDriver().findElements(paymentPage.deviceTrialPaymentBlock).size() == 1;
		LOG.info("\n ***** PaymentInfoBlock Boolean :  " + paymentInfoBlockDisplayed);
		assertFalse(paymentInfoBlockDisplayed);
	}

	@And("Device Trial Checkbox is displayed$")
	public boolean assertDeviceTrialCheckbox() {
		return paymentPage.deviceTrialTextBoxPresent();
	}

	@And("Device Trial Checkbox is not displayed$")
	public void deviceTrialCheckboxIsNotDisplayed() {
		paymentPage.waitForExpectedElement(paymentPage.deviceTrialPaymentBlock,10);
		boolean deviceTrialCheckboxDisplayed = paymentPage.getWebDriver().findElements(paymentPage.deviceTrialCheckbox).size() == 1;
		LOG.info("\n ***** Device Trial Checkbox Boolean :  " + deviceTrialCheckboxDisplayed);
		assertFalse(deviceTrialCheckboxDisplayed);
	}

	@And("user clicks Device Trial Checkbox$")
	public void selectDeviceTrialCheckbox() {
		paymentPage.selectDeviceTrialTextBox();
	}

	@And("assert that checkout was successful")
	public boolean assertCheckoutSuccess() {
		return paymentPage.assertCheckoutSuccess();
	}

	@Then("^user Should see the free shipping recommendation message$")
	public void userShouldSeeTheFreeShippingRecommendationMessage() {
		assertThat(paymentPage.isFreeShippingRecommendationMessageDisplayed()).isTrue();
	}

	@And("^remove promocode from cart$")
	public void removePromocodeFromCart() {
		paymentPage.clickMiniCartClose();
		paymentPage.cancelCouponCodeButton();
	}

	@Then("^user should see a checkbox with option to subscribe to newsletter with text (.*)$")
	public void userShouldSeeACheckboxWithOptionToSubscribeToNewsletterWithText(String key) {
		paymentPage.assertCheckoutNewsLetterCheckBoxAndText(key);
	}

	@When("^user select the newsletter subscription checkbox$")
	public void userSelectTheNewsletterSubscriptionCheckbox() {
		paymentPage.clickCheckoutNewsLetterSubscriptionCheckbox();
	}

	@And("^customer selects electronic invoice check and enter details$")
	public void customerSelectsElectronicInvoiceCheckAndEnterDetails() {
		paymentPage.paysWithElectronicInvoiceCheck();
	}

	@And("^fetch the selected parcel shop address$")
	public void fetchTheSelectedParcelShopAddress() {
		String selectedParcelShopAddress = paymentPage.fetchTheSelectedParcelShopAddress();
		scenarioContext.setContext(PARCEL_SHOP_ADDRESS, selectedParcelShopAddress);
	}

	@And("^assert that shipping cost is zero$")
	public void assertThatShippingCostIsZero() {
		paymentPage.assertPriceOfShipping();
	}

	@And("^user complete \"([^\"]*)\" payment details and click on place the order$")
	public void userCompletePaymentDetailsAndClickOnPlaceTheOrder(String paymentMethod) throws Throwable {
		completePaymentDetails(paymentMethod);
		selectPlaceOrder();
	}

	@And("^user completes \"([^\"]*)\" payment details$")
	public void completePaymentDetails(String paymentMethod) throws Throwable {
		if (paymentMethod.equalsIgnoreCase("visa")) {
			creditCardVISACardOrderOptionSelected();
			enterVisacardDetails();
		} else if (paymentMethod.equalsIgnoreCase("MasterCard")) {
			creditCardMasterCardOrderOptionSelected();
			enterMastercardDetails();
		}
		tickAgreeToTermsAndConditions();
		tickSaveCardBox();
	}

	@And("^user place the order after removing the tofino product$")
	public void userPlaceOrderAfterRemovingTofinoProduct() {
		paymentPage.userPlaceOrderAfterRemovingTofinoProduct();
	}

	@And("^user adds a new address with allowed post code$")
	public void userAddsNewAddressWithAllowedPostCode() {
		paymentPage.userAddsNewAddressWithAllowedPostCode();
	}

	@And("^assert Newsletter Sign Up is not displayed for TOFINO products$")
	public void assertNewsletterSignUpNotDisplayedForTofinoProducts() {
		paymentPage.assertNewsletterSignUpNotDisplayedForTofinoProducts();
	}

	@And("^submit visa details as below and eyes check error messages$")
	public void submitVisaDetailsAsBelowAndEyesCheckErrorMessages(List<VisaDetail> visaList) {
		for(VisaDetail visa:visaList) {
			paymentPage.enterInvalidVISACardDetailsAndSubmit(visa);
			paymentPage.clickPlaceOrder();
		}
	}

	@And("^visa order completion then navigate to order history page$")
	public void visaOrderCompletionThenNavigateToOrderHistoryPage() throws Throwable {
		paymentPage.waitForAjaxElementNotToBePresent(paymentPage.getWebDriver(), 10);
		paymentPage.VISACardOrderOptionSelected();
		paymentPage.enterValidVISACardDetailsAndSubmit();
		paymentPage.clickTermsAndConditionsBox();
		paymentPage.clickPlaceOrder();
		orderSuccessPage.assertOrderConfirmationPageLinksRedirectsToCorrectURLs();
		orderSuccessPage.grabOrderNumberAndAssertOrderStatusOnOrderDetailsPage();

	}

	@And("^coupon code '(.*)' successfully applied$")
	public void couponCodeSuccessfullyApplied(String discountCode) throws Throwable {
		assertTrue(paymentPage.applyDiscountCodeModulePresent());
		paymentPage.applyDiscountCode(discountCode);
		homePage.newsLetterValidationMessageDisplayed();
	}

	@And("^invalid coupon code '(.*)' applied$")
	public void invalidCouponCodeApplied(String discountCode) throws Throwable {
		assertTrue(paymentPage.applyDiscountCodeModulePresent());
		paymentPage.applyDiscountCode(discountCode);
		homePage.assertPromoErrorMessage();
	}

	@Then("^check subtotals does not contain Subscription discount$")
	public void checkSubtotalsDoesNotContainSubscriptionDiscount() {
		paymentPage.assertNoSubscriptionProDiscountInTotal();
	}

	@When("^user proceeds to checkout non-subscription product from checkout page$")
	public void userProceedsToCheckoutNonSubscriptionProductFromCheckoutPage() throws InterruptedException{
		paymentPage.clickCheckoutCTAOrderSummary();
		batHelper.paysBy("mastercard");
	}

    @And("^assert shipping cost is applied if products total price is less than shipping threshold$")
    public void assertShippingCostAtCheckout() {
		float shippingThreshold = (float) scenarioContext.getContext(SHIPPING_THRESHOLD);
		float totalProductsPrice = paymentPage.getSubtotalPrice();
		float shippingPrice = paymentPage.getShippingPrice();
		float totalPrice = paymentPage.getTotalPrice();

		if (totalProductsPrice < shippingThreshold) {
			assertThat(shippingPrice).isEqualTo(Float.valueOf(UrlBuilder.getMessage("shippingCost.key")));
		} else if (totalProductsPrice > shippingThreshold) {
			assertThat(shippingPrice).isEqualTo(0);
		}

		float expected = totalProductsPrice + shippingPrice;
		assertThat(totalPrice).isEqualTo(expected);
	}

	@And("^assert that product item displayed in checkout Order Overview is same as product added to cart$")
	public void assertThatProductItemDisplayedInCheckoutOrderViewIsSameAsProductAddedToCart() {
		String expected = (String) scenarioContext.getContext(Context.PRODUCT_NAME);
		Map<String, List<WebElement>> items = paymentPage.getOrderItemsInOrderOverviewCheckoutPage();
		String actualItemText = items.get("OrderOverViewItemsNamesList").stream().findFirst().get().getText();
		Assertions.assertThat(actualItemText).isEqualTo(expected);
	}

	@And("^user add address on payment page$")
	public void addAddressDetailsOnPaymentPage() {
		paymentPage.addNewAddressOnPaymentPage();
	}

	@Then("^check subtotals contains tier discount '(.*)'$")
	public void checkSubtotalsContainsTierDiscountSilver(String discountExpected) {
		paymentPage.assertSubscriptionProDiscountInTotal(discountExpected);
	}

	@And("^select Sofort payment option and click place order$")
	public void selectSofortPaymentOptionAndClickPlaceOrder() {
		paymentPage.selectAlternativePaymentOptions();
		paymentPage.selectSoFortRadioButton();
		paymentPage.waitForPage();
		paymentPage.clickByElementByQueryJSExecutor(paymentPage.BUTTON_PLACE_ORDER_DE);
	}

	@And("^select Cancelled in Payment outcome dropdown and press continue$")
	public void selectCancelledAsPaymentOutcomeAndPressContinue() {
		paymentPage.waitForExpectedElement(paymentPage.SELECT_PAYMENT_OUTCOME_DROPDOWN,10);
		paymentPage.selectOptionFromDropDownByValue("CANCELLED",paymentPage.SELECT_PAYMENT_OUTCOME_DROPDOWN);
		paymentPage.selectSofortContinueButton();
		paymentPage.waitForElementToAppearAndDisappear(LOADER,5,15);
	}

	@Then("^assert checkout page displays the engraving details$")
	public void assertCheckOutPageDisplaysTheEngravingDetails() {
		paymentPage.waitForLoaderToDisapear();
		paymentPage.engravingDetailsConfirmationOnCheckoutPage();
	}

	@And("^click on Manual Address Entry link$")
	public void clickOnManualAddressEntryLink() {
		switch (UrlBuilder.getLocale()) {
			case "vusemx":
				LOG.info("Not applicable");
				break;
			default:
				paymentPage.clickOnManualAddressEntryLink();
		}
	}

	@And("^select (.*) delivery as delivery method$")
	public void selectExpressDeliveryAsDeliveryMethod(String deliveryMethodName) {
		paymentPage.selectDeliveryMethodBy(deliveryMethodName);
	}

	@Then("^delivery method doesn't exist$")
	public void deliveryMethodNotExist(DataTable deliveryMethod) {
		List<String> lists = deliveryMethod.asList(String.class);
		for (String strDeliveryMethod : lists) {
			assertTrue(!paymentPage.waitForExpectedElement(paymentPage.SHIPPING_METHOD_LABEL).getText().contains(strDeliveryMethod.toUpperCase()));
		}
	}

	@Then("^verify delivery charges in the order summery$")
	public void verifyDeliveryChargesInTheOrderSummery() {
		paymentPage.verifyDeliveryChargesInTheOrderSummery();
	}

	@Then("^assert only delivery method '(.*)' is displayed for engraved product$")
	public void assertOnlyStandardDeliveryMethodDisplayedForEngravedProduct(String strDeliveryMethod) {
		assertTrue(paymentPage.getWebDriver().findElements(paymentPage.SHIPPING_METHODS).size()==1);
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.SHIPPING_METHOD_LABEL).getText().contains(strDeliveryMethod.toUpperCase()));
	}

	@Then("^assert delivery methods '(.*)' displayed for non-engraved regular product$")
	public void assertDeliveryMethodsDisplayedForNonEngravedRegularProduct(String strDeliveryMethods) {
		List<WebElement> eleDeliveryMethods = paymentPage.getWebDriver().findElements(paymentPage.SHIPPING_METHOD_LABEL);
		String[] lstDeliveryMethods = UrlBuilder.getMessage(strDeliveryMethods).split(",");
		for(int i=0;i<lstDeliveryMethods.length;i++){
			assertTrue(eleDeliveryMethods.get(i).getText().contains(lstDeliveryMethods[i].toUpperCase()));
		}
	}


	@And("^view the choose delivery method section all details$")
	public void viewTheChooseDeliveryMethodSectionAllDetails() {
		paymentPage.waitForExpectedElement(paymentPage.SHIPPING_METHODS,20);
		assertTrue(paymentPage.waitForExpectedElements(paymentPage.SHIPPING_METHODS).size()==2);
		paymentPage.deliveryMethodsDetails();
	}

	@And("^select first delivery option$")
	public void userSelectFirstDeliveryOption() {
		paymentPage.waitForExpectedElement(By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(1)"), 5).click();
	}

	@And("^select second delivery option and verify first option selection is clear$")
	public void userSelectSecondDeliveryOptionFirstclear() {
		paymentPage.waitForExpectedElement(By.cssSelector("table > tbody > tr:nth-child(2) > td:nth-child(1)"), 5).click();
	}


	@And("^click use this address$")
	public void clickUseThisAddress() {
		paymentPage.clickUseThisAddress();
	}

	@And("^assert the address has been set$")
	public void assertTheAddressHasBeenSet() {
		paymentPage.assertAddressIsSet();
	}

	@And("^I can see my delivery and payment options on the left side of this page$")
	public void iCanSeeMyDeliveryAndPaymentOptionsOnTheLeftSideOfThisPage() {
		paymentPage.canSeeDeliveryAndPaymentOptions();
	}

	@Then("^I am on the checkout page$")
	public void iAmOnTheCheckoutPage() {
		paymentPage.onTheCheckoutPage();
	}

	@And("^I can see my order summary on the right side of this page$")
	public void iCanSeeMyOrderSummaryOnTheRightSideOfThisPage() {
		paymentPage.canSeeOrderSummary();
	}

	@And("^I can see there is a change address CTA$")
	public void iCanSeeThereIsAChangeAddressCTA() {
		paymentPage.checkForChangeAddressLink();
	}

	@When("^I click on the change address CTA$")
	public void iClickOnTheChangeAddressCTA() {
		paymentPage.clickOnChangeAddress();
	}

	@Then("^the section expands and I can see my default address with a radio button$")
	public void theSectionExpandsAndICanSeeMyDefaultAddressWithARadioButton() {
		paymentPage.validateAddressesDisplayedOnCheckoutPage();
	}

	@And("^there is an Add new address CTA which opens a pop-up to add a new address$")
	public void thereIsAnAddNewAddressCTAWhichOpensAPopUpToAddANewAddress() {
		paymentPage.validateAddNewAddressCta();
	}

	@And("^there is a CTA Use this address below to save my address preferences$")
	public void thereIsACTAUseThisAddressBelowToSaveMyAddressPreferences() {
		paymentPage.validateUseThisAddresCta();
	}

	@And("^there is a CANCEL button to collapse this section$")
	public void thereIsACANCELButtonToCollapseThisSection() {
		paymentPage.validateAddNewAddressCancel();
	}

	@And("^the delivery section shows only one delivery option Standard Delivery$")
	public void theDeliverySectionShowsOnlyOneDeliveryOptionStandardDelivery() {
		paymentPage.validateShippingMethod();
	}

	@And("^the delivery method has a radio button which i can select$")
	public void theDeliveryMethodHasARadioButtonWhichICanSelect() {
		paymentPage.deliveryMethodHasRadioButton();
	}

	@And("^I see an expandable card payments tab in this section$")
	public void iSeeAnExpandableCardPaymentsTabInThisSection() {
		paymentPage.expandableCardPaymentsdisplayed();
	}

	@And("^I can see the total amount payable in this section$")
	public void iCanSeeTheTotalAmountPayableInThisSection() {
		paymentPage.canSeeTotalAmountPayable();
	}

    @And("^there is a checkbox to save my card details$")
    public void thereIsACheckboxToSaveMyCardDetails() {
		paymentPage.thereIsCheckboxToSaveCardDetails();
    }

	@And("^there is consent checkbox$")
	public void thereIsConsentCheckbox() {
		paymentPage.thereIsConsentCheckbox();
	}

	@And("^a link to terms and conditions policy$")
	public void aLinkToTermsAndConditionsPolicy() {
		paymentPage.thereIsLinkToTermsAndConditionsPolicy();
	}

	@And("^I should see that confirmation is sent to my email address$")
	public void iShouldSeeThatConfirmationIsSentToMyEmailAddress() {
		paymentPage.iShouldSeeThatConfirmationIsSentToMyEmailAddress();
	}

	@And("^there is a CTA to Go to My Account$")
	public void thereIsACTAToGoToMyAccount() {
		paymentPage.thereIsACTAToGoToMyAccount();
	}

	@And("^there is a CTA to return to homepage$")
	public void thereIsACTAToReturnToHomepage() {
		paymentPage.thereIsACTAToReturnToHomepage();
	}

	@And("^I can see the delivery options available including radio button estimated time and price$")
	public void iCanSeeTheDeliveryOptionsAvailable() {
		paymentPage.iCanSeeTheDeliveryOptionsAvailable();
	}

	@And("^assert thank you text of \"([^\"]*)\" is displayed$")
	public void assertThankYouTextOfIsDisplayed(String thankYouKey) throws Throwable {
		paymentPage.assertThankYou(UrlBuilder.getMessage(thankYouKey));
	}

	@And("^assert your ordernumber text of \"([^\"]*)\" is displayed$")
	public void assertYourOrdernumberTextOfIsDisplayed(String orderNumberKey) throws Throwable {
		paymentPage.assertOrderNumberMessage(UrlBuilder.getMessage(orderNumberKey));
	}

	@And("^checkout any available product$")
	public void checkoutAnyAvailableProduct() {
		paymentPage.checkoutAnAvailableProduct();
	}


	@And("^checkout any available product and check first order discount$")
	public void checkoutAnyAvailableProductAndCheckFirstOrderDiscount() {
		paymentPage.checkoutFirstOrderDiscount();
	}


	@And("^checkout more than \"([^\"]*)\" available product and get discount$")
	public void checkoutMoreThanAvailableProductAndGetDiscount(int qty)  {
		paymentPage.checkoutDiscountForFiveItems(qty);

	}
	@And("^checkout more than \"([^\"]*)\" available product$")
	public void checkoutMoreThanAvailableProduct(int qty)  {
		paymentPage.checkoutDiscountForOneItems(qty);

	}




	@And("^apply \"([^\"]*)\" valid coupon code$")
	public void applyValidCouponCode(String couponCode) {
		paymentPage.applyCouponCode(couponCode);
	}
    @Then("^assert discount is present$")
    public void assertDiscountIsPresent() {
		assertTrue(paymentPage.isDiscountTitlePresent());
		assertTrue(paymentPage.isDiscountAmountPresent());
    }

	@And("^assert add new address link is displayed$")
	public void assertAddNewAddressLinkIsDisplayed() {
		paymentPage.clickChangeAddressLink();
	}

	@And("^click change address button$")
	public void clickChangeAddressButton() {
		assertTrue(paymentPage.isNewAddressButtonPresent());
	}

	@And("^click add new address link$")
	public void clickAddNewAddressLink() {
		paymentPage.clickAddNewAddressLink();
	}

	@Then("^new address dialog is displayed$")
	public void newAddressDialogIsDisplayed() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(paymentPage.isAddressFinderPresent());
		softAssert.assertTrue(paymentPage.isModalTitlePresent());
		softAssert.assertTrue(paymentPage.isAddressTogglePresent());
		softAssert.assertAll();
	}

	@Then("^return to basket page$")
	public void returnToBasketPage() {
		paymentPage.clickUsingJS(PaymentPage.RETURN_BASKET_PAGE);
	}

	@And("^fetch the product price from MiniCart$")
	public void fetchProductPriceFromMiniCart() throws Throwable {
		batHelper.fetchProductPriceFromMiniCart();
	}

	@And("^verify delivery method section all details$")
	public void verifyDeliveryMethodsDetails() throws Throwable {
		batHelper.assertDeliveryMethodDetails();
	}

    @Then("^free delivery radio button is displayed$")
    public void freeDeliveryRadioButtonIsDisplayed() {
        assertTrue(paymentPage.isFreeDeliveryRadioButtonPresent());
    }

	@And("^click on a shipping option other than free standard shipping$")
	public void clickOnAShippingOptionOtherThanFree() {
		paymentPage.clickOnAShipOptionOtherThanFree();
	}

	@And("^user click on change address link$")
	public void userClicksOnChangeAddressLink() {
		paymentPage.userClicksOnChangeAddressLink();
	}
	@And("^user click on change address link in checkoutpage$")
	public void userClicksOnChangeAddressLinkCheckoutPage() {
		paymentPage.userClicksOnChangeAddressLinkCheckoutPage();
	}

	@And("^user Clicks On Age Verify Button$")
	public void assertAgeVerificationBlockOnCheckoutPage() {
		paymentPage.userClickOnAgeVeriyButton();
	}

	@Then("^Assert photo size disclaimer info displayed$")
	public void assertPhotoSizeDisclaimerInfoDisplayed() {
		assertTrue(paymentPage.isPhotoSizeInfoPresent());

	}

	@Then("^assert delivery methods '(.*)' displayed for engraved product$")
	public void assertDeliveryMethodsDisplayedForEngravedProduct(String strDeliveryMethods) {
		List<WebElement> eleDeliveryMethods = paymentPage.getWebDriver().findElements(paymentPage.SHIPPING_METHOD_LABEL);
		String[] lstDeliveryMethods = UrlBuilder.getMessage(strDeliveryMethods).split(",");
		for(int i=0;i<lstDeliveryMethods.length;i++){
			assertTrue(eleDeliveryMethods.get(i).getText().contains(lstDeliveryMethods[i].toUpperCase()));
		}
	}

	@And("^user select last mile delivery option$")
	public void selectLastMileDeliveryOption() {
		paymentPage.selectLastMileDelivery();
	}

	@And("^assert radio buttons for delivery companies is displayed$")
	public void assertRadioButtonsIsDisplayedForDeliveryCompanies() {
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.UPS_DELIVERY_COMPANY_CHECKBOX).isDisplayed());
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.DPD_PICKUP_POINT_DELIVERY_COMPANY_CHECKBOX).isDisplayed());
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.DPD_DOOR_TO_DOOR_DELIVERY_COMPANY_CHECKBOX).isDisplayed());
	}
	
	@And("^user clicks on Add New Address button$")
	public void userClicksOnAddNewAddressButton() {
		paymentPage.waitAndClickByElementByJSExecutor(paymentPage.ADD_NEW_ADDRESS_SECONDARY,10);
	}

	@And("^populate restricted address '(.*)' on Add New Address pop-up$")
	public void populateRestrictedAddressDetailsOnAddNewAddressPopUp(String strInputText) {
		paymentPage.populateRestrictedAddressFields(strInputText);
	}

	@And("^assert restricted address error message '(.*)' is displayed$")
	public void assertRestrictedAddressErrorMessageIsDisplayed(String strErrorText) {
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.ADDRESS_RESTRICTION_ERROR_MESSAGE,10).getText().contains(UrlBuilder.getMessage(strErrorText)));
	}

	@And("^assert Delivery Method input is not displayed for restricted address$")
	public void assertDeliveryMethodInputNotDisplayedForRestrictedAddress() {
		assertTrue(paymentPage.getWebDriver().findElements(paymentPage.CHECKOUT_DELIVERY_METHODS).size()==0);
	}

	@And("^assert Delivery Method input is displayed for default address$")
	public void assertDeliveryMethodInputDisplayedForDefaultAddress() {
		paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(), 5);
		assertTrue(paymentPage.getWebDriver().findElements(paymentPage.CHECKOUT_DELIVERY_METHODS).size() >= 1);
	}

	@And("assert phone number field is displayed as a mandatory field on Add Address (?:pop-up|form)$")
	public void assertPhoneNumberFieldIsDisplayedAsMandatoryOnAddAddressPopUpOrForm() {
		try{
			assertTrue(paymentPage.waitForExpectedElement(paymentPage.PHONE_NUMBER_FIELD,10).isDisplayed());
			assertTrue(paymentPage.waitForExpectedElement(paymentPage.TELEPHONE_NUMBER_FIELD_CHECKOUT).isDisplayed());}
		catch(Exception ex){
			assertTrue(Boolean.parseBoolean(paymentPage.waitForExpectedElement(paymentPage.PHONE_NUMBER_FIELD_PAYMENT_SECTION).getAttribute("aria-required")));
		}
	}

	@And("populate address fields on Checkout page$")
	public void populateAddressFieldsOnCheckoutPage() {
		paymentPage.populateAllAddressFieldsOnCheckout();
	}

	@And("user clicks on Change Address link below card holder details$")
	public void userClicksOnChangeAddressLinkbelowCardHolderDetails() {
		paymentPage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
		paymentPage.waitAndClickByElementByJSExecutor(paymentPage.LINK_CHANGE_ADDRESS_PAYMENT_SECTION,5);
	}

	@And("user selects New Address radiobutton and assert address form is populated$")
	public void userSelectsNewAddressRadioButtonAndAssertAddressFormIsPopulated() {
		paymentPage.waitAndClickByElementByJSExecutor(paymentPage.RADIO_NEW_ADDRESS_PAYMENT_SECTION,5);
		assertTrue(paymentPage.waitForExpectedElement(paymentPage.ADDRESS_FORM_PAYMENT_SECTION).isDisplayed());
	}

	@And("user makes a purchase with \"([^\"]*)\" on Payment page$")
	public void userMakesPurchaseOnPaymentPage(String paymentType) throws InterruptedException {
		batHelper.paysBy(paymentType);
	}

	@And("^assert Total Charges are back after removed promo code in Shopping Cart$")
	public void assertTotalChargesForTheProductAfterRemovedPromoCodeInShoppingCart() {
		paymentPage.assertTotalChargesForTheProductAfterRemovedDiscountInShoppingCart();
	}

	@Then("^assert Total Charges are updated after applying promo code in checkout page$")
	public void assertTotalChargesForTheProductAfterPromoCodeAppliedInCheckoutPage() {
		paymentPage.assertTotalChargesForTheProductAfterDiscountInCheckoutPage();
	}

	@Then("^assert Total Charges are back after removed promo code in checkout page$")
	public void assertTotalChargesForTheProductAfterRemovedPromoCodeInCheckoutPage() {
		paymentPage.assertTotalChargesForTheProductAfterRemovedDiscountInCheckoutPage();
	}

	@And("^click drop the pod link$")
	public void clickDropThePodLink() {
		paymentPage.waitForExpectedElement(paymentPage.DROP_THE_POD_LINK).click();
	}

	@And("^switch to window \"([^\"]*)\"$")
	public void switchToWindow(String tabIndex)  {
		paymentPage.switchBetweenWindowTabs(Integer.parseInt(tabIndex));
	}

	@Then("^verify price of product accordingly in summary cart$")
	public void verifyPriceOfProductAccordinglyInSummary() {
		paymentPage.verifyPriceOfProductAccordinglyInSummary();
	}

	@And("^user add single strength product to checkout$")
	public void userAddSingleStrengthProductToCheckout() {
		paymentPage.checkoutSimpleProduct();
	}

	@And("^user verify elements on PDP to checkout$")
	public void userVerifyElementsOnPDPToCheckout() {
		paymentPage.checkoutFromPdp();
	}
}
