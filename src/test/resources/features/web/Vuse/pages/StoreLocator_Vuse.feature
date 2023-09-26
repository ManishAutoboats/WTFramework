Feature: Vuse Germany Store locator validations

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @VuseDEReg @VuseDELive @VuseUKReg2 @VuseZAReg @VuseITAnonReg2
  Scenario: Store Locator - enter valid postcode
    And users clicks on the store locator icon
    And enter 'postCode.key' as lookup address
    And assert displayed address is 'myAddressAccount.key'

  @VuseDEReg @VuseDELive @VuseUKReg2 @VuseZAReg2 @VuseITAnonReg2
  Scenario: Store Locator - enter valid county
    And users clicks on the store locator icon
    And enter 'stateRegion.key' as lookup address
    And assert displayed address is 'stateRegionWithCountry.key'

 @VuseFRReg2
  Scenario: Store Locator - Products Stocked on Store Finder for selected location, 963903 store locator page working good
    When user click on StoreLocator link
    Then user should be redirected to StoreLocatorTitle.key page
    When enter 'postCode.key' as lookup address
    Then assert displayed address is 'myAddressAccount'
    And assert product stocks for selected location on Store Finder
    Then user clicks on a store and assert product stocks on store details pop-up

  @VuseDEReg @VuseDELive
  #@VuseUKReg - This enhancement is disabled as of now until EM confirms, will uncomment soon
  Scenario: VUSE DE - MapBox Store Locator Page and Navigation
    And users clicks on the store locator icon
    And user closes the alert if present
    And assert URL contains text 'storeLocatorURL.key'
    And assert CMS blocks on top and bottom of Store locator page
    And enter 'postCode.key' as lookup address
    Then assert filter by Shop and filter by Product dropdowns are displayed
    And user clicks on Route button for the displayed address
    And assert user is redirected to maps to find directions to the store

  @VuseDEReg
  #@VuseUKReg - This enhancement is disabled as of now until EM confirms, will uncomment soon
  Scenario: VUSE - MapBox Store Locator Filter by Shop
    And users clicks on the store locator icon
    And user closes the alert if present
    When user clicks on Shop type filter icon
    When Vuse select filter by 'shopTypes.key' type and assert results

  @VuseDEReg
  #@VuseUKReg - This enhancement is disabled as of now until EM confirms, will uncomment soon
  Scenario: VUSE - MapBox Store Locator Filter by Product
    And users clicks on the store locator icon
    And user closes the alert if present
    When user clicks on Product type filter icon
    When Vuse select filter by 'productTypes.key' type and assert results

  @VuseUKReg2 @VuseZAReg2
  Scenario: Store Locator - Products Stocked on Store Finder for selected location VuseUK
    And users clicks on the store locator icon
    Then user should be redirected to StoreLocatorTitle.key page
    And user closes the alert if present
    When enter 'postCode.key' as lookup address
    Then assert displayed address is 'myAddressAccount.key'
    Then user should see the list of stores
