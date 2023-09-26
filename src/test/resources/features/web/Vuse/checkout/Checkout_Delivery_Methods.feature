Feature: BAT checkout Delivery Methods Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseFRReg3
  #if the order price is more than 40 then the customer should get free shipping,
  #otherwise shipping charges 3.99 should be added to the price
  Scenario: 960777 New user select Collismo Standard Delivery Delivery at checkout and verify Delivery Charges
    And create a new account via api
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user login with credentials registered via api
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And select DOMICILE SANS SIGNATURE delivery as delivery method
    Then verify delivery charges in the order summery
    And click on the logo
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And assert if user is able to input any value '6' in mini basket Qty field
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Payment page details confirmed
    And select DOMICILE SANS SIGNATURE delivery as delivery method
    Then verify delivery charges in the order summery

  @VuseFRLive
  #if the order price is more than 40 then the customer should get free shipping,
  #otherwise shipping charges 3.99 should be added to the price
  Scenario: 960777 Signin user select Collismo Standard Delivery Delivery at checkout and verify Delivery Charges
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And select DOMICILE SANS SIGNATURE delivery as delivery method
    Then verify delivery charges in the order summery
    And click on the logo
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    And click add to cart button
    And click on basket icon
    And assert if user is able to input any value '6' in mini basket Qty field
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Payment page details confirmed
    And select DOMICILE SANS SIGNATURE delivery as delivery method
    Then verify delivery charges in the order summery

  @VuseUKReg3
  Scenario: Available Delivery Methods for Engraving Product on Checkout page
    Then create a new account with specific postcode 'postCodeWithNDD.key'
    And user click on search icon and submits the following search term 'searchTerm_EngravingProduct'
    And select product by index "productIndex.key" on plp page
    And user click to opt personalizable service
    And select engraving "text" on front
    And user add text on the "front" of the device with "vertical" orientation
    And user clicks on add and continue button "text"
    And user add text on the "back" of the device with "vertical" orientation
    And user clicks on add and continue button "back"
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    Then assert only delivery method 'STANDARD DELIVERY WITH 2 HOUR SLOT NOTIFICATION' is displayed for engraved product
    And click on the logo
    And search and checkout product "searchTerm_EngravingProduct"
    And Payment page details confirmed
    Then assert only delivery method 'STANDARD DELIVERY WITH 2 HOUR SLOT NOTIFICATION' is displayed for engraved product

  @VuseUKReg3
  Scenario: Available Delivery Methods for Non Engraving Product on Checkout page
    Then create a new account with specific postcode 'postCodeWithNDD.key'
    And user click on search icon and submits the following search term 'searchTerm_EngravingProduct'
    And select product by index "noEngraveProductIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    And assert delivery methods 'deliveryMethodsCheckout.key' displayed for non-engraved regular product

  @VuseUKReg #(remove last of step due to changes(983723))
  Scenario: Available Delivery Methods on Checkout page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And search and checkout product "searchTerm.key"
    Then view the choose delivery method section all details
    Then select first delivery option


  #@VuseUKReg3 disable this case due to changes for delivery option(983723)
  #if the order price is more than 50 then the customer should get free shipping,
  #otherwise shipping charges 4.99 should be added to the price - #558581
  Scenario Outline: Select Delivery at checkout and verify Delivery Charges
    And create a new account via api
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And select <delivery method> delivery as delivery method
    Then verify delivery charges in the order summery
    And click on the logo
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And select product by index "productIndex.key" on plp page
    And select product strength or colour
    Then click add to cart button
    And click on basket icon
    And assert if user is able to input any value '6' in mini basket Qty field
    And user clicks on the View Basket cta
    And click on proceed to checkout button
    And Payment page details confirmed
    And select <delivery method> delivery as delivery method
    Then verify delivery charges in the order summery
    Examples:
    | delivery method|
    | next day       |
    | saturday       |


  @VuseUKReg3
  Scenario: 558581-Not available delivery method for Scottish Highland Address
    When create a new account via api
    And search and checkout product "searchTerm.key"
    And user click on change address link
    When start entering the address in Checkoutpage with streetScottishHighlandKeyword.key
    And user has selected an address from the auto-completion
    And click the save address button
    Then delivery method doesn't exist
      | next day       |
      | saturday       |

  @VuseZAReg
  Scenario: Delivery Methods on Checkout page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    Then search and checkout product "searchTerm.key"
    Then return to basket page
    Then fetch the product price from MiniCart
    And click on proceed to checkout button
    And verify delivery method section all details
    Then return to basket page
    And user click on search icon and submits the following search term 'searchTerm.key'
    And click first result
    When modify quantity of the product to '5'
    And click add to cart button
    Then fetch the product price from MiniCart
    And click on proceed to checkout button
    And verify delivery method section all details

  @VuseCOReg
  Scenario: Check free shipping method is displayed for the order over $60,000
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    And user click on search icon and submits the following search term 'searchProductItem.key'
    And user closes the alert if present
    And click first result
    And select product strength or colour
    And set quantity to '5'
    Then click add to cart button
    And click on basket icon
    And click on proceed to checkout button
    And Payment page details confirmed
    Then free delivery radio button is displayed
    And assert text of 'freeShippingDescription.key' is displayed

  @VuseCOReg
  Scenario: Check free shipping message is displayed for the empty basket
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And empty basket
    Then assert text of 'freeShippingMessage.key' is displayed

  @VuseUKReg
  Scenario: Block orders to a specific address - assert error for restricted address checkout for a new address
    When create a new account via api
    And search and checkout product "searchTermVype.key"
    And assert add new address link is displayed
    And user clicks on Add New Address button
    And populate restricted address 'restrictedStreet.key' on Add New Address pop-up
    And assert restricted address error message 'restrictedAddressErrMessage.key' is displayed
    And assert Delivery Method input is not displayed for restricted address
    Then user click on change address link
    And populate restricted address 'restrictedPostCode.key' on Add New Address pop-up
    And assert restricted address error message 'restrictedAddressErrMessage.key' is displayed
    And assert Delivery Method input is not displayed for restricted address
    Then user adds a new address with allowed post code
    And assert Delivery Method input is displayed for default address

  @VuseUKReg
  Scenario: Block orders to a specific address - assert error for restricted address checkout for an existing address
    When create a new account with restricted address details 'restrictedStreet.key'
    And search and checkout product "searchTermVype.key"
    And assert restricted address error message 'restrictedAddressErrMessage.key' is displayed
    And assert Delivery Method input is not displayed for restricted address
    Then user click on change address link
    Then user adds a new address with allowed post code
    And assert Delivery Method input is displayed for default address

  @VuseUKReg
  Scenario: Block orders to a specific address - assert error for restricted post code checkout for an existing address
    When create a new account with restricted address details 'restrictedPostCode.key'
    And search and checkout product "searchTermVype.key"
    And assert restricted address error message 'restrictedAddressErrMessage.key' is displayed
    And assert Delivery Method input is not displayed for restricted address
    Then user click on change address link
    Then user adds a new address with allowed post code
    And assert Delivery Method input is displayed for default address