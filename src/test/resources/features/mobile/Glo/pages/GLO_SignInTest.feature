Feature: Glo LoginPage - Mobile

  Background: Navigate to Glo Home Page
    Given user navigates to BAT home page
    And turn off Eyes
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And recover Eyes status

  Scenario: Glo Login page - Ensure all expected elements are present
    And GloIt Login Heading assert text of 'loginpageHeading.key' is displayed
    And GloIt email input box displayed and enabled
    And GloIt Login password input box displayed and enabled
    And GloIt forgot your password link displayed
    And GloIt Login button is displayed
    And Register button is displayed
