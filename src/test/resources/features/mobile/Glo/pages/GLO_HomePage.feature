Feature: Glo HomePage - Mobile

	Background: Navigate to Glo Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option

	@RegGloIT_Mobile
	Scenario: Glo Home Page-Ensure all expected element are present
		When user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
		And user closes the RDB pop-up banner if present
		And logo is displayed
		And GloIt PersonIcon is displayed
		And GloIt miniCart is displayed