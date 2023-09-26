@epokCheckout @epokRegression @epokSmoke @checkout
Feature: Epok Order status feature 
Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And user click on PersonIcon and Navigate to the Login Page 
	When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key' 
	And Epok empty basket 
	When user click on humberger menu
	When user clicks on product menu
	And get all lists from page
	Then user Clicks on add to cart button
	Then validate Success Message of add to cart product 
	And user click on cart icon and Navigate to cart Page 
	And Go to checkout page 
	And Epok Payment page details confirmed 
	
@orderStatus 
Scenario: Order Status tests - Debit Card Payment - Pending Payment section 
	And Epok user select Card Payment option 
	And Epok user select credit card as payment option 
	And user select the Visa card as type 
	And user fills Master or Visa card information 
	And Epok select agree to terms and conditions for card payment 
	Then Epok select place order from Card 
	And Epok grab and output Order number 
	And Epok assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed 
#	And user click on PersonIcon and Navigate to the Login Page 
#	And users clicks on the my account menu dropdown 
#	And user select "textOrder.key" my orders in my account menu 
#	And Epok assert order number is displayed Previous orders on page 
#	And Epok grab contents of matched row and assert order status 
	
