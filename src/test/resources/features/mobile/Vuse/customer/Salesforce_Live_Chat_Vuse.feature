Feature: BAT Salesforce Live Chat - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - Live Chat visibility on Home Page
    Then assert CTA for Live chat icon at the bottom right of the page
    When user clicks on the Live chat icon at the bottom right of the page mobile
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    And user clicks the registration button
    When user clicks on the Live chat icon at the bottom right of the page mobile
    Then assert salesforce Chat pop-up appears