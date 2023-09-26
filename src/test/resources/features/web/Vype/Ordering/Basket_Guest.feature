Feature: BAT Basket feature - Guest
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

@COSmoke @CoLive @dk @dklive @dksmoke @ITReg2 @ITLive @VuseZAReg2 @VuseDKReg @MXReg2
Scenario: Basket tests - Basket Icon presence
    Then ensure basket icon is displayed
    And click on basket icon
    And basket is empty message is presented

@nlSmoke @ITSmoke @COSmoke @CoLive @dk @dklive @dksmoke @ITReg2 @ITLive @VuseZAReg2 @VuseDKReg @VuseDKLive @VuseZALive @MXReg2
Scenario: Basket tests - (from PDP)Populate Cart and assert header qty displayed on mini basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    And empty basket

  Scenario: Basket tests - (Add to cart from PLP) no colour selected, error message prompt presented to user
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click add to cart on a product has multiple swatches
    And assert text of 'selectProductColourOrStrengthText' is displayed

  @desmoke @ITSmoke @dk @dklive @dksmoke @ITReg2 @ITLive @VuseZAReg2 @MXReg2 @VuseITAnonReg2
  Scenario: Basket tests - (Add to cart from PLP) Populate Cart and assert header qty displayed on mini basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And empty basket

  @dk @dklive @dksmoke @ITReg2 @ITLive @VuseZAReg2 @VuseDKLive @VuseZALive #@MXReg commented because of Defect: 487546
  Scenario: Basket - empty basket Guest populates basket then removes items from basket and asserts basket is empty
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    Then empty basket
    And click on basket icon
    And basket is empty message is presented


  @frSmoke @CoLive @dk @dklive @dksmoke @ITReg2 @ITLive @VuseZAReg2 @VuseDKReg @VuseDKLive @VuseZALive @MXReg2
  Scenario: 9554 Basket Page (cart page) confirm product present and empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    Then confirm mini-basket displayed amount of '1'
    And empty basket
    And click on basket icon
    And assert text of 'basketCurrentlyEmptyMsg.key' is displayed

  @regression
  Scenario: Quantity Field Selector on Mini Basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And assert quantity selector is displayed on Mini Basket
    And assert if user is able to input any value '4' in mini basket Qty field
    And user clicks on plus button on MiniBasket qty selector and assert increase to '5'
    Then confirm mini-basket displayed amount of '5'
    And user clicks on minus button on MiniBasket qty selector and assert decrease to '4'

  @regression
  Scenario: Quantity Field Selector on Basket Page
    And search and view basket for the product 'searchTerm.key'
    And assert quantity selector is displayed on Mini Basket
    And assert if user is able to input any value '4' in mini basket Qty field
    And user clicks on plus button on MiniBasket qty selector and assert increase to '6'
    Then confirm mini-basket displayed amount of '6'
    And user clicks on minus button on MiniBasket qty selector and assert decrease to '5'

  @fr
  Scenario: customer should be able to add more than 99 quantity
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And user clicks on the View Basket cta
    And update mini-basket qty to '100'
    And cart icon should display the quantity '100'