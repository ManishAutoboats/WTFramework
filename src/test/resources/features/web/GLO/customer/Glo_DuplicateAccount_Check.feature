@gloDeRegression
Feature: Customer Registration - Glo Duplicate Account Checks

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup
		And user selects over 18 age confirmation option

	Scenario: Duplicate Account Error Validation On Creating a New User with existing account details
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with existing user details
		Then assert text of 'duplicateAccountErrMessage.key' is displayed

	Scenario: Create New User with Non-Duplicate DOB and Email Address
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with Non-Duplicate DOB and email address
		Then assert text of 'successUserCreationMsg.key' is displayed

	Scenario: Create New User with Non-Duplicate Post Code and Email Address
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with Non-Duplicate Postal Code and Email Address
		Then assert text of 'successUserCreationMsg.key' is displayed

	Scenario: Create New User with Non-Duplicate First Name and Email Address
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with Non-Duplicate First Name and Email Address
		Then assert text of 'successUserCreationMsg.key' is displayed

	Scenario: Create New User with Non-Duplicate Last Name and Email Address
		Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		Then attempt to logout
		When create a new account with Non-Duplicate Last Name and Email Address
		Then assert text of 'successUserCreationMsg.key' is displayed

	@gloKzRegression
	Scenario: Create New Account,delete the account and create new user with deleted details
		When create a new account
		And validation success message displayed
		And Glo user clicks on PersonIcon and Navigate to the Login Page
		Then fetch First and Last Names of the logged-in user
		And fetch Email Address of the logged-in user
		And fetch DOB and Address Details of the logged-in user
		When user navigates to my account page from header
		And user selects deleteMyAccount link on my account Page
		Then account is deleted successfully on my account Page
		And user wait for home page to load
		When create a new account with deleted user details
		Then verify New User's Email and navigate back to Account Page
