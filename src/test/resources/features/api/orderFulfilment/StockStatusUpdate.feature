@api  @MiddlewareToMagento @apiDe @apiSe
Feature: Update Stock Status

  Background:
    When I create sales order
    Then Sales order response status code is 200
    And Sales order response should contain created order details

  Scenario: Update stock status details
    When I update stock status details for the given sku
      | quantity | status |
      | 100      | 1      |
    Then Stock status response status code is 200
#    And Stock status update response should return empty response
    When I get stock status details for the given sku
    Then Stock status response status code is 200
    And Stock status update response should return stock details

  Scenario: Update stock status with random sku name
    When I update stock status details for the sku &
      | quantity | status |
      | 100      | 1      |
    Then Stock status response status code is 200
    When I get stock status details for the sku &
    Then Stock status response status code is 200
    And Stock status update response should return stock details

