Feature: BAT Customer Management - Read-Only Attributes
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
  Scenario: My Account cannot edit Customer Fields - Read Only
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And click on Edit button from Account Dashboard
    And assert DOB field isn't editable
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    And assert Change Email field isn't editable
    And assert content section by business regarding non-editable fields

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
  Scenario: Add Address - Create new default billing address with pre-populated read-only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
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

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
  Scenario: Add Address - Create new default shipping address with pre-populated read-only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
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

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
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

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
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

  @VuseDEReg @VuseFRReg @VuseITReg @VuseMXReg
  Scenario: Checkout - Add New Address Pop-Up - Pre-Populated Read-Only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And search for a Device 'searchTerm.key' and checkout
    And Payment page details confirmed
    And click add new address button
    And click on Manual Address Entry link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @VuseZAReg
  Scenario: My Account cannot edit Customer Fields - Read Only Vuse ZA
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    And click on Edit button from Account Dashboard
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    And assert Change Email field isn't editable
    And assert content section by business regarding non-editable fields