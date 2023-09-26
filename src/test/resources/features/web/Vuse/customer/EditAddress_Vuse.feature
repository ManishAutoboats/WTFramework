Feature: 10024 - BAT Address management - Edit address
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link

  @VuseDEReg @VuseFRReg @VuseUKReg
  Scenario: Edit Address Successful
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page

  @VuseDEReg @VuseDELive
  Scenario: Verify Guiding text in Billing and Shipping line items On Edit Address Page
    And users clicks on Edit Address link
    And expand address fields
    Then assert guiding text in address fields

    @VuseFRReg @VuseFRLive @VuseITReg @VuseITLive @VuseMXReg @VuseMXLive
  Scenario: Edit Address Error Validations and Cancel button CTA-Vuse
    And users clicks on Edit Address link
    And clear all address fields
    Then click the save address Pop up screen button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And user clicks on Cancel button from Address pop-up screen
    And user is returned to the base Address book page

  @VuseFRReg
  Scenario: Edit Address - MyAccountPage
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And add new address with more than 30 characters in address field
    Then assert error message is displayed for address field

  @VuseZAReg @VuseUKReg @VuseFRReg
  Scenario: Populate selected Address from Loqate look up - MyAccountAddressBookPage
    And users clicks on Edit Address link
    When start entering the address in MyAccountAddressBookPage with streetKeyword.key
    Then user should be presented with Loqate address look up auto-completion

  @VuseUKReg @VuseFRReg
  Scenario: Populate selected Address from Loqate look up - Checkoutpage
    And search and checkout product "searchTerm.key"
    And user click on change address link
    When start entering the address in Checkoutpage with streetKeyword.key
    Then user should be presented with Loqate address look up auto-completion

  @VuseZAReg @VuseZALive
  Scenario: Edit Address - assert Phone Number mandatory validation message on Edit Address pop-up
    And users clicks on Edit Address link
    And assert phone number is pre-filled with country code +27
    Then clear pre-filled data from Phone Number field
    And click the save address button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed

  @VuseMXReg
  Scenario: 980891 - Edit Address - assert STATE/PROVINCE field displayed as drop down
    And users clicks on Edit Address link
    And assert province field displayed as drop down