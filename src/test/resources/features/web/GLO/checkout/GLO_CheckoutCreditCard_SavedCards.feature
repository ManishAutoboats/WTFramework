Feature: Glo checkout feature - Saved Card
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page

	@checkout @gloItSmoke @gloItCheckout @gloItRegression2
	Scenario: Glo Checkout tests - GLO IT Saved Cards
		And user clicks the registration button
		And Glo user enters all the required data for a new user registration
		Then user selects the create an account button
		And assert text of 'registrationSuccessMsg.key' is displayed
		And Glo user clicks on Buy Link and add the product to mini cart
		And Payment page details confirmed
		And user select Card Payment option
		And Credit Card - MasterCard option and press next
		And enter mastercard details
		And click Save This Card checkbox
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		And users clicks on order number to be taken to order view order page
		And glo user click on a re-order link
		And user selects proceed to checkout from cart page
		And Payment page details confirmed
		And user select Card Payment option
		And user select credit card as payment option
		And user click on saved card type option checkbox
		And select users first saved card checkbox
		And enter saved card CVV number
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		And user deletes the account and verify deletion success page

	@gloDeSmoke @gloDeRegression
	Scenario: Glo Checkout tests - GLO DE Saved Cards
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
		And users clicks on the 'savedCardText.key' text link
		When Glo user deletes all the saved cards from My Saved Cards list
		And Glo user clicks on Buy Link and add the product to mini cart
		And Payment page details confirmed
		And user select Card Payment option
		And Credit Card - MasterCard option and press next
		And enter mastercard details
		And click Save This Card checkbox
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		And Glo user clicks on Buy Link and add the product to mini cart
		And Payment page details confirmed
		And user select Card Payment option
		And user select credit card as payment option
		And user click on saved card type option checkbox
		And select users first saved card checkbox
		And enter saved card CVV number
		Then select place order from Card
		And grab and output Order number
		And Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		And users clicks on the 'savedCardText.key' text link
		When Glo user deletes all the saved cards from My Saved Cards list
		And assert text of 'noSavedCardsText.key' is displayed