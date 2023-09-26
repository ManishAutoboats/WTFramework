# This is the editing customer account details story
# This covers BUG - 31562
 
Feature: 22888 - BAT Store Locator

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @COReg
  Scenario: 22888 - Store Locator - enter valid postcode
    When user click on StoreLocator link
    Then user should be redirected to StoreLocatorTitle.key page
    When enter 'postCode.key' as lookup address
    Then assert displayed address is 'myAddressAccount'

  @COReg
  Scenario: 22888 - Store Locator - enter valid county
    When user click on StoreLocator link
    Then user should be redirected to StoreLocatorTitle.key page
    And enter 'stateRegion.key' as lookup address
    And assert displayed address is 'stateRegionWithCountry.key'
    And user should see the stores as per the selected stateRegionCapital.key

  Scenario: Store Locator - enter invalid data
    And users clicks on the 'StoreLocator.key' text link
    And enter 'invalidStateRegion.key' as lookup address
    And assert displayed address is 'invalidStateRegion.key'

#  @de @delive
  Scenario: Store Locator - Vype DE - Shop Types and Product Types Drop-Downs
    Then user clicks on Store Locator link
    And assert Shop Type dropdown is displayed
    And assert Product Type dropdown is displayed
    And assert option none is selected by default for both Shop and Product Types
    And assert Shop Type options 'shopTypeOptions.key' available in the dropdown
    And assert Product Type options 'productTypeOptions.key' available in the dropdown

  #@fr
  Scenario: Store Locator - Products Stocked on Store Finder for selected location
    When user click on StoreLocator link
    Then user should be redirected to StoreLocatorTitle.key page
    When enter 'postCode.key' as lookup address
    Then assert displayed address is 'myAddressAccount'
    And assert product stocks for selected location on Store Finder
    Then user clicks on a store and assert product stocks on store details pop-up


