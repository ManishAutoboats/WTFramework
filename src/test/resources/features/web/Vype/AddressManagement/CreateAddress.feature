#This is the create account feature
#@regression @createAddress @10024
Feature: 10024 - BAT Address management - create address
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link

  @smokeLite  @dk @dklive @MXReg @COReg @fr @ITReg @IEReg @VuseZAReg @VuseDKReg @VuseDKLive @VuseZALive @VuseDEReg
  Scenario: Add Address - Page Check
    Then assert text of 'addressHeading.key' is displayed
    Then assert text of 'defaultBillingAddressText.key' is displayed
    Then assert text of 'defaultShippingAddressText.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    And confirm Add address form is presented to user

  Scenario: 10022 Edit Address
    And users clicks on the 'editText.key' text link
    Then assert text of 'editAddressText.key' is displayed
    Then assert text of 'addressText.key' is displayed
    And populate all address fields including new first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page
    And assert new first and surname are present

  @COReg @VuseFRReg  #@MXReg @MXLive @fr
  Scenario: Add Address - validation Errors
    And click on my account new address button
    #And ensure Add Address page is loaded
    And user clicks on Save address without populating any default fields
    Then page displays expected errors

  @MXReg @COReg @fr @ITReg @IEReg @VuseZAReg
  Scenario: Add Address - Go Back
    And click on my account new address button
    And ensure Add Address page is loaded
    And click the go back button
    And user is returned to the base Address book page

#@dk @dklive @MXReg @MXLive @fr
  Scenario: Add Address - create new default billing address overwrite first last name and assert entry included on Your address page
    And add new Address button displayed
    And click on my account new address button
    #And ensure Add Address page is loaded
    And populate all address fields including new first and last name
    And tick default billing address
    Then click the save address button
    And user is returned to the base Address book page
    And assert new first and surname are present

#@add @dk @dklive @MXReg @MXLive @fr
  Scenario: Add Address - create new default shipping address and assert entry included on Your address page
    And add new Address button displayed
    And click on my account new address button
    #And ensure Add Address page is loaded
    And populate all address fields including new first and last name
    And tick default shipping address
    Then click the save address button
    And user is returned to the base Address book page
    And assert new first and surname are present

  @nl
  Scenario: Add Address form should not have telephone number field
    And click on my account new address button
    Then assert phone number is field is not displayed
    And assert text of 'phoneNumberText.key' is not displayed

  @MXReg
  Scenario: pre-filled country code in phone number field - my account new Address Page
    And click on my account new address button
    And ensure Add Address page is loaded
    Then assert phone number is pre-filled with country code +52