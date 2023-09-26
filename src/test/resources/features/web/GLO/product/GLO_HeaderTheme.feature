Feature: 16513 BAT header Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloKzRegression @gloPlRegression @gloItRegression
  Scenario: Browse to main home page and assert header elements displayed as expected
    Given Glo user clicks on PersonIcon and Navigate to the Login Page
    And user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    Then logo is displayed
    And header class is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed