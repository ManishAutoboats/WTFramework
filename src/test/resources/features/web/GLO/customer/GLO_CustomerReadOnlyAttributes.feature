# 'Disable and Designate Customer Fields For Read-Only Attribution - Storefront' story - 294978
Feature: BAT Customer Management - Read-Only Attributes
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    Then fetch First and Last Names of the logged-in user

  @gloKzRegression @gloItRegression @gloDeRegression
  Scenario: GLO - My Account cannot edit Customer Fields - Read Only
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And users clicks on the dashboardEditLink text link
    And assert DOB field isn't editable
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    And assert Change Email field isn't editable
    And assert content section by business regarding non-editable fields

  @gloKzRegression @gloItRegression @gloDeRegression
  Scenario: GLO - Add Address - Create new default billing address with pre-populated read-only first last name fields
    Then users clicks on the The Address Book link
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

  @gloKzRegression @gloItRegression @gloDeRegression
  Scenario: GLO - Add Address - Create new default shipping address with pre-populated read-only first last name fields
    Then users clicks on the The Address Book link
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

  @gloItRegression
  Scenario: GLO - Edit Billing Address Fields - Read Only
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on the 'editBillingAddressLink.key' text link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @gloItRegression @gloDeRegression
  Scenario: GLO - Edit Shipping Address Fields - Read Only
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Shipping Address link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @gloKzRegression @gloDeRegression
  Scenario: GLO KZ - Edit Billing Address Fields - Read Only
    Then users clicks on the The Address Book link
    Then user clicks on Edit Billing Address Link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @gloKzRegression
  Scenario: GLO KZ - Edit Shipping Address Fields - Read Only
    Then users clicks on the The Address Book link
    And users clicks on Edit Shipping Address link
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields

  @gloKzRegression @gloItRegression @gloDeRegression
  Scenario: GLO Checkout - Add New Address Pop-Up - Pre-Populated Read-Only first last name fields
    And select a GLO product and checkout
    And click add new address button
    And assert First Name field isn't editable
    And assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    And assert pre-populated last name with the existing account
    And assert content section by business regarding non-editable fields