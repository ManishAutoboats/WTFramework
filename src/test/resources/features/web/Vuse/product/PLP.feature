Feature: PLP filtering to left hand side

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @VuseUKReg2
  Scenario: PLP filtering to left hand side - Vuse UK
    And user hovers over on the SHOP header link
 	And user hovers over on the See all Vype devices link
    Then users clicks on the See all Vype devices link from SHOP Menu
    And assert filters are in place as expected
    And user hovers over on the SHOP header link
 	And user hovers over on the See all Vype flavours link
    Then users clicks on the See all Vype flavours link from SHOP Menu
    #And users clicks on the '+' text link
    Then url contains 'DeviceUrlText.key'
    #And users clicks on the 'OPEN' text link
    #Then url contains 'system_type-open'

  @VuseUKReg2 @VuseUKLive2
  Scenario: PLP switch Devices and Accessories colour
    When users clicks on the 'ShopDevices.key' text link
    And user closes the alert if present
    And select product colour and assert device color switch on PLP
    When user click on search icon and submits the following search term 'AccessoriesFilter.key'
   # Then select product colour and assert device color switch on PLP //product doesn't have color

  @VuseUKReg2 #@VuseUKLive  The test should be enabled again after #789017 get fixed.
  Scenario: PLP Filter Devices and Flavours by Consumable Type
    When users clicks on the 'ShopDevices.key' text link
    And user closes the alert if present
    Then user clicks on Filters button to open filters flyout
    And assert Devices Category section on flyout
    And assert list of consumable types for 'Devices' and CTA for one type
    When users clicks on the 'ShopFlavours.key' text link
    Then user clicks on Filters button to open filters flyout
    And assert Devices Category section on flyout
    #And assert list of consumable types for 'Flavours' and CTA for one type

  @VuseUKReg2
  Scenario: Quantity Field Selector on PLP for each product
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And select first product color or strength on PLP
    And assert quantity selector is displayed on each SKU with default value 1
    And assert if user is able to input any value '4' in Qty text field
    Then confirm mini-basket displayed amount of '4'
    And user clicks on plus button on qty selector and assert qty increase to '5'
    Then confirm mini-basket displayed amount of '9'
    Then close side panel basket
    And user clicks on minus button on qty selector and assert qty decrease to '4'

  @VuseUKLive2
  Scenario: Quantity Field Selector on PLP for each product on PROD
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And select first product color or strength on PLP
    And assert quantity selector is displayed on each SKU with default value 1

    #(Removed the tag for Vuse IT after discussed with Manish as it is not applicable for below scenario)
  Scenario:PDP Vype Strength Swatch
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And assert product colour is displayed
    And assert product strength is displayed

  @VuseITAnonReg2 @VuseITLive
  Scenario:PLP filtering to left hand side
    And users clicks on the 'DeviceCategoryText.key' text link
    And assert filters are in place as expected
    And open eLiquid Flavours from the Categories menu
    Then url contains 'LiquidiUrlText.key'

  @VuseMXReg @VuseMXLive
  Scenario: Vuse flavours plp validation
    And user hovers on 'ShopFlavours.key' and clicks on 'EpenCartridges.key'
    And user lands on plp page

  #@VuseFRReg2(Going in disabled and need to rework in Release-82, US# 532083)
  Scenario: Vuse fr Product Filtering on PLP for e-cigarette-devices
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And user click on 'filter' option
    And assert below filter type options are available
      | filterByColor.key            |
      | filterByDevice.key           |

  #@VuseUKReg2
  Scenario: Vuse UK Product Filtering on PLP for e-cigarette-devices
    When users clicks on the 'shopDevices.key' text link
    And user closes the alert if present
    And user lands on plp page
    And user click on 'filter' option
    And assert below filter type options are available
      | filterByColor.key            |
      | filterByDevice.key           |

  #@VuseUKReg2
  Scenario: Vuse UK Product Filtering on PLPs for e-liquids
    When users clicks on the 'shopFlavours.key' text link
    And user closes the alert if present
    And user lands on plp page
    And user click on 'filter' option
    And assert below filter type options are available
      | filterByProduct.key            |
      | filterByDevice.key           |
      | filterByNicotine.key          |


  @VuseFRReg2  @VuseFRLive
  Scenario: Vuse FR-Product Sorting on PLPs
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And verify the sorting options 'sortingOptionseCigarette.key' for 'eCigarettes.key'
    And verify the sorting options 'sortingOptionsLiquid.key' for 'eLiquids.key'
    And user navigate to 'category' to 'subcategory' and verify the 'sorting option'
      | category           | subcategory           | sorting option                |
      | eCigarettes.key    | ePenlink.key          | sortingOptionseCigaretteEpen.key  |
      | eCigarettes.key    | ePodlink.key          | sortingOptionseCigaretteEpen.key  |
      | eCigarettes.key    | eTanklink.key         | sortingOptionseCigaretteEpen.key  |
      | eLiquids.key       | capsulesEPodlink.key  | sortingOptionseCapsuleEpod.key|
      | eLiquids.key       | capsulesEPenlink.key  | sortingOptionseCapsuleEpen.key|
      | eLiquids.key       | flaconsEliquid.key    | sortingOptionseFalcons.key    |

  #@VuseUKReg (Went live in disable mode(Release82), will uncomment once got the confirmation
  Scenario: Vuse UK-Product Sorting on PLPs
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And verify the sorting options 'sortingOptionseCigarette.key' for 'eCigarettes.key'
    And verify the sorting options 'sortingOptionsLiquid.key' for 'eLiquids.key'
    And user navigate to 'category' to 'subcategory' and verify the 'sorting option'
      | category            | subcategory            | sorting option                    |
      | eCigarettes.key     | ePenlink.key           | sortingOptionsEPen.key            |
      | eCigarettes.key     | starterKit.key         | sortingOptionsStarterBundles.key  |
      | eCigarettes.key     | ePodlink.key           | sortingOptionsEPod.key            |
      | eLiquids.key        | ePodPodlink.key        | sortingOptionsEpodPod.key         |
      | eLiquids.key        | ePenPodlink.key        | sortingOptionsEpenPod.key         |
      | eLiquids.key        | eLiquidBottleslink.key | sortingOptionsEliquidBottles.key  |
      | aboutVuseLink.key   | vapeAccessories.key    | sortingOptionsVapeAccessories.key |
      | eCigarettes.key     | starterKit.key         | sortingOptionsStarterBundles.key  |


  @VuseUKReg2
  Scenario: Vuse UK- subscription wording for ePod pod plp
    And user hovers on 'shopDevices.key' and clicks on 'ePodLink.key'
    And user closes the alert if present
    And user lands on plp page
    And click on first result
    And assert subscription wording is not present on 'pdp'
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    And assert subscription wording is not present on 'basket page'

  @VuseUKReg2
  Scenario: Vuse UK- subscription wording for ePen pod plp
    And user hovers on 'shopFlavours.key' and clicks on 'ePenLink.key'
    And user closes the alert if present
    And user lands on plp page
    And click on first result
    And assert subscription wording is not present on 'pdp'
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    And assert subscription wording is not present on 'basket page'

  @VuseFRReg2
  Scenario: Vuse FR- subscription wording for ePod pod plp
    Given user hovers on 'eCigarettes.key' and clicks on 'ePodLink.key'
    And user closes the alert if present
    And user lands on plp page
    And click on first result
    Then assert subscription wording is not present on 'pdp'
    When select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    Then assert subscription wording is not present on 'basket page'

    @VuseFRReg2
  Scenario: Vuse FR Product Filtering
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And user clicks on Filters button to open filters flyout
    And assert 'All' option is highlighted
    And verify that the navigated url should contains 'eCigarettesUrl.key'
    When users clicks on the 'eCigarettes.key' text link
    And user clicks on Filters button to open filters flyout
    And user assert the count after applying the filter for 'eCigarettes'
    When users clicks on the 'eLiquids.key' text link
    And user clicks on Filters button to open filters flyout
    And assert 'All' option is highlighted
    And verify that the navigated url should contains 'eLiquidUrl.key'
    When users clicks on the 'eLiquids.key' text link
    And user clicks on Filters button to open filters flyout
    And user assert the count after applying the filter for 'eLiquides'
    And user navigate to 'category' and 'subcategory' and verify the 'option' is highlighted and navigated to expected 'url'
      | category           | subcategory          | option       | Url                |
      | eCigarettes.key    | ePenlink.key         | ePen         | ePenUrl.key        |
      | eCigarettes.key    | ePodlink.key         | ePod         | ePodUrl.key        |
      | eCigarettes.key    | eTanklink.key        | eTank        | eTankUrl.key       |
      | eLiquids.key       | capsulesEPodlink.key | ePod         | eLiquidePodUrl.key |
      | eLiquids.key       | capsulesEPenlink.key | ePen         | eLiquidePenUrl.key |
      #| eLiquids.key       | flaconsEliquid.key   | Flacons      | eLiquidFlaconUrl.key |

    #@VuseUKReg(Going in disabled and need to rework in Release-82, US# 532083)
  Scenario: Vuse UK Product Filtering
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And user click on 'filter' option
    And assert 'All' option is highlighted
    And verify that the navigated url should contains 'eCigarettesUrl.key'
    When users clicks on the 'eCigarettes.key' text link
    And user click on 'filter' option
    And user assert the count after applying the filter for 'eCigarettes'
    When users clicks on the 'eLiquids.key' text link
    And user click on 'filter' option
    And assert 'All' option is highlighted
    And verify that the navigated url should contains 'eLiquidUrl.key'
    When users clicks on the 'eLiquids.key' text link
    And user click on 'filter' option
    And user assert the count after applying the filter for 'eLiquides'
    And user navigate to 'category' to 'subcategory' and verify the 'option' is highlighted and navigated to expected 'url'
      | category           | subcategory              | option         | Url                  |
      | eCigarettes.key    | ePenlink.key             | ePen           | ePenUrl.key          |
      | eCigarettes.key    | ePodlink.key             | ePod           | ePodUrl.key          |
      | eCigarettes.key    | eTanklink.key            | eTank          | eTankUrl.key         |
      | eLiquids.key       | ePodPodlink.key          | ePod Pod       | eLiquidePodUrl.key   |
      | eLiquids.key       | ePenPodlink.key          | ePen Pod       | eLiquidePenUrl.key   |
      | eLiquids.key       | eLiquidBottleslink.key   | eLiquid Bottle | eLiquidBottleUrl.key |

    #@VuseUKReg(Going in disabled and need to rework in Release-82, US# 532083)
  Scenario: Vuse UK Filter Options
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And verify the filter content for 'eCiggarette' with filter by 'Colour'
    And verify the content of the Filter type

  @VuseUKReg2
  Scenario: Verify label 'CHOOSE CBD STRENGTH (MG)' on the search result of CBD products
    And user click on search icon and submits the following search term 'searchCBDproductPLP.key'
    And user closes the alert if present
    Then assert strength label 'CHOOSE CBD STRENGTH (MG)' is displayed for CBD products on PLP
    And user click on search icon and submits the following search term 'searchItem.key'
    Then assert strength label 'CHOOSE CBD STRENGTH (MG)' is displayed for CBD products on PLP


  @VuseZAReg2
  Scenario: Shop Flavours landing page with CTA
    When users clicks on the 'ShopFlavours.key' text link
    Then url contains 'shopFlavoursUrl.key'
    And verify all the CTA present on PLP page and select product
    Then verify all CTA present on PDP page

  @VuseZAReg2
  Scenario: Shop Devices Twisp Accessories landing page with CTA
    Then user hovers on 'ShopDevices.key' and clicks on 'twispAccessories.key'
    Then url contains 'twispAccessoriesUrl.key'
    And verify all the CTA present on PLP page and select product on accessories
    Then verify all CTA present on PDP page

  @VuseZAReg2
  Scenario: Shop Devices Vuse Accessories Charging landing page with CTA
    Then user hovers on 'ShopDevices.key' and clicks on 'vuseAccessories.key'
    And verify all the CTA present on PLP page and select charger accessories
    Then verify all CTA present on PDP page

  @VuseCOReg
  Scenario: Check 'know more about' section for one-time purchase enabled product on PLP page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page
    And user hovers on one-time purchase enabled product
    Then know more about message is displayed
    And user clicks know more about subscription link
    Then user goes to know more about subscription page

  @VuseUKReg2
  Scenario: Pre selected first available nicotine strength
    When users clicks on the 'ShopFlavours.key' text link
    And user closes the alert if present
    And verify first available nicotine strength is selected

  @VuseITAnonReg2 @VuseITLive
  Scenario: Add to Cart and Change Quantity from Devices PLP for Vuse IT
    And users clicks on the 'DeviceCategoryText.key' text link
    And select product strength or colour
    Then assert Add to cart button and quantity selector CTAs works as expected from PLP
    Then confirm mini-basket displayed amount of '4'

  @VuseITAnonReg2 @VuseITLive
  Scenario: Add to Cart and Change Quantity from Flavors PLP for Vuse IT
    And users clicks on the 'eLiquidFlavoursURlText.key' text link
    And select product strength or colour
    Then assert Add to cart button and quantity selector CTAs works as expected from PLP
    Then confirm mini-basket displayed amount of '4'

  @VuseFRReg2
  Scenario: Quantity Field Selector on PLP
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And select second product color or strength on PLP
    Then assert quantity selector is displayed on each SKU with default value 1

    #add issue 866078 for vuse de
    @VuseDEReg2
    Scenario: Check equipment's categories on plp page
      When users clicks on the 'eLiquidFlavoursURlText.key' text link
      And user clicks on Filters button to open filters flyout
      Then assert equipment's categories are displayed correctly


  @VuseZAReg2
  Scenario: Sub Category landing page for vuseza
    Then user hover on 'LinkToHover' and click on 'LinkToClick' and verify url contains 'UrlToContain'
      | LinkToHover           | UrlToContain               | LinkToClick            |
      | ShopDevices.key       | vusePodUrlText.key         | vusePod.key            |
      | ShopDevices.key       | vusePodFlavoursUrlText.key | vusePodFlavours.key    |
      | ShopDevices.key       | vuseAccessoriesUrlText.key | vuseAccessories.key    |
      | ShopDevices.key       | TwispUrlText.key           | Twisp.key              |
      | ShopFlavours.key      | flavourVusePodUrl.key      | flavourVusePod.key     |
      | ShopFlavours.key      | flavourTwispUrl.key        | flavourTwisp.key       |

  @VuseZAReg2
  Scenario: Category landing page for vuseza
    And click on 'LinkToClick' and verify url contains 'UrlToContain' and verify content of the category
      | LinkToClick           | UrlToContain               |
      | ShopDevices.key       | AllDevicesUrlText.key      |
      | ShopFlavours.key      | shopFlavoursUrl.key        |

  # Automate bug 866106
  @VuseITAnonReg2
  Scenario: ePod device should not display in category epen devices
    Given user hovers on 'DeviceCategoryText.key' and clicks on 'ePen.key'
    Then assert epod is not displayed in epen devices

  @VuseUKReg2
  Scenario: Quick filters on PLP
    Given user hovers on 'eLiquidFirstMenu.key' and clicks on 'eLiquidSecondMenu.key'
    Then all quick filters display
    And all results contain the quick filter when clicking it one by one


  @VuseFRReg2
  Scenario: Filter cleareance in PLP
    Given user hovers on 'eLiquidFirstMenu.key' and clicks on 'eLiquidSecondMenu.key'
    And user click on filter option
    And select few filter option
    Then click on empty and verify filter as clear

  @VuseFRReg2
  Scenario: Quick filters on PLP VuseFR
    Given user hovers on 'eLiquidFirstMenu.key' and clicks on 'eLiquidSecondMenu.key'
    Then all quick filters display
    And all results contain the quick filter when clicking it one by one
    And select more than one options of same attribute
    And user click on filter option
    Then verify option selected in quick filter is selected in classic filter
    And unchecked first option strength


  @VuseFRReg2
  Scenario: PLP page details and product in  basket
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    Then verify product name price strength color purchase option
    And from PLP click add to cart
    Then click back button on view basket
    And user lands on plp page
    And click on basket icon
    And delete product and verify cart is empty

  @VuseITAnonReg2
  Scenario: Vuse IT-Product Sorting on PLPs
    When users clicks on the 'DeviceCategoryText.key' text link
    And url contains 'productCategoryURLText.key'
    Then verify the sorting options 'sortingOptionseCigarette.key' for 'eCigarettes.key'

  @VuseITAnonReg2
  Scenario: Filter results on PLP
    When users clicks on the 'DeviceCategoryText.key' text link
    And get all lists from page
    And user clicks on 'filter' option
    And user filters by product epods
    And get fitler count
    And user clicks done in filter pop-up
    Then confirm number of results returned is same as it in filter pop-up

  @VuseITAnonReg2
  Scenario: Clear filter on PLP
    When users clicks on the 'DeviceCategoryText.key' text link
    And get number of results returned
    And user clicks on 'filter' option
    And user filters by product epods
    And user clicks cancel in filter pop-up
    And user clicks done in filter pop-up
    Then confirm number of results returned is same as it was before
