#This is the login feature file
##Testing
## Invalid sign-in & error generated
## invalid email
## missing password
## incorrect password
## Valid sign-in
## Nav to Registration page
## Nav to Forgotten password page
@LyftRegression @LyftDKReg
Feature: BAT Login feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft

  Scenario:
  21654 Sign in Page - confirm expected elements are present on sign in page
    Then assert text of 'loginpageTopTextHeading.key' is displayed
    Then assert text of 'loginpageBottomTextHeading.key' is displayed
    And email input box displayed and enabled
    And password input box displayed and enabled
    And remember me box is displayed and enabled and checked by default
    And forgot your password button displayed
    And create an account button is displayed

	#ToDo
  Scenario: 21654 Forgotten password
    When user clicks the forgotten password link
	    #Then user is taken to forgot your password page

  @LyftLive
  Scenario: 9854 Password Manager Support - Remember me
    Then assert remember me tick box is present
    And user clicks the registration button
    Then assert remember me tick box is present

	#@LyftLive @LyftDKLive
  @LyftRegression
  Scenario Outline: 21654 Incorrect Sign-in - Validation check
    And user signs in to the site custom details '<userName>' '<password>'
    Then assert text of '<expectedMessage>' is displayed
    Examples:
      |userName					|password						|expectedMessage						|
			#|loginInvalidEmail.key		|loginNoPassword.key			|expectedRequiredFieldErrorMessage.key	|
			#|loginInvalidEmail.key		|loginNoPassword.key			|loginInvalidEmailErrorMsg.key			|
      |loginNonExistingEmail.key	|loginNonExistingPassword.key	|incorrectSignInMsg.key					|
			#|							|								|expectedRequiredFieldErrorMessage.key	|

  @LyftSmoke @LyftLive @LyftDKSmoke @LyftDKLive
  Scenario: 21654 Correct Sign-in
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then user is successfully logged in

  @LyftRegression @LyftDKReg
  Scenario: Forgot password and remember me check box
    And create a new account via api
    When lyft clicks on forgot password link
    Then lyft should be redirected to forgotYourPasswordTitle.key page
    And lyft email input box displayed and enabled
    When lyft enters email address registered via api in the email box
    And lyft click on submit button
    Then lyft should see a text of receiveLinkToResetPassword.key
    And lyft should receive a email to registered email with reset password link
    When lyft resets the password
    Then lyft should be redirected to loginPageTitle.key page
    And lyft should see a text of passwordUpdateSuccess.key
    And remember me box is displayed and enabled and checked by default
    And delete the account via api