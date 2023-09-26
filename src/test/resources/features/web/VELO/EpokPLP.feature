@epokLive @epokRegression @epokSmoke 
Feature: Epok PLP Page
Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And user click on PersonIcon and Navigate to the Login Page 
	When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key' 
	And Epok empty basket 
	When user click on humberger menu 
	When user clicks on product menu

Scenario: PLP page Verification
	And get all lists from page
	Then user Clicks on add to cart button
	Then validate Success Message of add to cart product 
	And user click on cart icon and Navigate to cart Page 
	And Go to checkout page 
	And Epok Payment page details confirmed 