#@LyftRegression
Feature: BAT Basket feature - Guest
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

@LyftSmoke  @LyftLive @LyftDKReg @LyftDKSmoke @LyftDKLive
  Scenario: Basket tests - Basket Icon presence
    Then ensure basket icon is displayed

  @LyftSmoke  @LyftLive @LyftDKSmoke @LyftDKLive
  Scenario: Basket tests - (Add to cart from PLP) Populate Cart and assert header qty displayed on mini basket
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'

  @LyftRegression
  Scenario: Basket - empty basket Guest populates basket then removes items from basket and asserts basket is empty
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    Then empty basket
    And click on basket icon
    Then basket is empty message is presented

  @LyftRegression
  Scenario: Basket - multiple product into the cart
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And user click on search icon and submits the following search term 'searchTermToAddInCart.key'
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '2'
    And click on basket icon
    And click on proceed to checkout button
    And navigate to previous page
    Then empty basket
    And click on basket icon
    Then basket is empty message is presented