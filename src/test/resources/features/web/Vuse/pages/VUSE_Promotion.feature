Feature: Glo Promotion feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then empty basket

  @VuseFRReg @VuseUKLive @VuseFRLive @VuseITReg @VuseDEReg
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

  @VuseUKReg @VuseFRReg @VuseUKLive @VuseFRLive
  Scenario: Vuse PROMOTION - Invalid Promotion applied on checkout page
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed