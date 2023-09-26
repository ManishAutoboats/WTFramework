Feature: BAT registration feature - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - Registration page - ensure all expected elements are present
   And user clicks the person icon
   And user clicks the registration button
   Then assert page title is 'registrationPageTitle.key'
   Then assert text of 'personalInfoText.key' is displayed
   Then assert text of 'addressInfoText.key' is displayed
   Then assert text of 'signInText.key' is displayed


  Scenario: Mobile Person Data verification after register new user
    When create a new account
    And users clicks on the 'dashboardEditLinkText.key' text link
    Then assert all elements are displayed


  Scenario: Assert checkbox under Mobile Number field displayed for customers not having a UK/FR number and create an account with checkbox selected
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    And user clicks the registration button
    And assert checkbox is displayed for customers not having a UK/FR number under Mobile Number field
    Then user selects the mobile number checkbox
    And assert mobile number field is greyed out and gets populated with 'defaultMobileNumber.key'
    Then user create a new account with default UK/FR mobile number
    Then users clicks on the 'yourAddressText.key' text link mobile
    And users clicks on Edit Address link
    And assert user is able to update any valid UK/FR number and updated information gets saved
    And assert text of 'addressSavedSuccessMessage.key' is displayed
