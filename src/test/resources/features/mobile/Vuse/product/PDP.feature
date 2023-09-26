Feature: BAT PDP feature - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - Guest-PDP elements present as expected
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And get all lists from page
    And click first result
    Then assert PDP product title
    Then assert PDP product price
    Then assert PDP product description
