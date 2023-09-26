Feature: Loyalty feature - Insiders Club - GLO IT - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Glo Add to cart from PLP as Loyalty User
    And user signs in with customer properties 'loginLoyaltyOptedEmail.key' 'loginLoyaltyOptedPassword.key'
    And empty basket
    And user click on 'device' menu navigation
    And user see the product name display
    When user hover the product and click on add to cart on plp page
    Then added product is visible in basket

  @RegGloIT_Mobile
  Scenario: Login as existing user who doesnt have existing devices and sign up for loyalty program
    And create a new account via api and log in with the account
    Then user should be able to see Insiders Club menu option
    When User clicks on Insiders Club menu option of IT
    Then I will see a pop up asking me to register a device

  Scenario: Glo Add to cart from PLP as Non Loyalty User
    When create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user login with credentials registered via api
    And empty basket
    And user click on 'device' menu navigation
    And user see the product name display
    Then add to cart is not visible for loyalty products
