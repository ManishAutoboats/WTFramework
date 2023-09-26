@api @yourChoice
Feature: Create customer reset password

  Scenario: Create customer reset password by valid customer token
    When reset customer password by valid customer token
    Then assert response status code is 200
    And reset customer password response should return true