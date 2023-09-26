# Created by Nitesh Goyal at 02/01/20

Feature: Glo LoginPage

  Background: Navigate to Glo Home Page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And recover Eyes status

  @gloItRegression2 @gloDeRegression2 @gloItLive
  Scenario: Glo Login page - Ensure all expected elements are present
    And GloIt Login Heading assert text of 'loginpageHeading.key' is displayed
    And GloIt email input box displayed and enabled
    And GloIt Login password input box displayed and enabled
	#	And GloIt remember Check box is displayed and enabled and unchecked by default
    And GloIt forgot your password link displayed
    And GloIt Login button is displayed
    And Register button is displayed
	#	And GloIt InfoText assert text of 'loginpageFooterHeading.key' is displayed

  @gloPlRegression @gloPlLive
  Scenario: Glo Login page - Ensure all expected elements are present
    And GloIt email input box displayed and enabled
    And GloIt Login password input box displayed and enabled
    And GloIt forgot your password link displayed
    And GloIt Login button is displayed
    And Register button is displayed

  @eyesGloItRegression @eyesGloDeRegression
  @gloKzRegression @gloItRegression2 @gloDeRegression @gloItLive @gloPlRegression @gloPlLive
  Scenario: Incorrect Sign-in - No Password provided
    When GloIt user enters sign in details, with username of 'loginValidEmail.key' and password ''
    Then  GloIt password is required error message pops up
    And eyes check entire page "Login Error - Invalid Email and No Password"

  @eyesGloItRegression @eyesGloDeRegression
  @gloKzRegression @gloItRegression2 @gloDeRegression @gloItLive @gloPlRegression @gloPlLive
  Scenario: Incorrect Sign-in - No UserName or Password provided
    When GloIt user enters sign in details, with username of '' and password ''
    Then GloIt password is required error message pops up
    And eyes check entire page "Login Error - No Email and No Password"

  @eyesGloItRegression @eyesGloDeRegression
  @gloItRegression @gloDeRegression @gloItLive @gloPlRegression @gloPlLive
  Scenario: Incorrect Sign-in - Invalid e-mail
    When GloIt user enters sign in details, with username of 'loginInvalidEmail.key' and password 'loginInvalidPassword.key'
    Then GloIt error message of 'loginInvalidEmailErrorMsg.key' is displayed to user
    And eyes check entire page "Login Error - Invalid Email and Valid Password"

  @eyesGloItRegression @eyesGloDeRegression @gloPlRegression @gloPlLive
  @gloDeSmoke @gloItSmoke @gloKzRegression @gloItRegression @gloDeRegression @gloItLive
  Scenario: Glo Incorrect Sign-in - Incorrect login details
    When GloIt user enters sign in details, with username of 'loginValidEmail.key' and password 'loginInvalidPassword.key'
    And assert text of 'loginInvalidPassowedErrorMsg.key' is displayed
    And eyes check entire page "Login Error - Valid Email and Invalid Password"
	#Then GloIt invalid email message of 'loginInvalidPassowedErrorMsg.key' is displayed to user

	#   @gloituat
	#  Scenario: GloIt Forgotten password
	#    When GloIt user clicks the forgotten password link
	#    Then user is taken to forgot your password page
  @gloPlRegression @gloPlLive
  @gloItLive @gloItSmoke @gloDeSmoke @gloDeLive @gloKzLive @gloItRegression @gloDeRegression @gloItLive
  Scenario: Correct Sign-in
    When GloIt user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    Then Glo user is successfully logged in

  #@gloDeRegression because of  Defect# 502038
  Scenario: Forgot password and remember me check box
    And create a new account via api
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And users clicks on the 'logoutText.key' text link
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When Glo clicks on forgot password link
    Then Glo should be redirected to forgotYourPasswordTitle.key page
    And Glo email input box displayed and enabled
    And Glo enters email address registered via api in the email box
    And Glo click on submit button
    Then Glo should see a text of receiveLinkToResetPassword.key
    And Glo should receive a email to registered email with reset password link
    When Glo resets the password
    Then Glo should be redirected to loginPageTitle.key page
    And Glo should see a text of passwordUpdateSuccess.key
    And remember me box is displayed and enabled and checked by default
    And delete the account via api

  @gloPlRegression
  Scenario: Forgot password and delete the account
    And create a new account via api
    And user hovers over the person icon
    And users clicks on the 'logoutText.key' text link
    And user wait for home page to load
    And Glo user clicks on PersonIcon and Navigate to the Login Page after logout
    When Glo clicks on forgot password link
    Then Glo should be redirected to forgotYourPasswordTitle.key page
    And Glo email input box displayed and enabled
    And Glo enters email address registered via api in the email box
    And Glo click on submit button
    Then Glo should see a text of receiveLinkToResetPassword.key
    And Glo should receive a email to registered email with reset password link
    When Glo resets the password
    Then Glo should be redirected to loginPageTitle.key page
    And Glo should see a text of passwordUpdateSuccess.key
    And delete the account via api

  @gloKzRegression
  Scenario: Forgot password and remember me check box - KZ
    And create a new account via api
    When Glo clicks on forgot password link
    Then Glo should be redirected to forgotYourPasswordTitle.key page
    And Glo email input box displayed and enabled
    And Glo enters email address registered via api in the email box
    And Glo click on submit button
    Then Glo should see a text of receiveLinkToResetPassword.key
    And Glo should receive a email to registered email with reset password link
    When Glo resets the password
    Then Glo should be redirected to loginPageTitle.key page
    And Glo should see a text of passwordUpdateSuccess.key
    And delete the account via api

  @gloKzRegression
	# USER STORY 127463 - kz
  Scenario: Customer name under/next to my account(person) icon
    When user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And user clicks the person icon
    And fetch First and Last Names of the logged-in user

  @gloPlRegression2
  Scenario: Page url after create account
    And create a new account via api
    And user navigate to my account page
    Then user is successfully logged in
