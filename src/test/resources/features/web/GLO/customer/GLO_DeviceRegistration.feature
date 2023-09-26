#Device Registration through automation is not possible in glo it at this moment because of limited glo number availability  
#@gloItRegression
Feature: BAT Device Registration feature

Background: Navigate to BAT Device registration page 
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

	And Glo user clicks on PersonIcon and Navigate to the Login Page
	When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

 @gloDeRegression @gloItRegression
 Scenario: Device Registration page
    And users clicks on the 'DeviceRegistrationText.key' text link
    And url contains 'deviceRegistration.key'

  #@gloItRegression(The functionality flow has changed and waiting for EM approval. User story#-517086)
  Scenario: DeviceRegistration Page with mandatory fields
    And users clicks on the 'DeviceRegistrationText.key' text link
    Then the user should see the "*" symbol for the mandatory fields in device registration page
      | postcode          |
      | device            |
      | myGloNumber       |
      | deviceColour      |
      | whereDidYouBuyGlo |

 #@gloItRegression(The functionality flow has changed and waiting for EM approval. User story#-517086)
  Scenario: DeviceRegistrationPage - DeviceColour Options are loaded only after DeviceType Selection
    And users clicks on the 'DeviceRegistrationText.key' text link
    Then Device Colour dropdown options should only be provided after selecting the Device Type

  #@gloItRegression(The functionality flow has changed and waiting for EM approval. User story#-517086)
  Scenario: DeviceRegistrationPage - Display Error Message for invalid My Glo Number
    And users clicks on the 'DeviceRegistrationText.key' text link
    When complete the form with invalid MyGloNumber
    Then assert text of 'devRegDeviceNumberNotFound.key' is displayed

  @gloItRegression
  Scenario: Device registration activation
    And users clicks on the 'DeviceRegistrationText.key' text link
    And assert the content of the page is displayed
    And enter invalid device number and assert the error message