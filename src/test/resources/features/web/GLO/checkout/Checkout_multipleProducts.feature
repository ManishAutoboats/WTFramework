Feature: Verify Checkout page with multiple products

  Background:
    Given user navigates to BAT home page

  @gloJPReg
  Scenario: Verify Order Summary (prices breakdown)
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      # Out of the stock and scripts is failing as another available
         # product is a subscription product which can not be mixed with other products
      | accessories.key |
      | velo.key        |
      #| sticks.key      |
      | devices.key     |
      #| bundle.key      |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user clicks on plus icon and product list is displayed
    And assert prices for products added
    And delivery fee and total amount is visible
    And a note saying tax is included is visible
    And user clicks on 'card_payment.key' method
    And delivery fee and total amount is visible
    And a note saying tax is included is visible
    And assert the order summary is updated
    And user clicks on 'cod_payment.key' method
    And verify associated fee is displayed in the Order summary
    And assert the order summary is updated


