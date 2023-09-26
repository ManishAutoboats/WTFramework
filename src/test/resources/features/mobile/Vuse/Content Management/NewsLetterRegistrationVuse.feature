Feature: BAT newsletter Registration Feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @RegVuseFR_Mobile
 Scenario: Mobile NewsLetter - Register and sign
    And create a new account via api and log in with the account
   And select from myAccount links NewsLetter link mobile
   Then assert status of marketing tickbox

  @RegVuseFR_Mobile
  Scenario: Mobile NewsLetter - Register Newsletter Subscription on registration page
    And user clicks the person icon
    And user clicks the registration button
    And populate Personal Information - first and last name with randomly generated data
    And populate gender field with 'Gender.key'
    And populate address fields
    And populate DOB field with 'ValidDOB.key'
    And populate signin fields
    And tick signup for newsLetter tick box
    And user selects the create an account button without Email Verification script
    And ensure newsletter sign up button present on page
    And enter newsletter details with random email and assert 'newUserEmail' subscription
    Then assert no Success email on newsletter subscription for user
