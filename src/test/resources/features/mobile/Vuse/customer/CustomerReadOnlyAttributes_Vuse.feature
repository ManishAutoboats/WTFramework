Feature: BAT Customer Management - Read-Only Attributes - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
  Scenario: Add Address - Create new default billing address with pre-populated read-only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    Then users clicks on the 'yourAddressText.key' text link mobile
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

  @RegVuseFR_Mobile
  Scenario: Checkout - Add New Address Pop-Up - Pre-Populated Read-Only first last name fields
    And create a new account via api and log in with the account
    And fetch First and Last Names of the logged-in user
    And search and checkout product "searchTerm.key"
    And Payment page details confirmed
    And click add new address button
    And click on Manual Address Entry link
    Then assert First Name field isn't editable
    Then assert Last Name field isn't editable
    Then assert pre-populated first name with the existing account
    Then assert pre-populated last name with the existing account
    Then assert content section by business regarding non-editable fields


  Scenario: Add Address - Create new default shipping address with pre-populated read-only first last name fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And fetch First and Last Names of the logged-in user
    Then users clicks on the 'yourAddressText.key' text link mobile
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