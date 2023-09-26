 Feature: 13842 BAT PDP Theme feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

   @VuseDELive  @VuseUKLive2 @VuseFRLive @VuseITLive @VuseDEReg2 @VuseFRReg2 @VuseITAnonReg2 @VuseZAReg2
   Scenario: 13842 Navigate to PDP page via search and product selection - ensure expected PDP elements are present as expected
    And user click on search icon and submits the following search term 'searchTerm.key'
     And user closes the alert if present
    And get all lists from page
    And clicks on product link on the basis of index '1'
    Then ensure PDP elements are displayed as expected

   @VuseMXReg @VuseMXLive
   Scenario: Navigate to PDP page via search and product selection - ensure expected PDP elements are present as expected MX
     And user click on search icon and submits the following search term 'searchTermDescription.key'
     And click first result
     Then ensure PDP elements are displayed as expected

  @VuseUKReg2 @VuseUKLive2
  Scenario: Verify PDP Delivery messages on the basis of UK Timelines
   And user click on search icon and submits the following search term 'searchTerm.key'
   And user closes the alert if present
   And select product by index "productIndex.key" on plp page
   Then ensure PDP elements are displayed as expected
   And assert Delivery message on PDP on basis of current timeline

   # @VuseUKReg2 - CBD products not available on UAT1'
   Scenario: Verify label 'CHOOSE CBD STRENGTH (MG)' on PDP of CBD products
     And user click on search icon and submits the following search term 'searchCBDproductPDP.key'
     And user closes the alert if present
     And user clicks on the first link if navigated to PLP page
     Then assert strength label 'CHOOSE CBD STRENGTH (MG)' is displayed for CBD products on PDP
     And assert strength label NICOTINE LEVEL is not displayed for CBD products

   @VuseUKReg2 @VuseUKLive2
   Scenario: Verify first Flavor and Device SKU pre-selected on page load when in stock
     And users clicks on the See all Vype devices link from SHOP Menu
     And user closes the alert if present
     And select product by index "productIndex.key" on plp page
     Then assert first device SKU is pre-selected by default when in stock
     And users clicks on the See all Vype flavours link from SHOP Menu
     And select product by index "productIndex.key" on plp page
     Then assert first flavor SKU is pre-selected by default when in stock

   @VuseFRReg2 @VuseFRLive
   Scenario: Verify first Flavor and Device SKU pre-selected on page load when in stock when select a index product on PLP
     #Commented out as none of item is in stock on the plp
#     And users clicks on the See all Vype devices link from SHOP Menu
#     And user closes the alert if present
#     And click first buyable result
#     Then assert first device SKU is pre-selected by default when in stock
     And users clicks on the See all Vype flavours link from SHOP Menu
     And click first result
     Then assert first flavor SKU is pre-selected by default when in stock

   @VuseUKReg2 @VuseFRReg2 @VuseUKLive2 @VuseFRLive
   Scenario: Verify Notify Me CTA when SKU is OOS for Devices and Flavors
     And users clicks on the See all Vype devices link from SHOP Menu
     And user closes the alert if present
     And clicks on product link with first SKU OOS and navigate to PDP
     Then user selects OOS device SKU and assert Notify Me CTA
     And click on the logo
     And users clicks on the See all Vype flavours link from SHOP Menu
     And clicks on product link with first SKU OOS and navigate to PDP
     Then user selects OOS flavor SKU and assert Notify Me CTA


   @VuseUKReg2 @VuseITAnonReg2
   Scenario: VuseUK product details on product page
     And user click on search icon and submits the following search term 'ePenLinkMango.key'
     And get all lists from page
     And select product by index "productIndex.key" on plp page
     And select product strength or colour
     Then user see default One time Purchase option selected
     Then user verify Subscription option is unselected
     Then user select subscription option
     Then customers see the at the end of the "One time purchase" label the amount spent on that SKU
     And customers see the at the end of the "Subscription" label the total monthly amount for that SKU
     And on clicking on "i" icon the modal window opens up
     And user adds subscription item 'SubscriptionItemTerm.key' to the basket by quantity '5'
     And click on basket icon
     And users clicks on the 'viewBasketText.key' text link
     Then user see "You save £xx" below the whole component

   @VuseUKReg2 @VuseITAnonReg2
   Scenario: VuseUK product price not increase with quantity
     And user click on search icon and submits the following search term 'SubscriptionItemTermMultiResults.key'
     And get all lists from page
     And select product by index "productIndex.key" on plp page
     Then verify product quantity and price is displayed
     Then user increases the quantity of the selected item and verify price remain same

   #@VuseUKReg2      Mix and Match is invisible on UAT1 and live
   Scenario: VuseUK Mix and Match
     And user click on mix and match from shop flavour
     Then clicks on product link on the basis of index '1'
     Then user select subscription option
     Then user increases the quantity of product
     And click add to cart button
     And click back button on view basket
     And user click on mix and match from shop flavour
     Then clicks on product link on the basis of index '2'
     Then user select subscription option
     Then user increases the quantity of product
     And click add to cart button
     And users clicks on the 'viewBasketText.key' text link
     Then 'subscriptionMinQtyError.key' Message is displayed in Cart page
     And user click on mix and match from shop flavour
     Then clicks on product link on the basis of index '4'
     Then user select subscription option
     And click add to cart button
     And users clicks on the 'viewBasketText.key' text link
     Then assert user get subscription discount
     Then delete product from remove button
     And user click on search icon and submits the following search term 'eLiquidUrl.key'
     And get all lists from page
     And click first result
     Then user increases the quantity of product
     And click add to cart button
     And users clicks on the 'viewBasketText.key' text link
     Then assert user subscription discount not display

   @VuseZAReg2
   Scenario: DEFECT 874260 Combos of product added in cart VuseZA
     When user select combos from shop devices
     And click first result
     And verify multiple product are present in combos
     Then click add to cart button
     And users clicks on the 'viewBasketText.key' text link
     Then verify combos discount is applied

   @VuseZAReg2
   Scenario Outline: Vuze ZA PDP products overview
     Then user click on search icon and submits the following search term '<ProductList>'
     Then verify FAQ's is present for product
     And verify all CTAs should navigate to the correct pages
     Examples:
       | ProductList                      |
       | twispClearo3Starterpack.key      |
       | twispClearoPODStarterpack.key    |
       | twispTYKOPLUSAIOStarterpack.key  |
       | twispAero2.key                   |

   @VuseZAReg2
   Scenario: Shop Devices epod landing page with CTA
     When users clicks on the 'ShopDevices.key' text link
     Then url contains 'AllDevicesUrlText.key'
     Then user hovers on 'ShopDevices.key' and clicks on 'vusePod.key'
     And user verify all the CTA present on Device epod page

   @VuseFRReg3 @VuseFRLive
   Scenario: Link for Free Device Trial navigation for a guest user for ePod and ePen devices
     And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
     And user closes the alert if present
     And user clicks on the first link if navigated to PLP page
     Then user clicks on Deferred Payment link for FDT navigation
     Then assert guided sell popup is displayed
     And click popup redirect CTA on popup page
     Then assert URL contains text 'deviceTrial.key'
     Then user selects 'No' option from FDT pop-up for a user with first order
     And user click on search icon and submits the following search term 'searchDeviceEPEN.key'
     And user closes the alert if present
     And user clicks on the first link if navigated to PLP page
     Then user clicks on Deferred Payment link for FDT navigation
     Then assert guided sell popup is displayed
     And click popup redirect CTA on popup page
     Then assert URL contains text 'deviceTrial.key'

   @VuseFRReg2
   Scenario: Verify PDP page display correctly with epen product
     And user click on search icon and submits the following search term 'capsulesEliquidEPenPDPlink.key'
     And user closes the alert if present
     Then ensure PDP elements are displayed as expected

   # Automate bug 926591
   @VuseFRReg
   Scenario: Get notification message after adding item to basket on PDP
     When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
     And user click on search icon and submits the following search term 'searchTerm.key'
     And user closes the alert if present
     And click first result
     And select product strength or colour
     And click add to cart button
     Then add product to basket success message is displayed


   @VuseFRReg2
   Scenario: Q&A section present on PDP
     And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
     Then verify Q&A section is present on PDP

   @VuseFRReg2
   Scenario: PDP product description
     And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
     Then verify description is present
     And user click on learn more from pdp
     And verify the content of the learn more pdp
     And users clicks on reduire from bottom description

   #automate issue 979000
   @VuseZAReg2
   Scenario: Validate More to explore section displayed correctly
     When user click on search icon and submits the following search term 'searchTermVype.key'
     And user closes the alert if present
     And get all lists from page
     And select product by index "productIndex.key" on plp page
     And wait for the page to fully load
     Then assert text of 'moreToExploreText.key' is displayed
     And assert more explore products displayed

   @VuseITAnonReg2
   Scenario: PDP product description for Vuse IT
     Given user click on search icon and submits the following search term 'EpodDevice.key'
     And user closes the alert if present
     And get all lists from page
     When click first result
     Then verify description is present
     When user click on learn more from pdp
     Then verify the content of the learn more pdp
     And users clicks on reduire from bottom description
  