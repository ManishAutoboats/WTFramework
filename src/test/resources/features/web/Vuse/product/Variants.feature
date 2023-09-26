@VuseUKReg2
Feature: BAT Search and Browse - Variants

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Search and Browse - variants displayed
   When user click on search icon and submits the following search term 'searchTermePen3.key'
   And search results title of 'searchResultsPen3.key' is returned
    And user closes the alert if present
   Then assert product colour is displayed
   And assert product strength is displayed
   And select product by index "productIndex.key" on plp page
   And assert text of 'strengthText.key' is displayed
   And all selectable colour variants displayed