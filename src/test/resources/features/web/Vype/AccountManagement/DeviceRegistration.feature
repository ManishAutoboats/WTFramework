#This is the PDP feature file
  ##Testing
    ## PDP
    ## Attributes
    ## Theme
#@regression
@smokeLite @deviceRegistration @IEReg
Feature: BAT Device Registration feature

  Background: Navigate to BAT Device registration page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

  Scenario: Device Registration page
    And users clicks on the 'DeviceRegistrationText.key' text link
    And url contains 'deviceRegistration.key'
    And assert PDF link is correct
    And populate device registration with valid details and submit
    And assert text of 'DeviceRegistrationSuccessMsg.key' is displayed





