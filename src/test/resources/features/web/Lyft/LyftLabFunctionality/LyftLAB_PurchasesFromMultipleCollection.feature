#This feature - verifies the purchase from previous LAB collection without any rules
@LyftRegression2
Feature: Lyft Lab - Multiple Purchases from Previous and New Collection

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  Scenario: Make a purchase with Previous collection and New collection
    And user select New collection
    And user selects strength 'productStrength.key' for LAB product,selects Quantity '1' and add to cart
    And user select Previous collection
    And user selects strength 'productStrength.key' for LAB product,selects Quantity '1' and add to cart
    And Customer makes a home delivery purchase with "mastercard"
    Then assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And I delete customer account