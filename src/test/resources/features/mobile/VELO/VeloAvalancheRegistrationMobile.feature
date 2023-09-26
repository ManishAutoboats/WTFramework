Feature: Velo Avalanche Registration Page - Mobile

  Background: Velo Avalanche Registration Page
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVeloZA_Mobile
  Scenario: AC1 VELO ZA > Customer Registration
    And user clicks the person icon
    And the user navigates to the registration page
    Given the form has the following fields marked as mandatory
      | sa id number     |
      | first name       |
      | last name        |
      | email            |
      | dob              |
      | password         |
      | confirm password |
    And the following mandatory consents with a link to notice copy
      | newsletter  |
      | remember me |
      | gender      |
    And the page has a create your account CTA



  @RegVeloZA_Mobile
  Scenario:  AC3 VELO ZA > Customer Registration
    And user clicks the person icon
    And the user navigates to the registration page
    And I have filled in all mandatory fields other than
      | sa id number     |
      | firstName       |
      | lastName       |
      | email            |
      | dob              |
      | password         |
      | confirmPassword |


  @RegVeloZA_Mobile
  Scenario:  AC1 VELO ZA> Customer Login
    And user clicks the person icon
    And the Login page heading is correct
    And verify all the field labels are correct
    Then I am able enter my email and password
    And there is a sign in CTA for me to login
    And there is a forgot password link
    And there is a create account button

  @RegVeloZA_Mobile
  Scenario:  AC2 VELO ZA> Customer Login
    And user clicks the person icon
    Given I omit the following and click on sign in I get the correct error message
      | email    |
      | password |
      | both     |


 @RegVeloZA_Mobile
  Scenario:  VELO ZA | Customer Login| AC 3 | Verify Forgot Password
    And create a new account via api
    And user clicks the person icon
    And user click on forgot password link
    Then user should be redirected to forgotYourPasswordTitle.key page
    And user email input box displayed and enabled
    And user enters email address registered
    And user click on submit button
    Then user should see the correct password reset message


  @RegVeloZA_Mobile
  Scenario:  VELO ZA | Customer Login| AC 4 | Validate Create Account button
    And user clicks the person icon
    And create a new account
