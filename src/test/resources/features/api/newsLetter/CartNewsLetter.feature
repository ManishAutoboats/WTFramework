@api
Feature: Cart News Letter Subscription

  Scenario Outline: Update guest news letter subscription with valid cart id
    When I update cart guest news letter with the cart id <cart-id>
      | subscribe   |
      | <subscribe> |
    Then News letter response status code is 200
    Examples:
      | subscribe | cart-id |
      | yes       | 123     |
      | no        | 123     |

    #for empty subscribe option with invalid cart id, it's returning 200 with empty response. Is it a bug??
  Scenario Outline: Update guest news letter subscription with invalid cart id
    When I update cart guest news letter with the cart id 123
      | subscribe   |
      | <subscribe> |
    Then News letter response status code is 404
    Examples:
      | subscribe |
      | yes       |
      | no        |
      |           |

  Scenario Outline: Update subscriber news letter subscription
    When I update cart subscriber news letter subscription
      | subscribe   |
      | <subscribe> |
    Then News letter response status code is 200
    Examples:
      | subscribe |
      | yes       |
      | no        |

    #It's weird as it expects cartid to be present even though there's no way to provide it at request level and also throwing 400 instead of 404
  #which is not in line with other endpoints
  Scenario Outline: Update subscriber news letter subscription with invalid options
    When I update cart subscriber news letter subscription
      | subscribe   |
      | <subscribe> |
    Then News letter response status code is 404
    Examples:
      | subscribe |
      |           |
      | 123       |
