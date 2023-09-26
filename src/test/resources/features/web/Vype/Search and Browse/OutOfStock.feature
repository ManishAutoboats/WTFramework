#This is the Search feature file
  ##Testing
    ## Presence of search bar
    ## Search returning no results
      ## valid search
      ## invalid search
        ## php data-injection
        ## symbols
    ## Search returning results
    ## Nav to Registration page
    ## Nav to Forgotten password page
    #461307 is done we can tag below 3 sceanrios as per the comments above each Scenario
@search
Feature: 21646 BAT Out of Stock feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
  #461307 - to be tagged for all locales regression pack except Velo-de,Glo-pl,Glo-it,Glo-de,Glo-KZ
  Scenario: 21646 9485 Out of stock indicator
    And user click on search icon and submits the following search term 'OutOfStockItem.key'
    And get all lists from page
    And assert text of 'OutofstockMessage.key' is displayed

  #461307-Below Scenario to be tagged for Velo-de,Glo-pl,Glo-it,Glo-de as search feature is not there so direct to PDP
  Scenario: 21646 9485 Out of stock indicator check on PDP
    Then user visits PDP page directly
    And assert text of 'OutofstockMessage.key' is displayed

  #461307-Glo-kz need below flow tag accordingly
  Scenario: 21646 9485 Out of stock indicator for Glo kz
    And create a new account
    Then user visits PDP page directly
    And assert text of 'OutofstockMessage.key' is displayed