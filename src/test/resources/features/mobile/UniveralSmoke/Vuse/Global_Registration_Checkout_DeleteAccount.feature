Feature: BAT Vuse Universal Smoke Test - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @UniversalSmokeVuseZA_mobile
  Scenario: VUSE - Smoke Flow - Registration - Visa Payment - Delete Account
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "Sid Secure EFT"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
   # And I delete customer account // Becuase of DPO issue and bug ID is 324016

  @UniversalSmokeVuse_mobile
  Scenario: Smoke Flow - Registration - Visa Payment - Delete Account
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And close the mini basket menu
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And I delete customer account


