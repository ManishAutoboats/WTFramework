##This is the Promotion feature file
  ##Testing
    ## Valid Promtion can be applied
    ## Amount removed is correct
    ## Cancellation of promotion
    ## Invalid promotion
    ## Error / validation messages
Feature: BAT Promotion feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket

  @VuseUKReg @vuseZAReg
  Scenario: 9601 PROMOTION - Valid Promotion applied
    And user performs search
    And get all lists from page
    And select product by index "promotionProductIndex.key" on plp page
    And select product colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    And assert message of 'Your coupon was successfully applied.' presented to user
    And remove promotion
    And click on the logo


  @VuseFRReg
  Scenario: FR PROMOTION - Valid Promotion applied
    Given user performs search
    And get all lists from page
    And click first result
    And select product colour
    When click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    Then assert message of 'promotionSucessMsg.key' presented to user
    And remove promotion


  @smokeLite @smokeFast @promo @regression @9601 @IEReg @fr @frlive
  Scenario: 9601 PROMOTION - Invalid Promotion applied
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And apply discount code module present
    And apply discount 'invalidPromo' to the discount module and assert success message
    And assert text of 'invalidPromoCodeText.key' is displayed

  @fr @frlive
  Scenario: Basket - Eco tax applied without Delivery charges,without any Discounts
    Then search and view basket for the product 'searchTermWithEcoTax.key'
    And apply discount code module present
    And assert Eco tax is applied for the product

  @IEReg @ITReg @de
  Scenario: 10% Discount Coupon applied in Shopping Cart
    And search and view basket for the product 'searchTerm.key'
    And apply discount code module present
    And apply discount '10off' to the discount module and assert success message
    And assert Total Charges are updated after applying promo code in Shopping Cart
    And remove promocode from cart

  #@fr
  #Scenario: Basket - Sub-Total and Total if VAT, Eco tax applied without Delivery charges, with 100% Discount
  #  Then search and view basket for the product 'searchTermWithEcoTax.key'
  #  And assert Eco tax is applied for the product
  #  And apply discount code module present
  #  And fetch the Sub-Total, Total and Delivery charges for the product
  #  And apply discount 'FR100' to the discount module and assert success message
  #  And assert Total Charges are updated after applying promo code
  #  And assert Eco tax is applied for the product

  @fr
  Scenario: Basket - Sub-Total and Total if VAT, Eco tax applied without Delivery charges, with 50% Discount
    Then search and view basket for the product 'searchTermWithEcoTax.key'
    And apply discount code module present
    And assert Eco tax is applied for the product
    And apply discount code module present
    And fetch the Sub-Total, Total and Delivery charges for the product
    And apply discount 'FR50' to the discount module and assert success message
    And assert Total Charges are updated with respect to other charges after applying 50% promo code
    And assert Eco tax is applied for the product

