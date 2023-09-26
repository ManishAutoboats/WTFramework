##This is the Checkout feature file
    ## PayPal
      ## Success
 @checkout @smokeLite @regression @smokeFast @fr @de @frSmoke @nl @ITSmoke @ITReg @desmoke
Feature: BAT checkout feature - PayPal
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  Scenario: Checkout tests - PayPal path
    And Check alternative payment order option
    And Check paypal option
    And tick agree to terms and conditions
    And select place order
    And click on Continue on Paypal button on Checkout page
    And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key'
    And paypal page - click continue
    And user selects agree to terms and conditions and clicks on Validate Payment on Review Order page
    And grab and output Order number
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
