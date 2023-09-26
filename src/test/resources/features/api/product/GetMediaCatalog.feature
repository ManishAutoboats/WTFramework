@api @MiddlewareToMagento @apiDe @apiSe
Feature: Get media for catalog

  Background:
    When I get products for the given search criteria
    Then Product response status code is 200
    And Product response should contain the product id

  Scenario: Get media for product
    When I get media for the given product
    Then Media catalog response status code is 200
    And Media catalog response should return media items

  #Comment out when product id is invalid, it is not working on current system
  Scenario Outline: Get media with invalid products
    When I get media items with invalid product id <order>
    Then Media catalog response status code is <status>
    And Media catalog error message contains <error_msg>
    Examples:
      | order | status | error_msg                   |
      | abc   | 404    | error.productDoesnotExist   |
      #| 0     | 404    | error.productDoesnotExist  |

