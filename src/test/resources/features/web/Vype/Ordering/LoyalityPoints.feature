#@de @delive - Blocked until we get an API to generate invoice from Backend
Feature: Loyality Points

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'

  Scenario: Loyality Points Calculation
    And Customer makes a home delivery purchase with "mastercard"
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert Loyality points against the order number generated
