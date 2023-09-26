## Navigation to registration page
## invalid registration details and validation msg's
## Valid registration
## Registration validation errors
@LyftRegression2
Feature: Registration form

	Background: Navigate to BAT Home Page
		Given user navigates to BAT home page
		And select allow all from cookie popup and select over 18 age confirmation option
		And user clicks the person icon -lyft
		And user clicks the registration button

	Scenario: Registration page - ensure rememberMe element is present
		Then assert text of 'remeberMeText.key' is displayed

	@LyftSmoke
	Scenario: Registration of new user
		And populate Personal Information - first and last name with randomly generated data
		And tick signup for newsLetter tick box
		And populate DOB field with 'ValidDOB.key'
		And populate gender field with 'gender.key'
		And populate address fields
		And add BankID for user-Lyft
		And populate signin fields
		And user selects the create an account button
		Then assert text of 'successRegistraionMsg.key' is displayed
		And user should receive a email with "welcomeEmailContentTxt.key" to registered email
		When select the "welcomeEmailContentTxt.key" email
		And the links on the email will take user back to website
		#And proceed to delete customer account
		#And assert text of 'DeleteSuccessMsg.key' is displayed

	Scenario: Registration of underage user
		And populate Personal Information - first and last name with randomly generated data
		And tick signup for newsLetter tick box
		And populate DOB field with 'UnderAgeDOB'
		And populate gender field with 'gender.key'
		And populate address fields
		And add BankID for user-Lyft
		And populate signin fields
		And user selects the create an account button
		Then assert text of 'underAgeErrorMsg.key' is displayed
	
#Scenario: Registration attempt without Ts and Cs checkbox ticked 
#	And populate Personal Information - first and last name with randomly generated data 
#	And tick signup for newsLetter tick box 
#	And populate DOB field with 'validDOB.key' 
#	And populate gender field with 'gender.key' 
#	And populate address fields 
#	And add BankID for user-Lyft
#	And populate signin fields without selecting Ts and Cs 
#	And user selects the create an account button
#	Then assert text of 'updateDetailsSucessMsg.key' is not displayed

	Scenario: Person Data verification after register new user
		When create a new account
		And user clicks on personal data link
		Then assert all elements are displayed

	@LyftRegression2
	Scenario: Delete user account
		When create a new account via api and log in with the account
		And fetch Email Address of the logged-in user
		And user click on Delete my account link
		Then Delete my account page is up
		And Without clicking checkbox click on Delete button verify error message
		Then click checkbox and delete account verify message
		And user clicks the person icon -lyft
		Then login with deleted user detail
		When create a new account with deleted user details
