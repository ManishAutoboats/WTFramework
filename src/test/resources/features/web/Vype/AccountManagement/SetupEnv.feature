#This is the registration feature file
  ##Testing
    ## Navigation to registration page
      ## invalid registration details and validation msg's
    ## Valid registration
    ## Registration validation errors
  @setUp
Feature: BUG - 21190 9991 BAT registration feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon

    And user clicks the registration button

  @setUp
  Scenario: Registration of new user
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with 'ValidDOB.key'
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields with username of 'loginValidEmail.key'
    And tick signup for newsLetter tick box
    Then user selects the create an account button
    And assert text of 'updateDetailsSucessMsg.key' is displayed

  @setUp
  Scenario: Registration of new user original username
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with '08/05/1977'
    And populate gender field with 'Male'
    And populate address fields
    And populate signin fields with username of '2atjhu@mailinator.com'
    And tick signup for newsLetter tick box
    Then user selects the create an account button
    And assert text of 'Thank you for registering with Vype.' is displayed





