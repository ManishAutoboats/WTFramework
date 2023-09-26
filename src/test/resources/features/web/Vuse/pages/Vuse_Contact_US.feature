Feature: Vuse Feedback form

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    Then user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'

  @VuseUKReg @VuseFRReg @VuseZAReg @VuseCOReg
  Scenario: customer feed back form and assert success message
    And users clicks on the 'contactUsText.key' link
    And url contains 'contactUsUrl.key'
    And user closes the alert if present
    When user completes the contact us form and submits
    Then user should see a text of contactusSuccessMsg.key
    Then this information should be sent from sender.key

  @VuseITReg
  Scenario: submit contact us form and assert success message
    When user navigates to BAT "Contact Us" page via URL
    And completes the contact us form and submits
    Then user should see a text of contactUsSuccessMsg.key

  @VuseITReg @VuseCOReg
  Scenario: Attempt to submit contact us form without agree to consent
    When user navigates to BAT "Contact Us" page via URL
    Then submit form and assert error message

  @VuseZAReg @VuseFRReg
  Scenario: submit contact us form without mandatory field
    When users clicks on the 'contactUsText.key' link
    And submit the form
    Then user should see a text of ErrorcontactUsMsg.key

  @VuseFRReg
  Scenario: Invalid email and Telephone scenario for contactus form
    When users clicks on the 'contactUsText.key' link
    And enter invalid combination for email and telephone number
    And submit the form
    Then assert error message for invalid format email and telephone number
