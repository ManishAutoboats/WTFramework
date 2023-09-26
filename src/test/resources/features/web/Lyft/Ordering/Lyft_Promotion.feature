##This is the Promotion feature file
  ##Testing
    ## Valid Promtion can be applied
    ## Amount removed is correct
    ## Cancellation of promotion
    ## Invalid promotion
    ## Error / validation messages
Feature: BAT Promotion Feature - Lyft

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftRegression
  Scenario: 100% Discount Coupon applied in Shopping Cart
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and checkout product "searchTermdi.key"
    Then Payment page details confirmed
    And apply discount code module present
    And fetch the Sub-Total, Total and Delivery charges for the product
    And apply discount '10off' to the discount module and assert success message
    Then assert Total Charges are updated after applying promo code in Shopping Cart

  @LyftRegression2
  #Bug880842
  Scenario: Promotion Added then remove and repeat
    When create a new account
    And search and checkout product "searchTermdi.key"
    Then Payment page details confirmed
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    Then remove the promotion
    And apply discount '10off' to the discount module and assert success message
    Then remove the promotion

  @LyftRegression2
  Scenario: Invalid Promotion applied
    When create a new account
    And search and checkout product "searchTermdi.key"
    Then Payment page details confirmed
    And apply discount code module present
    And invalid coupon code 'invalid' applied