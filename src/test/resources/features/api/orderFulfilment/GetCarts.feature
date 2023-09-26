@api  @MiddlewareToMagento @apiDe @apiSe
Feature: Get Carts

  Background:
    When the user create a quote
    Then the quote response status code is 200
    And the quote response should contain the created cart id

  Scenario: Get Cart Items
    #When I get cart items for the given order
    When I get cart items for the given quote
    Then Carts response status code is 200
    And Carts response should return cart items

  Scenario Outline: Get cart items with invalid orders
    When I get cart items with invalid order id <order>
    Then Carts response status code is <status>
    And Carts error message contains <error_msg>
    Examples:
      | order | status | error_msg                                                                             |
      | abc   | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |
      | 0     | 404    | error.noSuchEntity                                                                    |

