package com.salmon.test.page_objects.gui.epok;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EpokLoginPage extends PageObject {

	public By emailInputBox = By.cssSelector("input#email.input-text");
	public By passwordInputBox = By.name("login[password]");
	private By checkbox = By.cssSelector("span.checkmark");
	public By LoginInBtn = By.cssSelector("button#send2.action.login.primary");
	public By ToRegisterBtn = By.cssSelector("a.action.create.primary");
	private By forgotpasslink = By.cssSelector("a.action.remind");
	private By pageHeading = By.cssSelector("div.field.note");
	private By InfoText = By.cssSelector("div.text-advantage.hidden-mobile");
	public By passwordRequiredErrorMsg = By.cssSelector("#pass-error");
	public By errorMessage = By.cssSelector("div.message-error.error.message");
	public By invalidEmailErrorMsg = By.cssSelector("#email-error");
	public By forgotPasswordButton = By.cssSelector("a.action.remind");
	public By productSubmenu = By.xpath("//div[contains(@class,'top-menu-desktop')]//a[text()='Produkte']");

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

	public void epokForgotPasswordLinkDisplayed() {
		assertTrue(waitForExpectedElement(forgotpasslink, 10).isDisplayed());
	}

	public void ragisterButtonDisplayed() {
		assertTrue(waitForExpectedElement(ToRegisterBtn, 10).isDisplayed());
	}

	public void clickRegisterButton(){
		waitForExpectedElement(ToRegisterBtn);
		clickByElementByQueryJSExecutor(ToRegisterBtn);
	}

}
