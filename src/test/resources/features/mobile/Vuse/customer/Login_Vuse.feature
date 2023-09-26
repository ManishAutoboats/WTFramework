Feature: BAT Login feature - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - Incorrect Sign-in - Invalid e-mail
    Then user signs in with customer properties 'loginInvalidEmail.key' 'loginValidPassword.key'
    Then assert text of 'loginInvalidEmailErrorMsg.key' is displayed
    And eyes check entire page "Login Error - Invalid Email and Valid Password"

  #@RegVuseFR_Mobile //commented as we are not considering email now.
  Scenario: Mobile - Forgot password, check email and reset password vusefr
    Given create a new account via api
    And user clicks the person icon
    And user closes the alert if present
    When user clicks the forgotten password link
    And user is taken to forgot your password page
    And user enters email address registered via api in the email box
    And user click on submit button
    Then user should see a text of receiveLinkToResetPassword.key
    And user should receive a email to registered email with reset password link
    And user resets the password mobile
    Then user should see a text of passwordUpdateSuccess.key


  Scenario: Error Message on Incorrect login details
    Then user signs in to the site custom details 'loginNonExistingEmail.key' 'loginNonExistingPassword.key'
    Then assert error message 'incorrectSignInMsg.key' with contact us link is displayed
    And eyes check entire page "Login Error - Non-existing Email and Non-existing Password"
