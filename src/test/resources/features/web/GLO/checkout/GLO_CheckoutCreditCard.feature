Feature: Glo checkout feature - Credit Card
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
		And empty basket


	@gloDeSmoke @gloDeRegression @gloItSmoke @gloItRegression
	Scenario: Glo Checkout tests - Credit Card
		And Glo user clicks on buy button link and click on submenu
		And click on first result
		And select product strength or colour
		Then click add to cart button js
		And click on basket icon
		And click on proceed to checkout button on mini cart
		And Payment page details confirmed
		And user choose shipping method
		And user select Card Payment option
		And user select credit card as payment option
		And user select the Visa card as type
		And enter visa card details
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

	@gloPlRegression
	Scenario Outline: Glo PL Checkout - PayU different Payment
		And user click on search icon and enter product 'ProductName.key' in the search bar
		And user select product add to basket and view basket
		And use add free gift module according to device
		And click on proceed to checkout button
		And Customer makes payu payment with "<Payment Type>"
		Then Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		Examples:
			| Payment Type |
			| blik         |
			| mbank        |
			| visa         |

	@gloItSmoke @gloItCheckout @gloItRegression
	Scenario: 258528 Glo Checkout tests - Credit Card with resale code field validated
		And Glo user clicks on buy button link and click on submenu
		And click on first result
		And select product strength or colour
		Then click add to cart button js
		And click on basket icon
		And click on proceed to checkout button on mini cart
		And Payment page details confirmed
		And confirm that the resale code field is present
		And enter a value for resale code
		And user choose shipping method
		And user select Card Payment option
		And user select credit card as payment option
		And user select the Visa card as type
		And enter visa card details
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed

	@gloItRegression
	Scenario: Order Status tests -mastercard - email confirmation
		And Customer makes a home delivery purchase with "mastercard"
		Then assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		Then grab and output Order number
		Then user should receive a order confirmation email with order number
		And the links on the email will take user back to website