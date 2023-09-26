Feature: BAT Vype Universal Smoke Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @UniversalSmokeVypeNewUser_mobile
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account
    And create a new account
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account
    #Then url contains 'DeleteRegistrationUrl.key'

  @UniversalSmokeVypeSmokeUser_mobile
  Scenario: Existing user Smoke Flow  - Registration - Visa Payment - Delete Account
    And create a new account
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account
#    Then url contains 'DeleteRegistrationUrl.key'


  @UniversalSmokeVypeNewUserCO_mobile
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account - CO
    And create a new account
    And Customer makes a home delivery purchase with "open pay pse"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account
#    Then url contains 'DeleteRegistrationUrl.key'
