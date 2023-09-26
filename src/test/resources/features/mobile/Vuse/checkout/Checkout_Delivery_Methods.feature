Feature: BAT checkout Delivery Methods Test Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Select Express Delivery at checkout and verify Delivery Charges
    And create a new account via api and log in with the account
    And search and checkout product "searchProductItem.key"
    And select express delivery as delivery method
    Then verify delivery charges in the order summery
    And click on the logo
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And assert if user is able to input any value '6' in mini basket Qty field
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Payment page details confirmed
    And select express delivery as delivery method
    Then verify delivery charges in the order summery
