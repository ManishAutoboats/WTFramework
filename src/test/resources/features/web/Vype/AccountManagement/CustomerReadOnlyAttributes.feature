# 'Disable and Designate Customer Fields For Read-Only Attribution - Storefront' story - 294978
Feature: BAT Customer Management - Read-Only Attributes
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg @VuseDKReg
  Scenario: My Account cannot edit Customer Fields - Read Only
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And click on Edit button from Account Dashboard
    And assert DOB field isn't editable
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    And assert Change Email field isn't editable
    And assert content section by business regarding non-editable fields

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg @VuseDKReg
  Scenario: Add Address - Create new default billing address with pre-populated read-only first last name fields
    Then create a new account via api and log in with the account
    And fetch First and Last Names of the logged-in user
    Then users clicks on the 'yourAddressText.key' text link
    And add new Address button displayed
    And click on my account new address button
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields
    And populate all address fields except for first and last name
    And tick default billing address
    Then click the save address button
    And user is returned to the base Address book page

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg @VuseDKReg
  Scenario: Add Address - Create new default shipping address with pre-populated read-only first last name fields
    Then create a new account via api and log in with the account
    And fetch First and Last Names of the logged-in user
    Then users clicks on the 'yourAddressText.key' text link
    And add new Address button displayed
    And click on my account new address button
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields
    And populate all address fields except for first and last name
    And tick default shipping address
    Then click the save address button
    And user is returned to the base Address book page

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg @VuseZAReg @VuseDKReg
  Scenario: Edit Billing Address Fields - Read Only
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And users clicks on the 'yourAddressText.key' text link
    And user clicks on Edit Billing Address Link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg
  Scenario: Edit Shipping Address Fields - Read Only
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Shipping Address link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @nl @ITReg @IEReg @fr @dk @de @COReg @MXReg @VuseDKReg
  Scenario: Checkout - Add New Address Pop-Up - Pre-Populated Read-Only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And search for a Device 'searchTerm.key' and checkout
    And Payment page details confirmed
    And click add new address button
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  #@IEReg @fr @dk @de
  #Scenario: Payment Method with Checkbox 'My billing and shipping address are the same' Unchecked - Pre-Populated Read-Only first last name fields
  #  And create a new account
  #  And assert text of 'successRegistraionMsg.key' is displayed
  #  And fetch First and Last Names of the logged-in user
  #  And search for a Device 'searchTerm.key' and checkout
  #  Then Payment page details confirmed
  #  And Credit Card - MasterCard option and press next
  #  Then user de-selects 'My billing and shipping address are the same' checkbox
  #  And assert First Name field isn't editable under Billing Address section
  #  And assert Last Name field isn't editable under Billing Address section
  #  Then assert pre-populated first name with existing account under Billing Address
  #  And assert pre-populated last name with existing account under Billing Address
  #  And assert content section by business regarding non-editable fields is not displayed