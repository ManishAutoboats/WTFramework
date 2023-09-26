Feature: Glo checkout feature - Debit Card
Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option

@DebitCard @gloItSmoke @gloItCheckout @gloItRegression @checkout
Scenario: Glo Checkout tests - DebitCard
	And Glo user clicks on PersonIcon and Navigate to the Login Page
	When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
	And empty basket
	And Glo user clicks on buy button link and click on submenu
	And click on first result
	And select product strength or colour
	Then click add to cart button js
	And click on basket icon
	And click on proceed to checkout button on mini cart
	And Payment page details confirmed
	And user select Card Payment option
	And user select debit card as payment option
	And user select the Visa card as type
	And enter visa card details
	Then select place order from Card
	And grab and output Order number
	And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

  @gloItRegression2
  Scenario: Glo Checkout tests - verify order confirmation email
    When create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user login with credentials registered via api
    And Glo user clicks on Buy Link and add the product to mini cart
    And Payment page details confirmed
    And user select Card Payment option
    And user select debit card as payment option
    And user select the Visa card as type
    And enter visa card details
    Then select place order from Card
    And grab and output Order number
    And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
    And user should receive a order confirmation email with order number

	@gloJPReg
	Scenario Outline: Verify Card payment with discount and Terms and conditions consent on checkout page
		Then user signs in to the site credentials details 'loginValidEmail2.key' 'loginValidPassword.key'
		And user clicks on Close option
		And user navigates to Cart page and empty basket
		And user clicks on the tile and navigates to that page
			| accessories.key |
		And user navigates to cart page
		And gloJp user navigates to checkout page
		And assert that page title is 'checkout.key'
		And user selects verified delivery address
		And user selects courier delivery option
		And user clicks on plus icon and product list is displayed
		And user clicks on Card payment method
		And user selects delivery date and time
		And enters Credit card details '<cardNumber>' '<expiryMonth>' '<expiryYear>' '<cvv>'
		And tick agree to terms and conditions for card payment
		And verify Card Place Order button is enabled
		And user clicks on Card place order button
		And user is redirected order success page
		And verify order confirmation email is received
		Examples:
			| cardNumber          | expiryMonth | expiryYear | cvv                   |
			| validCardNumber.key | Month.key   | Year.key   | validSecurityCode.key |
