@api
Feature: Guest News Letter

  Scenario Outline: Get guest details with <valid-type> subscriber id
    When I get guest details for news letter with the subscriber id <subscriber-id>
    Then News letter response status code is 200
    And Guest news letter response should contain <valid-type> user details
    Examples:
      | subscriber-id | valid-type |
      | 215           | valid      |
      | 0             | invalid    |

  Scenario Outline: Get guest details with empty or invalid type subscriber
    When I get guest details for news letter with the subscriber id <subscriber-id>
    Then News letter response status code is <status>
    And Error message is "<error_msg>"
    Examples:
      | subscriber-id | status | error_msg                                                                             |
      |               | 404    | Request does not match any route.                                                     |
      | abc           | 400    | The "abc" value's type is invalid. The "int" type was expected. Verify and try again. |

  Scenario: Get guest details with out authentication token in the request
    When I get guest details for news letter with out auth token for the subscriber id 123
    Then News letter response status code is 401
    And Error message is "The consumer isn't authorized to access %resources."


