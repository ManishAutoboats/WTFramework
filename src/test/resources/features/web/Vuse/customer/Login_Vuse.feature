Feature: BAT Login feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present

  @VuseDEReg2 @VuseDELive @VuseUKReg2 @VuseUKLive2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseITAnonReg2
  Scenario: 21654 Sign in Page - confirm expected elements are present on sign in page
    And assert text of 'loginpageTopTextHeading.key' is displayed
    And email input box displayed and enabled
    And password input box displayed and enabled
    And remember me box is displayed and enabled and checked by default
    And forgot your password button displayed
    And create an account button is displayed


  @VuseDEReg2  @VuseUKReg @VuseUKLive2 @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseMXLive
  Scenario: 21654 Incorrect Sign-in - No Password provided
    Then user signs in to the site custom details 'loginInvalidEmail.key' 'loginNoPassword.key'
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And eyes check entire page "Login Error - Invalid Email and No Password"


  @VuseDEReg2 @VuseUKReg2 @VuseUKLive2 @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseMXLive
  Scenario: 21654 Incorrect Sign-in - No UserName or Password provided
    When user enters sign in details, with username of '' and password ''
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And eyes check entire page "Login Error - No Email and No Password"


  @VuseDEReg2 @VuseUKReg2 @VuseUKLive2 @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseMXLive
  Scenario: 21654  Incorrect Sign-in - Invalid e-mail
    Then user signs in to the site custom details 'loginInvalidEmail.key' 'loginValidPassword.key'
    Then assert text of 'loginInvalidEmailErrorMsg.key' is displayed
    And eyes check entire page "Login Error - Invalid Email and Valid Password"


  @VuseDEReg2 @VuseUKReg2 @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseMXLive
  Scenario: 21654 Incorrect Sign-in - Incorrect login details
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    Then assert text of 'incorrectSignInMsg.key' is displayed
    And eyes check entire page "Login Error - Non-existing Email and Non-existing Password"


  @VuseDEReg2 @VuseUKReg2 @VuseUKLive2 @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive @VuseMXReg @VuseMXLive @VuseZAReg
  Scenario: 21654 Forgotten password
    When user clicks the forgotten password link
    Then user is taken to forgot your password page

  @VuseDEReg @VuseUKReg @VuseUKLive @VuseITReg @VuseFRReg @VuseFRLive @VuseMXReg
  Scenario: 21654 Correct Sign-in
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in

  @VuseUKLive2 @VuseFRReg2 @VuseFRLive
  Scenario: Error Message on Incorrect login details
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    Then assert error message 'incorrectSignInMsg.key' with contact us link is displayed

  @VuseMXReg @VuseMXLive
  Scenario: Verify Delete Account
    And user clicks the registration button
    And create a new account
    Then verify if the Account is created
    And users clicks on the 'DeleteAccount.key' text link
    And click on delete account

  @VuseMXReg
  Scenario: Password Manager Support - Remember me
    Then assert remember me tick box is present
    And user clicks the registration button
    Then assert remember me tick box is present

  @VuseDEReg2 @VuseITAnonReg3
  Scenario: Forgot password, check email and reset password
    And create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    When user clicks the forgotten password link
    Then user is taken to forgot your password page
    And user enters email address registered via api in the email box
    And user click on submit button
    Then user should see a text of receiveLinkToResetPassword.key
    And user should receive a email to registered email with reset password link
    When user resets the password
    And user should see a text of passwordUpdateSuccess.key

  @VuseITAnonReg3
  Scenario: Forgot password and remember me check box
    And create a new account via api
    When user resets the password
    Then user should be redirected to loginPageTitle.key page
    And user should see a text of passwordUpdateSuccess.key
    And delete the account via api

  @VuseUKReg2
  Scenario: Forgot password, check email and reset password vuseuk
    And create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    When user clicks the forgotten password link
    Then user is taken to forgot your password page
    And user enters email address registered via api in the email box
    And user click on submit button
    Then user see text for reset password
    And user should receive a email to registered email with reset password link
    When user resets the new password
    And user should see a text of passwordUpdateSuccess.key

  @VuseITAnonReg3
  Scenario: Edit Password Functionality
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
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

  @VuseFRReg3
  Scenario: Forgot password, check email and reset password vusefr
    Given create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    When user clicks the forgotten password link
    And user is taken to forgot your password page
    And assert error message for incorrect email format reset password
    And user enters email address registered via api in the email box
    And user click on submit button
    Then user should see a text of receiveLinkToResetPassword.key
    And user should receive a email to registered email with reset password link
    And user resets the password
    Then user should see a text of passwordUpdateSuccess.key
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user try to login with new password assert redirection success

  @VuseUKReg2
  Scenario: Verify Registering as New Customer without Facebook
    Given create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    When user clicks the forgotten password link
    Then user is taken to forgot your password page
    And user enters email address registered via api in the email box
    And user click on submit button
    Then user should see a text of receiveLinkToResetPassword.key
    And user should receive a email to registered email with reset password link
    When user resets the new password
    Then user should see a text of passwordUpdateSuccess.key
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user signs in to the site via api user credential
    And user is successfully logged in
    And click on delete my account and proceed

  @VuseUKReg2  @VuseFRReg3
  Scenario: Error Message notification
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    And assert notification message is displayed
    When user close the notification message
    Then assert notification message is not displayed

#automate issue 980668
  @VuseMXReg
  Scenario: Forgot password for valid user
    When user click on forgot password link
    And enter email address "loginValidEmail.key"
    And user click on submit button
    Then user should see a text of resetPasswordMessage.key

  @VuseITAnonReg2
  Scenario: Forgot password flow
    Given create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    When user clicks the forgotten password link
    And user is taken to forgot your password page
    And user enters email address registered via api in the email box
    And user click on submit button
    And user should receive a email to registered email with reset password link
    And user resets the new password
    Then user should see a text of passwordUpdateSuccess.key
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user signs in to the site via api user credential
    Then assert text of 'incorrectSignInMsg.key' is displayed
    Then user try to login with new password assert redirection success