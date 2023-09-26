
Feature: VELO Promotion feature - Mobile


  @RegVeloZA_Mobile
  Scenario:VELO ZA Valid user get discount for valid coupon code
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And empty basket before adding related products
    And checkout more than "2" available product
    And apply "10off" valid coupon code



  @RegVeloZA_Mobile
  Scenario: VELO ZA-Freecan Flow
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And create a new account
    And checkout any available product
    And I add the delivery address during payment
    And user views the cart
    And user clicks on checkout button
