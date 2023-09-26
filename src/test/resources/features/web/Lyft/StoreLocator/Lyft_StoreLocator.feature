# This is the editing customer account details story
#	@LyftRegression 
Feature: BAT Store Locator 

Background: Navigate to BAT Home Page 
	Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftRegression2
  Scenario: Store Locator - enter valid postcode
    And users clicks on the 'StoreLocator.key' text link
    And url contains 'StoreLocatorUrl.key'
    And enter 'postCode.key' as lookup address
    And assert displayed address is 'myAddressAccount.key'

  @LyftRegression2
  Scenario: Store Locator - enter valid county
    And users clicks on the 'StoreLocator.key' text link
    And enter 'stateRegion.key' as lookup address
    And assert displayed address is 'stateRegionWithCountry.key'