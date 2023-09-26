@api @yourChoice
Feature: Create your choice token

  Scenario: Create your choice token for a given email and password
    When create your choice token via api
    Then assert response status code is 200
    And create customer token response should return token