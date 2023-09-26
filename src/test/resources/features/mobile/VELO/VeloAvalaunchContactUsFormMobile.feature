Feature: VELO Contact Us tests - Mobile

  Background: Navigate to BAT Home Page


  @RegVeloZA_Mobile
  Scenario: VELO ZA submit contact us form and assert success message
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user click 'contactHeading' from mobile sandwich menu
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key


  @RegVeloZA_Mobile
  Scenario:  Velo ZA Attempt to submit contact us form without agree to consent
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user click 'contactHeading' from mobile sandwich menu
    And Click all required fields and validate error messages
      | name    |
      | email   |
      | message |
