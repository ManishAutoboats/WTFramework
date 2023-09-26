Feature: BUG - 21190 9991 BAT registration feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg2 @VuseUKReg2 @VuseUKLive2 @VuseFRReg3 @VuseFRLive @VuseMXReg @VuseMXLive @VuseITAnonReg2
  Scenario: Registration page - ensure all expected elements are present
   And user clicks the person icon
   And user clicks on 'signInLink.key' link from Person Menu
   And user closes the alert if present
   And user clicks the registration button
   And assert page title is 'registrationPageTitle.key'
   And assert text of 'personalInfoText.key' is displayed
   And assert text of 'addressInfoText.key' is displayed
   And assert text of 'signInText.key' is displayed


   @VuseDEReg2 @VuseFRReg2 @VuseITAnonReg2 @VuseMXReg
   Scenario: Registration of new user (Bug - 103177 - FR)
    And create a new account
    And assert text of 'successRegistraionMsg.key' is displayed

   @VuseUKReg2
   Scenario: Registration of new user - confirm Email Message(Bug - 880835 - UK)
    And create a new account without confirm Email
    And assert text of 'confirmAccountMsg.key' is displayed

   @VuseDEReg2 @VuseUKReg2 @VuseFRReg2 @VuseMXReg @VuseITAnonReg2
   Scenario: Registration of underage user
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'UnderAgeDob.key'
    And populate signin fields
    And user selects the create an account button without Email Verification script
    And assert DOB error is presented

  @VuseDEReg2 @VuseUKReg2 @VuseFRReg2 @VuseMXReg
  Scenario: Registration attempt without Ts and Cs checkbox ticked
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields without selecting Ts and Cs
    And user selects the create an account button without Email Verification script
    And registration success message is not displayed

  @VuseDEReg2 @VuseDELive
  Scenario: Verify Guiding text in Billing and Shipping line items On Registration Page
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And expand address fields
    Then assert guiding text in address fields

    @VuseMXReg
  Scenario: pre-filled country code in phone number field
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And assert page title is 'registrationPageTitle.key'
    When user enters phone number in the phone number field as 123456789
    Then assert phone number is pre-filled with country code +52

 @VuseUKReg2 @VuseUKLive2 @VuseFRReg2
  Scenario: Assert checkbox under Mobile Number field displayed for customers not having a UK/FR number and create an account with checkbox selected
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user closes the alert if present
    And user clicks the registration button
    And assert checkbox is displayed for customers not having a UK/FR number under Mobile Number field
    Then user selects the mobile number checkbox
    And assert mobile number field is greyed out and gets populated with 'defaultMobileNumber.key'
    Then user create a new account with default UK/FR mobile number
    Then users clicks on the 'yourAddressText.key' text link
    And users clicks on Edit Address link
    And assert user is able to update any valid UK/FR number and updated information gets saved
    And assert text of 'addressSavedSuccessMessage.key' is displayed

  @VuseFRReg2
  Scenario: Add Address - Create Account for more than 30 characters in address field
    And create new account with more than 30 characters in address field
    Then assert error message is displayed for address field

  @VuseUKReg2 @VuseZAReg @VuseFRReg2
  Scenario: Delete Customer Account
    And create a new account
    And assert text of 'successRegistraionMsg.key' is displayed
    Then click on delete my account and proceed
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And login with account created details
    Then assert text of 'incorrectSignInMsg.key' is displayed
    And eyes check entire page "Login Error - Non-existing Email and Non-existing Password"

  @VuseDEReg2 @VuseUKReg2 @VuseZAReg @VuseFRReg2
  Scenario: Person Data verification after register new user
    When create a new account
    And users clicks on the 'dashboardEditLinkText.key' text link
    Then assert all elements are displayed

  @VuseDEReg2
  Scenario: Data protection link verification
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    Then assert data protection link displayed under registration
    And should get correct 'dataprotectionTitle.key'

  @VuseDEReg2 @gloDeRegression2
  Scenario: assert Phone Number error validation messages on Create Account page
    And user goes to registration form
    And assert user is not allowed to enter more than '13' digits in phone number field
    And assert error message 'errMaxLimitAllowedPhoneNumber.key' when phone number starting with 0 and less than limit is entered
    Then user attempts to create a new account without phoneNumber
    Then assert text of 'successRegistraionMsg.key' is displayed
    Then proceed to delete customer account

  @VuseFRReg2
  Scenario: Vuse fr- Successfull message notification
    And create a new account
    And assert text of 'successRegistraionMsg.key' is displayed
    And assert notification message is displayed
    When user close the notification message
    Then assert notification message is not displayed

  @VuseUKReg2
  Scenario: Vuse uk- Successfull message notification
    And create a new account without confirm Email
    And assert text of 'confirmAccountMsg.key' is displayed
    And assert notification message is displayed
    When user close the notification message
    Then assert notification message is not displayed

  @VuseITAnonReg2 @VuseZAReg @VuseZALive @VuseFRReg2
  Scenario: 925099 - create account without filling any details
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And user create a new account without filling any details
    And assert text of 'incorrectErrorMessage' is not displayed
    And assert error messages displayed of all mandatory fields

  @VuseZAReg @VuseZALive
  Scenario: Assert pre-filled country code in phone number field
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    Then assert country code was already entered as a prefix in registration page

  @VuseFRReg2 @VuseITAnonReg2
  Scenario: Create account with invalid format assert error
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And enter invalid format for email, mobile number, confirm password
    Then assert Error message for invalid format of email, mobile number and confirm password

  @VuseITAnonReg3
  Scenario: Registration of underage user with before 1921
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'UnderAgeDobBefore1921.key'
    And populate signin fields
    And user selects the create an account button without Email Verification script
    Then assert DOB error is presented
    And assert DOB error message 'DobBefore1921Error.key' is displayed

  @VuseITAnonReg3
  Scenario: Registration of user with invalid Codice Fiscale
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields
    And populate fiscal code with 'invalidCodiceFiscale.key'
    Then assert CodiceFiscale error message 'invalidCodiceFiscaleErrorMessage.key' is displayed

  @VuseITAnonReg3
  Scenario: Registration of user with an existing email
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields with an existing email
    And user selects the create an account button without Email Verification script
    Then assert exist email error message is displayed

  @VuseITAnonReg2
  Scenario: Registration of user with the password which has 4 characters
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields with the password which has 4 characters
    And user selects the create an account button without Email Verification script
    Then assert password error message is displayed

  @VuseITAnonReg3
  Scenario: Registration attempt without Ts and Cs checkbox ticked for VuseIT
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate signin fields without selecting Ts and Cs
    And user selects the create an account button without Email Verification script
    Then registration success message is not displayed
    And assert TsAndCs error message is displayed

  @VuseITAnonReg3
  Scenario: Registration and navigate to Marketing Preferences page
    Given create a new account
    And user clicks the person icon
    And user clicks on 'myAccountLink.key' link from Person Menu
    And navigate to Marketing Preferences page
    Then assert status of marketing tickbox