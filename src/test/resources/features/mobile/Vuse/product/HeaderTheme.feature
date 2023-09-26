Feature: BAT header Feature Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Browse to main home page and assert header elements displayed as expected
    And logo is displayed
    And header class is displayed
    And person icon is displayed
    And favorite icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert text 'myBasketText.key' on basket icon
