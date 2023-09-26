#This is the Search feature file
## Search returning no results
## valid search
## invalid search
## Search returning results
@LyftRegression2 @LyftDKReg
Feature: BAT Search & Browse feature
	Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option


@LyftSmoke @LyftLive @LyftDKSmoke @LyftDKLive
	Scenario: Search and Browse - no result returned
		And user click on search icon and submits the following search term 'searchTermDino.key'
		Then search results title of 'searchResultsForDino.key' is returned
		Then search results are returned saying 'searchResultsForDino.key'
		Then search notification says 'NoResultsReturnedText.key'


	Scenario: Search and Browse - two character should not return any search suggestions, 3 character do return search suggestions
		And user click on search icon and submits the following search term 'searchTermdi.key'
		Then assert css block is as expected with display attribute of 'none'
		#And user click on search icon and submits the following search term 'searchTermdin.key'
	#And assert css block is as expected with display attribute of 'block' 
	
#Scenario: Search and Browse - product returned 
#	And user click on search icon and submits the following search term 'OutOfStockProduct.key' 
#	And search results are returned saying 'vypeSerachResults.key'

	@LyftLive
	Scenario: Search and loop thur results
		And user click on search icon and submits the following search term 'liquidSearchTermTile.key'
		And get all lists from page
		Then confirm number of results returned is greater than '0'
		Then search results are returned saying 'searchResults.key'