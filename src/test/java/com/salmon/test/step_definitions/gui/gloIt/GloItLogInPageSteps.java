package com.salmon.test.step_definitions.gui.gloIt;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.page_objects.gui.HomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItHomePage;
import com.salmon.test.page_objects.gui.gloIt.GloItLoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class GloItLogInPageSteps {

	GloItLoginPage gloItLoginPage;
	GloItHomePage gloItHomePage;
	HomePage homePage;
	private static final Logger LOG = LoggerFactory.getLogger(GloItLogInPageSteps.class);
	public GloItLogInPageSteps(GloItLoginPage gloItLoginPage, GloItHomePage gloItHomePage, HomePage homePage) {

		this.gloItLoginPage = gloItLoginPage;
		this.gloItHomePage=gloItHomePage;
		this.homePage=homePage;
	}

	@Then("^assert glo login pageTitle is '(.*)'$")
	public void assert_login_pageTitle(String expectedLoginPageTitle) {
		LOG.info("actual tile of login page is " + gloItLoginPage.getCurrentPageTitle() + " expected was "+UrlBuilder.getMessage(expectedLoginPageTitle));
		assertThat(gloItLoginPage.getCurrentPageTitle().contains(UrlBuilder.getMessage(expectedLoginPageTitle)))
				.as("ERROR: glo login page title is incorrect").isTrue();
	}


	@And("^GloIt assert text of '(.*)' is displayed$")
	public void assertTextOfRegisteredCustomersIsDisplayed(String expectedText) throws Throwable {
		Thread.sleep(5000);
		assertTrue(gloItLoginPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText)));
	}

	@And("^GloIt InfoText assert text of '(.*)' is displayed$")
	public void gloIt_InfoText_text_is_displayed(String expectedText) {
		gloItLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedText), gloItLoginPage.InfoTextHeading());
	}

	@And("^GloIt Login Heading assert text of '(.*)' is displayed$")
	public void gloIt_assert_text_is_displayed(String expectedText) {
		switch (UrlBuilder.getLocale()) {
			case "glode":
				LOG.info("EXPECTED :"+gloItLoginPage.getCurrentPageTitle());
				gloItLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedText), gloItLoginPage.getCurrentPageTitle());
				break;
			default:
			gloItLoginPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedText), gloItLoginPage.getPageHeading());
		}
	}

	@And("^GloIt email input box displayed and enabled$")
	public void gloIt_email_input_box_displayed_and_enabled() {
		gloItLoginPage.emailInputBoxDisplayedAndEnabled();
	}

	@And("^GloIt Login password input box displayed and enabled$")
	public void gloIt_Login_password_input_box_displayed_and_enabled() {
		gloItLoginPage.passwordInputBoxDisplayedAndEnabled();
	}

	@And("^GloIt remember Check box is displayed and enabled and unchecked by default$")
	public void gloIt_remember_Check_box_is_displayed_and_enabled_and_unchecked_by_default() {
		gloItLoginPage.checkboxDisplayedEnabledAndUnchecked();
	}

	@And("^GloIt forgot your password link displayed$")
	public void gloIt_forgot_your_password_link_displayed() {
		gloItLoginPage.gloItForgotPasswordLinkDisplayed();
	}

	@And("^GloIt register button is displayed$")
	public void gloIt_register_button_is_displayed() {
		gloItLoginPage.ragisterButtonDisplayed();
	}

	@And("^GloIt Login button is displayed$")
	public void Login_button_is_displayed() {
		gloItLoginPage.loginBtnDisplayedAndEnabled();
	}

	@And("^GloIt user clicks the registration button$")
	public void userClicksTheRegistrationButton() {
		// Seen failures where the user is already logged in from previous test.
		// Code need to be able to smartly log user out if not in the correct state
		try {
			LOG.info("Registration Cycle Begun");
			gloItLoginPage.waitForExpectedElement(gloItLoginPage.ToRegisterBtn);
			gloItLoginPage.clickByElementByQueryJSExecutor(gloItLoginPage.ToRegisterBtn);
		} catch (Exception e) {
			LOG.info("\n User is logged in, logging out to re-set state");
			LOG.info("\n Logging OUT");
			gloItLoginPage.waitForExpectedElement(By.linkText("Logout")).click();
			LOG.info("\n Clicking on person icon to return to registration page");
			gloItLoginPage.waitForExpectedElement(By.cssSelector("div.account-icon")).click();
			gloItLoginPage.waitForExpectedElement(gloItLoginPage.ToRegisterBtn);
			gloItLoginPage.clickByElementByQueryJSExecutor(gloItLoginPage.ToRegisterBtn);
		}
	}

	@Then("^Glo user is successfully logged in$")
	public void userIsSuccessfullyLogged() {
		if(gloItLoginPage.doesURLContain("kz/ru")){
			gloItHomePage.waitForExpectedElement(gloItHomePage.PersonIcon).isDisplayed();
		}else{
			assertTrue(gloItLoginPage.doesURLContain("customer/account")); }

	}

	@Then("^GloIt password is required error message pops up$")
	public void passwordIsRequiredErrorMessagePopsUp() {
	gloItLoginPage.passwordIsRequiredErrorMessagePopsUp();
	}

	@Then("^GloIt invalid email message of '(.*)' is displayed to user$")
	public void invalidEmailMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(
			String expectedErrorMessage) {
		gloItLoginPage.invalidEmailMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(expectedErrorMessage);
	}


	@Then("^GloIt error message of '(.*)' is displayed to user$")
	public void errorMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(
			String expectedErrorMessage) {
		gloItLoginPage.errorMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(expectedErrorMessage);
	}

	@When("^GloIt user clicks the forgotten password link$")
	public void userClicksTheForgottenPasswordLink() {
		gloItLoginPage.waitForExpectedElement(gloItLoginPage.forgotPasswordButton).click();
		LOG.info("testing breakpoint");
	}

	@When("^GloIt user enters sign in details, with username of '(.*)' and password '(.*)'$")
	public void userEntersValidSigninDetails(String email, String password) {
		Boolean LoggedInState = gloItLoginPage.getWebDriver().getPageSource().contains("MyAccountTitle.key");
		if (LoggedInState) {
			LOG.info("\n ****** USER APPEARS TO ALREADY BE LOGGED IN ");
		} else {
			LOG.info("\n ****** Attempting to login");
			gloItLoginPage.waitForExpectedElement(gloItLoginPage.emailInputBox, 10).sendKeys(UrlBuilder.getMessage(email));
			gloItLoginPage.waitForExpectedElement(gloItLoginPage.passwordInputBox).sendKeys(UrlBuilder.getMessage(password));
			if (UrlBuilder.getLocale().equals("kz")) {
				gloItLoginPage.clickByElementByQueryJSExecutor(gloItLoginPage.LoginInBtn);
			} else {
				gloItLoginPage.clickUsingJS(gloItLoginPage.LoginInBtn);
			}
		}
	}

/*	@And("^user signs in to the site with credentials '(.*)' '(.*)'$")
	public void userSignsInToTheSiteWithCredentialsLoginValidEmailKeyLoginValidPasswordKey(String email, String passWord) {
		gloItLoginPage.username = UrlBuilder.getMessage(email);
		String password = UrlBuilder.getMessage(passWord);
		try {
			gloItHomePage.waitForExpectedElement(gloItLoginPage.EMAIL_INPUTBOX);
		} catch (Exception e) {
			homePage.refreshBrowser();
			gloItHomePage.waitForExpectedElement(gloItLoginPage.EMAIL_INPUTBOX);
		}
		gloItHomePage.waitForExpectedElement(gloItLoginPage.EMAIL_INPUTBOX).sendKeys(gloItLoginPage.username);
		gloItHomePage.waitForExpectedElement(gloItLoginPage.PASSWORD_INPUTBOX).sendKeys(password);
		homePage.waitForExpectedElement((gloItLoginPage.SIGNIN_BUTTON));
		gloItHomePage.clickByElement(gloItLoginPage.SIGNIN_BUTTON);
	}*/

	@And("^user clicks on Close button$")
	public void userClicksOnCloseButton() {
		try {

			gloItHomePage.waitForExpectedElement(gloItLoginPage.CLOSE_BUTTON);
			gloItHomePage.clickByElementByQueryJSExecutor(gloItLoginPage.CLOSE_BUTTON);
		} catch (Exception e) {
			LOG.info("Close button isn't present - continuing");
		}
	}


	@And("^user clicks on Close option$")
	public void userClicksOnCloseOption() {
		try {
			homePage.clickByElementByQueryJSExecutor(gloItLoginPage.CLOSE_BUTTON2);
		} catch (Exception e) {
				homePage.eyesCheckAgeGate();
				homePage.clickByElement(gloItLoginPage.CLOSE_BUTTON2);
			}
		}
	}
