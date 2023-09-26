package com.salmon.test.page_objects.gui.gloIt;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GloItLoginPage extends PageObject {

	public By emailInputBox = By.cssSelector("input#email.input-text");
	public By passwordInputBox = By.name("login[password]");
	private By checkbox = By.cssSelector("span.checkmark");
	public By LoginInBtn = By.cssSelector("button#send2.action.login.primary");
	public By ToRegisterBtn = By.cssSelector("a.action.create.primary");
	private By forgotpasslink = By.cssSelector("a.action.remind");
	private By pageHeading = By.cssSelector("div.field.note");
	private By InfoText = By.cssSelector("div.block.block-new-customer:nth-child(2) div.block-content > p:nth-child(1)");
	public By passwordRequiredErrorMsg = By.cssSelector("#pass-error");
	public By errorMessage = By.cssSelector("div.message-error.error.message");
	public By invalidEmailErrorMsg = By.cssSelector("#email-error");
	public By forgotPasswordButton = By.cssSelector("a.action.remind");
	public static final By EMAIL_INPUTBOX = By.id("email");
	public static final By PASSWORD_INPUTBOX = By.id("password");
	public static final By SIGNIN_BUTTON = By.id("btn-login-submit");
	public static final By CLOSE_BUTTON = By.cssSelector("div.col-overlay>button.btn-prim");
	public static final By CLOSE_BUTTON2 = By.cssSelector("a[href='javascript:void(0)']");
	public static String username;
	public String getPageHeading() {
		return waitForExpectedElement(pageHeading).getText();
	}

	public String InfoTextHeading() {
		return waitForExpectedElement(InfoText).getText();
	}

	public void emailInputBoxDisplayedAndEnabled() {
		assertTrue(waitForExpectedElement(emailInputBox, 10).isDisplayed());
		assertTrue(waitForExpectedElement(emailInputBox, 10).isEnabled());
	}

	public void passwordInputBoxDisplayedAndEnabled() {
		assertTrue(waitForExpectedElement(passwordInputBox, 10).isDisplayed());
		assertTrue(waitForExpectedElement(passwordInputBox, 10).isEnabled());
	}

	public void checkboxDisplayedEnabledAndUnchecked() {
		assertTrue(waitForExpectedElement(checkbox, 10).isDisplayed());
		assertTrue(waitForExpectedElement(checkbox, 10).isEnabled());
		assertFalse(waitForExpectedElement(checkbox, 10).isSelected());
	}

	public void loginBtnDisplayedAndEnabled() {
		assertTrue(waitForExpectedElement(LoginInBtn, 10).isDisplayed());
		assertTrue(waitForExpectedElement(LoginInBtn, 10).isEnabled());
	}

	public void gloItForgotPasswordLinkDisplayed() {
		assertTrue(waitForExpectedElement(forgotpasslink, 10).isDisplayed());
	}

	public void ragisterButtonDisplayed() {
		assertTrue(waitForExpectedElement(ToRegisterBtn, 10).isDisplayed());
	}

	public void errorMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(
			String expectedErrorMessage) {
		if (getCurrentUrl().contains("kz/ru")) {
			waitForExpectedElement(invalidEmailErrorMsg, 10).isDisplayed();
		} else {
			assertTrueWithCustomError(UrlBuilder.getMessage(expectedErrorMessage),
					waitForExpectedElement(invalidEmailErrorMsg, 10).getText());
			LOG.info("Testing breakpoint");
		}
	}

	public void passwordIsRequiredErrorMessagePopsUp() {
		if (getCurrentUrl().contains("kz/ru")) {
			waitForExpectedElement(passwordRequiredErrorMsg, 10).isDisplayed();
		} else {
			String expectedMissingPasswordErrorMsg = UrlBuilder.getMessage("loginMissingPasswordErrorMsg.key");
			assertTrueWithCustomError(expectedMissingPasswordErrorMsg,
					waitForExpectedElement(passwordRequiredErrorMsg, 10).getText());
		}
	}

	public void invalidEmailMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(String expectedErrorMessage) {
		if (getCurrentUrl().contains("kz/ru")) {
			waitForExpectedElement(errorMessage, 10).isDisplayed();
		} else
			assertTrueWithCustomError(UrlBuilder.getMessage(expectedErrorMessage),
					waitForExpectedElement(errorMessage, 10).getText());
		LOG.info("Testing breakpoint");
	}

}
