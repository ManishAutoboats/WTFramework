@api @yourChoice
Feature: Update Customer account

  Background:
    When I create a customer account
    Then Customer account response status code is 200
    And Verify customer account is created with the given details

  Scenario: Update customer account details
    When I update customer details for the given customer id
    Then Customer account response status code is 200
    And Verify customer account is created with the given details

  Scenario: Update customer account details with customer id value zero
    When I update customer details for the customer id 0
    Then Customer account response status code is 400
    And Customer account error message is Provided customer ID "%customer_id" isn't related to current customer address.

  Scenario Outline: Update customer account with invalid details
    When I update a customer account with invalid <field>
    Then Customer account response status code is <status>
    And Customer account error message is <error_msg>
    Examples:
      | field                 | status | error_msg                                                                       |
      | invalidBodyCustomerId | 404    | Nie znaleziono %fieldName = %fieldValue                                |
      | emptyEmail            | 400    | The customer email is missing. Enter and try again.                             |
      | existingEmail         | 400    | A customer with the same email address already exists in an associated website. |
      | emptyLastname         | 400    | "Nazwisko" jest polem wymaganym.                                              |



