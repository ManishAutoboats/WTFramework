Feature: BAT Address management - Edit Address - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Edit Address Successful
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page