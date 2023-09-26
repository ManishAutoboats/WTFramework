#This is the PLP feature file
  ##Testing
    ## PLP Filtering story
   @32298 
Feature: PLP filtering to left hand side

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @ITReg2 @ITLive
  Scenario: 21490 32298 PLP filtering to left hand side
    And users clicks on the 'DeviceCategoryText.key' text link
    And assert filters are in place as expected
    And open eLiquid Flavours from the Categories menu
    #And users clicks on the '+' text link
    #Then url contains 'DeviceUrlText.key'
    Then url contains 'LiquidiUrlText.key'
    #And users clicks on the 'OPEN' text link
    #Then url contains 'system_type-open'
    
@regression
  Scenario: PLP filtering to left hand side - Vype UK
    And user hovers over on the SHOP header link
 	And user hovers over on the See all Vype devices link
    Then users clicks on the See all Vype devices link from SHOP Menu
    And assert filters are in place as expected
    And user closes the alert if present
    And user hovers over on the SHOP header link
 	And user hovers over on the See all Vype flavours link
    Then users clicks on the See all Vype flavours link from SHOP Menu
    #And users clicks on the '+' text link
    Then url contains 'DeviceUrlText.key'
    #And users clicks on the 'OPEN' text link
    #Then url contains 'system_type-open'

  @delive @de @VuseDEReg2
  Scenario: PLP URLs - Assert Internal Link Redirects
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And assert internal redirect of PLP links URLs with success status code

  @regression @live
  Scenario: PLP switch Devices and Accessories colour
    When users clicks on the 'ShopDevices.key' text link
    And user closes the alert if present
    And select product colour and assert device color switch on PLP
    When user click on search icon and submits the following search term 'AccessoriesFilter.key'
    Then select product colour and assert device color switch on PLP

  @regression @live
  Scenario: PLP Filter Devices and Flavours by Consumable Type
    When users clicks on the 'ShopDevices.key' text link
    And user closes the alert if present
    Then user clicks on Filters button to open filters flyout
    And assert Devices Category section on flyout
    And assert list of consumable types for 'Devices' and CTA for one type
    When users clicks on the 'ShopFlavours.key' text link
    Then user clicks on Filters button to open filters flyout
    And assert Devices Category section on flyout
    And assert list of consumable types for 'Flavours' and CTA for one type

  @regression
  Scenario: Quantity Field Selector on PLP for each product
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select first product color or strength on PLP
    And assert quantity selector is displayed on each SKU with default value 1
    And assert if user is able to input any value '4' in Qty text field
    Then confirm mini-basket displayed amount of '4'
    Then close side panel basket
    And user clicks on plus button on qty selector and assert qty increase to '5'
    Then confirm mini-basket displayed amount of '9'
    And user clicks on minus button on qty selector and assert qty decrease to '4'

  @live
  Scenario: Quantity Field Selector on PLP for each product on PROD
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And select first product color or strength on PLP
    And assert quantity selector is displayed on each SKU with default value 1

   @MXReg2
  Scenario: Vype flavours plp validation
    And user hovers on 'ShopFlavours.key' and clicks on 'EpenCartridges.key'
    And user lands on plp page
  @fr
  Scenario: PLP - customer should be able to add more than 10 quantity
    And user click on search icon and submits the following search term 'searchTerm.key'
    And user closes the alert if present
    And select first product color or strength on PLP
    And update the quantity in plp to '15' and add to cart
    Then confirm mini-basket displayed amount of '15'

  @VuseFRReg3 @VuseITAnonReg3
  Scenario: PLP switch Devices colour
    When users clicks on the 'DeviceCategoryText.key' text link
    And user closes the alert if present
    Then select product colour and assert device color switch on PLP
