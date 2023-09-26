Feature: BAT Vuse Universal Smoke Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #velo product in vuse
  Scenario: VUSE-VELO - Smoke Flow
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "Sid Secure EFT"
    And assert that the 'ThankForPurchase.key' thank you message is displayed in the page header
    And assert text of 'yourOrderNumberText.key' is displayed