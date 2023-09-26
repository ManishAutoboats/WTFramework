#It's created to be a background for get products
@api
Feature: Create Product

  Scenario: Create product
    When I create a product
    Then Create product response status code is 200
    And Create product response should contain created product details

  Scenario Outline: Create product with invalid details
    When I create a product
    Then Create product response status code is <status>
    And Create product error message is <error_msg>
    Examples:
      | status | error_msg                                                                             |
      | 400    | An ID is needed. Set the ID and try again.                                            |
      | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |






