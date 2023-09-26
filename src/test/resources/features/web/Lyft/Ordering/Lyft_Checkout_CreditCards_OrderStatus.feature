##This is the Checkout feature file
## Credit Cards - Order Status after successful purchase
@LyftRegression @checkout @LyftDKReg
Feature: BAT Order status feature
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And user clicks the person icon -lyft
		And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on search icon and submits the following search term 'searchTermdi.key'
		And click first result
		And select product strength or colour
		And click add to cart button
		And click on basket icon
		And user clicks on checkout button
		Then Payment page details confirmed

	@LyftSmoke
	Scenario: Order Status tests - Debit Card Payment - Pending Payment section
		And enter select package shop details
		And click on shipping method
		And Credit Card - VISA order option and press next
		And enter visa card details
		And tick agree to terms and conditions
		And select place order
		And user clicks on Next button to move head and place the order
		Then assert print receipt link is present
		Then assert text of 'ThankForPurchase.key' is displayed
		Then assert text of 'yourOrderNumberText.key' is displayed
		And grab and output Order number
		And user clicks on the 'continue shopping' button
		And user clicks the person icon -lyft
		And user clicks on the recent orders link
		Then assert order number is displayed Previous orders on page