Feature: Glo Promotion feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When create a new account via api and log in with the account


  Scenario: Mobile - Vuse PROMOTION - Invalid Promotion applied on checkout page
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed


  Scenario: Vuse PROMOTION - Invalid Promotion applied on basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed