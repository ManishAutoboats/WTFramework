#TODO
Feature: 22658 Basket - VAT & shipping details

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user click on search icon and submits the following search term 'searchTermdi.key'
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link

  @LyftRegression
  Scenario: Basket - VAT & shipping details
    And div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed
    And user selects proceed to checkout from cart page
    And login with valid details
    And Shipping page details confirmed

  #@LyftRegression2 @LyftLive
  Scenario: Cart Page - Guest - assert navigation with no change in cart quantity on switching language using language selector
    Then confirm mini-basket displayed amount of '1'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to cart page with 'urlInEnglishOnLangSwitch.key' and response status code as 200
    Then assert that page title is 'basketPageTitleInEN.key'
    Then confirm mini-basket displayed amount of '1'
    Then assert text is displayed in English after switching language
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    Then confirm mini-basket displayed amount of '1'


  @LyftRegression
  Scenario: Basket - price update with increase in quantity
    And div cart summary pane is displayed
    Then verify product cost include taxes
    Then increase product quantity and verify pricing update accordingly

