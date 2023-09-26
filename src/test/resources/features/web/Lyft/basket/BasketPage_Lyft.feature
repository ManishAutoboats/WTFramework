Feature: Basket
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And users clicks on the 'viewBasketText.key' text link

  @LyftRegression2
  Scenario: Basket - Price includes taxes
    Given div cart summary pane is displayed
    And assert text of 'basketHeadingText.key' is displayed
    And assert text of 'taxRateText.key' is displayed on lyft cart page
    Then verify product cost include taxes

  @LyftRegression2
  Scenario: Basket page - Invalid promotion code
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed

  @LyftRegression2
  Scenario: Basket page - price according to product available
    Then verify price of product accordingly in summary cart

