
Feature: Customer Registration - Duplicate Account Checks

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup
		And user selects over 18 age confirmation option

	@VuseDEReg
	Scenario: Duplicate Account Error Validation On Creating a New User with existing account details
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with existing user details
		Then assert registration error message displayed

	@VuseFRReg
	Scenario: Duplicate Account Error Validation On Creating a New User with existing email address
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with duplicate email address
		Then assert duplicate email error message displayed