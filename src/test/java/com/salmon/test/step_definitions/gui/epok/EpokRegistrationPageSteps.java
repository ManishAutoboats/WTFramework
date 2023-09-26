package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.epok.EpokRegistrationPage;
import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpokRegistrationPageSteps {

	private EpokRegistrationPage epokregistrationPage;
	private static final Logger LOG = LoggerFactory.getLogger(EpokRegistrationPageSteps.class);
	private String postCodeData = "21307";
	private String emailAddressData = RandomGenerator.randomEmailAddress(6);
	private String password = "M@nish1710";

	public EpokRegistrationPageSteps(EpokRegistrationPage epokregistrationPage) {
		this.epokregistrationPage = epokregistrationPage;
	}

	@And("^Epok assert page title is '(.*)'$")
	public void assertPgeTaitleIsCreateNewCustomerAccount(String expectedPageTitle) {
		epokregistrationPage.waitForPage();

		epokregistrationPage.assertTrueWithCustomError(Props.getProp(expectedPageTitle),
				epokregistrationPage.getPageHeading());
	}

	@And("^Epok assert text of '(.*)' is displayed on my registration page$")
	public void assertTextOfCustomerLoginIsDisplayedOnMyRegistrationPage(String expectedText) {

		epokregistrationPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText));
	}

	@And("^Epok populate Personal Information - first and last name with randomly generated data$")
	public void populatePersonalInformationFirstAndLastNameWithRandomlyGeneratedData() throws Throwable {
		epokregistrationPage.enterFirstNameAndLastName();
		Thread.sleep(2000);
	}

	@And("^Epok populate Email Address and password$")
	public void Epok_populate_Email_Address() {
		epokregistrationPage.enterEmailAddressAndPassword();
	}

	@And("^Epok user Click on Confirmage button$")
	public void epok_user_Click_On_Confirmage_Button() throws Throwable {
		Thread.sleep(3000);
		epokregistrationPage.clickOnConfirmAgeButton();
	}

	@And("^Epok populate DOB field with '(.*)'$")
	public void populateDOBFieldWith(String DOBToEnter) {
		epokregistrationPage.enterDOB(UrlBuilder.getMessage(DOBToEnter));
	}

	@And("^Epok User click on checkData button$")
	public void epok_User_click_on_checkData_button() {
		epokregistrationPage.clickCheckDataButton();

	}

	@And("^Epok user click terms and comdintion ando complete registeration button$")
	public void epok_user_click_on_complete_registeration_button() throws Throwable {
		epokregistrationPage.completeRegistration();
	}

	@And("^Epok populate gender field with '(.*)'$")
	public void populateGenderFieldWithMale(String genderToSelect) {
		epokregistrationPage.clickByElementByQueryJSExecutor(epokregistrationPage.gender);
		epokregistrationPage.selectValueFromDropDownByby("Male", epokregistrationPage.gender);
	}

	@And("^Epok populate address fields$")
	public void populateAddressFields() {
		epokregistrationPage.populateAddressFields();
	}

	@And("^Epok populate signin fields$")
	public void populateSigninFields() {
		String emailAddress = emailAddressData;
		LOG.info("Email  : " + emailAddress);
		LOG.info("Password : " + password);
		epokregistrationPage.enterText(epokregistrationPage.emailaddress, emailAddress);
		epokregistrationPage.enterText(epokregistrationPage.password, password);
		epokregistrationPage.checkTsCs();
	}

	@And("^Epok populate phone number field with '(.*)'$")
	public void populatePhoneNumberFieldWith(String textToEnter) {
		epokregistrationPage.enterPhoneNumber(textToEnter);
	}

	@And("^Epok and assert error validation message of '(.*)'$")
	public void AssertErrorValidationMessageOfTelephoneError(String expectedMobileValidationMessage) {
		String telephoneErrorValidationMessage = epokregistrationPage
				.waitForExpectedElement(By.cssSelector("div.message-error.error.message")).getText();
		LOG.info("Error message provided : " + telephoneErrorValidationMessage);
		epokregistrationPage.assertTrueWithCustomError(Props.getProp(expectedMobileValidationMessage),
				telephoneErrorValidationMessage);
	}

	@And("^Epok enter postcode and assert postcode lookup working$")
	public void enterPostcodeAndAssertPostcodeLookupWorking() throws Throwable {
		epokregistrationPage.enterText(epokregistrationPage.postCode, postCodeData);
		epokregistrationPage.waitForExpectedElement(epokregistrationPage.postCode).sendKeys(Keys.DOWN);
		epokregistrationPage.waitForExpectedElement(epokregistrationPage.postCode).sendKeys(Keys.RETURN);
		epokregistrationPage.waitForExpectedElement(epokregistrationPage.postCode).sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		String valueInCityField = epokregistrationPage.getWebDriver().findElement(By.cssSelector("#city"))
				.getAttribute("value");
		epokregistrationPage.assertTrueWithCustomError("Uxbridge", valueInCityField);
		LOG.info("Assert the text displayed as expected - results should be available to choose");
	}
}
