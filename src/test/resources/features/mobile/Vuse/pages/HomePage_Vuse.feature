Feature: BAT homepage feature - page scraping for elements - Mobile

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  Scenario: Mobile Check Instagram social Media link and its Navigation page
    When Vype users click on Instagram link
    Then assert cart pageTitle is 'homepageInstgramTitle.key'