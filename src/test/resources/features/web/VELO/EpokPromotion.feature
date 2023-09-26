
Feature: Epok Promotion feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on PersonIcon and Navigate to the Login Page
    When Epok user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And Epok empty basket
    When user click on humberger menu
    When user clicks on product menu
    And get all lists from page
    Then user Clicks on add to cart button
    And user click on cart icon and Navigate to cart Page
    And Go to checkout page
    And Epok Payment page details confirmed

@promotion
  Scenario: Epok PROMOTION - Valid Promotion applied
   
    And Epok user select Card Payment option 
    And Epok user select debit card as payment option
    And user select the Master card as type
    And user fills Master or Visa card information
    And Epok select agree to terms and conditions for card payment
    And Epok user apply the discount coupon 'validCouponCode.key'
    And Epok promocode error 'prmotionAppliedSucessMsg.key' message displayed
    
  @epokSmoke @epokLive @epokRegression 
  Scenario: Epok PROMOTION - Invalid Promotion applied
    And Epok user select Card Payment option
    And Epok user select debit card as payment option
    And user select the Master card as type
    And user fills Master or Visa card information
    And Epok select agree to terms and conditions for card payment
    And Epok user apply the discount coupon 'inValidCouponCode.key'
  # And Epok invalid promocode error 'invalidPromoCodeError.key' message displayed