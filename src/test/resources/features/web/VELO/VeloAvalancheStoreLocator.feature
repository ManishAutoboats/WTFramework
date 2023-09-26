
Feature: Velo Avalanche Page Store Locator

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'

  @veloZAReg
  Scenario: Store Locator - enter valid postcode
    And users clicks on the store locator icon
    And enter 'postCode.key' as lookup address
    And assert displayed address is 'myAddressAccount.key'

  @veloZAReg
  Scenario: Store Locator - enter valid county
    And users clicks on the store locator icon
    And enter 'stateRegion.key' as lookup address
    And assert displayed address is 'stateRegionWithCountry.key'


