package com.salmon.test.page_objects.gui.epok;

import com.applitools.eyes.selenium.fluent.Target;
import com.salmon.test.enums.EyesCheckpoints;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyAccountPage extends PageObject {

	private By myAccountDropDown = By.cssSelector("div.select-selected");
	private By Selectmyaccount = By.cssSelector("div.select-selected");
	private By myaccount = By.cssSelector("div.select-items:nth-child(4) > div:nth-child(1)");
	private By Tellafriend = By.cssSelector("div.select-items:nth-child(4) > div:nth-child(4)");
	private By EditPasswordEmailaddress = By.cssSelector("div.box.box-information:nth-child(1) div.box-content-container div.box-actions a.action.edit > div.edit-icon");
	private By editNewsletterLink = By.cssSelector("div.box.box-newsletter:nth-child(2) div.box-content-container div.box-actions a.action.edit > div.edit-icon");
	private By personicon = By.cssSelector("div.account-icon");
	public  By newsletterSubscribCheckbox=By.cssSelector("span.checkmark");
	private By emailcheckbox = By.cssSelector("label.field.choice.radiocontainer:nth-child(7) > span.checkmark");
	private By passwordCheckbox = By.cssSelector("label.field.choice.radiocontainer:nth-child(8)");
	public By emailField = By.cssSelector("#email");
	public By curremtPasswordField = By.cssSelector("#current-password");
	private By saveButton = By.cssSelector("button.action.save.primary");
	public By newPasswordField = By.cssSelector("#password");
	public By confirmnewPasswordField = By.cssSelector("#password-confirmation");
	private static final By ACCOUNT_INFO = By.cssSelector(".box-information .box-content-container");
	public By NewsletterSubscriptionCheckedCheckbox = By.cssSelector("#subscription");
	private By passwordCheckboxGloKz = By.cssSelector("div.field.choice.choice-box__container.choice-password > label");

	public void clickOnMyAccountDropDown() {
		waitForExpectedElement(myAccountDropDown, 20);
		clickByElement(myAccountDropDown);
	}

	public void selectValueFromDropDown(String expectedValue) {
		List <WebElement> ele=getWebDriver().findElements(By.cssSelector("div.select-items>div"));
	 for(WebElement ele1:ele) {
		 if(ele1.getText().contains(expectedValue)) {
			 ele1.click();
			 break;
		 }	
		 }
	 }	 

	 public void selectAccountSection() {
		waitForExpectedElement(Selectmyaccount, 20);
		clickByElement(Selectmyaccount);
	}

	public void selectMyAccountOption() {
		waitForExpectedElement(myaccount, 20);
		clickByElement(myaccount);
	}

	public void clickEditPasswordEmail() {
		waitForExpectedElement(EditPasswordEmailaddress, 20);
		clickByElement(EditPasswordEmailaddress);
	}

	public void selectEmailCheckbox() {
		waitForExpectedElement(emailcheckbox, 20);
		clickByElement(emailcheckbox);
	}

	public void clickOnSave() {
		waitForExpectedElement(saveButton, 20);
		clickByElement(saveButton);
	}

	public void selectPasswordCheckbox() {
		switch (UrlBuilder.getLocale()){
			case "kz":
				waitAndClickByElementByJSExecutor(passwordCheckboxGloKz,10);
				break;
			default:
				waitForExpectedElement(passwordCheckbox, 20);
				clickByElement(passwordCheckbox);
		}
	}

	public void selectInviteFriendMenuOption() {
		waitForExpectedElement(Tellafriend, 20);
		clickByElementByQueryJSExecutor(Tellafriend);
	}
	
	public void selectNewsLetterEditButton() {
		waitForExpectedElement(personicon, 20).click();
		waitForExpectedElement(editNewsletterLink , 20);
		clickByElementByQueryJSExecutor(editNewsletterLink );
	}

	public void selectNewsLetterSubscriptionCheckbox() {
		waitForExpectedElement( newsletterSubscribCheckbox , 20);
		clickByElementByQueryJSExecutor(newsletterSubscribCheckbox);
	}

	public void eyesCheckAccountDashboardPage() {
		if (Props.EYES_ON && EyesCheckpoints.ACCOUNT_PAGE.isSwitchOn()) {
			scrollToShowEntirePage();
			eyes.check(EyesCheckpoints.ACCOUNT_PAGE.getName(),
					Target.window().fully().layout(ACCOUNT_INFO));
		}
	}

}

