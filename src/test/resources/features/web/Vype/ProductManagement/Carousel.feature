#This is the PDP feature file
  ##Testing
    ## PDP
    ## Attributes
    ## Theme
  #@regression
Feature: 37163 Related products carousel feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @37163
  Scenario: 37163 Related products carousel present
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And related carousel div block is present
    And assert text of 'Why not try a dash of...' is displayed
    And assert text of 'We found other products you might like!' is displayed

  @dklive @dk
  Scenario: Verify Product Carousel Block on Homepage
    Then assert text of 'productCarouselTitle.key' is displayed
    And assert all the fields under Product Carousel block
    And assert carousel works on both left and right arrow CTAs







