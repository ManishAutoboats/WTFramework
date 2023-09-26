 @checkout
Feature: BAT checkout feature - Guest
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg3 @VuseUKLive2 @VuseFRReg2 @VuseFRLive
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

  @VuseUKLive
  Scenario: BUG - 26280 Checkout tests - Guest to Create an account - Populate Cart proceed to checkout
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And clicks on product link on the basis of index '1'
    And select product strength or colour
    Then click add to cart button
    And click on proceed to checkout button
    And confirm popup mask present
    And select create new account from checkout PopUp
    And assert page title is 'registrationPageTitle.key'
    And assert text of 'createAccountHeading.key' is displayed
    And assert text of 'personalInfoText.key' is displayed

  @VuseFRReg
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

  @VuseUKReg3
  Scenario: BUG - 26280 Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in from basket page - Paypal path -Empty basket
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And users clicks on the 'logoutText.key' text link
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And select product by index "productIndex.key" on plp page
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

  @VuseUKReg3 @VuseUKLive2 @VuseFRReg2 @VuseFRLive
  Scenario: Assert checkbox under Mobile Number field displayed for customers not having a UK/FR number on Registration page
    And search and view basket for the product 'searchTermVuse.key'
    And user selects proceed to checkout from cart page
    And select create new account from checkout PopUp
    And assert checkbox is displayed for customers not having a UK/FR number under Mobile Number field
    Then user selects the mobile number checkbox
    And assert mobile number field is greyed out and gets populated with 'defaultMobileNumber.key'

  @VuseUKReg3 @VuseFRReg3
  Scenario: Add manual address input on checkout journey
    When search and checkout product "searchTerm.key"
    And confirm popup mask present
    Then select create new account from checkout PopUp
    And create a new account with manual address input
    Then Payment page details confirmed
    And enter select package shop details
    And Credit Card - VISA order option and press next
    And enter visa card details
    And tick agree to terms and conditions and select place order
    And assert text of 'ThankForPurchase.key' is displayed
