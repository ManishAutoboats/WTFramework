Feature: BAT Age module feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from new cookie consent popup
    And assert entry age module is presented to user


  Scenario: Pre site landing page - user should not be able to view any content of site until confirming their age
    Then assert logo is not displayed
    When user selects over 18 age confirmation option
    Then age confirmation box is no longer displayed
    And logo is displayed
