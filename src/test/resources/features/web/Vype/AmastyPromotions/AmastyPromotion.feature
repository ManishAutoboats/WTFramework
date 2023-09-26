##This is the Promotion feature file
  ##Testing
    ## Valid Promtion can be applied
    ## Amount removed is correct
    ## Cancellation of promotion
    ## Invalid promotion
    ## Error / validation messages
@regression
Feature: BAT Amasty Promotion feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And create a new account

  Scenario: PROMOTION - Percent of product price discount Promotion with invalid promotion
    And search and add 2 products into the cart 'searchVypeStartKit.key'
    And invalid coupon code 'invalid' applied

  Scenario: PROMOTION - Percent of product price discount valid promotion
    And search and add 2 products into the cart 'searchVypeStartKit.key'
    And coupon code 'ASP-UKEPEN3100' successfully applied
    And coupon code validation on cartpage '-£4.99'
    And click on basket icon

  Scenario: PROMOTION - Percent of product price discount Promotion Checkout
    And search and add 2 products into the cart 'searchVypeStartKit.key'
    And click on proceed to checkout button
    And coupon code 'ASP-UKEPEN3100' successfully applied
    And visa order completion then navigate to order history page
    And coupon code validation on OrderHistorypage '-£4.99'
    And search and add 2 products into the cart 'searchVypeStartKit.key'
    And invalid coupon code 'ASP-UKEPEN3100' applied

  Scenario: Fixed amount discount for whole cart - Promotion for Vype UK
    And user click on search icon and submits the following search term 'eLiquidesUrlText.key'
    And click first result
    And quantity should be set to '6'
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And coupon code 'ASP-10OFFCART1' successfully applied
    And coupon code validation on cartpage '-£3.96'

  Scenario: Fixed amount discount for whole cart - Promotion for Vype UK checkout
    And user click on search icon and submits the following search term 'eLiquidesUrlText.key'
    And click first result
    And quantity should be set to '6'
    And select product strength or colour
    Then click add to cart button
    And click on proceed to checkout button
    And coupon code 'ASP-10OFFCART1' successfully applied
    And visa order completion then navigate to order history page
   And coupon code validation on OrderHistorypage '-£17.92'
    And user click on search icon and submits the following search term 'eLiquidesUrlText.key'
    And click first result
    And quantity should be set to '6'
    And select product strength or colour
    Then click add to cart button
    And click on proceed to checkout button
    And invalid coupon code 'ASP-10OFFCART1' applied

  Scenario: Fixed price for product set for Vype UK
    And user click on search icon and submits the following search term 'maclarenRacingSearch.key'
    And click add to cart button
    And search and add the product into the basket 'BlandedTabacoo.key'
    And user click on search icon and submits the following search term 'Sleeve.key'
    And click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And coupon code validation on cartpage '-£11.48'
    And click on proceed to checkout button
    And visa order completion then navigate to order history page
    And coupon code validation on OrderHistorypage '-£11.48'

  Scenario: Fixed Discount each 2-d,4-th, 6th with 15% Off - Promotion for Vype UK
    And user click on search icon and submits the following search term 'liquidBottle.key'
    And click first result
    And quantity should be set to '3'
    And click add to cart button
    And search and add the product into the basket 'liquidBottle.key'
    And coupon code validation on cartpage '-£3.96'
    And click on proceed to checkout button
    And visa order completion then navigate to order history page
    And coupon code validation on OrderHistorypage '-£3.99'

  Scenario: Percent Discount each 2-d,4-th, 6th with 15% Off - Promotion for Vype UK
    And user click on search icon and submits the following search term 'ePodStarterVapeKit.key'
    And click first result
    And quantity should be set to '4'
    And click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And coupon code 'ASP-50PCOFF246-UK' successfully applied
   And coupon code validation on cartpage '-£5.00'
    And click on proceed to checkout button
    And visa order completion then navigate to order history page
    And coupon code validation on OrderHistorypage '-£5.00'

  Scenario: Fixed amount discount Promotion for Vype UK
    And user hovers over on the SHOP header link
    And users clicks on the 'ePen3Device.key' text link
    And users clicks on the 'mariglod.key' text link
    And quantity should be set to '6'
    And select product strength or colour
    Then click add to cart button
    And users clicks on the 'viewBasketText.key' text link
    And coupon code 'ASP-2OFFITEM' successfully applied
   And coupon code validation on cartpage '-£10.00'
    And click on proceed to checkout button
    And visa order completion then navigate to order history page
    And coupon code validation on OrderHistorypage '-£10.00'


