Feature: Glo Promotion feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
  Scenario: Glo -100% Discount Coupon applied in Shopping Cart
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message