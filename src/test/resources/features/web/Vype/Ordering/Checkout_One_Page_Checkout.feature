##This is the one page checkout content checks, functionality is proved in the actual payments tests
 @onePageCheckout @22467 @9599 @checkout
Feature: 22647 Checkout - One Page Checkout
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTermWithEcoTax.key'
    #And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  Scenario: 22647 One page Checkout content checks
    And url contains 'checkoutUrl.key'
    #And assert text of 'ShippingAddressText.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    #And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert that one block summary div is displayed
    And selectable shipping options displayed
    And select credit card radio box
    And assert credit card information for presented to user


@regression
  Scenario: 9605 Ordering - Check Out - Review Order
    And url contains 'checkoutUrl.key'
    And assert text of 'addressHeading.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert that one block summary div is displayed
    And assert items are displayed within the summary plain
    And selectable shipping options displayed


  Scenario: 9599 Ordering - Check Out - Delivery Options
    And url contains 'checkoutUrl.key'
    #And assert text of 'ShippingAddressText.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    #And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And selectable shipping options displayed
    And click on shipping method


  @fr @frlive
  Scenario: Checkout - Sub-Total and Total if VAT, Eco tax applied with Delivery charges, without any Discounts
    And apply discount code module present
    And assert Eco tax is applied for the product
    And fetch the Sub-Total, Total and Delivery charges for the product
    And assert Delivery Charges before applying promo code

  #@fr
  #Scenario: Checkout - Sub-Total and Total if VAT, Eco tax applied with Delivery charges, with 100% Discount
  #  And apply discount code module present
  #  And assert Eco tax is applied for the product
  #  And fetch the Sub-Total, Total and Delivery charges for the product
  #  And apply discount 'FR100' to the discount module and assert success message
  #  And assert Total Charges are updated after applying promo code
  #  And assert Delivery Charges after applying promo code
  #  And assert Eco tax is applied for the product

  @fr
  Scenario: Checkout - Sub-Total and Total if VAT, Eco tax applied without Delivery charges, with 50% Discount
    And apply discount code module present
    And assert Eco tax is applied for the product
    And fetch the Sub-Total, Total and Delivery charges for the product
    And apply discount 'FR50' to the discount module and assert success message
    And assert Total Charges are updated with respect to other charges after applying 50% promo code
    And assert Delivery Charges after applying promo code
    And assert Eco tax is applied for the product