@api @MiddlewareToMagento @apiDe @apiSe
Feature: Get Sales Order Details

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

  Scenario: Get sales order details by order id
    When I get sales order details with the given order id
    Then Sales order response status code is 200
    And Sales order response should contain valid user details

  Scenario Outline: Get sales order with invalid details
    When I get sales order details with the order id <order_id>
    Then Sales order response status code is <status>
    And Sales order error message is <error_msg>
    Examples:
      | order_id | status | error_msg                                                                             |
      | 0        | 400    | error.IDNeeded                                                                        |
      | abc      | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |






