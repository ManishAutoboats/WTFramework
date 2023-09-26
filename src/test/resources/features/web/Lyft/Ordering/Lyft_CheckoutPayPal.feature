##This is the Checkout feature file
## PayPal
## Success
@LyftRegression @checkout
Feature: BAT checkout feature - PayPal 
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
	And user click on search icon and submits the following search term 'searchTermdi.key'
	And click first result 
	And select product strength or colour
	And click add to cart button
	And click on basket icon 
	And user clicks on checkout button 
	Then Payment page details confirmed
	
@LyftSmoke 
Scenario: Checkout tests - PayPal path 
	And selectable shipping options displayed
    And enter select package shop details
	And Check alternative payment order option 
	And Check paypal option 
	And tick agree to terms and conditions 
	And select place order 
	And click on Continue on Paypal button on Checkout page 
	And user sign-in into Paypal account with username 'PaypalValidEmail.key' and password 'PaypalValidPassword.key' 
	And paypal page - click continue 
	And grab and output Order number
	Then assert text of 'ThankForPurchase.key' is displayed