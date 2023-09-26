Feature: BAT Vuse Universal Smoke Test - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@RegVuseFR_Mobile
  Scenario: Mobile - PROMOTION-Invalid Promotion applied
    And create a new account via api and log in with the account
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on proceed to checkout button
    And invalid coupon code 'invalid' applied

  @RegVuseFR_Mobile
  Scenario: Mobile - FR PROMOTION - Valid and Invalid Promotion applied
    And create a new account via api and log in with the account
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product colour
    When click add to cart button
    And user clicks on the View Basket cta
    Then apply discount '10off' to the discount module and assert success message
    And remove the promotion
    Then invalid coupon code 'invalid' applied
