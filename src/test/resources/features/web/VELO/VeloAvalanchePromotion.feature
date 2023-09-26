
Feature: VELO Promotion feature

  # @veloPLReg commentted till cart rule changed
  Scenario: 741017 Velo PL AC1 > Discount with First Purchase Price Cart Rule
  Given user navigates to BAT home page for language "pl"
  And create a new account via api
  And select allow all from cookie popup and select over 18 age confirmation option
  And empty basket before adding related products
    And checkout more than "2" available product
  And checkout any available product and check first order discount


  @veloPLReg
  Scenario: 741017 Velo PL AC2 > 10% Discount for Purchase more than 5 item
    Given user navigates to BAT home page for language "pl"
    And create a new account via api
    And select allow all from cookie popup and select over 18 age confirmation option
    And checkout more than "5" available product and get discount


  @veloZAReg
  Scenario:VELO ZA Valid user get discount for valid coupon code
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    And empty basket before adding related products
    And checkout more than "2" available product
    And apply "10off" valid coupon code

 # @veloPLReg commentted till cart rule changed
  Scenario: 741017 Velo PL AC2 > Valid user get discount for valid coupon code
    Given user navigates to BAT home page for language "pl"
    And create a new account via api
    And select allow all from cookie popup and select over 18 age confirmation option
    And empty basket before adding related products
    And checkout more than "2" available product
    And apply "QA_OFF1" valid coupon code


  @veloZAReg
  Scenario: VELO ZA-Freecan Flow
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And create a new account
    And checkout any available product
    And I add the delivery address during payment
    And user views the cart
    And user clicks on checkout button
    #Remove comment when promocode working fine
    #And apply "FREECAN" valid coupon code
