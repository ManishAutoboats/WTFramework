@api
Feature: Create Sales Order

  Scenario: Create sales order
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

#  TODO: This feature is created to as background step for get sales order details. Need to revisit this after having a chat with Matt.
#  Scenario Outline: Get sales order with invalid details
#    When I create sales order
#    Then Sales order response status code is <status>
#    And Sales order error message is <error_msg>
#    Examples:
#      | status | error_msg                                                                             |
#      | 400    | An ID is needed. Set the ID and try again.                                            |
#      | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |






