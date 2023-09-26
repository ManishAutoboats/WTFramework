Feature: 22647 Checkout - One Page Checkout-Vuse
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

    @VuseUKReg
  Scenario: 9605 Ordering - Check Out - Review Order-Vuse
    And url contains 'checkoutUrl.key'
    And assert text of 'addressHeading.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert that one block summary div is displayed
    And assert items are displayed within the summary plain
    And selectable shipping options displayed
