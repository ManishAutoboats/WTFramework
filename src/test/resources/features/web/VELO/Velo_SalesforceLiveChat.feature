Feature: BAT Salesforce Live Chat

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @veloDEReg
  Scenario: Verify Chat widget when Agents online or offline
    Then assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user click on PersonIcon and Navigate to the Login Page
    And user clicks the registration button
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears