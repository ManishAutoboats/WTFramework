Feature: BAT Address management - Edit Address

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloPlRegression2
  Scenario: My Account - Edit Address - Verify Tax number field contains numeric data in format XXX-XXX-XX-XX and add address
    When create a new account
    And user clicks on PersonIcon and Navigate to My Account Page
    And user add address details after registration - GloPL
    Then users clicks on Edit Shipping Address link
    And click on Manual Address Entry link
    And user enters '10' numeric characters in Tax Number field and assert format
    And populate all address fields including new first and last name
    Then click the save address button
    Then assert text of 'saveNewAddressSuccessMsg.key' is displayed
    Then users clicks on Edit Shipping Address link
    And click on Manual Address Entry link
    And populate all address fields including new first and last name
    And user clears Tax Number data as the field is optional
    Then click the save address button
    Then assert text of 'saveNewAddressSuccessMsg.key' is displayed

  @gloPlRegression @gloPlLive
  Scenario: My Account - Edit Address - Tax number field Error Validations
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    Then users clicks on Edit Shipping Address link
    And click on Manual Address Entry link
    And user enters '10' letters in Tax Number field and assert error message 'errValidTaxNumberMsg.key'
    And user enters more than '10' characters in Tax Number field and assert field accepts '10' digits as format
    And close the pop-up window and return to base page
    Then users clicks on Edit Address link
    And click on Manual Address Entry link
    And user enters '10' letters in Tax Number field and assert error message 'errValidTaxNumberMsg.key'
    And user enters more than '10' characters in Tax Number field and assert field accepts '10' digits as format

  @gloKzRegression
  Scenario Outline: My Account - edit shipping address basic information
    Given user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And users clicks on the 'yourAddressText.key' text link
    And users clicks on edit <addressType> address
    When edit address basic information
      | company | addressLine                                 | town   | postCode | country   | addressType      |
      | salmon  | 12 Market Place,Ivanov Alexander Ivanovitch | Moscow | 030000   | Казахстан | billing,shipping |
    Then assert edit address success
    Examples:
      | addressType |
      | billing     |
      | shipping    |

  @gloItRegression @gloDeRegression
  Scenario: Edit Address Successful
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    Then click the save address Pop up screen button
    And user is returned to the base Address book page

  @gloItRegression @gloDeRegression
  Scenario: Edit Address Error Validations and Cancel button CTA
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    And clear all address fields
    And click the save address Pop up screen button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And user clicks on Cancel button from Address pop-up screen
    And user is returned to the base Address book page

  @gloPlRegression
  Scenario: My Account - Edit Address - city field error message validation
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    Then users clicks on Edit Shipping Address link
    And click on Manual Address Entry link
    And user enters '2' characters in City field and assert error message 'errorMessageForAddress.key'
    And user enters '1' characters in City field and assert error message 'errorMessageForAddress.key'