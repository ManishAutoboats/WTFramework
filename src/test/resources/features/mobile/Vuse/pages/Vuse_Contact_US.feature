Feature: Vuse Feedback form - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'

  @RegVuseFR_Mobile
  Scenario: Mobile - customer feed back form and assert success message
    And users clicks on the 'contactUsText.key' from here to help menu on footer
    And user closes the alert if present
    When user completes the contact us form and submits
    Then user should see a text of contactusSuccessMsg.key
    Then this information should be sent from sender.key
