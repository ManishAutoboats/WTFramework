@api @yourChoice
Feature: Get customer password token

  Scenario: Get customer password token by given email
    When get customer password token by given email
    Then assert response status code is 200
    And get customer password token response should return token