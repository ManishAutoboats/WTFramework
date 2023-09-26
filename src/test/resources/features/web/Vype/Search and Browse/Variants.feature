#When looking at PDP/PLP variants will be displayed (i.e. colours) so base product is displayed, with variabnts logically displayed

Feature: BAT Search and Browse - Variants

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @sprint1 @smokeLite @search @regression
  Scenario: Search and Browse - variants displayed
   When user click on search icon and submits the following search term 'searchTermePen3.key'
   And search results title of 'searchTermePen3.key' is returned
   Then assert product colour is displayed
   And assert product strength is displayed
   And click first result
   And assert text of 'strengthText.key' is displayed
   And all selectable colour variants displayed

  @IEReg
  Scenario: Search and Browse - variants displayed - IE
    When user click on search icon and submits the following search term 'searchTermVariants.key'
    And search results title of 'searchTermVariants.key' is returned
    Then assert product colour is displayed
    And assert product strength is displayed
    And user click on search icon and submits the following search term 'searchTermStrength.key'
    And assert text of 'strengthText.key' is displayed
    And user click on search icon and submits the following search term 'searchTermColour.key'
    And all selectable colour variants displayed



