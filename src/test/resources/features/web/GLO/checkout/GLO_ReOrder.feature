##This is the Reorder feature file
## Account Order History
## Reorder functionality
#@gloDeRegression - Commenting regression tab because functionality of re-order has been disabled for now
Feature: 101372 - BAT reorder feature 
Background: Navigate to BAT Home page 
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And Glo user clicks on PersonIcon and Navigate to the Login Page
	When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key' 
	
Scenario: 101372 - Quick Order - reorder from order history page 
	Then user is successfully logged in 
	And users clicks on the 'recentlyOrderedText.key' text link 
	And url contains 'recentOrderUrlContains.key' 
	And assert My Orders Table is displayed 
	And users clicks on the 'viewOrderLinkText.key' text link 
	And glo user click on a re-order link 
	And url contains 'viewOrderURLcontains.key' 
	
Scenario: 101372 - Quick Order - reorder from account page
	And glo user click on a re-order link 
	And url contains 'viewOrderURLcontains.key'