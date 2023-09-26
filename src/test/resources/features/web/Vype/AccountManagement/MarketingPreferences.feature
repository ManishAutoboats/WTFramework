# REMOVED FROM PROJECT
Feature: BAT marketing preferences feature
  Background: Navigate to BAT Home Page
    Given user navigates to privacy policy page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks the registration button

  @sprint2 @18269
  Scenario: Registrater, nav to my account, select and confirm marketing preferences
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with '08/05/1977'
    And populate gender field with 'Male'
    And populate address fields
    And populate signin fields
    And tick signup for newsLetter tick box
    Then user selects the create an account button
    And assert text of 'updateDetailsSucessMsg.key' is displayed
    And navigate to Marketing Preferences page
    And assert all marketing preferences displayed as expected
    And select all remaining marketing preferences
    And click Save
    Then assert all expected tickboxes have been successfully selected