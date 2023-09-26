Feature: 22888 - BAT Store Locator - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Store Locator - filter by shop type - <shopType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    And Glo should see the stores as per the selected stateRegionCapital.key
    When Glo select filter by shopType type and assert results are filtered accordingly
      | shopType         | expected         |
      | glo™ Shop         | glo™ Shop         |
#      | NEO™ Availability | NEO™ Availability |
#      | glo™ Popup Store  | glo™ Popup Store  |

  @RegGloIT_Mobile
  Scenario: Store Locator - filter by product type - <productType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    And Glo should see the stores as per the selected stateRegionCapital.key
    When Glo select filter by productType type and assert results are filtered accordingly
      | productType | expected         |
#      | Dispositivi | glo™ Agent        |
#      | NEO™ stick  | glo™ Shop |
      | Assistenza  | glo™ Popup Store |