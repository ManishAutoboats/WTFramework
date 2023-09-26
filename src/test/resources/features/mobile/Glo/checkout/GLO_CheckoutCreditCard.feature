Feature: Glo checkout feature - Credit Card - Mobile
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

	@RegGloIT_Mobile
	Scenario: Glo Checkout tests - Credit Card
		And empty basket
		And Glo user clicks on buy button link and click on submenu
		And Customer makes a home delivery purchase with "mastercard"
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed