package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.page_objects.gui.ForgotPasswordPage;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class ForgotPasswordPageSteps {

    private ForgotPasswordPage forgotPasswordPage;
    private ScenarioContext scenarioContext;
    public String validPassword;
    private String validPasswordNew;

    public ForgotPasswordPageSteps(TestContext testContext, ForgotPasswordPage forgotPasswordPage) {
        scenarioContext = testContext.getScenarioContext();
        this.forgotPasswordPage = forgotPasswordPage;
        validPassword = UrlBuilder.getMessage("loginValidPassword.key");
        if(UrlBuilder.getLocale().equalsIgnoreCase("vuseuk")){
        validPasswordNew = UrlBuilder.getMessage("loginValidPasswordNew.key");
        }
    }

    @Then("^user is taken to forgot your password page$")
    public void userIsTakenToForgotYourPasswordPage() {
        String expectedPageTitle = UrlBuilder.getMessage("forgotYourPasswordTitle.key");
        String expectedPageHeadering = UrlBuilder.getMessage("passwordRecoveryHeading.key");
        assertTrue(forgotPasswordPage.getPageHeading().contains(expectedPageHeadering));
        assertTrue(forgotPasswordPage.getWebDriver().getTitle().contains(expectedPageTitle));
    }

    @And("^(?:Glo|velo|lyft|vype|user) email input box displayed and enabled$")
    public void emailInputBoxDisplayedAndEnabled() {
        assertThat(forgotPasswordPage.isEmailInputBoxDisplayed()).isTrue();
        assertThat(forgotPasswordPage.isEmailInputBoxEnabled()).isTrue();
    }

    @When("^(?:Glo|velo|lyft|vype|user) resets the password$")
    public void userResetsThePassword() throws Exception {
        forgotPasswordPage.resetPasswordWith(validPassword);
    }

    @Then("^user change the password$")
    public void userChangeThePassword() throws Exception {
        forgotPasswordPage.resetPasswordWith(validPassword);
    }

    @And("^(?:Glo|velo|lyft|vype|user) should receive a email to registered email with reset password link$")
    public void userShouldReceiveAEmailToRegisteredEmailWithResetPasswordLink() {
        String emailIdRegisteredViaUI = (String) scenarioContext.getContext(Context.EMAIL_ID);
        String emailId = Optional.ofNullable(emailIdRegisteredViaUI)
                .orElseGet(() -> {
                    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
                    return context.getEmail();
                });
        forgotPasswordPage.checkEmailInMailinator(emailId, "createPassword");
    }

    @When("^(?:Glo|velo|lyft|vype|user) enters email address registered via api in the email box$")
    public void userEntersEmailAddressRegisteredViaApiInTheEmailBox() {
        Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
            forgotPasswordPage.enterEmailAddress(context.getEmail());
    }

    @And("^a success message is received for language \"([^\"]*)\"$")
    public void aSuccessMessageIsReceivedForLanguage(String language) throws Throwable {
        forgotPasswordPage.validatePasswordResetSuccessMessage(language);
    }

    @Then("^the user should be redirected to the login page for language \"([^\"]*)\"$")
    public void theUserShouldBeRedirectedToTheLoginPageForLanguage(String language) throws Throwable {
        forgotPasswordPage.checkIsOnLoginpage(language);
    }

    @And("^user enters email address registered$")
    public void userEntersEmailAddressRegistered() {
        forgotPasswordPage.enterEmailAddress(scenarioContext.getContext(Context.EMAIL_ID).toString());
    }

    @When("^user resets the new password$")
    public void userSetNewPassword() throws Exception {
        switch (UrlBuilder.getLocale()) {
            case "vuseit":
                forgotPasswordPage.resetNewPassword(UrlBuilder.getMessage("resetValidPasswordNew.key"));
                break;
            default:
                forgotPasswordPage.resetNewPassword(validPasswordNew);
        }
    }

    @When("^user resets the password mobile$")
    public void userResetsThePasswordMobile() throws Exception {
        forgotPasswordPage.resetPasswordMobileWith(validPassword);
    }

    @Then("^Password Change notification message is displayed$")
    public void passwordChangeNotificationMessageIsDisplayed() {
        String expectedPasswordResetSuccessMessage = UrlBuilder.getMessage("updateDetailsSucessMsg.key");
        String actualPasswordResetSuccessMessage = forgotPasswordPage.getPasswordResetSuccessMessage();
        assertThat(actualPasswordResetSuccessMessage)
                .as("ERROR validatePasswordResetSuccessMessage: expected success message was "+expectedPasswordResetSuccessMessage+" but i got "+actualPasswordResetSuccessMessage)
                .contains(expectedPasswordResetSuccessMessage);
    }

    @Then("^error message of '(.*)' is displayed$")
    public void errorMessageOfIsDisplayed(String expectMessage) {
        String expectedText = UrlBuilder.getMessage(expectMessage);
        String actualText = forgotPasswordPage.getErrorMessage();
        assertThat(actualText.contains(expectedText))
                .as("ERROR validate error message: expected message was "+expectedText+" but I got "+actualText).isTrue();
    }

    @And("^user click on save button$")
    public void userClickOnSaveButton() throws Exception {
        forgotPasswordPage.clickSubmitButton();
    }

    @And("^user try to login with old password assert error$")
    public void userTryLoginWithOldPasswordAssertError() {
        forgotPasswordPage.userTryLoginWithOldPasswordAssertError();
    }

    @And("^user try to login with new password assert redirection success$")
    public void userTryLoginWithNewPasswordAssertSuccess() {
        Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
        forgotPasswordPage.enterEmailAddressNew(context.getEmail());
        forgotPasswordPage.userTryLoginWithNewPasswordAssertSuccess();
    }

    @And("^enter email address \"([^\"]*)\"$")
    public void enterEmailAddress(String emailAddress)  {
        forgotPasswordPage.enterEmailAddress(UrlBuilder.getMessage(emailAddress));
    }
}