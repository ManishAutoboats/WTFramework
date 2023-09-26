@posts
Feature: Posts Api GET POST

  Scenario: Get all the posts
    Given I have post details to get posts
      | id | title | author |
      |    |       |        |
    When I send get posts requests
    Then I should get json response with status code 200
    And posts should match with "allposts.json"

  Scenario: Get post by id
    Given I have post details to get posts
      | id | title | author |
      | 1  |       |        |
    When I send get posts by id requests
    Then I should get json response with status code 200
    And post should match with "getpost.json"

  Scenario: Get post by title
    Given I have post details to get posts
      | id | title       | author |
      |    | json-server |        |
    When I send get posts requests
    Then I should get json response with status code 200
    And post should match with title "json-server"

  Scenario: Get post by title and author
    Given I have post details to get posts
      | id | title       | author |
      |    | json-server | typicode |
    When I send get posts requests
    Then I should get json response with status code 200
    And post should match with title "json-server"

  Scenario: create a new post
    Given I have required data to create a post "newpost.json"
    When I create a new post
    Then new post should be created with status code 201
    And new post response should be returned

