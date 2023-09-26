##This is the one page checkout content checks, functionality is proved in the actual payments tests
Feature: Checkout - One Page Checkout Contents verification
Background: Navigate to BAT Home page
	Given user navigates to BAT home page
	And select allow all from cookie popup and select over 18 age confirmation option

@gloItLive @gloDeLive @gloPlLive @gloItRegression @gloDeRegression @checkout
Scenario: 22647 One page Checkout content checks
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And Payment page details confirmed
	And url contains 'checkoutUrl.key'
	And assert text of 'ShippingAddressText.key' is displayed
	And assert text of 'ShippingMethodHeading.key' is displayed
	And assert text of 'orderSummaryText.key' is displayed
	And assert text of 'discountCodeText.key' is displayed
	And assert that one block summary div is displayed
	And selectable shipping options displayed
	And select credit card radio box
	And assert credit card information for presented to user

  @gloPlRegression
  Scenario: 22647 Glo Poland One page Checkout content checks
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And url contains 'checkoutUrl.key'
    And assert text of 'ShippingAddressText.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    And assert text of 'paymentMethodText.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert text of 'discountCodeText.key' is displayed
    And assert that one block summary div is displayed
    And selectable shipping options displayed
    And select credit card radio box
    And assert credit card information for presented to user

  #Needs to enable this scenarios again once #505365 is done on GLO PL, currently, scenarios are not valid
  #@gloItLive glo it blocked by bug-610881 #@gloPlLive #@gloDeLive blocked by bug
  Scenario: Populate selected Address from Look up and amend manually - checkoutOverlay
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And Payment page details confirmed
    And click add new address button
  	When Glo start entering the address in checkoutOverlay with streetKeyword.key
  	Then Glo should be presented with Loqate address look up auto-completion
    When Glo has selected an address from the auto-completion
    Then Glo address fields should be populated with the selected address
    And Glo can amend the fields manually if desired

  #@gloKzRegression ToDO: UnComment the tag once bug 115330 fixed - existing issue at add new address on checkout page
  # USER STORY 115330
  Scenario: Delivery Address Validation - Error Message when selected other than list of postcodes displayed in Checkout Page
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And Payment page details confirmed
    Then assert checkout-delivery-notice is present with list of postcodes
    When click add new address button
    And complete the form and submit with postcode.key which is not present in list of postcodes
    And user choose shipping method
    And Select COD CheckBOX
    And select place order from Card
    Then assert text of 'deliveryAddressError.txt' is displayed
    When user selects correct delivery address
    And select place order from Card
    Then Order Confirmation page thankYouMessageHeading is displayed

  @gloKzRegression
  Scenario: Free shipping threshold & shipping cost recommendation message
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And click on basket icon
    Then user Should see the free shipping recommendation message
    And assert text of 'freeShippingRecommendationMessage1.key' is displayed

  @gloKzRegression
  Scenario: Apply shipping cost at checkout if total products cost is under shipping threshold
    When create a new account via api
    And user login with credentials registered via api
    And user closes the RDB pop-up banner if present
    Then assert shipping bar message displayed on top of the page
    And fetch free shipping threshold price from the message displayed
    And Glo user clicks on Shop link and click on 'ShopCategory.key' category link
    And click on first result
    When click add to cart button js
    And click on basket icon
    When click on proceed to checkout button on mini cart
    Then Payment page details confirmed
    And assert shipping cost is applied if products total price is less than shipping threshold

  @gloJPReg @gloJPLive
  Scenario: Verify user is not allowed to place order if address is not saved for user
    Then user signs in to the site credentials details 'loginValidEmail3.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    Then address is not saved for user
    And assert update address link is present for address1
    #And assert update address link is present for address2
    And assert payment methods section is not displayed
    And assert order summary section is not displayed

  @gloJPReg @gloJPLive
  Scenario: Verify Order Summary Products in Checkout page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    Then Order Summary is displayed
    And total cart value and number of items is displayed
    And user clicks on plus icon and product list is displayed
    And assert product name, price,image and quantity is displayed for each product
    And verify products added
    And assert the order summary is updated
    And verify grand total on checkout page
    And user clicks on collapse icon

  @gloJPReg
  Scenario Outline: Verify Card payment fails for invalid details
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user adds product to cart and navigates to checkout
    And user selects fluid delivery address
    And user selects delivery date and time
    And user clicks on Card payment method
    And enters Credit card details '<cardNumber>' '<expiryMonth>' '<expiryYear>' '<cvv>'
    And tick agree to terms and conditions for card payment
    And user clicks on Card place order button
    And assert '<cardError>' '<cvvError>' '<dateError>' message displayed for invalid card details
    Examples:
      | cardNumber                      | expiryMonth    | expiryYear    | cvv                     | cardError                           | cvvError               | dateError               |
      | invalidCardNumber.key           | Month.key      | Year.key      | invalidSecurityCode.key | cardError_text.key                  | cvvError_text.key      |                         |
      | insufficientFundsCardNumber.key | Month.key      | Year.key      | validSecurityCode.key   | insufficientFundsCardError_text.key |                        |                         |
      |                                 | blankMonth.key | blankYear.key |                         | blankCardError_text.key             | blankCvvError_text.key | blankDateError_text.key |

  @gloJPReg
  Scenario: Verify discount code field is available and add valid / invalid discount code
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user adds product to cart and navigates to checkout
    And verify discount code field is present
    And assert that 'discountHeading.key' and 'discountNote.key' is displayed
    And enter invalid discount code and click apply
    And assert error message for coupon is displayed
    # -> Comment below code as it was based on the pre-prd configuation for coupoun and causing automation failure <-#
#    And enter valid discount code and click apply
#    And assert success message for coupon is displayed
#    And assert remove discount coupon button is available
#    And remove existing coupon and add new coupon code
#    And assert success message for coupon is displayed
#    And assert remove discount coupon button is available

  @gloJPReg @gloJPLive
  Scenario: Verify Courier Delivery fee, COD fee  on checkout page
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user adds product to cart and navigates to checkout
    And verify courier delivery option is selected
    And assert fee is displayed for courier delivery
    And assert courier fee amount
    And assert payment methods section is present
    And assert fee is displayed for COD payment
    And order summary prices breakdown is displayed
    And verify associated fee is displayed in the Order summary
    And assert terms and conditions text for payment
    And assert cvv hint for card payment
    And user clicks on COD payment method
    And assert COD fee amount on checkout page

  @gloJPReg @gloJPLive
  Scenario: Verify Header and Footer on Checkout Page and checking Ts and Cs
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user adds product to cart and navigates to checkout
    And assert that page title is 'checkout.key'
    And verify Terms and Conditions is present
    And verify title for terms and conditions
    And verify title for Glo contacts
    And verify title for Velo contacts
    And header is displayed
    And glo velo logo is displayed
    And person icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert basket icon link
    And assert internal redirect of header link URLs with success status code
    And assert homepage icon link
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

  Scenario Outline: Verify user is redirected to checkout page on payment failure
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | accessories.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user selects fluid delivery address
    And user selects delivery date and time
    And user clicks on Card payment method
    And enters Credit card details '<cardNumber>' '<expiryMonth>' '<expiryYear>' '<cvv>'
    And tick agree to terms and conditions for card payment
    And verify Card Place Order button is enabled
    And user clicks on Card place order button
    And user clicks on cancel on next page
    And verify user is redirected to 'checkout.key' page
    Examples:
      | cardNumber            | expiryMonth | expiryYear | cvv                   |
      | paymentFailedCard.key | Month.key   | Year.key   | validSecurityCode.key |

  @gloJPReg @gloJPLive
  Scenario: Verify user can place an order in M2 by using fluid address when shiping address 2 is in 'Pending Verification'
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | devices.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And assert that page title is 'checkout.key'
    And assert 'shippingAddress.key' is displayed
    And assert update address link
    And update address
    And verify address contains updated text
    And user selects delivery date and time
    And user clicks on COD payment method
    And tick agree to terms and conditions
    And verify Place Order button is enabled

  @gloJPReg
  Scenario: Update Shipping address 2 with invalid details and verify status is updated to pending verification
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | velo.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user clicks on update address link for address2
    And updates shipping address2 with invalid details
    And address2 is pending verification for the user

  @gloJPReg
  Scenario: Place order CTA id disabled when Terms and Conditions are not selected
    Then user signs in to the site credentials details 'loginValidEmail.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | velo.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user selects fluid delivery address
    And assert update address link is present for address2
    And verify Place Order button is disabled

    #@gloJPReg @gloJPLive
  Scenario: Update Shipping address 2 and verify status is updated to verified and place order button is enabled
      Then user signs in to the site credentials details 'loginValidEmail2.key' 'loginValidPassword.key'
    And user clicks on Close option
    And user navigates to Cart page and empty basket
    And user clicks on the tile and navigates to that page
      | velo.key |
    And user navigates to cart page
    And gloJp user navigates to checkout page
    And user clicks on update address link for address2
    And updates shipping address2
    And assert address2 status is verified
    And user selects delivery date and time
    And user clicks on COD payment method
    And tick agree to terms and conditions
    And verify Place Order button is disabled
    And user selects verified delivery address
    And assert address2 is reflected in address1
    And verify Place Order button is enabled
    And user clicks on person icon

  @gloPlRegression2
  Scenario: Glo Poland - Assert Error validation messages for Address fields on Checkout page
    When create a new account
    And user clicks on PersonIcon and Navigate to My Account Page
    And user add address details after registration - GloPL
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And click add new address button
    And user enters more than '51' characters in Street field and assert error message 'errMaximum50CharactersMsg.key'
    And user enters more than '11' characters in House number field and assert error message 'errMaximum10CharactersMsg.key'
    And user enters string values in House number field and assert error message 'errNumericReqdHouseFieldMessage.key'
    And user enters more than '11' characters in Apartment number field and assert error message 'errMaximum10CharactersMsg.key'
    And user enters string values in Apartment number field and assert error message 'errNumericReqdFlatFieldMessage.key'
    And user creates a new address on Checkout page - GloPL

  @gloPlRegression2
  Scenario: Checkout - Add New Address - Verify Tax number field contains numeric data in format XXX-XXX-XX-XX and add address
    When create a new account
    And user clicks on PersonIcon and Navigate to My Account Page
    And user add address details after registration - GloPL
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And click add new address button
    And user enters '10' numeric characters in Tax Number field and assert format
    And user creates a new address on Checkout page - GloPL
    And click add new address button
    And user clears Tax Number data as the field is optional
    And user creates a new address on Checkout page - GloPL

  @gloPlRegression @gloPlLive
  Scenario: Checkout - Add New Address - Tax number field Error Validations
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And Payment page details confirmed
    And click add new address button
    And user enters '10' numeric characters in Tax Number field and assert format
    And user enters '10' letters in Tax Number field and assert error message 'errValidTaxNumberMsg.key'
    And user enters more than '10' characters in Tax Number field and assert field accepts '10' digits as format

  @gloPlRegression
  Scenario: Glo Poland - Assert Fast Address Finder field is hidden and Error validation messages for blocked billing and shipping phone numbers on Checkout page
    When create a new account via api
    Then user clicks on Edit Billing Address Link
    And user enters 'blockedPhoneNumber.key' in phone number field on Address pop-up
    Then user selects Default Billing Address radiobutton and click Save Address - Glo
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And user select product add to basket and view basket
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And click add new address button
    And assert Fast Address Finder field is not displayed
    And user clicks on Cancel button from Address pop-up screen
    And user select PayU as payment option, select TCs and click Place Order
    And assert error message 'blockedPhoneNumErrMessage.key' displayed on top of Checkout page
    And click on the logo
    And user navigates to my account page from header
    Then users clicks on Edit Shipping Address link
    And user enters 'blockedPhoneNumber.key' in phone number field on Address pop-up
    Then click the save address button
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Payment page details confirmed
    And assert error message 'blockedPhoneNumErrMessage.key' displayed in place of Delivery Methods
    And assert Delivery Method input is not displayed for restricted address

  @gloPlLive
  Scenario: Glo Poland Live - Add New Address - Assert Fast Address Finder field is hidden on Checkout page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and enter product 'ProductName.key' in the search bar
    And user select product add to basket and view basket
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And click add new address button
    And assert Fast Address Finder field is not displayed

  #add backlog case for bug 876761
  @gloPlRegression2
  Scenario: User should not see free gift items add to cart message on order confirmation page
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And user click on 'device' menu navigation
    And click first result
    Then click add to cart button js
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout for guest user
    And select create new account from checkout PopUp
    And create a new account for guest
    And click on basket icon
    And user clicks on the View Basket cta
    And use add free gift module according to device
    And click on proceed to checkout button
    And Payment page details confirmed
    And user add address on payment page
    And user choose shipping method
    And Select COD CheckBOX
    Then select place order from Card
    And assert Free gift items message is not displayed

  @gloPlRegression
  Scenario: Checkout add Address - city field error message validation
    When user sign in with 'loginValidEmail.key' and 'loginValidPassword.key' and proceed to checkout page
    And Payment page details confirmed
    And click add new address button
    And user enters '2' characters in City field and assert error message 'errorMessageForAddress.key'
    And user enters '1' characters in City field and assert error message 'errorMessageForAddress.key'