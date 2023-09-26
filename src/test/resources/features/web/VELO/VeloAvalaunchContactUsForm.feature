Feature: VELO Contact Us tests

  Background: Navigate to BAT Home Page

  @veloPLReg
  Scenario: submit contact us form and assert success message
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on the link that contains 'contactUsUrl.key' href
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key

  @veloPLReg @veloPLLive
  Scenario: Attempt to submit contact us form without agree to consent
    Given user navigates to BAT home page for language "pl"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    When user enters sign in details, with username of 'loginValidUser' and password 'loginValidPassword'
    When user clicks on the link that contains 'contactUsUrl.key' href
    And Click all required fields and validate error messages
      | name    |
      | email   |
      | phone   |
      | message |


  @veloZAReg
  Scenario: VELO ZA submit contact us form and assert success message
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on the link that contains 'contactHeading' href
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key

  @veloZAReg
  Scenario:  Velo ZA Attempt to submit contact us form without agree to consent
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on the link that contains 'contactUsUrl.key' href
    And Click all required fields and validate error messages
      | name    |
      | email   |
      | message |



