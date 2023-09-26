Feature: BAT Search & Browse feature - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @RegVuseFR_Mobile
  Scenario: Mobile - 9439 Search and loop thur results
    And user click on search icon and submits the following search term 'liquidSearchTermTile.key'
    And user closes the alert if present
    And get all lists from page
    Then confirm number of results returned is greater than '0'