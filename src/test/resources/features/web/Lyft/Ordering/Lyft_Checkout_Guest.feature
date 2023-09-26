#TODO
#@LyftRegression
Feature: BAT checkout feature - Guest 
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And user clicks the person icon -lyft
	And attempt to logout 
	And user click on search icon and submits the following search term 'searchTermdi.key' 
	And click first result 
	And select product strength or colour 
	And click add to cart button
	And click on basket icon 
	
	
	@LyftSmoke @LyftDKReg
Scenario: Checkout tests - Guest to Create an account - Populate Cart proceed to checkout - from Basket Page 
	And users clicks on the 'viewBasketText.key' text link 
	And assert text of 'basketHeadingText.key' is displayed 
	And user selects proceed to checkout from cart page 
	And confirm popup mask present 
	And select create new account from checkout PopUp 
	And assert text of 'createAccountPageText.key' is displayed 
	And assert text of 'remeberMeText.key' is displayed 

		@checkout
Scenario: Checkout tests - Guest to Sign in - Populate Cart proceed to checkout, then sign in from basket page - Paypal path 
	And users clicks on the 'viewBasketText.key' text link 
	And user selects proceed to checkout from cart page 
	And login with valid details 
	And click on shipping method
	And Check alternative payment order option 
	And Check paypal option 
	And tick agree to terms and conditions 
	And select place order 
	And click on Continue on Paypal button on Checkout page 
	And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key' 
	And paypal page - click continue 
	And grab and output Order number 
	And assert text of 'ThankForPurchase.key' is displayed 
	
