@api @MiddlewareToMagento @apiDe @apiSe
Feature: Get Customer Details

  Scenario: Get customer account details with customer id
    When I create a customer account
    Then Customer account response status code is 200
    And Verify customer account is created with the given details
    When I get the customer details for the given customer id
    Then Customer account response status code is 200
    And Verify appropriate customer details are returned

  Scenario: Get customer account details with valid customer details
    When I get the customer details for the given customer details
    Then Customer account response status code is 200
    And Verify appropriate customer details are returned

  Scenario Outline: Get customer account with invalid id
    When I get the customer details for the invalid <customer_id>
    Then Customer account response status code is <status>
    And Customer account error message is <error_msg>
    Examples:
      | customer_id | status | error_msg                                                                             |
      | 0           | 404    | error.noSuchEntity                                                                    |
      #| abc         | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |

  Scenario: Get customer account without token
    When I get the customer details without token
    Then Customer account response status code is 401
    And Customer account error message is error.consumerNotAuthorized




