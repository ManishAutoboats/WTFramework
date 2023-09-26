Feature: Glo checkout feature - world pay
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'


	@gloDeRegression
	Scenario: Glo Checkout tests - world pay
		And empty basket
		And Glo user clicks on buy button link and click on submenu
		And click on first result
		And select product strength or colour
		Then click add to cart button js
		And click on basket icon
		And click on proceed to checkout button on mini cart
		And Payment page details confirmed
		And click on PayPal Payment Option
		Then select place order from Card
		And assert world pay page is displayed