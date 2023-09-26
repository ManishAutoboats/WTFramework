#ToDo
#This is the customer Account Dashboard feature
#@LyftRegression
Feature: 13848 BAT Account management - My Account dash board tests 

Background: Navigate to BAT Home Page 
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option
	And user clicks the person icon -lyft
	Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
	
Scenario: My Account - Edit Account 
	#Then assert text of 'dashboardEditText.key' is displayed 
	Then user clicks the myAccount edit link - lyft
	And assert first name input is present 
	And assert last name input is present 
	And assert DOB input is present 
	And assert save button is present 
	And assert cancel button is present 
	
Scenario: My Account Dashboard page contents information expected 
	Then assert text of 'myDetailsBlockTextHeading' is displayed 
	Then assert text of 'deliveryAddressBlockTextHeading.key' is displayed 
	Then assert text of 'paymentMethodBlockTextHeading.key' is displayed 
	Then assert text of 'OrderDetailsBlockTextHeading.key' is displayed

	#@LyftRegression2 @LyftLive
	Scenario: My Account Page - Logged-In - assert navigation and change in language on switching language using language selector
		And switch to 'EN' site using language selector
		And select allow all from cookie popup and select over 18 age confirmation option
		And assert navigation to account details page with 'urlInEnglishOnLangSwitch.key' and response status code as 200
		Then assert that page title is 'mytAccountHeaderInEN.key'
		Then assert text is displayed in English after switching language
		And switch to 'SV' site using language selector
		Then assert text is displayed in Swedish after switching language
		Then assert that page title is 'mytAccountHeaderInSwedish.key'

	@LyftRegression
	Scenario: My Account - Signout redirect to homepage
		And users clicks on the 'logoutText.key' text link
		Then assert home page is displayed