@api @yourChoice
Feature: Get customer by token

  Scenario: Get customer by token
    When get customer by token
    Then assert response status code is 200
    And Verify appropriate customer details are returned