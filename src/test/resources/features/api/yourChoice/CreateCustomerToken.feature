@api @yourChoice
Feature: Create customer token

  Scenario: Create customer token for a given email and password
    When create customer token via api
    Then assert response status code is 200
    And create customer token response should return token