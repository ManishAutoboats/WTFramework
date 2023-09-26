Feature: PDP Page - Mobile

	Background: Navigate to BAT Home page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

	@RegGloIT_Mobile
	Scenario: logged-in user Ratings on PDP GLO IT
		Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And user click on 'device' menu navigation
		And click first result
		And assert review rating is present
		When user click on add rating CTA
		And user fill the rating form and submit
		Then opinion successfully send

	@RegGloIT_Mobile
	Scenario: Guest - PDP elements present as expected
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And Glo user clicks on buy button link and click on submenu
		And click first result
		Then assert PDP product title for Glo product