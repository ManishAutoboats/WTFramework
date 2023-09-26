@api @MiddlewareToMagento @apiDe @apiSe
Feature: Subscriber News Letter

  #TODO: There's difference in implementation from suscriber vs guest users. Need to verify this.
  Scenario: Get subscriber details with valid subscriber id
    When I get subscriber details for news letter with the existing subscriber id
    Then News letter response status code is 200
    And Registered subscriber news letter response should contain valid user details

  Scenario: Update registered subscriber details with valid subscriber id
    When I update subscriber details for news letter with the existing subscriber id
      | status  |
      | 3       |
    Then News letter response status code is 200
    And Registered subscriber news letter response should contain valid user details

  Scenario Outline: Update subscriber details with invalid subscriber id
    When I update subscriber details for news letter with the subscriber id <subscriber-id>
      | first_name  | last_name  |
      | <firstName> | <lastName> |
    Then News letter response status code is 404
    And Error message is "Subscriber to update does not exist for this ID: "<subscriber-id>""
    Examples:
      | subscriber-id | firstName | lastName |
      | 123           | Chandra   | Reddy    |

  Scenario Outline: Get subscriber details with invalid subscriber id
    When I get subscriber details for news letter with the subscriber id <subscriber-id>
    Then News letter response status code is 404
    And Error message is "Subscriber does not exist for this ID: "<subscriber-id>""
    Examples:
      | subscriber-id |
      | 123           |

  Scenario Outline: Get subscriber details with empty subscriber id
    When I get subscriber details for news letter with the subscriber id <subscriber-id>
    Then News letter response status code is 404
    And Error message is "error.requestDoesNotMatch"
    Examples:
      | subscriber-id |
      |               |

  Scenario: Get subscriber details with out authentication token in the request
    When I get subscriber details for news letter with out auth token for the subscriber id 123
    Then News letter response status code is 401
    And Error message is "error.consumerNotAuthorized"