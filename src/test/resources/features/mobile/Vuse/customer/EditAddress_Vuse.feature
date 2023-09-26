Feature: BAT Address management - Edit address - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link mobile

  @RegVuseFR_Mobile
  Scenario: Mobile - Edit Address - MyAccountPage
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And add new address with more than 30 characters in address field
    Then assert error message is displayed for address field


  Scenario: Edit Address Successful
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page


  Scenario: Populate selected Address from Loqate look up - Checkoutpage
    And search and checkout product "searchTerm.key"
    And user click on change address link
    When start entering the address in Checkoutpage with streetKeyword.key
    Then user should be presented with Loqate address look up auto-completion


  Scenario: Populate selected Address from Loqate look up - MyAccountAddressBookPage
    And users clicks on Edit Address link
    When start entering the address in MyAccountAddressBookPage with streetKeyword.key
    Then user should be presented with Loqate address look up auto-completion
