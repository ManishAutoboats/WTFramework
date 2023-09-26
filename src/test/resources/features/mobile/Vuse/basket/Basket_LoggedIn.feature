Feature: BAT Basket Persistence feature - Logged In Mobile

    Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket


    Scenario: 9598 - Basket - Persistence - Logged in to Logged out item do not remain in basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And click on the logo
   	And user clicks the person icon
   	And user clicks on 'myAccountLink.key' link from Person Menu
    When users clicks on the 'logoutText.key' text link mobile
    And click on the logo
    And click on basket icon
    And basket is empty message is presented