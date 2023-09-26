@veloDELive
Feature: Velo Billing Infromation

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'


  Scenario: Billing information - Debit Card
    When user clicks on product menu
    And get all lists from page
    Then user Clicks on add to cart button
    And click on basket icon
    And Go to checkout page
    When create a new account
    And validation success message displayed
    And click on basket icon
    And Go to checkout page
    And Enter "mastercard" card details