package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.epok.EpokLoginPage;
import com.salmon.test.page_objects.gui.epok.MyAccountPage;
import com.salmon.test.services.posts.CommentsApi;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EpokLoginPageSteps {

	EpokLoginPage epokLoginPage;
	MyAccountPage accountPage;
	private static final Logger LOG = LoggerFactory.getLogger(EpokLoginPageSteps.class);

	public EpokLoginPageSteps(EpokLoginPage epokLoginPage, MyAccountPage accountPage) {
		this.accountPage = accountPage;
		this.epokLoginPage = epokLoginPage;
	}

	@Then("^assert login pageTitle is '(.*)'$")
	public void assert_login_pageTitle(String expectedLoginPageTitle) {
		LOG.info("tile of login page is " + epokLoginPage.getCurrentPageTitle());

		assertEquals(UrlBuilder.getMessage(expectedLoginPageTitle), epokLoginPage.getCurrentPageTitle());

	}

	@And("^Epok assert text of '(.*)' is displayed$")
	public void assertTextOfRegisteredCustomersIsDisplayed(String expectedText) throws Throwable {
		Thread.sleep(5000);
		try {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(epokLoginPage.getWebDriver().getPageSource().toLowerCase()
					.contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
		}
		catch(Exception e){
				assertTrue(epokLoginPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText)));
			}
	}

	@And("^Epok InfoText assert text of '(.*)' is displayed$")
	public void Epok_InfoText_text_is_displayed(String expectedText) {
		epokLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedText), epokLoginPage.InfoTextHeading());
	}

	@And("^EPOK Login Heading assert text of '(.*)' is displayed$")
	public void Epok_assert_text_is_displayed(String expectedText) {
		epokLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedText), epokLoginPage.getPageHeading());

	}

	@And("^Epok email input box displayed and enabled$")
	public void Epok_email_input_box_displayed_and_enabled() {
		epokLoginPage.emailInputBoxDisplayedAndEnabled();

	}

	@And("^Epok Login password input box displayed and enabled$")
	public void Epok_Login_password_input_box_displayed_and_enabled() {
		epokLoginPage.passwordInputBoxDisplayedAndEnabled();
	}

	@And("^Epok remember Check box is displayed and enabled and unchecked by default$")
	public void Epok_remember_Check_box_is_displayed_and_enabled_and_unchecked_by_default() {
		epokLoginPage.checkboxDisplayedEnabledAndUnchecked();
	}

	@And("^Epok forgot your password link displayed$")
	public void Epok_forgot_your_password_link_displayed() {
		epokLoginPage.epokForgotPasswordLinkDisplayed();
	}

	@And("^Register button is displayed$")
	public void register_button_is_displayed() {
		epokLoginPage.ragisterButtonDisplayed();
	}

	@And("^Login button is displayed$")
	public void Login_button_is_displayed() {
		epokLoginPage.loginBtnDisplayedAndEnabled();
	}

	@And("^Epok user clicks the registration button$")
	public void userClicksTheRegistrationButton() {
		// Seen failures where the user is already logged in from previous test.
		// Code need to be able to smartly log user out if not in the correct state
		try {
			epokLoginPage.clickRegisterButton();
		} catch (Exception e) {
			epokLoginPage.waitForExpectedElement(By.linkText("Logout")).click();
			epokLoginPage.waitForExpectedElement(By.cssSelector("div.account-icon")).click();

			epokLoginPage.clickRegisterButton();
		}
	}

	@Then("^Epok user is successfully logged in$")
	public void userIsSuccessfullyLogged() {
		assertTrue(epokLoginPage.getWebDriver().getTitle().contains(UrlBuilder.getMessage("MyAccountTitle.key")));
	}

	@Then("^Epok password is required error message pops up$")
	public void passwordIsRequiredErrorMessagePopsUp() {
		String expectedMissingPasswordErrorMsg =UrlBuilder.getMessage("loginMissingPasswordErrorMsg.key");
		epokLoginPage.assertTrueWithCustomError(expectedMissingPasswordErrorMsg,
				epokLoginPage.waitForExpectedElement(epokLoginPage.passwordRequiredErrorMsg).getText());
	}

	@Then("^Epok invalid email message of '(.*)' is displayed to user$")
	public void invalidEmailMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(
			String expectedErrorMessage) {
				epokLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedErrorMessage),
				epokLoginPage.waitForExpectedElement(epokLoginPage.errorMessage, 10).getText());
				LOG.info("Testing breakpoint");
	}

	@Then("^Epok error message of '(.*)' is displayed to user$")
	public void errorMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(
			String expectedErrorMessage) {
				epokLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedErrorMessage),
				epokLoginPage.waitForExpectedElement(epokLoginPage.invalidEmailErrorMsg, 10).getText());
		LOG.info("Testing breakpoint");
	}

	@When("^Epok user clicks the forgotten password link$")
	public void userClicksTheForgottenPasswordLink() {
		epokLoginPage.waitForExpectedElement(epokLoginPage.forgotPasswordButton).click();
		LOG.info("testing breakpoint");
	}

	@When("^Epok user enters sign in details, with username of '(.*)' and password '(.*)'$")
	public void userEntersValidSigninDetails(String email, String password) {
		Boolean LoggedInState = epokLoginPage.getWebDriver().getPageSource().contains("MyAccountTitle.key");

		if (LoggedInState) {
			LOG.info("\n ****** USER APPEARS TO ALREADY BE LOGGED IN ");

		} else {
			LOG.info("\n ****** Attempting to login");
			epokLoginPage.waitForExpectedElement(epokLoginPage.emailInputBox, 10).sendKeys(UrlBuilder.getMessage(email));
			epokLoginPage.waitForExpectedElement(epokLoginPage.passwordInputBox).sendKeys(UrlBuilder.getMessage(password));
			epokLoginPage.clickUsingJS(epokLoginPage.LoginInBtn);
			epokLoginPage.waitForExpectedElement(epokLoginPage.productSubmenu, 10);
			accountPage.eyesCheckAccountDashboardPage();
		}
	}

}
