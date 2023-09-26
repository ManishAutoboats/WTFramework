Feature: BAT Device Registration feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@VuseITReg(Feature will go live is disable mode and renabled after EM approval. User story#- 491829)
  Scenario: Device registration activation guest user
    And user clicks the person icon
    And user clicks on 'deviceRegistrationLink.key' link from Person Menu
    And assert that page title is 'loginPageTitle.Key'

  #@VuseITReg(Feature will go live is disable mode and renabled after EM approval. User story#- 491829)
  Scenario: Device registration activation logged in user
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on 'deviceRegistrationLink.key' link from Person Menu
    And assert the content of the page is displayed
    And assert that page title is 'deviceRegistrationPageTitle.key'
    And enter invalid device number and assert the error message