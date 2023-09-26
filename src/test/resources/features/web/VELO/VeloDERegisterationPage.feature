Feature: Velo Registration Page

Background: Navigate to Velo Home Page
  Given user navigates to BAT home page
  And select allow all from cookie popup and select over 18 age confirmation option

  @veloDEReg
  Scenario: Registration Page with mandatory fields
    And user click on PersonIcon and Navigate to the Login Page
    And user clicks the registration button
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
      |country                |

  @veloDEReg
  Scenario: Registration form mandatory field tests and error message
    And user click on PersonIcon and Navigate to the Login Page
    And user clicks the registration button
    And click on Manual Address entry link
    When user click on submit button
    Then assert error message of RegistrationRequiredField.key is displayed for registration
      | firstname             |
      | lastname              |
      | email_address         |
      | dob                   |
      | streetAndAddressLine1 |
      | city                  |
      | zip                   |
      | password              |
      | password-confirmation |
      | termsAndCondition     |

  @veloDEReg
  Scenario: Registration attempt without Ts and Cs checkbox ticked
    And user click on PersonIcon and Navigate to the Login Page
    And user clicks the registration button
    When user attempts to create a new account without TermsAndConditions
    Then assert text of 'requiredField.key' is displayed
    And assert user is still on the create account page

  @veloDEReg
  Scenario: Registration of new-user
    When create a new account
    Then assert text of 'successRegistrationMsg.key' is displayed
    And validation success message displayed