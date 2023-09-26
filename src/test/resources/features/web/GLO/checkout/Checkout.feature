Feature: Verify Checkout page

  Background:  Navigate to Checkout Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket

  @gloJPReg
  Scenario: Verify COD payment with discount and Terms and conditions consent on checkout page
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And assert that page title is 'checkout.key'
    And user selects fluid delivery address
    And user selects courier delivery option
    And verify select delivery date and time fields are displayed
    And assert fee is displayed for courier delivery
    And user clicks on plus icon and product list is displayed
    And user clicks on COD payment method
    And assert fee is displayed for COD payment
    And verify associated fee is displayed in the Order summary
    #And assert error message displayed for Delivery date time
    And user selects delivery date and time
    And tick agree to terms and conditions
    And verify Place Order button is enabled
    And user clicks on place order button
    And user is redirected order success page
    And verify order confirmation email is received
    #And verify order confirmation email received - commented out since code rafactorization required and funtionality wise it is working fine

  @gloJPReg
  Scenario: Verify only GMO payment method displayed for Mail bin Shipping,neither COD payment method nor Select Date-Time slot
    And user clicks on the tile and navigates to that page
      | velo.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    Then assert Mail Bin Delivery option is displayed
    And assert courier delivery option is not displayed
    And assert select delivery date and time fields are not displayed
    And assert COD payment method is not displayed
    And assert only GMO Card payment method is displayed

  @gloJPReg
  Scenario: Verify Mail bin option is not displayed when mail bin conditions are not met
    And user clicks on the 'velo.key' product category,navigate to PDP and add quantity '11' to cart
    And user clicks on the tile and navigates to that page
      | devices.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    Then assert Mail Bin Delivery option is not displayed
    Then assert Courier Delivery option is displayed
    And verify select delivery date and time fields are displayed
    And assert COD payment method is displayed
