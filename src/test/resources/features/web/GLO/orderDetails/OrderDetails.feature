Feature: Verify COD and Card Order Details page and cancel order

  Background:   Navigate to Checkout Page
    Given user navigates to BAT home page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option

  @gloJPReg
  Scenario: Verify order details page for COD payment and cancel order
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user selects fluid delivery address
    And user selects courier delivery option
    And verify courier delivery option is selected
    And user selects delivery date and time
    And user clicks on COD payment method
    And verify associated fee is displayed in the Order summary
    And user clicks on plus icon and product list is displayed
    And assert the order summary is updated
    And verify grand total on checkout page
    And tick agree to terms and conditions
    And verify Place Order button is enabled
    And user clicks on place order button
    And get generated Order Number
    And user clicks on manage order link
    And verify Order is displayed in Order history page
    And users clicks on the View Order link for generated Order Number
#    And assert header 'OrderDetails.key' is displayed
    And assert COD fee is displayed
    And assert order details with history page
    And assert order details is displayed
    And assert order summary section
    And verify cancel button is displayed with note
    And assert payment and shipping method
    And assert products section
    And click on Cancel order button and confirm
    And verify order is canceled
    And user can navigate back to the order list via breadcrumb
    And assert header 'YourOrders.key' is displayed
    And verify order cancelled email is received

   Scenario: Verify Order Details Page
    And user clicks on person icon
    And user clicks on My Orders
    And assert header 'YourOrders.key' is displayed
    And users clicks on the View Order link
    And assert header 'OrderDetails.key' is displayed
    And assert order details is displayed

  @gloJPReg
  Scenario: Verify Header and footer on Order Details Page
    And user clicks on person icon
    And user clicks on My Orders
    And assert header 'YourOrders.key' is displayed
    And users clicks on the View Order link
    And header is displayed
    And glo velo logo is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert basket icon link
    And assert homepage icon link
    And assert internal redirect of header link URLs with success status code
    And assert links in person icon
      | yourInformation_glo     | yourInformationURL.key    |
      | yourInformation_velo    | yourInformationURL.key    |
      | yourRegularDelivery     | myRegularDelivery.key     |
      | myOrders                | myOrdersURL.key           |
      | deviceRegistration      | deviceRegistrationURL.key |
      | signOut                 | logoutURL.key             |
    # Below is merged test
    And footer is present and displayed
    And assert content links with success status code
    And assert glo velo contact details are displayed
    And assert copyright is present and assert text
    And assert social media links with success status code
      | instagram |
      | line      |
      | twitter   |


  @gloJPReg
  Scenario: Verify order details page for Card payment and cancel order
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | velo.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user selects verified delivery address
    And verify mail bin delivery option is selected
    And assert select delivery date and time fields are not displayed
    And user clicks on plus icon and product list is displayed
    And user clicks on GMO Card payment method when mail bin conditions are met
    And enters Credit card details
    And assert the order summary is updated
    And verify grand total on checkout page
    And tick agree to terms and conditions for card payment
    And verify Card Place Order button is enabled
    And user clicks on Card place order button
    And get generated Order Number
    And user clicks on manage order link
    And verify Order is displayed in Order history page
    And users clicks on the View Order link for generated Order Number
    And assert order details with history page
    And assert order details is displayed
    And assert payment and shipping method
    And assert products section
    And verify cancel button is displayed with note
    And click on Cancel order button and confirm
    And verify order is canceled
    And verify order cancelled email is received
