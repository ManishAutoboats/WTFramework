Feature: BAT Vuse Universal Smoke Test

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @VuseDEReg2 @VuseITAnonReg2 @VuseZAReg2 @COReg
  Scenario Outline: Promotion Added then remove
    When create a new account via api
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    And user clicks on the View Basket cta
    And add promotion '<Promo Code>' at basket page
    And remove the promotion
    Examples:
      | Promo Code |
      | 10Off      |

  @VuseDEReg2 @VuseITAnonReg2
  Scenario Outline: Promotion Added then complete payment
    When create a new account
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    And user clicks on the View Basket cta
    And add promotion '<Promo Code>' at basket page
    And Customer makes a home delivery purchase with "<Payment Type>"
    Then assert text of 'ThankForPurchase.key' is displayed
    Then I delete customer account
    Examples:
      | Payment Type | Promo Code |
      | mastercard   | 10Off      |

  @VuseUKReg @VuseFRReg @VuseZAReg @VuseMXReg @VuseITReg
  Scenario: 9601, 989359 PROMOTION - Invalid Promotion applied
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product by index "promotionProductIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And invalid coupon code 'invalid' applied

  Scenario: Basket - Eco tax applied without Delivery charges,without any Discounts
    Then search and view basket for the product 'searchTermWithEcoTax.key'
    And apply discount code module present
    And assert Eco tax is applied for the product

  Scenario: Basket - Sub-Total and Total if VAT, Eco tax applied without Delivery charges, with 50% Discount
    Then search and view basket for the product 'searchTermWithEcoTax.key'
    And apply discount code module present
    And assert Eco tax is applied for the product
    And apply discount code module present
    And fetch the Sub-Total, Total and Delivery charges for the product
    And apply discount 'FR50' to the discount module and assert success message
    And assert Total Charges are updated with respect to other charges after applying 50% promo code
    And assert Eco tax is applied for the product

  @VuseDEReg
  Scenario Outline: verify discount on checkout page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user navigates to PLP page and adds a product to basket
    And click on basket icon
    And user clicks on the View Basket cta
    And add promotion '<Promo Code>' at basket page
    And user navigates to checkout page
    Then assert discount is present
    Examples:
      | Promo Code |
      | 10Off      |

  @VuseZAReg
  Scenario: Promotion cart rule for 5+1 product in vuseZA
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    Then add 5 products from promotion flavour category
    And fetch the product price
    Then user add one product and get flavours discount

  @VuseITReg
  Scenario: 10% Discount Coupon applied in Shopping Cart
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and view basket for the product 'searchTerm.key'
    And apply discount '10off' to the discount module and assert success message
    Then assert Total Charges are updated after applying promo code in Shopping Cart
    And remove the promotion
    Then assert Total Charges are back after removed promo code in Shopping Cart

  @VuseITReg
  Scenario: 10% Discount Coupon applied in checkout page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and view basket for the product 'searchTerm.key'
    And click on basket icon
    And click on proceed to checkout button
    And apply discount '10off' to the discount module and assert success message
    Then assert Total Charges are updated after applying promo code in checkout page
    And remove the promotion
    Then assert Total Charges are back after removed promo code in checkout page