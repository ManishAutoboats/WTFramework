Feature: BAT Search & Browse feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @VuseDEReg @VuseDELive @VuseUKReg2 @VuseUKLive2 @VuseFRReg2 @VuseFRLive @VuseITAnonReg2 @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: 9429 Search and Browse - no result returned
    And user click on search icon and submits the following search term 'searchTermDino.key'
    And user closes the alert if present
    Then search results title of 'searchResultsForDino.key' is returned
    And search results are returned saying 'searchResultsForDino.key'
    And search notification says 'NoResultsReturnedText.key'


  @VuseDEReg @VuseDELive @VuseUKReg2 @VuseUKLive2 @VuseFRReg2 @VuseFRLive @VuseITAnonReg2 @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: 9439 Search and loop thur results
    And user click on search icon and submits the following search term 'liquidSearchTermTile.key'
    And user closes the alert if present
    And get all lists from page
    And user clicks on 'filter' option
    And user filters by product epods
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'

  @VuseUKReg2 @VuseUKLive2 @VuseFRReg2 @VuseFRLive  @VuseITLive @VuseMXReg @VuseMXLive @VuseITAnonReg2
  Scenario: 9449 Search and Browse - two character should not return any search suggestions, 3 character do return search suggestions
    And user click on search icon and submits the following search term 'searchTermRt.key'
    And user closes the alert if present
    And assert css block is as expected with display attribute of 'none'
    And user click on search icon and enters the following search term 'searchTermVype.key'
    And assert css block is as expected with display attribute of 'block'

  @VuseITAnonReg3
  Scenario: 9439 Search and Browse - product returned
    And user click on search icon and submits the following search term 'OutOfStockProduct.key'
    And search results are returned saying 'vypeSerachResults.key'

  @VuseUKReg2
  Scenario: Search and Browse - closest matches display
    And user click on search icon and submits the following search term 'searchTermEpin.key'
    And user closes the alert if present
    And search results returned 'ClosestMatchReturnedText.key'
    Then closest match product list is displayed

  @VuseFRReg2
  Scenario: Product image click navigate PDP
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click on first product image and PDP page is open

  @VuseFRReg2 @VuseITAnonReg3
  Scenario: search navigate to PLP and PDP
    And user click on search icon and submits search term 'searchItem.key' and select product display on left
    Then verify PDP page is open
    And user click on search icon and submits search term 'searchTerm.key' and select text display on right
    Then verify PLP page is open

  @VuseITAnonReg3
  Scenario: Vuse IT-Product Sorting on search page
    When user click on search icon and submits the following search term 'searchTerm.key'
    And url contains 'categorySearchURLText.key'
    Then verify the sorting options 'sortingOptionseCigarette.key' for 'eCigarettes.key'
