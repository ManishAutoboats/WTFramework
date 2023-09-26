Feature: Glo About page

  Background: Navigate to Glo Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @gloPlRegression2
#  USER STORY 194611-pl
  Scenario: Confirm About page title and confirm text exists
    When click on about page link href aboutPageUrl.key
    And url contains 'aboutPageUrl.key'
    And assert glo pageTitle is 'aboutPageTitle.key'
    Then assert text of 'howDoesGloWork.key' is displayed
    And  click on the logo
    Then assert glo pageTitle is 'homePageTitle.key'