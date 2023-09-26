Feature: BAT Vuse Universal Smoke Test
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg2
  Scenario Outline: Registration - Payments - Delete Account
    When create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "<Payment Type>"
    Then assert text of 'ThankForPurchase.key' is displayed
    Examples:
      | Payment Type |
      | paypal       |
      | sofort       |

  @VuseUKReg2 @VuseFRReg2 @VuseITAnonReg2
  Scenario Outline: Registration - Payments - Delete Account -Vuse
    When create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "<Payment Type>"
    Then assert text of 'ThankForPurchase.key' is displayed
    Examples:
      | Payment Type |
      | mastercard   |
      | paypal       |

  # Implement this later in future release
  Scenario: Smoke Flow - Registration - Google Pay Payment
    And create a new account
    And user navigates to PLP page and adds a product to basket
    And Customer makes a home delivery purchase with "google pay"
