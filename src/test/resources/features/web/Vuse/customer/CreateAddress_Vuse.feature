Feature: 10024 - BAT Address management - create address
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDKReg @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg @VuseMXLive @VuseUKReg
  Scenario: Add Address - Page Check
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    Then assert text of 'addressHeading.key' is displayed
    Then assert text of 'defaultBillingAddressText.key' is displayed
    Then assert text of 'defaultShippingAddressText.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    And confirm Add address form is presented to user

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg @VuseMXLive @VuseUKReg
  Scenario: Add Address - Go Back
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And ensure Add Address page is loaded
    And user clicks on Cancel button from Address pop-up screen
    And user is returned to the base Address book page

  @VuseDEReg @VuseDELive
  Scenario: Verify Guiding text in Billing and Shipping line items On Add New Address Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And expand address fields
    Then assert guiding text in address fields

  @VuseFRReg
  Scenario: Add Address - MyAccountPage
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And ensure Add Address page is loaded
    And add new address with more than 30 characters in address field
    Then assert error message is displayed for address field

  @VuseFRReg
  Scenario: Checkout - Add New Address more than 30 characters in address
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And click add new address button
    And add new address with more than 30 characters in address field
    Then assert error message is displayed for address field

  @VuseMXReg @VuseMXLive
  Scenario: pre-filled country code in phone number field - my account new Address Page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And ensure Add Address page is loaded
    Then assert phone number is pre-filled with country code +52

  @VuseFRReg
  Scenario: Add Address Successfully
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And ensure Add Address page is loaded
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page

  @VuseITReg
  Scenario: Address finder field is exist in Add new address form
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And ensure Add Address page is loaded
    Then address finder field should be present in the page

  @VuseDEReg2 @gloDeRegression
  Scenario: Add Address - assert Phone Number error validation messages on Add New Address pop-up
    When create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And assert phone number is pre-filled with country code +49
    And assert user is not allowed to enter more than '13' digits in phone number field
    And assert error message 'errMaxLimitAllowedPhoneNumber.key' when phone number starting with 0 and less than limit is entered
    And populate address fields without phone number
    And tick default shipping address
    Then click the save address button
    And user is returned to the base Address book page

  @VuseDEReg
  Scenario: Add Address - assert address content displayed correctly
    When create a new account via api
    And user clicks on PersonIcon and Navigate to My Account Page
    And users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And populate address fields without phone number
    And tick default shipping address
    And click the save address button
    Then assert address 'yourAddressDisplayContent.key' displayed correctly

  @VuseZAReg
  Scenario: Add Address - assert Phone Number mandatory validation message on Add New Address pop-up
    When create a new account via api and log in with the account
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And assert phone number is pre-filled with country code +27
    And tick default shipping address
    Then click the save address button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    Then user closes New Address pop-up
    And click on my account new address button
    Then user populates address details using loqate including phone number and save
    Then click the save address button
    And assert text of 'successfulSaveAddressMessage.key' is displayed