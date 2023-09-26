#This is the login feature file
  ##Testing Password rules

Feature: BAT Account Management - Password rules
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft

  @LyftRegression @LyftLive @LyftDKReg
  Scenario: 18939 Password rules
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And user clicks the myAccount edit link - lyft
    And select update password checkbox
    Then update password with '1' and assert error msg
    Then update password with '122' and assert error msg
    Then update password with '1333' and assert error msg
    Then update password with '144444' and assert error msg
    Then update password with '15555555' and assert error msg
    Then update password with '^&*(^&*(^&*(^*(' and assert error msg
    Then update password with 'asferwq' and assert error msg

  @LyftRegression
  Scenario: Edit Password with incorrect confirm password
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in
    And user clicks on Change Password button
    And select Change Password checkbox
    When update password with incorrect confirm password
    And assert text of 'incorrectConfirmPasswordError.key' is displayed

  @LyftRegression
  Scenario: Password change and login with new password
    Given create a new account via api and log in with the account
    And user clicks on Change Password button
    And select Change Password checkbox
    When update password with correct confirm password
    Then Password Change notification message is displayed
    And users clicks on the 'logoutText.key' text link
    And user clicks the person icon -lyft
    # Then user try to login with old password assert error
    Then user try to login with new password assert redirection success
