Feature: Glo Promotion feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloKzRegression @gloKzSmoke @gloRegression @gloItRegression
  Scenario: Glo PROMOTION - Invalid Promotion applied
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode invalidPromoCodeError.key error message displayed

  @gloPlRegression
  Scenario: Glo Poland PROMOTION - Invalid Promotion applied
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And user close the free gift module
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed

  @gloDeRegression
  Scenario: GLO DE - 100% Discount Coupon applied in Shopping Cart
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then select a GLO product and view basket
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    And assert Total Charges are updated after applying promo code in Shopping Cart

  @gloItRegression
  Scenario: Glo -100% Discount Coupon applied in Shopping Cart
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message

  @gloKzRegression @gloPlRegression @gloDeRegression
  Scenario: Glo kz -100% Discount Coupon applied in Shopping Cart
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And empty basket
    And select a GLO product and view basket
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    And assert Total Charges are updated after applying promo code in Shopping Cart
    And remove the promotion

  Scenario: Glo Poland - Free Gift Added in basket - Confirmation Message
    And create a new account via api
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And user close the free gift module
    And apply discount 'Freecoupon' to the discount module and assert success message
    And assert success message of 'freeGiftAddedSuccessMessage.key' presented to user
    And confirm mini-basket displayed amount of '2'

  @gloDeRegression
  Scenario: GLO - Invalid Promotion applied
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then select a GLO product and view basket
    And apply discount code module present
    And user apply the discount coupon 'inValidCouponCode.key'
    And promocode 'invalidPromoCodeError.key' error message displayed

  #automate baklog bug 861841
  @gloDeRegression
  Scenario: Verify error message for guest user
    When select a GLO product and view basket
    And apply discount code module present
    And user apply the discount coupon 'inValidCouponCode.key'
    Then promocode 'invalidPromoCodeErrorForGuest.key' error message displayed