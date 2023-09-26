Feature: BAT Account management - My Favorites
  Background: 9597 - Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Favourite is Enable
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And If the Favourites feature is enabled in Magento
    Then user should see the Favourites icon on PDP
    And Clicking on the icon should add the product to My Favourites


  Scenario: Favourite is Disable
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And If the Favourites feature is disabled in Admin console
    Then user should not see the Favourites icon on PDP

