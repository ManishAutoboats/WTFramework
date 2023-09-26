@api @MiddlewareToMagento @apiDe @apiSe
Feature: Check Stock Status

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

  Scenario: Get stock status details
    When I get stock status details for the given sku
    Then Stock status response status code is 200
    And Stock status response should return stock details

  Scenario: Get stock status with invalid sku details
    When I get stock status details for the sku abc
    Then Stock status response status code is 200
    And Stock status response should return empty status

