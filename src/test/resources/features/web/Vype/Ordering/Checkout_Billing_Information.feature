Feature: BAT checkout billing information
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed

  @regression @fr @dklive @dk @frlive  @checkout1 @de @nl @ITReg @ITLive  @checkout @IElive @IEReg @live @VuseDKReg  @VuseDKLive
  Scenario: 9608 Ordering - Check Out - Billing Information
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And assert billing and shipping are the same tick box present

  @dklive @dk @checkout1 @de @nl @ITReg @ITLive @checkout @IElive @IEReg @VuseDKReg @VuseDKLive
  Scenario: 9607 15993 Ordering - Check Out - Change Address
    And Credit Card - MasterCard option and press next
    And enter mastercard details
   # And assert ship here button is displayed
    And assert new address button is displayed
    And click add new address button
   # And assert text of 'addressHeading.key' is displayed
    And assert text of 'firstNameText.key' is displayed
    #And assert billing and shipping are the same tick box present
#    And assert Telephone Number field is a mandatory field

  @fr @frlive
  Scenario: 9607 15993 Ordering - Check Out - Change Address - FR
    And Credit Card - MasterCard option and press next
    And enter mastercard details
    And assert new address button is displayed
    And click add new address button
    And assert text of 'firstNameText.key' is displayed

  @MXReg
  Scenario: Vype MX Ordering - Check Out - Change Address CTA and Age Verification Labels
    And assert new address button is displayed
    And click add new address button
    Then click on Cancel button on Add new Address pop-up
    And assert text of 'firstNameText.key' is displayed
    And click on Debit/Credit Card Payment Option
    Then assert Checkout Age Verification warning label
    And assert Terms and Conditions link in Age Verification Warning
    And click on PayPal Payment Option
    Then assert Checkout Age Verification warning label
    And assert Terms and Conditions link in Age Verification Warning

  @101470
  Scenario: Checkout - Sub-Total and Total if VAT,Eco tax applied with Delivery charges,with and without 100% Discount
    #assert Delivery charges not appearing
    And user click on search icon and submits the following search term 'searchTerm.key'
    And get all lists from page
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    # And click on the logo
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    When users clicks on the 'logoutText.key' text link
    And user clicks the person icon
    And click on basket icon
    And basket is empty message is presented

  @dklive @dk @VuseDKReg @VuseDKLive
  Scenario: Vype DK - Br Exp - Parcel Shop Locator Pop-up
    And enter select package shop details
    Then user clicks on Shop on Map button under GLS Shipping Method
    And assert GLS Pop-up is displayed with GLS map inside
    Then user clicks on Continue button On GLS pop-up
    And assert GLS pop-up has closed
    And assert new address button is displayed
    Then user clicks on Shop on Map button under GLS Shipping Method
    Then user clicks on Close button on GLS pop-up
    And assert GLS pop-up has closed

  @dk @dklive @VuseDKReg @VuseDKLive
  Scenario: Checkout Page - Separate Checks for Terms Conditons and Age Verification
    And url contains 'checkoutUrl.key'
    And assert text of 'ShippingAddressText.key' is displayed
    And assert text of 'ShippingMethodHeading.key' is displayed
    And assert text of 'orderSummaryText.key' is displayed
    And assert text of 'itemInBasketText.key' is displayed
    And assert text of 'IntheBasketText.key' is displayed
    And selectable shipping options displayed
    And click on shipping method
    And click on Debit/Credit Card Payment Option
    And assert text of 'termsConditionsChkText.key' is displayed
    And assert text of 'ageVerificationChkText.key' is displayed
    And assert Age Verification checkbox under Card Details section
    And assert Terms and Conditions checkbox under Card Details section

  @nl
  #  50307
  Scenario: Checkout page New Address form should not have telephone number field
    And click add new address button
    Then assert phone number is field is not displayed
    And assert text of 'phoneNumberText.key' is not displayed