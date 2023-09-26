Feature: Glo Contact Us tests

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloPlRegression2 @gloItRegression2
  Scenario: submit contact us form and assert success message
    When user clicks on the link that contains 'contactUsUrl.key' href
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key

  @gloPlRegression2
  Scenario: Attempt to submit contact us form without agree to consent
    When user clicks on the link that contains 'contactUsUrl.key' href
    And completes the contact us form and submits without agree to consent
    Then user should see a text of contactUsConsentBoxErrorMsg.key

  @gloKzRegression @gloDeRegression
  Scenario: glokz - submit contact us form and assert success message
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And users clicks on the 'contactUsTopNavigation.key' text link
    And url contains 'contactUsUrl.key' text
    And assert visit correct environment
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key

  @gloKzRegression
  Scenario: glokz - Attempt to submit contact us form without agree to consent
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And users clicks on the 'contactUsBottomNavigation.key' text link
    And completes the contact us form and submits without agree to consent
    Then user should see a text of contactUsFormRequiredErrorMsg.key

  @gloKzRegression @gloDeRegression
  Scenario: glokz - Attempt to submit contact us form without mandatory fields
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user closes the RDB pop-up banner if present
    And users clicks on the 'contactUsTopNavigation.key' text link
    When submit the form without mandatory fields then assert error message
      | field   |
      | name    |
      | email   |
      | message |