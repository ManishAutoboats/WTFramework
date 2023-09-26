#WIP
Feature: Epok BAT Store Locator
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario:- Epok Store Locator - enter valid postcode
    And Epok users clicks on the 'StoreLocator.key' text link
    And Epok url contains 'StoreLocatorUrl.key'
    And Epok enter 'postCode.key' as lookup address
    And Epok assert displayed address is 'myAddressAccount'

  Scenario:- Epok Store Locator - enter valid county
    And Epok users clicks on the 'StoreLocator.key' text link
    And Epok enter 'stateRegion.key' as lookup address
    And Epok assert displayed address is 'stateRegionWithCountry.key'

  Scenario: Epok Store Locator - enter invalid data
    And Epok users clicks on the 'StoreLocator.key' text link
    And Epok enter 'invalidStateRegion.key' as lookup address
    And Epok assert displayed address is 'invalidStateRegion.key'

