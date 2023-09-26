Feature: 13848 BAT Account management - My Account dash board tests - Mobile

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

	@RegGloIT_Mobile
	Scenario: Refer A Friend CMS Page
	    When users clicks on the 'PortaUnAmicoLinktext.key' text link
		And  select a bring friend way if it is presence
		Then url contains 'PortaUnAmicoUrl.key'