Feature: Basket - VAT & shipping details - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta

  @RegVuseFR_Mobile
  Scenario: Mobile - Basket - VAT & shipping details
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And login with valid details
    And Shipping page details confirmed


  Scenario: Mobile Basket - Continue Shopping
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And user click on search icon and submits the following search term 'searchTerm.key'
    And search results title of 'searchResults.key' is returned
    And confirm mini-basket displayed amount of '1'
