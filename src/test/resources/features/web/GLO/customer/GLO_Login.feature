Feature: BAT Login feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present

  @gloItRegression2
  Scenario: Forgot password, check email and reset password
    Given create a new account via api
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
    And delete the account via api