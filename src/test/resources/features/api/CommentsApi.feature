@comments
Feature: Comments Api GET POST

  Scenario: Get all the comments
    Given I have comment details to get comments
      | id | body | postId |
      |    |      |        |
    When I send get comment request
    Then I should get comment json response with status code 200

  Scenario: Get the comment by id
    Given I have comment details to get comments
      | id | body | postId |
      | 1  |      |        |
    When I send get comment request by id
    Then I should get comment json response with status code 200
    And comment should match with "onecomment.json"


  Scenario: Get the comment by body
    Given I have comment details to get comments
      | id | body    | postId |
      |    | comment |        |
    When I send get comment request
    Then I should get comment json response with status code 200
    And comments should match with "getcomments.json"

  Scenario: create a new comment
    Given I have required data to create a comment
      | id | body         | postId |
      |    | Nice Picture | 1      |
    When I create a new comment
    Then new comment should be created with status code 201
    And new comment response should be returned