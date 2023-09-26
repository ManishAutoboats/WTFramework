@api @MiddlewareToMagento
Feature: Update Shipment Tracking Details

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details
    When I create a shipment for the given order
    Then Shipment response status code is 200
    And Shipment response should return shipment id
    When I get shipment tracking details for the given order
    Then Shipment response status code is 200
    And Shipment response should return tracking details

  Scenario: Update shipment tracking details
    When I update shipment tracking details for the given order
    Then Shipment response status code is 200
    And Update Shipment tracking response should return tracking details

  Scenario Outline: Update shipment tracking with invalid shipping details
    When I update shipment tracking details with invalid ship id <ship_id>
    Then Shipment response status code is <status>
    And Shipment error message contains <error_msg>
    Examples:
      | ship_id | status | error_msg                                                                                                                           |
      | 0198267 | 404    | An exception has occurred, the parent_id probably does not exist.                                                                   |
      | abc     | 400    | error.invalidValuesType |


