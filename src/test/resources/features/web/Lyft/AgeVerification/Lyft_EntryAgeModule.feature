# @LyftRegression @LyftSmoke @LyftLive
#Needs to be corrected and update steps due to new cookies banner from OT
Feature: 14782 BAT Age module feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from new cookie consent popup
    And assert entry age module is presented to user

  @LyftRegression2
  Scenario: user selects not over 18 age confirmation
    And user selects not over 18 age confirmation option, confirm text and google redirection

  #@LyftRegression2 (already cover in below scenario)
  Scenario: user selects over 18 age confirmation
    And user selects over 18 age confirmation option
    And age confirmation box is no longer displayed

  @LyftRegression2
  Scenario: Pre site landing page - user should not be able to view any content of site until confirming their age
    Then assert logo is not displayed
    When user selects over 18 age confirmation option
    Then age confirmation box is no longer displayed
    And logo is displayed

