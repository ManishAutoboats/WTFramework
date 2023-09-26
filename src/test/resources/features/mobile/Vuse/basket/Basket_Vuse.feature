Feature: BAT Basket feature - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @RegVuseFR_Mobile
  Scenario: Mobile - Basket - Persistence - Guest to Logged in item remains in basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And click on the logo
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then confirm mini-basket displayed amount of '1'

  @RegVuseFR_Mobile
  Scenario: Mobile - Remove SKU from basket with "-" sign on Mini Cart
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on mini cart and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty


  Scenario: Bug 832195 - After first product add to cart PLP page hang
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    Then select first product color or strength on PLP
    And from PLP click add to cart
    And click back button on view basket
    Then verify PLP page is scrollable


  Scenario: Remove SKU from basket with "-" sign on Basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Cancel button from confirm removal pop up and assert pop up is closed
    Then user clicks on minus button on basket page and assert pop-up to confirm product removal appears
    And user clicks on Remove button from confirm removal pop up and assert basket gets empty
