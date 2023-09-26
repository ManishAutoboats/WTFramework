Feature: BAT Account management - My Account dash board tests - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @RegVuseFR_Mobile
  Scenario: Mobile - FR My Account - Password change
    And create a new account via api and log in with the account
    Then assert text of 'dashboardEditText.key' is displayed
    Given users clicks on the 'dashboardEditLinkText.key' text link mobile
    Then assert text of 'editAccountInfoText.key' is displayed
    When select update password checkbox
    And user change the password
    And users clicks on the 'logoutText.key' text link mobile
    Then user signs in to the site custom details mobile

  @RegVuseFR_Mobile
  Scenario: My Account Dashboard Page Contents
    And user clicks the person icon
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then assert text of 'DashboardTitle.key' is displayed
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed
    Then assert text of 'dateOfBirthText.key' is displayed


  Scenario: My Account Dashboard page contents information expected
    Then assert text of 'dashboardEditText.key' is displayed
    Then assert text of 'myAccountDashboardText.key' is displayed
    Then assert text of 'firstNameText.key' is displayed
    Then assert text of 'lastNameText.key' is displayed
    Then assert text of 'emailText.key' is displayed
    Then assert text of 'dateOfBirthText.key' is displayed
