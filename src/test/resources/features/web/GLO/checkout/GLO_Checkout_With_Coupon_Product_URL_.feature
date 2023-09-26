@gloPlRegression2
Feature: Glo checkout - Checkout using Coupon and Product URL
  Background: Navigate to BAT Home page
    When user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: Glo Poland Checkout - Apply Coupon and Add Product via URL
    When create a new account via api
    And user navigates to BAT home page
    And launch URL 'couponWithProductURL.key' with coupon and product SKU and assert it returns success
    And user navigate to mini cart as a guest user with coupon 'couponCode.key' applied and place order via URL