##This is the Checkout feature file
    ## Debit/Credit Cards - Order Status after successful purchase, when product is selected from Category Menu
Feature: BAT Order From Category Menu
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user selects over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And empty basket
    And user clicks on 'categoryMenuLink.key' from Menu
    And get all lists from page

    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
   	
   	 Scenario: Checkout tests - Signed In - Credit Cards - Master Card - Payment
    And enter select package shop details
    And Credit Card - MasterCard option and press next
    And enter mastercard details
   	And tick agree to terms and conditions
    And select place order
    And user clicks on Next button and place the order
    And assert text of 'ThankForPurchase.key' is displayed
   # And assert text of 'yourOrderNuberText.key' is displayed
    And assert text of 'orderCofirmDetailText.key' is displayed
   # And assert print receipt link is present
    And grab and output Order number
    And user clicks on the 'continue shopping' button
    And user clicks the person icon
    And users clicks on the 'recentlyOrderedText.key' text link
    And assert order number is displayed Previous orders on page
    And grab contents of matched row and assert order status
    
    Scenario: Checkout tests - PayPal path
    And Check alternative payment order option
    And Check paypal option
   	And tick agree to terms and conditions
    And select place order
    And click on Continue on Paypal button on Checkout page
    And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key'
    And paypal page - click continue
    And user selects agree to terms and conditions and clicks on Validate Payment on Review Order page
    And assert text of 'ThankForPurchase.key' is displayed
   # And assert text of 'yourOrderNuberText.key' is displayed
    And assert text of 'orderCofirmDetailText.key' is displayed
   # And assert print receipt link is present
    And grab and output Order number
    And user clicks on the 'continue shopping' button
    And user clicks the person icon
    And users clicks on the 'recentlyOrderedText.key' text link
    And assert order number is displayed Previous orders on page
    And grab contents of matched row and assert order status
