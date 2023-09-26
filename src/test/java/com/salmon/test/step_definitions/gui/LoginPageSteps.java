package com.salmon.test.step_definitions.gui;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.framework.helpers.utils.SessionInfo;
import com.salmon.test.models.api.customer.Customer;
import com.salmon.test.page_objects.gui.*;
import com.salmon.test.page_objects.gui.constants.Context;
import com.salmon.test.page_objects.gui.cucumberContext.ScenarioContext;
import com.salmon.test.page_objects.gui.cucumberContext.TestContext;
import com.salmon.test.page_objects.gui.models.RegistrationPageModel;
import com.salmon.test.page_objects.gui.newsLetter.NewsLetterPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.List;
import java.util.StringJoiner;

import static com.salmon.test.framework.helpers.WebDriverHelper.getWebDriver;
import static com.salmon.test.page_objects.gui.constants.Context.CUSTOMER;
import static com.salmon.test.page_objects.gui.constants.Context.*;
import static com.salmon.test.page_objects.gui.constants.Context.EMAIL_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginPageSteps extends PageObject {

  private LogininPage loginPage;
  private HomePage homePage;
  private RegistrationPage registrationPage;
  private ForgotPasswordPage forgotPasswordPage;
  BATHelper batHelper;
  private ScenarioContext scenarioContext;
  private PDP pdp;
  private TermsAndConditions termsAndConditions;
  private SubscriptionsPage subscriptionsPage;
  private PrivacyPolicyPage privacyPolicyPage;

  public LoginPageSteps(TestContext testContext, LogininPage loginPage, HomePage homePage, BATHelper batHelper, RegistrationPage registrationPage,
  TermsAndConditions termsAndConditions,SubscriptionsPage subscriptionsPage,PrivacyPolicyPage privacyPolicyPage,ForgotPasswordPage forgotPasswordPage, PDP pdp) {
    scenarioContext = testContext.getScenarioContext();
    this.loginPage = loginPage;
    this.homePage = homePage;
    this.batHelper=batHelper;
    this.registrationPage=registrationPage;
    this.termsAndConditions=termsAndConditions;
    this.subscriptionsPage=subscriptionsPage;
    this.privacyPolicyPage=privacyPolicyPage;
    this.forgotPasswordPage=forgotPasswordPage;
    this.pdp = pdp;
  }

  @Then("^ensure text of '(.*)' present on login page$")
  public void ensureTextOfCustomerLoginPresentOnLoginPage(String expectedHeading) {
    String expectedHeadingText = "Customer Login";
    loginPage.assertTrueWithCustomError(expectedHeadingText,loginPage.getPageHeading());
  }

  @And("^email input box displayed and enabled$")
  public void emailInputBoxDisplayedAndEnabled() {
    assertTrue(loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX).isDisplayed());
    assertTrue(loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX).isEnabled());
  }

  @And("^password input box displayed and enabled$")
  public void passwordInputBoxDisplayedAndEnabled() {
    assertTrue(loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX).isDisplayed());
    assertTrue(loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX).isEnabled());
  }

  @And("^remember me box is displayed and enabled and checked by default$")
  public void rememberMeBoxIsDisplayedAndEnabledAndCheckedByDefault() {
    if (!UrlBuilder.getLocale().equals("uk") && !UrlBuilder.getLocale().equals("vusede")
            && !UrlBuilder.getLocale().equals("fr") && !UrlBuilder.getLocale().equals("vuseuk")
            && !UrlBuilder.getLocale().equals("mx") && !UrlBuilder.getLocale().equals("vusefr")
            && !UrlBuilder.getLocale().equals("vuseza")&&!UrlBuilder.getLocale().equals("vusemx")&&!UrlBuilder.getLocale().equals("vuseit")) {
      assertTrue(loginPage.waitForExpectedElement(loginPage.REMEMBERME_CHECKOUTBOX).isSelected());
      assertTrue(loginPage.waitForExpectedElement(loginPage.REMEMBERME_CHECKOUTBOX).isEnabled());
      assertTrue(loginPage.waitForExpectedElement(loginPage.REMEMBERME_CHECKOUTBOX).isDisplayed());
    }
  }

  @And("^forgot your password button displayed$")
  public void forgotYourPasswordButtonDisplayed() {
    assertTrue(loginPage.waitForExpectedElement(loginPage.FORGOTPASSWORD_BUTTON).isDisplayed());
  }

  @And("^create an account button is displayed$")
  public void createAnAccountButtonIsDisplayed() {
    assertTrue(loginPage.waitForExpectedElement(loginPage.CREATE_ACCOUNT_BUTTON).isDisplayed());
  }

  @And("^assert text of '(.*)' is displayed$")
  public void assertTextOfRegisteredCustomersIsDisplayed(String expectedText) {
    try{
      if (UrlBuilder.getLocale().equals("glode") && UrlBuilder.isDesktop()) {
        loginPage.waitForPage(30);
      }
      if (UrlBuilder.getLocale().equals("vuseit") && UrlBuilder.isDesktop()) {
        loginPage.waitForPage(20);
      }
      if (UrlBuilder.getLocale().equals("vusemx") && UrlBuilder.isDesktop()) {
        loginPage.waitForPage(20);
      }
      if(UrlBuilder.getLocale().equals("vuseza")){
        while(!getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase())){
          loginPage.waitForPage(50);
        }
      }
      if(UrlBuilder.getLocale().equals("it") || UrlBuilder.getLocale().equals("vusefr"))
      {
        loginPage.waitForPage(20);
      }
      if(UrlBuilder.getLocale().equals("pl")|| UrlBuilder.getLocale().equals("vuseco"))
      {
        loginPage.waitForPage(20);
      }
      waitForAjaxElementNotToBePresent(getWebDriver(),3);
      if (!getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()))
      {
        loginPage.waitForPage(20);
      }
      assertTrue(getWebDriver().getPageSource().toLowerCase().contains(UrlBuilder.getMessage(expectedText).toLowerCase()));
    }
    catch(Exception e) {
      loginPage.assertTextOfRegisteredCustomersIsDisplayed(expectedText);
    }
  }

  @Then("^assert subtitles of terms and conditions page$")
  public void assertSubtitlesOfTermsConditionPage(){
    List <String> actualTitles = termsAndConditions.getExpectedSubTitles();
    actualTitles.forEach(actualTitle -> {
      assertThat(loginPage.getWebDriver().getPageSource().contains(actualTitle));
    });
  }

  @Then("^assert subtitles of subscription conditions page$")
  public void assertSubtitlesOfSubscriptionConditionPage(){
    List <String> actualTitles = subscriptionsPage.getExpectedSubTitlesSubscriptionCondition();
    actualTitles.forEach(actualTitle -> {
      assertThat(loginPage.getWebDriver().getPageSource().contains(actualTitle));
    });
  }

  @Then("^assert Delivery and Return policies in the page$")
  public void assertDeliveryAndReturnPage(){
    List <String> actualTitles = privacyPolicyPage.getExpectedpolicies();
    actualTitles.forEach(actualTitle -> {
      assertThat(loginPage.getWebDriver().getPageSource().contains(actualTitle));
    });
  }

  @And("^assert text of First Name is displayed$")
  public void assertTextOfFirstNameIsDisplayed()  {
    assertThat(loginPage.getFirstNameFiledDisplayed().equals(UrlBuilder.getMessage("firstNameText.key")));
  }

  @And("^assert confirmation message is displayed$")
  public void assertConfirmationMessageIsDisplayed(){
    loginPage.successfulAccountCreationMessageDisplayed();
  }

  @And("^assert text of '(.*)' is not displayed$")
  public void assertTextOfRegisteredCustomersIsNotDisplayed(String expectedText) throws Throwable {
    Thread.sleep(5000);
    assertFalse(loginPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(expectedText)));
  }

  @And("^assert text of '(.*)' is not displayed for language \"([^\"]*)\"$")
  public void assertTextIsNotDisplayedForLanguage(String messageKey, String language) throws Throwable {
    loginPage.waitForPage();
    assertFalse(loginPage.getWebDriver().getPageSource().contains(UrlBuilder.getMessage(messageKey + "-" + language)));
  }

  @And("^user clicks the registration button$")
  public void userClicksTheRegistrationButton() {
    loginPage.clickRegistrationButton();
  }

  @When("^user enters sign in details, with username of '(.*)' and password '(.*)'$")
  public void userEntersValidSigninDetails(String email, String password) throws Throwable {
    loginPage.userEntersValidSigninDetails(email,password);
  }

  @When("^user sign in with '(.*)' and '(.*)' and proceed to checkout page$")
  public void userSingInAndProceedToCheckoutPage(String email, String password) throws Throwable {
    batHelper.loginAndProceedToCheckoutPage(email,password);
  }

  @And("^user enters username '(.*)' and password '(.*)' in the login popup$")
  public void userEntersValidSigninDetailsPopup(String email, String password) {
    loginPage.fillInPopupLoginForm(
        UrlBuilder.getMessage(email),
        UrlBuilder.getMessage(password)
    );
  }

  @And("^user submits the login popup form$")
  public void userEntersValidSigninDetailsPopup() {
    loginPage.submitPopupLoginForm();
  }

  @Then("^user is successfully logged in$")
  public void userIsSuccessfullyLogged()  {
    if(loginPage.doesURLContain("mx/es/")) {
      loginPage.waitForExpectedElement(loginPage.pageSubHeading,30);
      assertTrue(loginPage.getWebDriver().getTitle().contains(UrlBuilder.getMessage("DashboardTitle.key")));
    }
    else if(loginPage.doesURLContain("kz/ru/")){
      assertTrue(loginPage.doesURLContain("kz/ru/"));
    }
    else {
      assertTrue(loginPage.doesURLContain("customer/account"));
      
  
    }
  }

  @Then("^password is required error message pops up$")
  public void passwordIsRequiredErrorMessagePopsUp() {
    String expectedMissingPasswordErrorMsg = "This is a required field.";
    loginPage.assertTrueWithCustomError(expectedMissingPasswordErrorMsg,loginPage.waitForExpectedElement(loginPage.PASSWORD_REQUIRED_ERROR_MESSAGE).getText());
  }

  @Then("^invalid email message of '(.*)' is displayed to user$")
  public void invalidEmailMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(String expectedErrorMessage ) {
    loginPage.assertTrueWithCustomError(expectedErrorMessage,loginPage.waitForExpectedElement(loginPage.errorMessage,10).getText());
    LOG.info("Testing breakpoint");
  }

  @Then("^error message of '(.*)' is displayed to user$")
  public void errorMessageOfPleaseEnterAValidEmailAddressExJohndoeDomainComIsDisplayedToUser(String expectedErrorMessage ) {
    loginPage.assertTrueWithCustomError(expectedErrorMessage,loginPage.waitForExpectedElement(loginPage.INVALID_EMAIL_ERROR_MESSAGE,10).getText());
    LOG.info("Testing breakpoint");
  }

  @When("^user clicks the forgotten password link$")
  public void userClicksTheForgottenPasswordLink() {
    try{loginPage.waitForExpectedElement(loginPage.FORGOTPASSWORD_BUTTON,10).click();
    }catch(Exception e) {
      loginPage.clickByElementByQueryJSExecutor(loginPage.FORGOTPASSWORD_BUTTON);
    }
  }

  @And("^assert remember me tick box is present$")
  public void assertRememberMeTickBoxIsPresent() {
    switch(UrlBuilder.getLocale()){
      case "uk":
      case "mx":
      case "vuseco":
      case "vuseza":
      case "vusemx":
        break;
      default:
        assertTrue(loginPage.waitForExpectedElement(By.name("persistent_remember_me")).isDisplayed());
    }
  }

  @When("^user signs in to the site$")
  public void userSignsInToTheSite() {
    String username = UrlBuilder.getMessage("userName");
    String password = UrlBuilder.getMessage("passWord");
    loginPage.login(username,password);
  }

  @When("^user signs in to the site custom details '(.*)' '(.*)'$")
  public void userSignsInToTheSiteCustom(String userName, String passWord) {
    String username = UrlBuilder.getMessage(userName);
    String password = UrlBuilder.getMessage(passWord);
    scenarioContext.setContext(Context.EMAIL_ID, username);
    loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX, 20).sendKeys(username);
    loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX,10).sendKeys(password);
    loginPage.clickByElementByQueryJSExecutor(loginPage.SIGNIN_BUTTON);
    waitForExpectedElement(loginPage.elePageTitle,5);
  }
  @When("^user signs in to the site credentials details '(.*)' '(.*)'$")
  public void userSignIntoSiteApp(String email, String password) {
    if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
      loginPage.eyesCheckLoginPage();
    }
    loginPage.userSignIntoSiteApp(email, password);
    if (UrlBuilder.getLocale().equalsIgnoreCase("glojp")) {
      homePage.eyesCheckAgeGate();
    }
  }

  @When("^user signs in to the Lyft Lab site custom details '(.*)' '(.*)'$")
  public void userSignsInToTheLyftLabSiteCustom(String userName, String passWord) {
    String username = UrlBuilder.getMessage(userName);
    String password = UrlBuilder.getMessage(passWord);
    loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX, 20).sendKeys(username);
    loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX,10).sendKeys(password);
    loginPage.clickByElementByQueryJSExecutor(loginPage.SIGNIN_BUTTON);
    waitForExpectedElement(loginPage.elePageTitle,30);
  }

  @When("^user signs in with customer properties '(.*)' '(.*)'$")
  public void userSignsInWithCustomerProperties(String userName, String passWord) {
    homePage.navigateToSignInPage();
    loginPage.loginWithConfigProperties(userName, passWord);
  }

  public void userSignsIn(String userName, String passWord) {
    homePage.navigateToSignInPage();
    loginPage.login(userName, passWord);
  }

  @And("^assert text of newsletter subscription messages '(.*)' is displayed$")
  public void assertTextOfNewsletterSubscriptionMessageIsDisplayed(String expectedText) {
    loginPage.assertTextOfNewsletterSubscriptionMessageIsDisplayed(expectedText);
  }

  @And("^verify the phone number field validation$")
  public void verifyPhoneNumberFieldValidations() {
    homePage.phoneNumberFieldValidation();
  }

  @And("^verify opt in or out of mobile consent is available under my preferences$")
  public void verifyOptInOutOfMobileConsentIsDisplayed() {
    homePage.assertOptInOutOfMobileConsentIsDisplayed();
  }

  @When("^(?:Glo|velo|lyft|vype|user|I) (?:clicks|click) on forgot password link$")
  public void userClicksOnForgotPasswordLink() {
    loginPage.clickOnForgotPasswordLink();
  }

  @And("^(?:Glo|velo|lyft|vype|user) click on submit button$")
  public void userClickOnSendButton() throws Exception {
    loginPage.clickSubmitButton();
  }

  @Then("^(?:Glo|velo|lyft|vype|user) should see a text of (.*)$")
  public void userShouldSeeATextOf(String expectedText) {
    homePage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
    loginPage.assertTextOfRegisteredCustomersIsDisplayed(expectedText);
  }

  @Then("^customer firstname should be displayed under my account icon$")
  public void customerFirstnameShouldBeDisplayedUnderMyAccountIcon() {
    String actualNameDisplayed = loginPage.getCustomerNameElementNextToPersonIcon().getText();
    String expectedName = System.getProperty("UserFirstName.key");
    assertThat(actualNameDisplayed).isEqualTo(expectedName.toUpperCase());
  }

  @Then("^assert customer name exists next to person icon$")
  public void assertCustomerNameExistsNextToPersonIcon() {
    assertThat(loginPage.getCustomerNameElementNextToPersonIcon().isDisplayed()).isTrue();
  }

  @When("^user attempts to login with registered random email$")
  public void userAttemptsToLoginWithRegisteredRandomEmail() {
    batHelper.loginWithRegisteredRandomEmailAndPassword();
  }

  @And("^Asserted text for globalSubs is displayed$")
  public void assertedTextForGlobalSubsIsDisplayed(List<String> expectedText) {
    for (String s : expectedText) {
      loginPage.assertTextOfRegisteredCustomersIsDisplayed(s);
    }
  }

  @And("^verify New User's Email and navigate back to Account Page$")
  public void verifyNewUserEmailAndNavigateBackToAccountPage() {
    registrationPage.verifyEmailAndReturnToMyAccountPage();}

  @And("^user login with credentials registered via api$")
  public void userLoginWithCredentialsRegisteredViaApi() {
    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
    String emailId = context.getEmail();
    String password = scenarioContext.getContext(Context.PASSWORD_CREATED_VIA_API_CREATE_ACCOUNT).toString();
    loginPage.login(emailId, password);
  }

  @And("^user login on checkout pop up with credentials registered via api$")
  public void userLoginOnCheckoutPopUpWithCredentialsRegisteredViaApi() {
    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
    String emailId = context.getEmail();
    String password = scenarioContext.getContext(Context.PASSWORD_CREATED_VIA_API_CREATE_ACCOUNT).toString();
    homePage.checkOutPopUpSignIn(emailId, password);
  }

  @And("^assert text '(.*)' on basket icon$")
  public void assertTextOnBasketIcon(String expectedText) {
      switch(UrlBuilder.getSite()) {
        case "vuseza":
        case "ie":
            assertTrue(getTextFor(loginPage.BASKET_DETAILS_HEADER).contains(UrlBuilder.getMessage(expectedText).trim()));
          break;
        default:
      }
  }

  @And("^assert error message '(.*)' with contact us link is displayed$")
  public void assertErrorMessageWithContactUsLinkIsDisplayed(String expectedText) {
      assertTrue(waitForExpectedElement(loginPage.errorMessage,10).getText().contains(UrlBuilder.getMessage(expectedText)));
      if(getWebDriver().findElements(By.linkText(UrlBuilder.getMessage("contactUsLinkText.key"))).size()>0) {
        String strContactUsText = waitForExpectedElement(By.linkText(UrlBuilder.getMessage("contactUsLinkText.key"))).getText();
        StringJoiner strContactUsError = new StringJoiner(" ");
        strContactUsError.add(UrlBuilder.getMessage("contactUsForSignInErrorP1.key")).add(strContactUsText).add(UrlBuilder.getMessage("contactUsForSignInErrorP2.key"));
        assertTrue(waitForExpectedElement(loginPage.errorMessage, 10).getText().contains(strContactUsError.toString()));
      }
  }

  @Then("^assert all marketing consent checkbox$")
  public void assertAllMarketingConsentCheckox() throws Throwable {
    assertTrue(waitForExpectedElement(NewsLetterPage.EMAIL_CONSENT_CHK_BOX).isDisplayed());
    assertTrue(waitForExpectedElement(NewsLetterPage.MOBILE_SOCIAL_CONSENT_CHK_BOX).isDisplayed());
    assertTrue(waitForExpectedElement(RegistrationPage.THIRD_PARTY_CONSENT_CHK_BOX).isDisplayed());
    assertTrue(waitForExpectedElement(NewsLetterPage.CONSENT_ALL).isDisplayed());
    registrationPage.assertAllMarketingConsentCheckox();
  }

  @Then("^assert all marketing consent checkox is deselected$")
  public void assertAllMarketingConsentCheckoxIsDeselected() throws Throwable {
    registrationPage.assertAllMarketingConsentCheckoxIsDeselected();
  }

  @And("^user clicks create an account button$")
  public void userClicksCreateAnAccountButton() {
    loginPage.waitForItemToBeClickableAndClick(loginPage.CREATE_ACCOUNT_BUTTON);
  }

  @Then("^verify if the Account is created$")
  public void verifyNewAccount() {
    loginPage.verifyNewAccount();
  }

  @Then("^click on delete account$")
  public void DeleteNewAccount() {
    loginPage.DeleteNewAccount();
  }

  @And("^Sign In with loyalty user '(.*)' '(.*)'$")
  public void signInWithLoyaltyUser(String userName, String passWord) {
    loginPage.loginWithConfigProperties(userName, passWord);
  }

  @When("^Login again with newly created user in same session$")
  public void ReLoginWithNewlyCreatedUserInSameSession() {
    loginPage.login(registrationPage.emailAddressData, UrlBuilder.getMessage("loginValidPassword.key"));
  }

  @And("^navigate to user creation form$")
  public void navigateToUserCreationForm() {
    batHelper.navigateToUserCreationForm();
  }

  @And("^click on 'Terms and Conditions' link in loyalty consent section$")
  public void clickOnTermsAndConditionLinkUnderLoyaltyConsentSection() {
    registrationPage.clickOnTermsAndConditionLinkUnderLoyaltyConsentSection();
  }

  @Then("^assert default county or land is '(.*)'$")
  public void assertDefaultCountyOrLandIsLandKey(String land) {
    loginPage.clickRegistrationButton();
    assertTrue(registrationPage.getLandTextOnRegistrationPage().equals(UrlBuilder.getMessage(land)));
  }

  @And("^customer has a subscription$")
  public void userCustomerHasSubs() {
    loginPage.assertUserHasSubscription();
  }

  @Then("^display total price of subscription in the subscription order header$")
  public void userTotalSubsPrice() {
    loginPage.assertSubsTotalPrice();
  }

  @Then("^user see text for reset password$")
  public void verifyResetPasswordText() {
    loginPage.assertResetPasswordText();
  }

  @Then("^user should see password update text of (.*)$")
  public void verifyPasswordUpdateText(String expectedText) {
    loginPage.waitForAjaxElementNotToBePresent(getWebDriver(),10);
    loginPage.assertTextOfPasswordReset(expectedText);
  }

  @Then("^assert confirm link is displayed$")
  public void assertConfirmLinkIsDisplayed() {
    assertTrue(loginPage.isConfirmLinkPresent());
  }

  @And("^user logins with credentials registered via api$")
  public void userLoginsWithCredentialsRegisteredViaApi() {
    homePage.clickPersonIcon();
    homePage.clickOnLinksFromPersonMenu("signInLink.key");
    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
    String emailId = context.getEmail();
    String password = scenarioContext.getContext(Context.PASSWORD_CREATED_VIA_API_CREATE_ACCOUNT).toString();
    loginPage.login(emailId, password);
  }

  @And("^the user navigates to the registration page$")
  public void theUserNavigatesToTheRegistrationPageForLanguage() throws Throwable {
    loginPage.clickCreateAccountButton();
  }

  @Given("^the form has the following fields marked as mandatory$")
  public void theFormHasTheFollowingFieldsMarkedAsMandatory(DataTable fieldTable) {
      List<List<String>> fields = fieldTable.asLists(String.class);
      String fieldName;
      for (List<String> field: fields) {
        fieldName = field.get(0);
          assertThat(registrationPage.fieldMarkedAsMandatory(fieldName))
                  .as("ERROR: field "+ fieldName + " should be marked as mandatory but isn't").isTrue();
        }
      }

  @And("^the page has a create your account CTA$")
  public void thePageHasACreateYourAccountCTA() {
    registrationPage.validateCreateAccountButton();
  }

  @And("^the following mandatory consents with a link to notice copy$")
  public void theFollowingMandatoryConsentsWithALinkToNoticeCopy(DataTable dataTable) {
    List<List<String>> fields = dataTable.asLists(String.class);
    for (List<String> field: fields) {
      registrationPage.validateAgreement(field.get(0));
    }
  }

  @And("^I have filled in all mandatory fields$")
  public void iHaveFilledInAllMandatoryFields() {
    RegistrationPageModel registrationPageModel = RegistrationPageModel.builder().build()
            .withDefaultValues();
      registrationPage.populatePersonalInformation(registrationPageModel);
      registrationPage.createEmailAndPassword();

  }

  @And("^(?:I|user) (?:click|clicks) on create your account CTA$")
  public void iClickOnCreateYourAccountCTA() {
    registrationPage.clickCreateAccountFromElementsList();
  }

  @Then("^the double opt-in and AV process is initiated for my account\\.$")
  public void theDoubleOptInAndAVProcessIsInitiatedForMyAccount() {
    registrationPage.assertThatOnYotiAgeEstimationPage();
  }

  @And("^I have filled in all mandatory fields other than$")
  public void iHaveFilledInAllMandatoryFieldsOtherThan(DataTable fieldTable) {
    List<List<String>> fields = fieldTable.asLists(String.class);
    String fieldName;
    for (List<String> field: fields) {
      fieldName = field.get(0);
      LOG.info("Checking error message for "+fieldName);
      registrationPage.enterRegistrationDetailsAndCreateAccountWithout(fieldName);
      registrationPage.checkRegistrationError(fieldName);
    }
  }

  @Then("^I am able enter my email and password$")
  public void iAmAbleEnterMyEmailAndPassword() {
    loginPage.emailAndPasswordFieldsDisplayed();
  }

  @And("^the page heading is correct$")
  public void thePageHeadingIsCorrect() {
    switch (UrlBuilder.getLocale()) {
      case "velopl":
      case "velobe":
      loginPage.validateLoginPageHeading(scenarioContext.getContext(LANGUAGE).toString());
        break;
      default:
        loginPage.validateLoginPageHeading();
    }
  }

  @And("^the Login page heading is correct$")
  public void theLoginPageHeadingIsCorrect() {
    switch (UrlBuilder.getLocale()) {
      case "velopl":
        loginPage.verifyLoginPageHeading(scenarioContext.getContext(LANGUAGE).toString());
        break;

    }
  }

  @And("^all the field labels are correct for language \"([^\"]*)\"$")
  public void allTheFieldLabelsAreCorrectForLanguage(String language) throws Throwable {
    loginPage.validateLoginFieldLabels(language);
  }

  @And("^there is a sign in CTA for me to login$")
  public void thereIsASignInCTAForMeToLogin() {
    loginPage.LoginButtonDisplayed();
  }

  @And("^there is a forgot password link$")
  public void thereIsAForgotPasswordLink() {
    loginPage.forgotPasswordLinkDisplayed();
  }

  @And("^there is a create account button$")
  public void thereIsACreateAccountButton() {
    loginPage.createAccountButtonDisplayed();
  }

  @And("^verify all the field labels are correct$")
  public void verifyAllTheFieldLabelsAreCorrect() {
    loginPage.validateLoginFieldLabels();
  }

    @When("^(?:I|user) (?:am|is) on the account log-in page$")
    public void iAmOnTheAccountLogInPage() {
      loginPage.validateLoginFieldLabels();
    }

  @And("^I have not filled in my personal details$")
  public void iHaveNotFilledInMyPersonalDetails() {
  }

  @And("^I click on Sign In$")
  public void iClickOnSignIn() {
    loginPage.submitPopupLoginForm();
  }

//  @Then("^I should get an error message that my email and password are needed to login$")
//  public void iShouldGetAnErrorMessageThatMyEmailAndPasswordAreNeededToLogin() {
//    loginPage.emailIsRequiredErrorMessagePopsUp();
//    loginPage.passwordIsRequiredErrorMessagePopsUp();
//  }

  @And("^user clicks on create your account CTA Link$")
  public void userClicksOnCreateYourAccountCTALink() throws Exception {
    loginPage.clickCreateAccountButton();
  }

  @Given("^I omit the following and click on sign in I get the correct error message$")
  public void iOmitTheFollowing(DataTable fieldTable) {
    List<List<String>> fields = fieldTable.asLists(String.class);
    loginPage.loginWithMissingFields(fields);
  }

  @And("^I have ticked the newsletter sign up$")
  public void iHaveTickedTheNewsletterSignUp() {
    registrationPage.signUpForNewsLetterTickBoxSelection();
  }

  @And("^confirm deletion$")
  public void confirmDeletion() {
    loginPage.DeleteNewAccount();
  }

  @And("^user login with credentials$")
  public void userIsUnableToLoginWithCredentials() throws Throwable {
      loginPage.login(scenarioContext.getContext(EMAIL_ID).toString(), scenarioContext.getContext(PASSWORD).toString());
  }

  @Then("^user is taken to forgot your password page for language \"([^\"]*)\"$")
  public void userIsTakenToForgotYourPasswordPageForLanguage(String language) throws Throwable {
    String expectedPageTitle = UrlBuilder.getMessage("forgotYourPasswordTitle-" + language);
    String expectedPageHeading = UrlBuilder.getMessage("forgotYourPasswordHeading-" + language);
    String actualPageHeading = forgotPasswordPage.getPageHeading();
    String actualPageTitle = forgotPasswordPage.getWebDriver().getTitle();
    assertThat(actualPageHeading.contains(expectedPageHeading))
            .as("ERROR expected page heading was " + expectedPageHeading + " but I got " + actualPageHeading);
    assertThat(actualPageTitle.contains(expectedPageTitle))
            .as("ERROR expected page heading was " + expectedPageTitle + " but I got " + actualPageTitle);
  }

  @Then("^user enters his email address$")
  public void userEntersHisEmailAddress() throws Exception {
    forgotPasswordPage.enterEmailAddress(scenarioContext.getContext(EMAIL_ID).toString());
    forgotPasswordPage.submitEmailAddress();
  }

  @Then("^user should see the correct password reset message$")
  public void userShouldSeeThe() {
    loginPage.validatePasswordResetConfirmation();
  }

  @And("^the user navigates to the login page for language \"([^\"]*)\"$")
  public void theUserNavigatesToTheLoginPageForLanguage(String lang) throws Throwable {
    scenarioContext.setContext(LANGUAGE, lang);
    homePage.clickPersonIcon();
  }

  @And("^the form has the following non mandatory marketing consents$")
  public void theFormHasTheFollowingNonMandatoryMarketingConsents(DataTable dataTable) {
    List<List<String>> fields = dataTable.asLists(String.class);
    for (List<String> field : fields) {
      registrationPage.validateAgreement(field.get(0));
    }
  }

  @Then("^I should get an error message that my email and password are needed to login$")
  public void iShouldGetAnErrorMessageThatMyEmailAndPasswordAreNeededToLogin() {
    loginPage.validateEmailRequiredMessage();
    loginPage.validatePasswordRequiredMessage();
  }

  @And("^the user navigates back to login page$")
  public void theUserNavigatesBackToLoginPage() {
    waitForAjaxElementNotToBePresent(webDriver,5);
    homePage.clickPersonIcon();
    homePage.chooseMyAccountOnAccountDropdown();
  }

  @And("^user clicks on Continue with Facebook button$")
  public void userClicksOnContinueWithFacebookButton() {
    homePage.waitForLoaderIconDisappear();
    waitForExpectedElement(loginPage.SIGN_IN_WITH_FACEBOOK_BUTTON).click();
  }

  @And("^assert new window opens with '(.*)' login page$")
  public void assertNewWindowOpensWithSocialLoginPage(String strURL) {
    loginPage.assertNewWindowOpensWithSocialLoginPage(strURL);
  }

  @And("user enters facebook email id '(.*)' and password '(.*)' and click login$")
  public void userEntersFacebookEmailAndPasswordAndClickLogin(String strUserName,String strPassword) {
    loginPage.enterFacebookEmailAndPasswordAndClickLogin(strUserName,strPassword);
  }

  @And("^user signs in to the site via api user credential$")
  public void userSignsInToTheSiteViaAPIUserCredential() {
    Customer context = (Customer) scenarioContext.getContext(Context.CUSTOMER);
    String username = context.getEmail();
    String password = UrlBuilder.getMessage("loginValidPasswordNew.key");
    scenarioContext.setContext(Context.EMAIL_ID, username);
    loginPage.waitForExpectedElement(loginPage.EMAIL_INPUTBOX, 20).sendKeys(username);
    loginPage.waitForExpectedElement(loginPage.PASSWORD_INPUTBOX,10).sendKeys(password);
    loginPage.clickByElementByQueryJSExecutor(loginPage.SIGNIN_BUTTON);
    waitForExpectedElement(loginPage.elePageTitle,5);
  }

  @And("^user clicks on Continue with Google button$")
  public void userClicksOnContinueWithGoogleButton() {
    waitAndClickByElementByJSExecutor(loginPage.SIGN_IN_WITH_GOOGLE_BUTTON, 8);
  }

  @When("^users clicks on the data protection link under registration$")
  public void userClickOnDataProtectionLink() {
    waitAndClickByElementByJSExecutor(loginPage.DATA_PROTECTION,5);
  }

  @And("^assert notification message is displayed$")
  public void assertNotificationMessageIsDisplayed() {
    assertTrue(waitForExpectedElement(loginPage.NOTIFICATION_MESSAGE).isDisplayed());
  }
  @When("^user close the notification message$")
  public void userCloseNotificationMessage() {
    waitAndClickByElementByJSExecutor(loginPage.NOTIFICATION_MESSAGE_CLOSE_BUTTON, 4);
  }
  @And("^assert notification message is not displayed$")
  public void assertNotificationMessageIsNotDisplayed() {
    try {
      waitAndClickByElementByJSExecutor(loginPage.NOTIFICATION_MESSAGE_CLOSE_BUTTON, 4);
      assertTrue(false);
    }
    catch(Exception e){
      assertTrue(true);
    }
  }
}
