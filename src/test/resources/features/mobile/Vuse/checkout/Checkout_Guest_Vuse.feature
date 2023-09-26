Feature: BAT checkout feature Guest - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Checkout tests - Guest to Create an account - Populate Cart proceed to checkout - from Basket Page
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert text of 'createAccountPageText.key' is displayed
    And assert text of 'personalInfoText.key' is displayed
    And assert text of 'remeberMeText.key' is displayed


  Scenario: BUG - 26280 Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in from basket page - Paypal path
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And user selects proceed to checkout from cart page
    And login with valid details
    And Check alternative payment order option
    And Check paypal option
    And tick agree to terms and conditions
    And select place order
    And click on Continue on Paypal button on Checkout page
    And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key'
    And paypal page - click continue
    And user selects agree to terms and conditions and clicks on Validate Payment on Review Order page
    And grab and output Order number



  Scenario: Assert checkbox under Mobile Number field displayed for customers not having a UK/FR number on Registration page
    And search and view basket for the product 'searchTermVuse.key'
    And user selects proceed to checkout from cart page
    And select create new account from checkout PopUp
    And assert checkbox is displayed for customers not having a UK/FR number under Mobile Number field
    Then user selects the mobile number checkbox
    And assert mobile number field is greyed out and gets populated with 'defaultMobileNumber.key'


  @RegVuseFR_Mobile
  Scenario: Mobile Add manual address input on checkout journey
    When search and checkout product "searchTerm.key"
    And confirm popup mask present
    Then select create new account from checkout PopUp
    And create a new account with manual address input
    And Shipping page details confirmed
    When Credit or Debit Cards (SubscribePro) option is chosen
    And Checkout is selected with appropriate card details entered '5555555555554444'
    Then assert print receipt link is present
    And assert text of 'ThankForPurchase.key' is displayed
