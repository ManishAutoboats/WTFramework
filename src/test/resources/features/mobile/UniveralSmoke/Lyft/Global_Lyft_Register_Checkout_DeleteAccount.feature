Feature: Universal smoke flow - lyft
	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And verify the order of cookiebanner
		And select allow all from cookie popup and select over 18 age confirmation option

	@UniversalSmokeLyft_mobile
	Scenario: Lyft - Smoke Flow - Registration - Card Payment - Delete Account
		When create a new account
		And Customer makes a home delivery purchase with "mastercard"
		Then assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
		When user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page
#		Then account is deleted successfully on my account Page

