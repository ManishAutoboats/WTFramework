  #This is the login feature file
  ##Testing Password rules
Feature: BAT Account Management - Password rules
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    Then user is successfully logged in

  @gloItRegression @gloDeRegression @gloPlRegression @gloPlLive @gloKzRegression
  Scenario: Password rules
    And users clicks on the 'dashboardEditLinkText.key' text link
    And select update password checkbox
    And update password with '1' and assert error msg
    And update password with '122' and assert error msg
    And update password with '1333' and assert error msg
    And update password with '144444' and assert error msg
    And update password with '15555555' and assert error msg
    And update password with '^&*(^&*(^&*(^*(' and assert error msg
    And update password with 'asferwq' and assert error msg

  @gloKzRegression
  Scenario: Edit Password Functionality
    And user clicks on PersonIcon and Navigate to My Account Page
    And Click on change Password
    And Click on change Password checkbox
    And Enter current Password 'currentPassword.key' in new password box
    And Enter new Password 'newPassword.key' in new password box
    And Enter confirm new Password 'confirmPassword.key' in confirm new password box
    And Click on save button

  @gloPlRegression @gloDeRegression
  Scenario: My Account - Password update
    And Click on change Password
    Then select update password checkbox
    Then user change the password
    And user hovers over the person icon
    And users clicks on the 'signOutLink.key' link
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPasswordNew.key'
    Then user is successfully logged in

  # Automate bug 907801
  @gloPlRegression
  Scenario: My Account - update password with incorrect current password
    Given users clicks on the 'dashboardEditLinkText.key' text link
    And select update password checkbox
    And Enter current Password 'loginInvalidPassword.key' in new password box
    And Glo resets the password
    Then error message of 'errorMessageForCurrentPassword.key' is displayed

   # Automate bug 907801
  @gloPlRegression
  Scenario: My Account - update email with incorrect current password
    Given users clicks on the 'dashboardEditLinkText.key' text link
    And select update email checkbox
    And Enter new Email id in email box
    And Enter current Password 'loginInvalidPassword.key' in new password box
    And user click on save button
    Then error message of 'errorMessageForCurrentPassword.key' is displayed

  # Automate bug 954416
  @gloItRegression
  Scenario: Update password with incorrect current password
    When Click on change Password
    And update password with incorrect current password
    Then error message of 'errorMessageForCurrentPassword.key' is displayed