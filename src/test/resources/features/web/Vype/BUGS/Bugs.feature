# Cookie policy appears on home page
  # cookie policy continues to be displayed on other pages until accepted
  # cookie policy can be accepted
  # cookie policy details can be viewed
@smokeLite @bugs
Feature: BAT Bug feature

  Background: Known bugs
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: BUG - 39606 Add to cart from Homepage Carousels
    When user clicks the person icon
    And cookies popup is displayed to user
    And click on the logo
    And click homePage Add to Cart Mix and Match Carousel without selecting required attribute
    And assert text of 'Please select product strength' is displayed
    And assert page title is as expected displaying 'Home page'
    #And click homePage Add to Cart ePod available Flavours
    #And user selects learn more link from cookie popup
    #Then assert text of 'What we collect' is displayed
    #Then assert text of 'What we do with the information we gather' is displayed
    #Then assert text of 'Security' is displayed
    #Then assert text of 'How we use cookies' is displayed
    #Then assert text of 'Links to other websites' is displayed
    #Then assert text of 'Controlling your personal information' is displayed
    #Then assert text of 'List of cookies we collect' is displayed

  Scenario: BUG - 34760 Basket - Update Qty
    And user clicks the person icon
    And login with valid details
    And empty basket
    And user click on search icon and submits the following search term 'liquid'
    And get all lists from page
    And Click specific result of 'Vype eLiquid Banaberry Blend'
    And select product colour
    Then click add to cart button js
    And click on basket icon
    And users clicks on the 'View Basket' text link
    And update mini-basket qty to '4'
    Then confirm mini-basket displayed amount of '4'

  Scenario: BUG - 35272 ensure cookie policy present, more information
    When user clicks the person icon
    And cookies popup is displayed to user
    And user selects learn more link from cookie popup
    Then assert text of 'What we collect' is displayed
    Then assert text of 'What we do with the information we gather' is displayed
    Then assert text of 'Security' is displayed
    Then assert text of 'How we use cookies' is displayed
    Then assert text of 'Links to other websites' is displayed
    Then assert text of 'Controlling your personal information' is displayed
    Then assert text of 'List of cookies we collect' is displayed