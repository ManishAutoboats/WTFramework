package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class EpokCheckoutPage extends PageObject {

	private By cardPaymentMethodCheckbox = By.cssSelector("input#worldpay_cc");
	private By creditCardCheckbox = By.cssSelector("input#cardTypeCredit");
	private By creditCardHolderNameField = By.cssSelector("input#worldpay_cc_cc_name");
	private By creditCardNumberField = By.cssSelector("input#worldpay_cc_cc_number");
	private By creditCardCVVField = By.cssSelector("input#worldpay_cc_cc_cid");
	private By creditCardExpirationDateMonthDropDown = By.cssSelector("#worldpay_cc_expiration");
	private By creditCardExpirationDateYearDropDown = By.cssSelector("select#worldpay_cc_expiration_yr.select.select-year");
	private By debitCardCheckbox = By.cssSelector("input#cardTypeDebit");
	private By masterCardTypeCheckbox = By.cssSelector("#direct_cc_ECMC-SSL");
	private By visaCardTypeCheckbox = By.cssSelector("input#direct_cc_VISA-SSL");
	private By savedCardTypeCheckbox = By.cssSelector("input.input-check.savecard");
	private By usersSavedCardCheckbox = By.cssSelector("input[name=\"payment\\5b token_to_use\\5d\"]");
	private By usersSavedCardCVVField = By.cssSelector("input.input-text.cvv.saved-cvv-number");
	private By otherPaymentMethodCheckbox = By.cssSelector("#worldpay_apm");
	private By buyNowButtonPayPal = By.xpath("//div[@class='payment-method _active']//button[@class='action primary checkout']");
	private By placeOrderButtonCard = By.cssSelector("button.action.primary.checkout");
	private By orderSummaryHeading = By.cssSelector("div.opc-block-summary");
	private By paypalCheckbox = By.cssSelector("#apm_PAYPAL-EXPRESS");
	private By couponCodeLink = By.cssSelector("span#block-discount-heading");
	public By couponCodeInputField = By.cssSelector("#discount-code");
	private By appyDiscountButton = By.cssSelector("button.action.action-apply");
	private By invalidPromoCodeError=By.cssSelector("div.message.message-error.error > div:nth-child(1)");
	private By validPromoCodeSucessMsg=By.cssSelector("div.message.message-success.success");
	private By switchAddressCheckbox = By.cssSelector("button.action.action-select-shipping-item> span:nth-child(1)");
	private static final By SAVED_CARD = By.cssSelector("[id^=saved_card_");

	public void selectCardPaymentMethod() {
		waitForAjaxElementNotToBePresent(getWebDriver(),5);
		waitForExpectedElement(cardPaymentMethodCheckbox, 20);
		clickByElementByQueryJSExecutor(cardPaymentMethodCheckbox);
	}

	private void enterCardHolderName(String creditCardHolderName) {
		waitForExpectedElement(creditCardHolderNameField).sendKeys(creditCardHolderName);
	}

	private void enterCustomCreditCardNumber(String creditCardNumber) {
		waitForExpectedElement(creditCardNumberField).sendKeys(creditCardNumber);
	}

	private void enterCreditCardExpiryDate() {
		waitForExpectedElement(creditCardExpirationDateMonthDropDown);
		if(doesURLContain("de/de")) {
			selectValueFromDropDownByIndex(5,creditCardExpirationDateMonthDropDown);
		}
		else if(doesURLContain("it/it"))
			selectValueFromDropDownByby("05 - maggio", creditCardExpirationDateMonthDropDown);
		else
			selectValueFromDropDownByby("05 - May", creditCardExpirationDateMonthDropDown);
		selectValueFromDropDownByby("2022", creditCardExpirationDateYearDropDown);
	}

	public void enterCreditCardVerificationNumber() throws InterruptedException {
		waitForExpectedElement(creditCardCVVField).sendKeys("123");
		LOG.info("Testing breakpoint");
		Thread.sleep(2000);
	}

	public void enterValidMasterOrVisaCardDetailsAndSubmit() throws InterruptedException {
		enterCardHolderName(random(8, ALPHABETS));
		enterCustomCreditCardNumber("5555555555554444");
		enterCreditCardExpiryDate();
		enterCreditCardVerificationNumber();
	}

	public void selectSavedCardTypeCheckbox() {
		waitForExpectedElement(savedCardTypeCheckbox, 10);
		clickByElementByQueryJSExecutor(savedCardTypeCheckbox);
	}

	public void selectUsersSavedCard() {
		waitForExpectedElement(usersSavedCardCheckbox, 10);
		List<WebElement> savedCards = getWebDriver().findElements(SAVED_CARD);
		savedCards.get(savedCards.size()-1).click();
	}

	public void enterUserSavedCardCVVNumber() {
		waitForExpectedElement(usersSavedCardCVVField, 10).sendKeys("123");
	}

	public void selectCreditCardCheckbox() {
		waitForExpectedElement(creditCardCheckbox);
		clickByElementByQueryJSExecutor(creditCardCheckbox);
	}

	public void selectDebitCardCheckbox() {
		waitForExpectedElement(debitCardCheckbox);
		clickByElement(debitCardCheckbox);
	}

	public void selectMasterCardTypeCheckbox() {
		waitForExpectedElement(masterCardTypeCheckbox);
		clickByElement(masterCardTypeCheckbox);
	}

	public void selectVisaCardTypeCheckbox() {
		waitForExpectedElement(visaCardTypeCheckbox);
		clickByElementByQueryJSExecutor(visaCardTypeCheckbox);
	}

	public void selectOtherPaymentMethodCheckbox() {
		waitForExpectedElement(otherPaymentMethodCheckbox, 20);
		clickByElementByQueryJSExecutor(otherPaymentMethodCheckbox);
	}

	public void selectPayPalCheckbox() {
		waitForExpectedElement(paypalCheckbox, 10);
		clickByElementByQueryJSExecutor(paypalCheckbox);

	}

	public void selectBuyNowButtonPalPal() {
		waitForExpectedElement(buyNowButtonPayPal, 20);
		clickByElement(buyNowButtonPayPal);
	}

	public void selectBuyNowButtonFromCard() {
		waitForExpectedElement(placeOrderButtonCard, 10);
		clickByElement(placeOrderButtonCard);
	}

	public void clickOnCouponCodeLink() {
		waitForExpectedElement(couponCodeLink,20);
		clickByElement(couponCodeLink);
	}

	public String invalidPromoCodeError() {
		return waitForExpectedElement(invalidPromoCodeError, 10).getText();
	}
	public String validPromoCodeSucessMsg() {
		return waitForExpectedElement(validPromoCodeSucessMsg, 10).getText();
	}
	public void ClickOnApplyDiscountButton() {
		waitForExpectedElement(appyDiscountButton, 10);
		clickByElement(appyDiscountButton);
	}

	public void selectSwitchAddressCheckbox() {
		waitForExpectedElement(switchAddressCheckbox, 20);
		clickByElementByQueryJSExecutor(switchAddressCheckbox);
	}

	public Boolean isOrderSummaryPresent() {
		return waitForExpectedElement(orderSummaryHeading, 30).isDisplayed();
	}
}