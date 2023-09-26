# Cookie policy appears on home page
  # cookie policy continues to be displayed on other pages until accepted
  # cookie policy can be accepted
  # cookie policy details can be viewed
#@sprint1 @cookie @regression @mobile @IEReg
Feature: BAT Mobile Cookie feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks the mobile person icon

  Scenario: ensure cookie policy present on mobile
    And cookies popup is displayed to user
    Then resize browser screen - full screen

  Scenario: ensure cookie policy text is as expected
    And assert cookie text is present as expected
    Then resize browser screen - full screen

  Scenario: ensure cookie policy present, click accept
    And cookies popup is displayed to user
    Then resize browser screen - full screen






