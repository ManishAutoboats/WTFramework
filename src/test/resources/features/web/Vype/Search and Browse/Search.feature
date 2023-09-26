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
  @search123 @regression @live @9412 @sprint2 @smokeLite @19454 @20728 @9439 @ITReg2 @ITLive @IEReg @IElive
Feature: BAT Search & Browse feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

@MXReg  @fr @dk  @frlive @fr @NLlive @COReg @VuseZAReg2 @VuseDKReg
  Scenario: 9429 Search and Browse - no result returned
    And user click on search icon and submits the following search term 'searchTermDino.key'
    Then search results title of 'searchResultsForDino.key' is returned
    And search results are returned saying 'searchResultsForDino.key'
    And search notification says 'NoResultsReturnedText.key'

@MXReg @NonCaptchaLive  @dk  @MXSmoke @NLlive @VuseZAReg2 @VuseDKReg
  Scenario: 9449 Search and Browse - two character should not return any search suggestions, 3 character do return search suggestions
    And user click on search icon and submits the following search term 'searchTermRt.key'
    And assert css block is as expected with display attribute of 'none'
    And user click on search icon and enters the following search term 'searchTermVype.key'
    And assert css block is as expected with display attribute of 'block'

  @COReg
  Scenario: 9439 Search and Browse - product returned
    And user click on search icon and submits the following search term 'OutOfStockProduct.key'
    And search results are returned saying 'vypeSerachResults.key'

@MXReg @NonCaptchaLive @fr @dk  @frlive @fr @NLlive @COReg @VuseZAReg2 @VuseDKReg
  Scenario: 9439 Search and loop thur results
    And user click on search icon and submits the following search term 'liquidSearchTermTile.key'
    And get all lists from page
    And confirm number of results returned is greater than '0'
    #Then check search result list - This step is not required as already covered above - Neha