@api @MiddlewareToMagento
Feature: Get Shipment Tracking Details

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details
    When I create a shipment for the given order
    Then Shipment response status code is 200
    And Shipment response should return shipment id

  Scenario: Get shipment tracking details
    When I get shipment tracking details for the given order
    Then Shipment response status code is 200
    And Shipment response should return tracking details

  Scenario Outline: Get shipment tracking with invalid shipping details
    When I get shipment tracking details with invalid ship id <ship_id>
    Then Shipment response status code is <status>
    And Shipment error message contains <error_msg>
    Examples:
      | ship_id | status | error_msg                                                                             |
      | 0198267 | 404    | error.entityNotExist         |
      | abc     | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |
      | 0       | 400    | error.IDNeeded                                            |


