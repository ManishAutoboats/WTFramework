Feature: BUG - 21190 9991 BAT registration feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

 @sprint1 @18283 @live @regression @IElive @MXReg2 @IEReg2 @COReg @VuseZAReg2
  Scenario: Registration page - ensure all expected elements are present
   And user clicks the person icon
   And user clicks on 'signInLink.key' link from Person Menu
   And user clicks the registration button
   And assert page title is 'registrationPageTitle.key'
   And assert text of 'createAccountHeading.key' is displayed
   And assert text of 'personalInfoText.key' is displayed
   # Below removed as Alex advised this is affecting other locales
   #And assert text of 'addressInfoText.key' is displayed
   #And assert text of 'signInText.key' is displayed
   And assert text of 'remeberMeText.key' is displayed

   @smokeLite  @frSmoke @fr @MXSmoke @regression @bug @ITSmoke @de @dksmoke @MXReg2 @IEReg2 @ZASmoke @VuseZAReg2 @VuseDKReg
   Scenario: Registration of new user (Bug - 103177 - FR)
     And create a new account
     And assert text of 'successRegistraionMsg.key' is displayed

  @CoLive
  Scenario: 980507 Registration of new user
    When create a new account
    Then assert text of 'successRegistraionMsg.key' is displayed
    When I delete customer account
    Then the account has been deleted

  @smokeLite @62025 @smokeFast @regression @registration @ITReg @dk @MXReg2 @IEReg2 @COReg @VuseZAReg2
  Scenario: Registration of underage user
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And add SA ID Numer field
    And populate DOB field with 'UnderAgeDob.key'
    And populate signin fields
#    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    And assert text of 'underAgeErrorMsg.key' is displayed

  @smokeLite @62026 @smokeFast @regression @registration @ITReg @dk @MXReg2 @IEReg2 @COReg @VuseZAReg2 @VuseDKReg
  Scenario: Registration attempt without Ts and Cs checkbox ticked
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate personal informations section fields
    And populate address informations section fields
    And populate signin fields without selecting Ts and Cs
    And user selects the create an account button without Email Verification script
    And registration success message is not displayed

  Scenario: Registration attempt with existing fiscal code
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate DOB field with 'ValidDOB.key'
    And populate gender field with 'Gender.key'
    And populate fiscal code with 'ExistingFiscalCode.key'
    And populate address fields
    And populate signin fields
    And user selects the create an account button without Email Verification script
    And assert error message displayed
    And assert user is still on the create account page
    And assert text of 'updateDetailsSucessMsg.key' is not displayed

  @dk @dklive @VuseDKReg
  Scenario: Privacy Policy Text and Link CTA on Create Account Page
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    Then assert text of 'privacyPolicyText.key' is displayed
    And assert 'privatePolicy.key' text link and navigation on CTA

  #@34673 - REMOVED FOR NOW _ NEED TO BE ADDED BACK IN
  #Scenario Outline: Registration - phone number validation tests
  #
  #  And populate Personal Information - first and last name with randomly generated data
  #  And populate phone number field with '<PhoneNumber>'
  #  And and assert error validation message of '<Expected Error>'
  #  Examples:
  #    | PhoneNumber         | Expected Error                          |
   #   | blank.key           | expectedRequiredFieldErrorMessage.key   |
      #| InvalidPhoneNumber.key           | invalidPhoneNumberErrorText.key    |


      #| 123-123-123         | Please specify a valid phone number |
      #| jfjdkfjkjfdjk       | Please specify a valid phone number |
      #| 0192!3728271        | Please specify a valid phone number |
      #| 045645645645645645  | Please specify a valid phone number |

  #@fr #invalid scenario for vype FR #477260
  Scenario: Connected Consumer - Mobile Consent
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And verify the phone number field validation
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And verify opt in or out of mobile consent is available under my preferences

  @nl
#  50307
  Scenario: Registration page should not have telephone number field
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    Then assert phone number is field is not displayed
    And assert text of 'phoneNumberText.key' is not displayed

  @MXReg2
  Scenario: pre-filled country code in phone number field
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And assert page title is 'registrationPageTitle.key'
    When user enters phone number in the phone number field as 123456789
    Then assert phone number is pre-filled with country code +52

  #@ITLive NA for VYPEIT
  Scenario: Populate selected Address from Look up and amend manually - RegistrationPage
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    When user start entering the address in registrationPage with streetKeyword.key
    Then user should be presented with Loqate address look up auto-completion
    When user has selected an address from the auto-completion
    Then user address fields should be populated with the selected address
    And user can amend the fields manually if desired