Feature: BAT Login feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And recover Eyes status

  @sprint1 @smokeLite @live @MXReg2 @dk @retesting @dklive @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de @desmoke @nl @IEReg2 @ @VuseZAReg2 @VuseDKReg
  Scenario: 21654 Sign in Page - confirm expected elements are present on sign in page
    And assert text of 'loginpageTopTextHeading.key' is displayed
    And assert text of 'loginpageBottomTextHeading.key' is displayed
    And email input box displayed and enabled
    And password input box displayed and enabled
    And remember me box is displayed and enabled and checked by default
    And forgot your password button displayed
    And create an account button is displayed

  @eyesVypeUkRegression
  @live @dk @dksmoke @dklive @eyestest @dklive @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de @nl @IEReg2 @COReg @VuseZAReg @VuseDKReg
  Scenario: 21654 Incorrect Sign-in - No Password provided
    Then user signs in to the site custom details 'loginInvalidEmail.key' 'loginNoPassword.key'
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And eyes check entire page "Login Error - Invalid Email and No Password"

  @eyesVypeUkRegression
  @live @dk @dklive @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de  @nl @IEReg2 @COReg @VuseZAReg2 @VuseDKReg
  Scenario: 21654 Incorrect Sign-in - No UserName or Password provided
    When user enters sign in details, with username of '' and password ''
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And eyes check entire page "Login Error - No Email and No Password"

  @eyesVypeUkRegression
  @live @dk @dklive @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de @nl @IEReg2 @COReg @VuseZAReg @VuseDKReg
  Scenario: 21654  Incorrect Sign-in - Invalid e-mail
    Then user signs in to the site custom details 'loginInvalidEmail.key' 'loginValidPassword.key'
    Then assert text of 'loginInvalidEmailErrorMsg.key' is displayed
    And eyes check entire page "Login Error - Invalid Email and Valid Password"

  @eyesVypeUkRegression
  @smokeLite @live @dk @MXReg2 @ITReg @dklive @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de @nl @IEReg2 @COReg @VuseZAReg
  Scenario: 21654 Incorrect Sign-in - Incorrect login details
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    Then assert text of 'incorrectSignInMsg.key' is displayed
    And eyes check entire page "Login Error - Non-existing Email and Non-existing Password"

  @live @MXReg2 @ITReg @dk @login @regression @21654 @NLlive  @frSmoke @fr @frlive @de @nl @IEReg2 @COReg @VuseDKReg
  Scenario: 21654 Forgotten password
    When user clicks the forgotten password link
    Then user is taken to forgot your password page

  @regression @MXReg2 @login @regression @21654 @NLlive @de  @nl @IEReg2 @COReg @VuseZAReg2
  Scenario: 9854 Password Manager Support - Remember me
    Then assert remember me tick box is present
    And user clicks the registration button
    Then assert remember me tick box is present

  @smokeLite @login @loginTest @MXSmoke @MXReg @dk @fr @de @nl @dksmoke @dklive @nlSmoke @desmoke @regression @frSmoke @ITSmoke @ITReg @IElive @COSmoke @NLlive @frlive @COReg @VuseZAReg @VuseDKReg @VuseDKLive @VuseZALive
  Scenario: 21654 Correct Sign-in
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in

  @dk @dklive
  Scenario: Privacy Policy Text and Link CTA on Login Page
    Then assert text of 'privacyPolicyText.key' is displayed
    And assert 'privatePolicy.key' text link and navigation on CTA

#TODO: This feature is blocked due to the defect creating an account via API. Need to revisit this after the defect is fixed.
  #@COReg because of  Defect# 502038
  Scenario: Forgot password and remember me check box
    And create a new account via api
    When user clicks on forgot password link
    Then user should be redirected to forgotYourPasswordTitle.key page
    And user email input box displayed and enabled
    When user enters email address registered via api in the email box
    And user click on submit button
    Then user should see a text of receiveLinkToResetPassword.key
    And user should receive a email to registered email with reset password link
    When user resets the password
    Then user should be redirected to loginPageTitle.key page
    And user should see a text of passwordUpdateSuccess.key
    And remember me box is displayed and enabled and checked by default
    And delete the account via api

  @fr @frlive @regression @live
  Scenario: Error Message on Incorrect login details
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    Then assert error message 'incorrectSignInMsg.key' with contact us link is displayed

  @MXReg2
  Scenario: Verify Delete Account
    And user clicks the registration button
    And create a new account
    Then verify if the Account is created
    And users clicks on the 'DeleteAccount.key' text link
    And click on delete account