Feature: BAT Address management - create address - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link mobile

  @RegVuseFR_Mobile
  Scenario: Mobile - Add Address Successfully
    And click on my account new address button
    And ensure Add Address page is loaded
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page


  Scenario: Add Address - Page Check
    Then assert text of 'addressHeading.key' is displayed
    Then assert text of 'defaultBillingAddressText.key' is displayed
    Then assert text of 'defaultShippingAddressText.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    And confirm Add address form is presented to user