package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.constants.Locale;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import org.openqa.selenium.By;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;

public class ForgotPasswordPage extends PageObject {
  private ScenarioContext scenarioContext;

  // ELEMENT MAPPING
    public By pageHeading = By.cssSelector(".block-customer-register-heading"); // Forgot Your Password

  public static final By PASSWORD = By.cssSelector("#password");
  public static final By PASSWORD_CONFIRMATION = By.cssSelector("#password-confirmation");
  private static final By EMAIL_INPUT_BOX = By.cssSelector("input#email_address.input-text");
  private static final By SUBMIT_EMAIL_ADDRESS_BUTTON = By.cssSelector("#form-validate > div > div > button > span");
  private static final By PASSWORD_RESET_SUCCESS_MESSAGE = By.cssSelector("div.message-success");
  public static final By SUBMIT_BUTTON = By.cssSelector("#form-validate > div > div > button");
  public static final By SAVE_BUTTON = By.cssSelector("button.action.save.primary");
  private static final By CURRENT_PASSWORD = By.cssSelector("#current-password");
  private static final By PASSWORD_SET = By.xpath("//*[@id=\"password\"]");
  private static final By PASSWORD_SET_CONFIRMATION = By.xpath("//*[@id=\"password-confirmation\"]");
  private static final By CONFIRM_UPDATE = By.cssSelector("#form-validate > div.actions-toolbar > div > button");
  public static final By PASSWORD_INPUT_FIELD = By.cssSelector("input#password");
  public static final By errorMessage = By.cssSelector("div.message-error.error.message");
  private static final By EMAIL_FIELD = By.cssSelector("#email");
  private static final By PASSWORD_FIELD = By.cssSelector("#pass");
  private static final By CREATE_ACCOUNT = By.cssSelector("#send2");
  private static final By LOGIN_ERROR = By.cssSelector("div.page.messages > div:nth-child(2) > div > div");
  private static final By WELCOME_MESSAGE = By.cssSelector(".welcome-message");



  public String getPageHeading() {
    return waitForExpectedElement(pageHeading).getText();
  }

  public boolean isEmailInputBoxDisplayed() {
    return waitForExpectedElement(EMAIL_INPUT_BOX).isDisplayed();
  }

  public boolean isEmailInputBoxEnabled() {
    return waitForExpectedElement(EMAIL_INPUT_BOX).isEnabled();
  }

  public void enterEmailAddress(String input) {
    waitClearAndEnterText(EMAIL_INPUT_BOX,input);
    //waitForExpectedElement(EMAIL_INPUT_BOX).sendKeys(input);
  }

  public void checkEmailInMailinator(String emailAddressData, String expectedStringInUrl) {
      new RegistrationPage().verifyEmailAndReturn(emailAddressData, expectedStringInUrl);
  }

  public void resetPasswordWith(String newPassword) throws Exception {
    String currentPassword= "Pa55w@rd12345";
    switch (Locale.valueOf(UrlBuilder.getLocale().toUpperCase())) {
      case VUSEFR:
      case VUSEDE:
      case VUSEIT:
      case VUSEUK:
      case VUSEMX:
        enterText(PASSWORD_INPUT_FIELD, newPassword);
        break;
      case IT:
      case LYFTSE:
      case PL:
        enterText(PASSWORD, newPassword);
        break;
      default:
        if (!UrlBuilder.getLocale().equalsIgnoreCase("kz"))
          enterText(CURRENT_PASSWORD, currentPassword);
        enterText(PASSWORD, newPassword);
    }
    enterText(PASSWORD_CONFIRMATION, newPassword);
    waitForExpectedElement(SUBMIT_BUTTON, 10);
    clickByElementByQueryJSExecutor(SUBMIT_BUTTON);
  }

  public void resetNewPassword(String newPassword) throws Exception {
    enterText(PASSWORD_SET, newPassword);
    enterText(PASSWORD_SET_CONFIRMATION, newPassword);
    waitForExpectedElement(CONFIRM_UPDATE, 10);
    clickByElementByQueryJSExecutor(CONFIRM_UPDATE);
    Thread.sleep(2000);
  }

  public void submitEmailAddress() {
    try {
      waitForExpectedElement(SUBMIT_EMAIL_ADDRESS_BUTTON).click();
    } catch (Exception e) {
      clickByElementByQueryJSExecutor(SUBMIT_EMAIL_ADDRESS_BUTTON);
    }
  }

  public void validatePasswordResetSuccessMessage(String language) {
    String expectedPasswordResetSuccessMessage = UrlBuilder.getMessage("passwordUpdateSuccess-" + language);
    String actualPasswordResetSuccessMessage = waitForExpectedElement(PASSWORD_RESET_SUCCESS_MESSAGE).getText();
    assertThat(expectedPasswordResetSuccessMessage)
            .as("ERROR validatePasswordResetSuccessMessage: expected success message was "+expectedPasswordResetSuccessMessage+" but i got "+actualPasswordResetSuccessMessage)
            .isEqualTo(actualPasswordResetSuccessMessage);
  }

  public void checkIsOnLoginpage(String language) {
    String expectedLoginpageTitle = UrlBuilder.getMessage("loginPageTitle-" + language);
    String actualLoginPageTitle = getCurrentPageTitle();
    assertThat(expectedLoginpageTitle).as("ERROR checkIsOnLoginpage: expected page title was "+expectedLoginpageTitle+" but I got "+actualLoginPageTitle)
            .isEqualTo(actualLoginPageTitle);
  }

  public void resetPasswordMobileWith(String newPassword){
      enterText(PASSWORD_INPUT_FIELD, newPassword);
      enterText(PASSWORD_CONFIRMATION, newPassword);
      waitForExpectedElement(SUBMIT_BUTTON, 10);
      clickByElementByQueryJSExecutor(SUBMIT_BUTTON);
  }

  public String getPasswordResetSuccessMessage() {
     return waitForExpectedElement(PASSWORD_RESET_SUCCESS_MESSAGE, 10).getText();
  }

  public String getErrorMessage() {
    return waitForExpectedElement(errorMessage, 10).getText();
  }

  public void clickSubmitButton() {
    waitForExpectedElement(SAVE_BUTTON, 10);
    clickByElementByQueryJSExecutor(SAVE_BUTTON);
  }

  public void userTryLoginWithOldPasswordAssertError() {
    String oldPassword= "Pa55w@rd12345";
    String actualErrorMessage = null;
    String expectedErrorMessage;
    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
    waitClearAndEnterText(EMAIL_FIELD,context.getEmail());
    waitClearAndEnterText(PASSWORD_FIELD,oldPassword);
    clickUsingJS(CREATE_ACCOUNT);
    expectedErrorMessage = UrlBuilder.getMessage("incorrectSignInMsg.key");
    actualErrorMessage = waitForExpectedElement(LOGIN_ERROR, 5).getText();
    assertThat(expectedErrorMessage.equals(actualErrorMessage))
            .as("ERROR : expected error message was " + expectedErrorMessage + " but I got " + actualErrorMessage + " for field ").isTrue();
  }

  public void userTryLoginWithNewPasswordAssertSuccess() {
    String newPassword;
    switch (UrlBuilder.getLocale()) {
      case "vuseit":
        newPassword = UrlBuilder.getMessage("resetValidPasswordNew.key");
        break;
      case "lyftse":
        newPassword = UrlBuilder.getMessage("loginValidNewPassword.key");
        break;
      default:
        newPassword = UrlBuilder.getMessage("loginValidPasswordNew.key");
    }
    waitClearAndEnterText(PASSWORD_FIELD,newPassword);
    clickUsingJS(CREATE_ACCOUNT);
    if (!UrlBuilder.getLocale().equalsIgnoreCase("lyftse"))
    Assert.assertTrue(isElementPresentByby(WELCOME_MESSAGE));
  }

  public void enterEmailAddressNew(String input) {
    waitClearAndEnterText(EMAIL_FIELD,input);
  }
}
