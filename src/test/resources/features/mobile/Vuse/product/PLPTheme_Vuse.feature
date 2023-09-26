Feature: Theme - PLP - Quick add to basket (including quantities) Feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Mobile - Navigate to PLP and ensure Quantity and Strength (or colour) is selectable
    And user click on search icon and submits the following search term 'searchTermePen3.key'
    And user closes the alert if present
    And get all lists from page
    And select product colour
