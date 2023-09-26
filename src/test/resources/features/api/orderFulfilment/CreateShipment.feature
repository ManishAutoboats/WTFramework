@api @MiddlewareToMagento
Feature: Create Shipment Order

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

  Scenario: Create shipment
    When I create a shipment for the given order
    Then Shipment response status code is 200
    And Shipment response should return shipment id

  Scenario: Create shipment with incorrect order numbers
    When I create a shipment with invalid order id 0193674
    Then Shipment response status code is 200
    And Shipment response should return shipment id as empty

  #Failed on uat when order id=0, track as bug 131726
  Scenario Outline: Create shipment with invalid orders
    When I create a shipment with invalid order id <order>
    Then Shipment response status code is <status>
    And Shipment error message contains <error_msg>
    Examples:
      | order   | status | error_msg                                                                             |
      | abc     | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |
      #| 0       | 400    | An ID is needed. Set the ID and try again.                                        |

  Scenario Outline: Create shipment with invalid order items
    When I create a shipment with invalid order item id <order_item>
    Then Shipment response status code is <status>
    And Shipment error message contains <error_msg>
    Examples:
      | order_item | status | error_msg                           |
      | 1234       | 400    | error.createShipmentWithoutProducts |
      | 0          | 400    | error.createShipmentWithoutProducts |

  Scenario Outline: Create shipment with invalid tracking details
    When I create a shipment with invalid tracking details <track>
    Then Shipment response status code is <status>
    And Shipment error message contains <error_msg>
    Examples:
      | track              | status | error_msg                             |
      #| invalidTrackNumber | 400    | error.invalidTrackNumber              |
      | invalidCarrierCode | 400    | error.invalidCarrierCode              |


