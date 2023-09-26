Feature: BAT Device Registration feature - Mobile

  Background: Navigate to BAT Device registration page
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
	And Glo user clicks on PersonIcon and Navigate to the Login Page
	When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

  @RegGloIT_Mobile
  Scenario: Device Registration page
    And users clicks on the 'DeviceRegistrationText.key' text link
    And url contains 'deviceRegistration.key'