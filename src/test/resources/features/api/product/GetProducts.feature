@api @MiddlewareToMagento @apiDe @apiSe
Feature: Get Products

#  Background:
#    When I create a product
#    Then Create product response status code is 200

  Scenario: Get products
    When I get products for the given search criteria
    Then Product response status code is 200
    And Product response should contain created product details

  Scenario Outline: Get products with invalid details
    When I get products for the invalid <field> and <value>
    Then Product response status code is <status>
    And Product error message is <error_msg>
    Examples:
      | field      | value | status | error_msg                                                                                   |
      | abc        | 2     | 400    | error.attributeInvalid                                                                      |
      | website_id | 2020  | 404    | The website with id 2020 that was requested wasn't found. Verify the website and try again. |






