# 'Disable and Designate Customer Fields For Read-Only Attribution - Storefront' story - 294978
@LyftRegression @LyftDKReg
Feature: BAT Customer Management - Read-Only Attributes
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then fetch First and Last Names of the logged-in user

  Scenario:  Lyft - My Account cannot edit Customer Fields - Read Only
    And user clicks the myAccount edit link - lyft
    Then assert DOB field isn't editable
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    And assert Change Email field isn't editable
    And assert content section by business regarding non-editable fields

  Scenario: Lyft - Add Address - Create new default billing address with pre-populated read-only first last name fields
    And users clicks on the 'yourAddressText.key' text link
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

  Scenario: Lyft - Add Address - Create new default shipping address with pre-populated read-only first last name fields
    And users clicks on the 'yourAddressText.key' text link
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

  Scenario:  Lyft - Edit Billing Address Fields - Read Only
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on the 'editBillingAddressLink.key' text link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  Scenario: Lyft - Edit Shipping Address Fields - Read Only
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Shipping Address link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  Scenario: Lyft Checkout - Add New Address Pop-Up - Pre-Populated Read-Only first last name fields
    And search for a Device 'searchTermdi.key' and checkout
    Then Payment page details confirmed
    And click add new address button
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields