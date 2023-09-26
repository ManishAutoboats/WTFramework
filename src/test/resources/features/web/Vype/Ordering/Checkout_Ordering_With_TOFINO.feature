##This is the Checkout feature file
    ## Successful Order Placement with TOFINO Product
@regression
Feature: BAT Ordering with TOFINO Product
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket

  Scenario: Allow TOFINO purchase to specific postcodes and place order with Non TOFINO product
    And Search for a product 'searchProduct.key' , and add to cart from PDP page
    And click on the logo
    And users navigates to TOFINO products landing page URL 'tofinoProductsUrl.key'
    Then user clicks on Find Out More link for a Tofino product
    And add Tofino product to the basket
    And assert Newsletter Sign Up is displayed on Basket page
    And click on proceed to checkout button
    And Payment page details confirmed
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    And assert invalid post code message is displayed
    And user place the order after removing the tofino product

  Scenario: Verify TOFINO PLP,PDP page and Place Order with a TOFINO Product and assert order details
    And click on the logo
    And users navigates to TOFINO products landing page URL 'tofinoProductsUrl.key'
    And assert page title contains 'tofinoPageTitle.key'
    And assert tofino products section at bottom of the page
    Then user clicks on Find Out More link for a Tofino product
    And assert user navigates to Tofino PDP
    And assert Newsletter Component and footer disclaimer for Tofino products
    Then select product strength or colour
    And fetch selected CBD strength variant
    Then click add to cart button
    And click on basket icon
    And assert tofino strength on 'tofinoMiniCart.key'
    And user clicks on the View Basket cta
    And assert Newsletter Sign Up is not displayed for TOFINO products
    And click on proceed to checkout button
    And Payment page details confirmed
    And assert tofino strength on 'tofinoPaymentPage.key'
    Then user adds a new address with allowed post code
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button and place the order
    And assert print receipt link is present
    And assert Newsletter Sign Up is not displayed for TOFINO products
    And assert text of 'ThankForPurchase.key' is displayed
    Then users clicks on order number to be taken to order view order page
    And assert CBD Strength variant on Order Details Page
    And assert tofino strength on 'tofinoOrder.key'
