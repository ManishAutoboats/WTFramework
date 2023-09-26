Feature: 22888 - BAT Store Locator

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    # @gloDeLive - 'Store Locator' on Prod commented out as the enhancement is toggled off due to pending EM confirmation and
    # will uncomment as soon as EM confirms, UAT1 has the required enhancement so these tests are covered in regression

  @gloPlRegression2 #@gloDeRegression2 #@gloDeLive #@gloPlLive
  Scenario: 22888 - Store Locator - enter valid postcode
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters postCode.key as lookup address
    Then Glo assert displayed address is myAddressAccount.key

  #@gloDeRegression2 #@gloDeLive(Due to bug# 582877, the mapbox is disabled as the it is not yet confirmed by EM)
    #@gloPlRegression #@gloPlLive
  Scenario: 22888 - Store Locator - enter valid county
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key

  #@gloDeRegression2 #@gloDeLive #@gloPlRegression #@gloPlLive
  Scenario: Store Locator - enter invalid data
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters invalidStateRegion.key as lookup address
    Then Glo assert displayed address is invalidStateRegion.key

  #@gloDeRegression2 #@gloDeLive(Due to bug# 582877, the mapbox is disabled as the it is not yet confirmed by EM)
  Scenario: Store Locator - Shop Types and Product Types Drop-Downs
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    And Glo assert Shop Type dropdown is displayed
    And Glo assert Product Type dropdown is displayed
    And Glo assert Shop Type options shopTypeOptions.key available in the dropdown
    And Glo assert Product Type options productTypeOptions.key available in the dropdown

  #@gloDeRegression2 @gloDeLive (There is an enhancement task is going on for below scenario, will re-enable later)
  Scenario Outline:Glo Store Locator - filter by shop type - <shopType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    And Glo should see the stores as per the selected stateRegionCapital.key
    When Glo select to filter by shopType with <shopType>
    Then the results should be filtered accordingly and should contain <expected>
    Examples:
      | shopType               | expected               |
      | glo™ POPUP STORE       | glo™ POPUP STORE       |
      | Vype Inspiration Store | Vype Inspiration Store |

  Scenario Outline: Store Locator - filter by shop type - <shopType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    And Glo should see the stores as per the selected stateRegionCapital.key
    When Glo select to filter by shopType with <shopType>
    Then the results should be filtered accordingly and should contain <expected>
    @gloItRegression2 @gloItLive
    Examples:
      | shopType          | expected          |
      | glo™ Shop         | glo™ Shop         |
      | NEO™ Availability | NEO™ Availability |
      | glo™ Popup Store  | glo™ Popup Store  |

  @gloItRegression2 @gloItLive
  Scenario Outline: Store Locator - filter by product type - <productType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    And Glo should see the stores as per the selected stateRegionCapital.key
    When Glo select to filter by productType with <productType>
    Then the results should be filtered accordingly and should contain <expected>
    Examples:
      | productType | expected                                |
      | Dispositivi | glo™ Agent,glo™ Shop                    |
      | NEO™ stick  | glo™ Agent,glo™ Shop,NEO™ Availability  |
      | Assistenza  | glo™ Popup Store                        |

  #@gloDeLive blocked because of bug 509169 #@gloDeRegression -Block because on ADO google blocking to open Map looking and trying for solution.
  Scenario: Store Locator - Route Navigation
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    When Glo clicks on Route button for the displayed address
    Then Glo assert google maps are opened in a new tab with Destination pre-populated with storeLocatorCountry.key

  #@gloPlLive @gloPlRegression
  Scenario: Store Locator - Route Navigation from store finder overlay on the google map
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    And Glo enters stateRegion.key as lookup address
    When Glo clicks on any of the listed stores in the search result page
    Then Glo should see its location on the google map
    When Glo clicks on the 'Route' link on the store finder overlay on the map
    Then Glo assert google maps are opened in a new tab with Destination pre-populated with storeLocatorCountry.key

 #@gloPlRegression This enhancement is going live with RT77 but disabled currently until EM confirms,hence commented out as of now
  Scenario: GLO PL - MapBox Store Locator Page and Navigation
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    Then assert URL contains text 'StoreLocatorUrl.key'
    And assert CMS blocks on top and bottom of Store locator page
    When Glo enters postCode.key as lookup address
    Then assert filter by Category dropdown is displayed
    And user clicks on Route button for the displayed address
    And assert user is redirected to maps to find directions to the store

  #@gloPlRegression This enhancement is going live with RT77 but disabled currently until EM confirms,hence commented out as of now
  Scenario: GLO PL - MapBox Store Locator Filter by Category
    And users clicks on the store locator icon
    When Glo select filter by category type and assert results are filtered accordingly
    | categoryType                        | expected        |
    | Punkty eSmokingWorld                | eSmokingWorld   |
    | sklepy z Glo i wkladami tytoniowymi | AMIC            |
    | sklepy z wkladami tytoniowymi       | LOTOS           |

  #@gloDeRegression2 #@gloDeLive(Due to bug# 582877, the mapbox is disabled as the it is not yet confirmed by EM)
  Scenario: Store Locator - filter by retailer - <retailerType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    When Glo select filter by retailer type and assert results are filtered accordingly
      | retailerType           | expected                |
      | glo™ POPUP STORE       | glo™ Popup Store        |
      | glo™ Händler           | Shell Station           |
      | Vuse Inspiration Store | Vuse Inspiration Store  |

  #@gloDeRegression2 #@gloDeLive(Due to bug# 582877, the mapbox is disabled as the it is not yet confirmed by EM)
  Scenario: Store Locator - filter by product - <productType> - return store results as per selection
    When Glo click on StoreLocator link
    Then Glo should be redirected to StoreLocatorTitle.key page
    When Glo enters stateRegion.key as lookup address
    Then Glo assert displayed address is stateRegionWithCountry.key
    When Glo select filter by product type and assert results are filtered accordingly
      | productType         | expected          |
      | glo™ Tabak Heater   | Shell Station     |
      | neo™ sticks         | Shell Station     |

  #Store Locator auto suggestion(136986)
  @gloKzRegression
  Scenario: User can select the locator from auto suggestion list
    Given user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Glo click on StoreLocator link
    When Glo enters LocatorName.key as lookup address
    Then assert the location should be selected

  @gloKzRegression
  Scenario: User can filter locator by store category or assortment
    Given user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    When Glo click on StoreLocator link
    Then user select the store category or assortment from dropdown
      | category   | Кордай |
      | category   | Балхаш |
      | category   | Астана |
      | assortment | Девайс |
      | assortment | Стики  |

  @gloKzRegression
  Scenario: User can navigate to suggest route
    Given user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Glo click on StoreLocator link
    When user select the store category and "Тараз" from dropdown
    And user can click suggest route
    Then assert google maps are opened in a new tab

  #automate issue 907987
  @gloItRegression
  Scenario:  Validate map icon is displayed correctly on menthol ban page
    When user navigate menthol ban page
    And assert text of 'mentholBanText.key' is displayed on page
    Then assert map icon is displayed
    And find store near "storelocatorText.key"
    And assert URL contains text 'expectedStorelocator.key'