Feature: BAT Vype Universal Smoke Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @UniversalSmokeVypeNewUser
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And eyes check sub pages on My Account page
    And I delete customer account

  @UniversalSmokeVypeSmokeUser
  Scenario: Existing user Smoke Flow  - Registration - Visa Payment - Delete Account
    And create a new account with email address "battestsmoke@mailinator.com"
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account

  @UniversalSmokeVypeNewUserCO
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account - CO
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "open pay pse"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account
