#TODO: This needs to be fixed as we don't have a way to get it working
@api @MiddlewareToMagento @apiDe @apiSe
Feature: Get Media

  Scenario: Get Media
    When I get media for the given attributeSet name
    Then Media response status code is 200
    And Media response should return media items


