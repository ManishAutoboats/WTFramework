#This feature - verifies First Purchase rules for all LAB products
#@LyftRegression
@LyftDKReg
Feature: Lyft Lab First Purchase Rules - Place order

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Add 6 Lyft LAB products in basket and place an order
    And create a new account
    Then navigate to LAB Collection PLP on the Lyft Lab home page
    And user selects strength 'Regular.key' for a single pack LAB product,selects Quantity '3' and add to cart
    And user selects strength 'Regular.key' for a three pack LAB product,selects quantity '1' and add to cart
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And I delete customer account


