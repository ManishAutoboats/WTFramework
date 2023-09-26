package com.salmon.test.step_definitions.gui.epok;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.page_objects.gui.epok.MyAccountPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPageSteps {
	MyAccountPage epokMyAccountPage;
	private String emailAddressData = RandomGenerator.randomEmailAddress(6);
	private static final Logger LOG = LoggerFactory.getLogger(MyAccountPageSteps.class);

	public MyAccountPageSteps(MyAccountPage epokMyAccountPage) {
		this.epokMyAccountPage = epokMyAccountPage;
	}

	@And("^users clicks on the my account menu dropdown$")
	public void users_Clicks_On_MyAccount_DropDown() {
		epokMyAccountPage.clickOnMyAccountDropDown();
	}
	@And("^user select (.*) in my account menu$")
	public void user_Select_My_Orders_In_My_Account_Menu(String expectedValue) {
		epokMyAccountPage.selectValueFromDropDown(UrlBuilder.getMessage(expectedValue));
	}
	@Given("^user navigates EPOK Account Section")
	public void userNavigatesEPOKAccountSection() {
		epokMyAccountPage.selectAccountSection();
	}
	@Given("^Select my account from dropdown")
	public void selectMyAccountFromDropdown() {
		epokMyAccountPage.selectMyAccountOption();
	}
	@Then("^Epok assert text of my account (.*) is displayed$")
		public void assertTextOfPageDisplayed(String expectedText) {
		epokMyAccountPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText));
	}
	@Given("^Click on edit button to change password & Email address")
	public void clickOnEditButtonToChangePasswordEmailAaddress() {
		epokMyAccountPage.clickEditPasswordEmail();
	}
	@Given("^Click on change email checkbox")
	public void clickOnChangeEmailCheckbox() {
		epokMyAccountPage.selectEmailCheckbox();
	}
	@Given("^Enter new Email id in email box")
	public void enterNewEmailIdInEmailBox() {
		epokMyAccountPage.waitClearAndEnterText(epokMyAccountPage.emailField, emailAddressData);
	}
	@And("^Enter confirm new Password '(.*)' in confirm new password box$")
	public void enterConfirmPassowrd(String confirmPassowrd) {
		epokMyAccountPage.enterText(epokMyAccountPage.confirmnewPasswordField,UrlBuilder.getMessage(confirmPassowrd));
	}

	@Given("^Click on save button")
	public void clickOnSaveButton() {
	epokMyAccountPage.clickOnSave();
	}

	@Given("^Click on change Password checkbox")
	public void clickOnChangePasswordCheckbox() {
		epokMyAccountPage.selectPasswordCheckbox();
	}
	@Given("^Enter new Password '(.*)' in new password box")
	public void enterNewPasswordInNewPasswordField(String newPassword) {
		epokMyAccountPage.enterText(epokMyAccountPage.newPasswordField,UrlBuilder.getMessage(newPassword));
	}
	@And("^Enter current Password '(.*)' in new password box")
	public void enterurrentPasswordInNewPasswordField(String cureentPassword) {
		epokMyAccountPage.enterText(epokMyAccountPage.curremtPasswordField, UrlBuilder.getMessage(cureentPassword));
	}
	@And("^Select shipping addresses option from dropdown$")
	public void select_Shipping_Addresses_Option_From_Dropdown() {
		epokMyAccountPage.getWebDriver().findElements(By.cssSelector("div.select-items>div"));
	}
	@And("^Select tell a friend option from dropdown$")
	public void select_Tell_Friend_Option_From_Dropdown() {
		epokMyAccountPage.selectInviteFriendMenuOption();

	}
	@And("^click on edit button of newsletter$")
	public void click_On_Edit_ButtonNewsletter() {
		epokMyAccountPage.selectNewsLetterEditButton();
	}
	@And("^select check box of newsletter subscription$")
	public void select_Checkbox_Newsletter_Subscription() {
		epokMyAccountPage.selectNewsLetterSubscriptionCheckbox();
	}
	  @And("^Epok enter '(.*)' as lookup address$")
	  public void enterAsLookupAddress(String storeLocatorLookup) throws Throwable {
	    Thread.sleep(6000);
	    epokMyAccountPage.waitForExpectedElement(By.cssSelector("input#pac-input.input-field.controls")).clear();
	    epokMyAccountPage.waitForExpectedElement(By.cssSelector("input#pac-input.input-field.controls")).sendKeys(UrlBuilder.getMessage(storeLocatorLookup));
	    epokMyAccountPage.waitForExpectedElement(By.cssSelector("input#pac-input.input-field.controls")).sendKeys(Keys.DOWN);
	    epokMyAccountPage.waitForExpectedElement(By.cssSelector("input#pac-input.input-field.controls")).sendKeys(Keys.RETURN);
	  }
	  @And("^Epok assert displayed address is '(.*)'$")
	  public void assertDisplayedAddressIsTheCloistersRickmansworthWDHLUK(String expectedAddress) throws Throwable {
	    Thread.sleep(4000);
	    String addressDisplayed = epokMyAccountPage.waitForExpectedElement(By.cssSelector("#pac-input")).getAttribute("value");
		  LOG.info("Address displayed : " + addressDisplayed);
	    epokMyAccountPage.assertTrueWithCustomError(UrlBuilder.getMessage(expectedAddress),addressDisplayed);
	  }
}
