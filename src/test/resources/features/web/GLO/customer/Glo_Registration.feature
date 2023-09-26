Feature: User Registration

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloItRegression
  Scenario: Registration of underage user
    And user goes to registration form
    And add personal data for glo user with date of birth 'UnderAgeDob.key' and gender 'gender.key'
    And add postal code field with 'codiceTaxUnderAge.key'
    And add home town field with 'homeTown.key'
    And add telephone number field with 'validPhoneNumber.key'
    And populate glo address fields 'addressKeyword.key'
    And add province field with 'province.key'
    And populate signin fields for Glo
    And tick agree to terms and conditions of glo on registration page
    Then user selects the create an account button
    Then assert text of 'regUnderAgeErrorMsg.key' is displayed

  @gloDeSmoke @gloDeRegression2 @gloItSmoke @gloItRegression @gloPlRegression2
  Scenario: Registration of new-user
    When create a new account
    And wait for the page to fully load
    Then validation success message displayed
    And assert text of 'successRegistraionMsg.key' is displayed

  @gloItRegression2
  Scenario: User should receive Welcome email on successful registration
    When create a new account with random email
    Then assert text of 'successRegistraionMsg.key' is displayed
    And user should receive a email with "welcomeEmailContentTxt.key" to registered email

  @gloDeRegression2
  Scenario: User should receive Welcome email on successful registration - GloDE
    When create a new account with random email
    Then assert message of 'successRegistraionMsg.key' is displayed on the page
    And user should receive a email with "welcomeEmailContentTxt.key" to registered email

  @gloKzRegression
  # USER STORY 141036 - news letter - kz
  Scenario: Glo Registration - Register new user - signup news letter
    When create a new account
    And user closes the RDB pop-up banner if present
    And user clicks the person icon
    And user clicks on 'MyAccount.key' link from Person Menu
    Then Glo should be redirected to myAccountPageTitle.key page
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox

  @gloKzRegression
  Scenario: Glo Registration - Register new user - new workflow
    When create a new account with random email
    Then assert text of 'registrationPendingMsg.key' is displayed
    When user attempts to login with registered random email
    Then assert text of 'accountCreationUnderReviewMsg.key' is displayed
    When Admin approves the newly created account
    And user attempts to login with registered random email
    Then assert glo pageTitle is 'homePageTitle.key'

  @gloKzRegression
#  USER STORY 217646
  Scenario: Minimum age 21 years - Registration Page DOB field validation
    When user goes to registration form
    And user attempt to create an account with age as in table then assert expected message is displayed
      | age      | expected                  |
      | 20-years | minimumAgeErrorMsg.key    |
      | 21-years | successRegistraionMsg.key |

  @gloDeRegression2 @gloItRegression2
  Scenario Outline: Registration attempt without mandatory <field>
    And user goes to registration form
    When user attempts to create a new account without <field>
    Then assert text of 'requiredField.key' is displayed
    And assert user is still on the create account page
    Examples:
      | field                 |
      | firstName             |
      | lastName              |
      | dob                   |
      | city                  |
      | postcode              |
      | email                 |
      | password              |
      | confirmPassword       |

  @gloItRegression2
  Scenario Outline: Registration attempt without mandatory streetAndAddressLine1
    And user goes to registration form
    When user attempts to create a new account without <field>
    Then assert text of 'requiredField.key' is displayed
    And assert user is still on the create account page
    Examples:
      | field                 |
      | streetAndAddressLine1 |

  @gloDeRegression2
  Scenario Outline: Registration attempt without mandatory streetAndAddressLine1 - Glo DE
    And user goes to registration form
    When user attempts to create a new account without <field>
    Then assert text of 'requiredStreet.key' is displayed
    And assert user is still on the create account page
    Examples:
      | field                 |
      | streetAndAddressLine1 |

  #@gloDeRegression2(Gender option is removed from create account steps from Glo DE)
  Scenario Outline: Registration attempt without non-mandatory <field>
    And user goes to registration form
    When user attempts to create a new account without <field>
    Then assert text of 'successRegistraionMsg.key' is displayed
    And validation success message displayed
    Examples:
      | field  |
#      | phoneNumber | blocked by bug 514426
      | gender |

  @gloDeSmoke @gloDeRegression2 @gloKzRegression @gloItRegression2
  Scenario: Registration attempt without Ts and Cs checkbox ticked
    And user goes to registration form
    When user attempts to create a new account without TermsAndConditions
    Then assert text of 'requiredField.key' is displayed
    And assert user is still on the create account page

  @gloDeSmoke #@gloDeRegression- Block by bug 484087
  Scenario: pre-filled country code in phone number field
    And user goes to registration form
    When user enters phone number in the phone number field as 123456789
    Then assert phone number is pre-filled with country code +49

  @gloDeSmoke @gloDeRegression2 @gloKzRegression @gloItRegression2
  Scenario: Registration Page with mandatory fields
    And user goes to registration form
    Then the user should see the "*" symbol for the mandatory fields
      | firstName             |
      | lastName              |
      | streetAndAddressLine1 |
      | city                  |
      | postcode              |
      | email                 |
      | password              |
      | confirmPassword       |
      | dob                   |

  @gloDeRegression2 @gloKzRegression @gloItRegression2
  Scenario: Registration Page password and confirm password mismatch error
    And user goes to registration form
    When the user attempts to create an account with mismatch password and confirm password
    Then assert text of 'passwordMismatchError.key' is displayed

  @gloItLive #@gloDeLive blocked by bug 351449
  Scenario: Populate selected Address from Look up and amend manually - RegistrationPage
    And user goes to registration form
    When Glo start entering the address in registrationPage with streetKeyword.key
    Then Glo should be presented with Loqate address look up auto-completion
    When Glo has selected an address from the auto-completion
    Then Glo address fields should be populated with the selected address
    And Glo can amend the fields manually if desired

  @gloItRegression2
  Scenario Outline: 53335 Registration - Codice Fiscale with/without DOB tests.
    And user goes to registration form
    When Glo attempts to create an account with CodiceFiscal-<Condition>
    Then assert text of '<message>' is displayed
    Examples:
      | Condition                   | message                      |
      | NoDOBField                  | DateErrorMessage.key         |
      | NoGenderField               | GenderErrorMessage.key       |
      | RandomNumberNoDOBIncluded   | CodiceFiscalErrorMessage.key |
      | RandomNumberWithDOBIncluded | successRegistraionMsg.key    |

  @gloItRegression2
  Scenario Outline: 53335 Registration - Codice Fiscale length(16 or less) validation tests
    And user goes to registration form
    When Glo attempts to create an account with CodiceFiscal-<length>
    Then assert text of '<message>' is displayed
    Examples:
      | length    | message                            |
      | length-17 | CodiceFiscalLenghtErrorMessage.key |
      | length-16 | successRegistraionMsg.key          |
      | length-15 | successRegistraionMsg.key          |
      | length-10 | successRegistraionMsg.key          |

  @gloPlRegression2
  Scenario: GloPl Subscribe to newsletter via registration - verify in my account page
    And assert Newsletter button is not present on footer
    When create a new account with marketing communication checks selected
    When user navigates to my account page from header
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox
    And ensure SMS checkbox is selected
    And ensure thirdParty consent checkbox is selected

  @gloPlRegression2
  Scenario: GloPl Mark all marketing consents field in registration
    And user goes to registration form
    Then assert all marketing consent checkbox
    Then assert all marketing consent checkox is deselected
    When create a new account with mark all marketing consent checks selected
    When user navigates to my account page from header
    And select from myAccount links NewsLetter link
    Then assert all marketing tickbox should be selected
    And user selects deleteMyAccount link on my account Page
    And user goes to registration form
    When create a new account with marketing communication checks selected
    When user navigates to my account page from header
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox
    And ensure SMS checkbox is selected
    And ensure thirdParty consent checkbox is selected

  @gloKZRegression @gloKzLive
  Scenario: Glo KZ - assert error validations on entering invalid User ID
    And user goes to registration form
    And user attempts to create a new account without ValidUserID
   #Commenting below assertion as the ID field made non mandatory temporarily-will uncomment once 652624 is resolev
   # Then assert error message 'idRequiredErrMessage.key' is displayed for User ID field
    And user goes to registration form
    Then user enters more than '13' characters in ID field and assert error message
    Then user enters less than '10' characters in ID field and assert error message
    Then user enters non-numeric '12' characters in ID field and assert error message

  @gloKzRegression
  Scenario: Glo KZ - assert error validations on entering invalid Phone number
    When user goes to registration form
    Then user enters less than '11' characters in Phone number field and assert error message
    Then user enters non-numeric '11' characters in Phone number field and assert error message
    Then user enters invalid phone number in Phone number field and assert error message

  @gloKZRegression @gloPlRegression2 @gloItRegression2 @gloDeRegression
  Scenario: Person Data verification after register new user
    When create a new account
    And user clicks on personal data link
    Then assert all elements are displayed

  @gloItRegression2
  Scenario: Create New Account,delete the account and create new user with deleted details GLO IT
    When create a new account
    And validation success message displayed
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    Then fetch First and Last Names of the logged-in user
    And fetch Email Address of the logged-in user
    And fetch DOB and Address Details of the logged-in user
    When user navigates to my account page from header
    And user selects deleteMyAccount link on my account Page
    Then account is deleted successfully on my account Page
    And user wait for home page to load
    When create a new account with deleted user details
    Then user should receive a email with "welcomeEmailContentTxt.key" to registered email

    #user story 112178 account deletion cases begin
  @gloKzRegression
  Scenario: User can see delete my account option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    When users clicks on the 'deleteMyAccountLinkText.key' text link
    Then assert text of 'deleteMyAccountLinkText.key' is displayed
    And assert delete my account button displayed

  @gloKzRegression
  Scenario: User can delete account
    Given create a new account
    And user closes the RDB pop-up banner if present
    When user delete account from delete my account page
    Then assert title 'logonPageTitle.key' is displayed

  @gloKzRegression
  Scenario: User can't delete account without ticking the consent CheckBox
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'
    Given users clicks on the 'deleteMyAccountLinkText.key' text link
    When User delete account without ticking the consent CheckBox
    Then assert text of 'accountDeleteError.key' is displayed
   #user story 112178 account deletion cases end

  @gloItRegression2
  Scenario: No duplicate items in the address dropdown list - RegistrationPage
    When user goes to registration form
    Then assert no duplicate items in the address dropdown list

  #automate issue 965135
  Scenario: Validate Autocomplete results dropdown displayed
    When user goes to registration form
    And user type partial post code "4"
    Then assert autocomplete results dropdown displayed
