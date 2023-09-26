package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.BATHelper;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.RegistrationPage;
import com.salmon.test.page_objects.gui.LogininPage;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.salmon.test.enums.PermittedCharacters.*;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@Getter
public class GloItRegistrationPageSteps extends PageObject {

	private RegistrationPage registrationPage;
	private HomePage homePage;
	private LogininPage loginPage;

	private String firstNameData = random(6, ALPHABETS);
	private String lastNameData = random(6, ALPHABETS);
	private String DOBData = "08/05/2019";
	private String IntMGMPromoCode = "MGM-" + random(5, NUMERIC);
	private String companyName = random(20, ALPHABETS);
	private String phoneNumber = random(10, NUMERIC);
	private String loginIdData = random(6, ALPHANUMERIC);
	private String passwordData = random(6, ALPHANUMERIC);
	private String titleData = "Dr.";
	private String postCodeData = "UB10 9DW";
	private String address1Data = random(6, ALPHABETS);
	private String townOrCityData = random(6, ALPHABETS);
	private String birthDateData = "1";
	private String birthMonthData = "1";
	private String birthYearData = "1982";


	public GloItRegistrationPageSteps(RegistrationPage registrationPage, HomePage homePage, LogininPage loginPage) {
		this.registrationPage = registrationPage;
		this.homePage = homePage;
		this.loginPage = loginPage;
	}

	@And("^populate signin fields for Glo$")
	public void populateSigninFieldsForGlo() {
		registrationPage.createEmailAndPassword();
	}

	@And("^user goes to registration form$")
	public void navigateToRegistrtionPage() {
		homePage.navigateToSignInPage();
		loginPage.clickRegistrationButton();
		loginPage.waitForPage();
	}

	@And("^add personal data for glo user with date of birth '(.*)' and gender '(.*)'$")
	public void addDataWithNameDOBAndGender(String validDOB, String genderToSelect) {
		registrationPage.waitForExpectedElement(registrationPage.firstNameInput, 10);
		registrationPage.enterDataAndWait(registrationPage.firstNameInput, firstNameData);
		registrationPage.enterDataAndWait(registrationPage.surNameInput, lastNameData);
		registrationPage.enterDataAndWait(registrationPage.DOBInput, UrlBuilder.getMessage(validDOB));
		if (registrationPage.getCurrentUrl().contains("kz/ru")) {
			registrationPage.selectValueFromDropDownByIndex(2, By.cssSelector("#gender"));
		} else {
			registrationPage.selectValueFromDropDownByby(UrlBuilder.getMessage(genderToSelect), registrationPage.gender);
		}
	}

	@And("^populate glo address fields '(.*)'$")
	public void populateGloAddressFields(String suggestedkeywords) {
		try {
			Thread.sleep(4000);
			registrationPage.waitForExpectedElement(By.cssSelector("#street_1"))
					.sendKeys(UrlBuilder.getMessage(suggestedkeywords));
			registrationPage.waitForExpectedElement(By.cssSelector("#street_1")).sendKeys(Keys.DOWN);
			registrationPage.waitForExpectedElement(By.cssSelector("#street_1")).sendKeys(Keys.RETURN);
			Thread.sleep(1000);
			registrationPage.waitForExpectedElement(By.cssSelector("#street_1")).sendKeys(Keys.RETURN);
			registrationPage.waitForExpectedElement(By.cssSelector("#city"))
					.sendKeys(UrlBuilder.getMessage("cityKeyword.key"));
			registrationPage.waitForExpectedElement(By.cssSelector("#zip"))
					.sendKeys(UrlBuilder.getMessage("postalCode.key"));
			if (registrationPage.getWebDriver().findElements(registrationPage.MGM_PROMO_CODE).size() > 0)
				registrationPage.enterDataAndWait(registrationPage.MGM_PROMO_CODE, IntMGMPromoCode);
		} catch (Exception e) {
			LOG.info("Error while filling address details " + e);
		}
	}

	@And("^add home town field with '(.*)'$")
	public void populateHomeTownField(String homeTown) {
		switch (UrlBuilder.getLocale()) {
			case "kz":
				registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage(homeTown));
				break;
			default:
				try {
					registrationPage.enterText(registrationPage.homeTown, UrlBuilder.getMessage(homeTown));
				} catch (Exception e) {
					LOG.info("**** ERROR - Can't fine key : homeTown ");
				}
		}
	}

	@And("^add postal code field with '(.*)'$")
	public void populateCodiceFiscale(String postalCode) {
		switch (UrlBuilder.getLocale()) {
			case "kz":
				registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage(postalCode));
				break;
			default:
				try {
					registrationPage.enterText(registrationPage.postalCode, UrlBuilder.getMessage(postalCode));
				} catch (Exception e) {
					LOG.info("**** ERROR - Can't find key : codice fiscale ");
				}
		}
	}

	@And("^add telephone number field with '(.*)'$")
	public void addTelephoneNumber(String telephone) {
		try {
			registrationPage.enterText(registrationPage.phoneNumber, UrlBuilder.getMessage(telephone));
		} catch (Exception e) {
			LOG.info("**** ERROR - Can't find key : telephone ");
		}
	}

	@And("^add province field with '(.*)'$")
	public void populateDOBFieldWith(String province) {
		try {
			registrationPage.enterText(registrationPage.province, UrlBuilder.getMessage(province));
		} catch (Exception e) {
			LOG.info("**** ERROR - Can't fine key : province ");
		}
	}

	@And("^tick agree to terms and conditions of glo on registration page$")
	public void tickAgreeToTermsAndConditionsOnGlo() {
		registrationPage.tickAgreeToTermsAndConditionsOnGlo();
	}

	@And("^Glo user enters all the required data for a new user registration$")
	public void GloUserEntersAllRequiredInfoforNewUserRegistration() {
		addDataWithNameDOBAndGender("validDOB.key", "gender.key");
		switch (UrlBuilder.getLocale()) {
			case "it":
				String fiscalCode = RegistrationPageModel.builder().build().getValidFiscalCode(
						UrlBuilder.getMessage("validDOB.key"));
				registrationPage.enterText(registrationPage.postalCode, fiscalCode);
				addTelephoneNumber("validPhoneNumber.key");
				populateGloAddressFields("addressKeyword.key");
				populateHomeTownField("homeTown.key");
				populateDOBFieldWith("province.key");
				break;
			case "glode":
				registrationPage.enterText(registrationPage.streetAddressLine1, UrlBuilder.getMessage("streetAddress.key"));
				registrationPage.enterText(registrationPage.city, UrlBuilder.getMessage("city.key"));
				registrationPage.enterText(registrationPage.postCode, UrlBuilder.getMessage("postalCode.key"));
				registrationPage.enterText(registrationPage.phoneNumber, phoneNumber);
				break;
			case "pl":
				break;
		}
		populateSigninFieldsForGlo();
		tickAgreeToTermsAndConditionsOnGlo();
	}

	@And("^populate signin fields with specific email for Glo$")
	public void populateSigninFieldsWithSpecificEmailForGlo() {
		registrationPage.enterDataAndWait(registrationPage.email, UrlBuilder.getMessage("loginValidEmail.key"));
		registrationPage.enterDataAndWait(registrationPage.password, UrlBuilder.getMessage("loginValidPassword.key"));
		registrationPage.enterDataAndWait(registrationPage.passwordConfirmation, UrlBuilder.getMessage("loginValidPassword.key"));
	}

	@Then("^user verify confirmation mail in the mail box and returns to the my account page$")
	public void user_verify_confirmation_mail_in_the_mail_box_and_returns_to_the_my_account_page() {
		registrationPage.verifyEmailAndReturnToMyAccountPage();
	}

	@Then("^assert Subscription message '(.*)' is displayed$")
	public void assertSubscriptionMessageIsDisplayed(String strExpectedMessage) {
		registrationPage.waitForTextToBe(getWebDriver(), 10, registrationPage.SUBSCRIPTION_MESSAGE, UrlBuilder.getMessage(strExpectedMessage));
		}

	@And("^assert user is not allowed to enter more than '(.*)' digits in phone number field$")
	public void assertUserNotAllowedToEnterMoreThanDigitsInPhoneNumberField(String strCharlimit) {
		registrationPage.assertUserNotAllowedToEnterMoreThanDigitsInPhoneNumberField(strCharlimit);
	}

	@And("^assert error message '(.*)' when phone number starting with 0 and less than limit is entered$")
	public void assertErrorMessageWhenPhoneNumberStartingWithZeroLessThanLimitIsEntered(String strErrorText) {
		registrationPage.assertErrorMessageWhenPhoneNumberStartingWithZeroLessThanLimitIsEntered(strErrorText);
	}

	@Then("^assert no duplicate items in the address dropdown list$")
	public void assertNoDuplicateItemsInTheAddresDropdownList() {
		assertFalse(registrationPage.hasDuplicateItemsInTheAddressDropdownList());
	}

	@And("^user type partial post code \"([^\"]*)\"$")
	public void userTypePartialPostCode(String postCode) {
		registrationPage.enterPostCode(postCode);
	}

	@Then("^assert autocomplete results dropdown displayed$")
	public void assertAutocompleteResultsDropdownDisplayed() {
		assertTrue(registrationPage.isPostCodeResultsDropdownDisplayed());
	}
}

