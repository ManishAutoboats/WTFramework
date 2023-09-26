Feature: 10024 - BAT Address management - create address

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloPlRegression @gloPlLive
  Scenario: Add Address - Page Check
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    Then assert text of 'addressHeading.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    And confirm Add address form is presented to user

  @gloPlLive
  Scenario: Add Address - validation Errors
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And user clicks on Save address without populating any default fields
    Then page displays expected errors

  @gloPlRegression
  Scenario: Glo Poland Add Address - validation Errors
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And user clicks on Save address without populating any default fields
    Then page displays expected errors

  @gloDeRegression
  Scenario: Add Address - Go Back
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And add new Address button displayed
    And click on my account new address button
    And click the go back button
    And user is returned to the base Address book page

  @gloDeRegression
  Scenario: Add Address - create new default shipping address and assert entry included on Your address page
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And add new Address button displayed
    And click on my account new address button
    And populate all address fields including new first and last name
    And tick default shipping address
    Then click the save address button
    And user is returned to the base Address book page
    And assert new first and surname are present
    Then verify new address is update on Address Book for default shipping address

#   user story - 229599 pl
# bug 505365- pl commenting out pl scenario until bug fixed
  @gloItLive
    #@gloDeLive #@gloPlLive
  Scenario: Populate selected Address from Look up and amend manually - MyAccountAddressBookPage
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    Then users clicks on the 'yourAddressText.key' text link
    Then assert text of 'addressHeading.key' is displayed
    And add new Address button displayed
    And click on my account new address button
    When Glo start entering the address in MyAccountAddressBookPage with streetKeyword.key
    Then Glo should be presented with Loqate address look up auto-completion
    When Glo has selected an address from the auto-completion
    Then Glo address fields should be populated with the selected address
    And Glo can amend the fields manually if desired

  @gloPlRegression
  Scenario Outline: glopl NIP(Tax ID) field validations - success scenario
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And add new Address button displayed
    And click on my account new address button
    When user attempts to add the new address with NIP-<pattern>
    Then assert text of '<message>' is displayed
    Examples:
      | pattern   | message                      |
      | length-0  | saveNewAddressSuccessMsg.key |
      | length-10 | saveNewAddressSuccessMsg.key |

  #@gloPlRegression uncomment once the Bug 446813 fixed
  Scenario: glopl NIP(Tax ID) field validations - Failure scenario
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And add new Address button displayed
    And click on my account new address button
    When user attempts to add the new address as with following NIP Then assert error message
      | pattern          | message             |
#      | length-9         | nipErrorMessage.key     | TODO: uncomment once the Bug 446813 fixed
      | 123-45-67-89-123 | nipErrorMessage.key |
      | 12345#####       | nipErrorMessage.key |

  @gloPlRegression
  Scenario: Glo Poland - Assert Error validation messages for Address fields on Add New Address
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And click on Manual Address Entry link
    And user enters more than '51' characters in Street field and assert error message 'errMaximum50CharactersMsg1.key'
    And user enters more than '11' characters in House number field and assert error message 'errMaximum10CharactersMsg.key'
    And user enters string values in House number field and assert error message 'errNumericReqdHouseFieldMessage.key'
    And user enters more than '11' characters in Apartment number field and assert error message 'errMaximum10CharactersMsg.key'
    And user enters string values in Apartment number field and assert error message 'errNumericReqdFlatFieldMessage.key'

  @gloPlRegression2
  Scenario: My Account - Add New Address - Verify Tax number field contains numeric data in format XXX-XXX-XX-XX and add address
    When create a new account
    And user clicks on PersonIcon and Navigate to My Account Page
    And user add address details after registration - GloPL
    And click on my account new address button
    And click on Manual Address Entry link
    And user enters '10' numeric characters in Tax Number field and assert format
    And populate all address fields including new first and last name
    And tick default shipping address
    Then click the save address button
    Then assert text of 'saveNewAddressSuccessMsg.key' is displayed
    And click on my account new address button
    And click on Manual Address Entry link
    And populate all address fields including new first and last name
    And tick default shipping address
    Then click the save address button
    Then assert text of 'saveNewAddressSuccessMsg.key' is displayed

  @gloPlRegression @gloPlLive
  Scenario: My Account - Add New Address - Tax number field Error Validations
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And click on Manual Address Entry link
    And user enters '10' letters in Tax Number field and assert error message 'errValidTaxNumberMsg.key'
    And user enters more than '10' characters in Tax Number field and assert field accepts '10' digits as format

  @gloPlRegression2 @gloPlLive
  Scenario: My Account - Addressbook - Assert Fast Address Finder field is hidden
    When create a new account
    And user clicks on PersonIcon and Navigate to My Account Page
    Then users clicks on the 'yourAddressText.key' text link
    And assert Fast Address Finder field is not displayed
    And user navigates to my account page from header
    And user selects deleteMyAccount link on my account Page

  @gloPlRegression
  Scenario: Add Address - city field error message validation
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'yourAddressText.key' text link
    And click on my account new address button
    And click on Manual Address Entry link
    And user enters '2' characters in City field and assert error message 'errorMessageForAddress.key'
    And user enters '1' characters in City field and assert error message 'errorMessageForAddress.key'