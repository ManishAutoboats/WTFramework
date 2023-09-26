##This is the Checkout feature file
    ## Credit Cards - both debit and credit options
 #@checkout
 #@regression
Feature: BAT checkout feature - Subscriptions
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on search icon and submits the following search term 'searchTermTitle.key'
    And click subscription icon
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed

  Scenario: BUG 44225 / Story 34840 - Subscription tests - Credit Card - Saved Card
    And select Card Payment option then credit Card option
    And click use saved card
    And tick agree to terms and conditions
    And select place order
    And assert text of 'ThankForPurchase.key' is displayed
    And assert text of 'yourOrderNumberText.key' is displayed
   # And assert text of 'orderCofirmDetailText.key' is displayed
    And assert print receipt link is present
    And grab and output Order number
    And user clicks on the 'continue shopping' button


  # TEST TO CHECK SUMMARY PLAN FOR VIEW DETAILS AND SUBS INFORMATION
  Scenario: Checkout Subscription tests - Signed In - Credit Cards - Master Card - View order and confirm subscription details and specific order number
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And tick agree to terms and conditions
    And tick save card box
    And select place order
    And assert text of 'Thank you for your purchase!' is displayed
    And assert text of 'Your order number is:' is displayed
    And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
    And assert print receipt link is present
    And grab and output Order number
    And users clicks on order number to be taken to order view order page
    And assert text of 'Order #' is displayed
    And assert expected order number is present
    And assert text of 'Subscription' is displayed
    And assert text of 'Every day for 5 installments' is displayed
    And users clicks on the 'Logout' text link

  #@sprint1 @18283 @creditCard
  Scenario: Checkout tests - Signed In - Credit Cards - VISA Card - Debit Card Option - Payment
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions
    And select place order
    And assert text of 'Thank you for your purchase!' is displayed
    And assert text of 'Your order number is:' is displayed
    And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
    And assert print receipt link is present
    And grab and output Order number
    And user clicks on the 'continue shopping' button


  #@sprint1 @18283 @creditCard
  Scenario: Checkout tests - Signed In - Credit Cards - AMEX Card - Credit Card option - Payment
    And Credit Card - AMEX order option and press next
    And enter amex card details
    And tick agree to terms and conditions
    And select place order
    And assert text of 'Thank you for your purchase!' is displayed
    And assert text of 'Your order number is:' is displayed
    And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
    And assert print receipt link is present
    And grab and output Order number
    And user clicks on the 'continue shopping' button
