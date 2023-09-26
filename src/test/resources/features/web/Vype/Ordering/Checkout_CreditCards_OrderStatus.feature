##This is the Checkout feature file
    ## Credit Cards - Order Status after successful purchase
@regression @fr @dk @de @nlSmoke @dksmoke @nl @IEReg #@MXReg
Feature: BAT Order status feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  @regression @fr @dk @de @nlSmoke @dksmoke @nl @IEReg #@MXReg Debit card payment is not applicable for MX
  @sprint1 @18283 @creditCard @frSmoke
  Scenario: Order Status tests - Debit Card Payment - Pending Payment section
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And user clicks on Next button and place the order
    And assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert links on Order Confirmation page redirects to correct URLs
    And grab Order Number and assert Order Status on View Order Details page

  #@MXReg DEDIT CARD PAYMENT IS NOT APPLICABLE FOR MEXICO
  Scenario: Order Status tests - Debit Card Payment - confirmation email
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    When user clicks on Next button and place the order
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
    And assert links on Order Confirmation page redirects to correct URLs
    And grab Order Number and assert Order Status on View Order Details page
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link
    And user should receive a order confirmation email with order number