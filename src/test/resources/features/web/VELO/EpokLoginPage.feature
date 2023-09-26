@epokRegression @liveEpok @epoklogin @epokLive
Feature: EPOK LoginPage feature
Background: Navigate to EPOK Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option
  And user click on PersonIcon and Navigate to the Login Page
    
  @epokuat
  Scenario: EPOK Login page - Ensure all expected elements are present
    And EPOK Login Heading assert text of 'loginpageHeading.key' is displayed
    And Epok email input box displayed and enabled
    And Epok Login password input box displayed and enabled
    And Epok remember Check box is displayed and enabled and unchecked by default
    And Epok forgot your password link displayed
    And Login button is displayed
    And Register button is displayed
    And Epok InfoText assert text of 'loginpageFooterHeading.key' is displayed


  @epokuat
Scenario: Incorrect Sign-in - No Password provided
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password ''
    Then  Epok password is required error message pops up

  @epokuat
  Scenario: Incorrect Sign-in - No UserName or Password provided
    When Epok user enters sign in details, with username of '' and password ''
    Then Epok password is required error message pops up
    
 @epokuat
  Scenario: Incorrect Sign-in - Invalid e-mail
    When Epok user enters sign in details, with username of 'loginInvalidEmail.key' and password 'loginInvalidPassword.key'
    Then Epok error message of 'loginInvalidEmailErrorMsg.key' is displayed to user

  @epokuat
  Scenario: Epok Incorrect Sign-in - Incorrect login details
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginInvalidPassword.key'
    Then Epok invalid email message of 'loginInvalidPassowedErrorMsg.key' is displayed to user

   @epokuat
  Scenario: Epok Forgotten password
    When Epok user clicks the forgotten password link
    Then Epok user is taken to forgot your password page

  @epokuat  @epokSmoke @epokLive @epokLive
  Scenario: Correct Sign-in
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    Then Epok user is successfully logged in

    