@LyftRegression2 @LyftLive
Feature: BAT - Lyft Live Chat

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Live Chat visibility and pop up
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears