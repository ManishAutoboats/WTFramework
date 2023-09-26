@api @yourChoice
Feature: Update Customer account

  Scenario: Update customer account details by ME
    When get customer details by the customer token
    Then update customer details by ME
    And Customer account response status code is 200
    And Verify customer account is created with the given details