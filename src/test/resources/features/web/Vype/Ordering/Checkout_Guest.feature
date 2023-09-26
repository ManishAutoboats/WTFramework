 @checkout
Feature: BAT checkout feature - Guest
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user click on search icon and submits the following search term 'searchTermePen3.key'
    #And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon

  @sprint1 @18283 @live @frlive @NLlive  @delive @IElive @NonCaptchaLive @COReg @IEReg2 @VuseZAReg
  Scenario: BUG - 26280 Checkout tests - Guest to Create an account - Populate Cart proceed to checkout
    And users clicks on the 'viewBasketText.key' text link
    And user selects proceed to checkout from cart page
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert page title is 'registrationPageTitle.key'
    And assert text of 'createAccountHeading.key' is displayed
    And assert text of 'personalInfoText.key' is displayed
    # Below removed as Alex advised this is affecting other locales
#    And assert text of 'addressInfoText.key' is displayed
#    And assert text of 'signInText.key' is displayed
    And assert text of 'remeberMeText.key' is displayed

  @sprint1 @18283 @live @smokeLite @smokeFast @frlive @checkout1 @fr @NLlive @NonCaptchaLive @regression @COReg @IEReg2
  Scenario: Checkout tests - Guest to Create an account - Populate Cart proceed to checkout - from Basket Page
    And users clicks on the 'viewBasketText.key' text link
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'deliveycostconfirmTextMsg.key' is displayed
    And user selects proceed to checkout from cart page
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert text of 'createAccountPageText.key' is displayed
    And assert text of 'personalInfoText.key' is displayed
    And assert text of 'remeberMeText.key' is displayed

  @sprint1 @18283 @regression
  Scenario: BUG - 26280 Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in from basket page - Paypal path
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
    #And assert text of 'ThankForPurchase.key' is displayed
  	#And assert text of 'yourOrderNumberText.key' is displayed
    #And assert text of 'orderCofirmDetailText.key' is displayed


  #@sprint1 @18283
  #Scenario: BUG - 26280 Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in
  #  And user click on search icon and submits the following search term 'liquid'
  #  And get all lists from page
  #
  #  And click first result
  #  And select product colour
  #  Then click add to cart button
  #  And click on basket icon
  #  And click on proceed to checkout button
  #  And confirm popup mask present
  #  And login with valid details
  #  And Shipping page details confirmed
  #  And Check money order option selected
  #  And tick agree to terms and conditions
  #  And select place order
  #  And assert text of 'Thank you for your purchase!' is displayed
  #  And assert text of 'Your order number is:' is displayed
  #  And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
  #  And user clicks the person icon
  #  And users clicks on the 'Logout' text link
  #  And testingBreakpoint

  #@sprint1 @18283
  #Scenario: Bug - 26280 Checkout tests - Signed In - Check Money Order Payment - from basket page
  #  And user clicks the person icon
  #  And login with valid details
  #  And user click on search icon and submits the following search term 'liquid'
  #  And get all lists from page
  #
  #  And click first result
  #  And select product colour
  #  Then click add to cart button
  #  And click on basket icon
  #  And users clicks on the 'View Basket' text link
  #  And user selects proceed to checkout from cart page
  #  And Payment page details confirmed
  #  And click on shipping method
  #  And Check money order option selected
  #  And tick agree to terms and conditions
  #  And select place order
  #  And assert text of 'Thank you for your purchase!' is displayed
  #  And assert text of 'Your order number is:' is displayed
  #  And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
  #  And assert print receipt link is present
  #  And grab and output Order number
  #  And user clicks on the 'continue shopping' button
  #  And user clicks the person icon
  #  And users clicks on the 'Logout' text link
  #  And testingBreakpoint

  #@sprint1 @18283 @smokeLite
  #Scenario: Checkout tests - Signed In - Check Money Order Payment
  #  And user clicks the person icon
  #  And login with valid details
  #  And user click on search icon and submits the following search term 'liquid'
  #  And get all lists from page
  #
  #  And click first result
  #  And select product colour
  #  Then click add to cart button
  #  And click on basket icon
  #  And click on proceed to checkout button
  #  And click on shipping method
  #  And Check money order option selected
  #  And tick agree to terms and conditions
  #  And select place order
  #  And assert text of 'Thank you for your purchase!' is displayed
  #  And assert text of 'Your order number is:' is displayed
  #  And assert text of 'We'll email you an order confirmation with details and tracking info.' is displayed
  #  And assert print receipt link is present
  #  And grab and output Order number
  #  And user clicks on the 'continue shopping' button
  #  And user clicks the person icon
  #  And users clicks on the 'Logout' text link
  #  And testingBreakpoint
