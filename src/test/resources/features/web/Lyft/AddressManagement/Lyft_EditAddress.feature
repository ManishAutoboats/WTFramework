Feature: BAT Address management - Edit address

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    Then users clicks on the 'yourAddressText.key' text link

  @LyftRegression
  Scenario Outline: Edit delivery/biliing Address Successful
    When users clicks on address link <change address>
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    And click the save address Pop up screen button
    Then user is returned to the base Address book page
    Examples:
      | change address          |
      | change delivery address |
      | change billing address  |

  @LyftRegression
  Scenario Outline: Edit Address Error Validations and Cancel button CTA
    When users clicks on address link <change address>
    And clear all address fields
    And click the save address Pop up screen button
    Then assert text of 'expectedRequiredFieldErrorMessage.key' is displayed
    And user clicks on Cancel button from Address pop-up screen
    Then user is returned to the base Address book page
    Examples:
      | change address          |
      | change delivery address |
      | change billing address  |

  @LyftRegression
  Scenario: Edit Address with default billing address
    When users clicks on address link 'change billing address'
    Then assert text of 'editAddressText.key' is displayed
    And populate all address fields except for first and last name
    And tick default shipping address
    And click the save address Pop up screen button
    Then user is returned to the base Address book page