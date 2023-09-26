  Feature: BAT Order status feature Mobile
    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account via api and log in with the account


  Scenario: Order Status tests - Debit Card Payment - Pending Payment section
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "mastercard"
    Then assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And grab Order Number and assert Order Status on View Order Details page
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link mobile

