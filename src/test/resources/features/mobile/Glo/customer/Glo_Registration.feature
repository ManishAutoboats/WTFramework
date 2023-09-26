Feature: User Registration - Mobile

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegGloIT_Mobile
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

  @RegGloIT_Mobile
  Scenario: Registration Page password and confirm password mismatch error
    And user goes to registration form
    When the user attempts to create an account with mismatch password and confirm password
    Then assert text of 'passwordMismatchError.key' is displayed

  Scenario: Person Data verification after register new user
    When create a new account
    And user clicks on personal data link
    Then assert all elements are displayed

  Scenario: User should receive Welcome email on successful registration
    When create a new account with random email
    Then assert text of 'successRegistraionMsg.key' is displayed
    And user should receive a email with "welcomeEmailContentTxt.key" to registered email