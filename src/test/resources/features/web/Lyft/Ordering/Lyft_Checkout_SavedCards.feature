##This is the Checkout feature file
## Credit Cards - Order Status after successful purchase
@LyftRegression2 @checkout
Feature: BAT Order with Saved Cards feature
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And user clicks the person icon -lyft
		And user clicks the registration button
		
	@LyftSmoke
	Scenario: Order Status tests - Saved Cards - Complete Flow
		And populate Personal Information - first and last name with randomly generated data
		And tick signup for newsLetter tick box
		And populate DOB field with 'ValidDOB.key'
		And populate gender field with 'gender.key'
		And populate address and Sign in fields and click on Create Account button
		Then assert text of 'successRegistraionMsg.key' is displayed
		And Search for a product 'searchTermdi.key' , and add to cart from PDP page
		And click on basket icon
		And user clicks on checkout button
		Then Payment page details confirmed
		And enter Package shop details and select Credit Card - VISA order option
		And enter visa card details
		And click Save This Card checkbox
		And tick agree to terms and conditions and select place order
		Then assert print receipt link is present
		Then assert text of 'ThankForPurchase.key' is displayed
		And grab and output Order number
		And users clicks on order number to be taken to order view order page
    	And click on a re-order link -Lyft
    	And user selects proceed to checkout from cart page
		Then Payment page details confirmed
		And enter Package shop details and select Credit Card - VISA order option
		And click use saved card
		And tick agree to terms and conditions and select place order
		Then assert print receipt link is present
		Then assert text of 'ThankForPurchase.key' is displayed
		And user clicks the person icon -lyft
		And user deletes the account and verify deletion success page