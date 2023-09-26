Feature: Verify Order Success page

    #Todo - Need to check impact of updated address link (CSS change) for "fluid delivery address"
  Background: Navigate to Checkout Page
    Given user navigates to BAT home page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user selects fluid delivery address
    And user selects courier delivery option
    And user selects delivery date and time
    And user clicks on COD payment method
    And tick agree to terms and conditions
    And user clicks on place order button
    And user is redirected order success page

  @gloJPReg
  Scenario: Verify Order Success page
    And assert header 'OrderConfirmation.key' is displayed
    And verify thank you note is displayed
    And verify Cancellation note is displayed
    And verify Order number is displayed
    And assert Order Number link redirects to order details
    And verify Manage Orders button is displayed
    And assert manage orders link redirects to order history
    And verify link to Homepage is displayed
    And assert Homepage link redirects to homepage
    And header is displayed
    And glo velo logo is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert basket icon link
    And assert homepage icon link
    And assert internal redirect of header link URLs with success status code
    And assert links in person icon
      | yourInformation_glo    | yourInformationURL.key    |
      | yourInformation_velo   | yourInformationURL.key    |
      | myOrders           | myOrdersURL.key           |
      | deviceRegistration | deviceRegistrationURL.key |
      | signOut            | logoutURL.key             |
    And footer is present and displayed
    And assert content links with success status code
    And assert glo velo contact details are displayed
    And assert copyright is present and assert text
    And assert social media links with success status code
      | instagram |
      | line      |
      | twitter   |




