Feature: Glo JP checkout feature - Login_checkout
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page

	@UniversalSmokeGloJP
	Scenario: Checkout test
		Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And user clicks on Close option
		And user navigates to Cart page and empty basket
		And user clicks on the tile and navigates to that page
			| accessories.key |
		And user navigates to cart page
		And gloJp user navigates to checkout page
		And user selects fluid delivery address
		And user selects courier delivery option
		And user selects delivery date and time
		And user clicks on COD payment method
		And tick agree to terms and conditions
		And user clicks on place order button
		And user is redirected order success page
		Then assert on Order Confirmation page 'OrderConfirmation.key' is displayed
