#@regression @createAddress @10024
Feature: 10024 - BAT Address management - Edit address
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @fr @MXReg
  Scenario: Edit Address Successful
    Then create a new account via api and log in with the account
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page

  @dk @dklive @fr @frlive @MXReg @COReg @ITReg @ITLive @IEReg @IElive @nl @de @delive
  Scenario: Edit Address Error Validations and Cancel button CTA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    And clear all address fields
    Then click the save address Pop up screen button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And user clicks on Cancel button from Address pop-up screen
    And user is returned to the base Address book page

  #@ITLive NA for VYPE-IT
  Scenario: Populate selected Address from Look up and amend manually - MyAccountAddressBookPage
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    When Glo start entering the address in MyAccountAddressBookPage with streetKeyword.key
    Then Glo should be presented with Loqate address look up auto-completion
    When Glo has selected an address from the auto-completion
    Then Glo address fields should be populated with the selected address
    And Glo can amend the fields manually if desired

  @nl
  #  50307
  Scenario: Edit Billing address and Shipping address form should not have telephone number field
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And user clicks on Edit Billing Address Link
    Then assert phone number is field is not displayed
    And assert text of 'phoneNumberText.key' is not displayed
    When user clicks the person icon
    And users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Shipping Address link
    Then assert phone number is field is not displayed
    And assert text of 'phoneNumberText.key' is not displayed