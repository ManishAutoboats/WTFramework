# Cookie policy appears on home page
# cookie policy continues to be displayed on other pages until accepted
# cookie policy can be accepted
# cookie policy details can be viewed
#@LyftRegression @LyftLive
	#ToDO
Feature: 9242 BAT Cookie feature

	Background: Navigate to BAT Home page and confirm over 18
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

		And resize browser screen - full screen

	Scenario: ensure cookie policy present
#When user clicks the person icon
		Then cookies popup is displayed to user
	
#Scenario: ensure cookie policy text is as expected 
##When user clicks the person icon
#	And assert cookie text is present as expected

	Scenario: ensure cookie policy present, click accept
#When user clicks the person icon
		Then cookies popup is displayed to user
