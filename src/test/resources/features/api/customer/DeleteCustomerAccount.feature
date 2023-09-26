@api
Feature: Delete Customer account

  Scenario: Delete customer account
    When I create a customer account
    Then Customer account response status code is 200
    And Verify customer account is created with the given details
    When I delete customer details for the given customer id
    Then Customer account response status code is 200
    And Verify the given customer account is deleted

  Scenario: Delete customer account with invalid details
    When I delete a customer account with invalid 12345657
    Then Customer account response status code is 404
    And Customer account error message is No such entity with %fieldName = %fieldValue


