
Feature: BAT Newsletter Subscription Scenarios as a Guest User
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And ensure newsletter element is present on page
    And ensure newsletter sign up button present on page

  @VuseMXReg @VuseUKReg @VuseFRReg3 @VuseITAnonReg3
  Scenario: Newsletter Guest - enter valid e-mail address and assert success message
   And enter newsletter details with random email and assert 'newUserEmail' subscription
   Then assert no Success email on newsletter subscription for user

    @VuseMXReg @VuseMXLive @VuseUKReg @VuseITReg
  Scenario: Newsletter Guest - enter invalid e-mail address and assert Error
    And enter newsletter details with invalid email address 'loginInvalidEmail.key' and assert 'invalidEmail' subscription

  @VuseMXReg @VuseUKReg @VuseITReg @VuseFRReg3
  Scenario: Newsletter Guest - enter existing e-mail address and expected message
    And enter newsletter details with existing email address 'loginSubscribedEmail.key' and assert 'alreadySubscribed' subscription

  @VuseUKReg3
  Scenario: SMS marketing Consent
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And assert country code was already entered as a prefix in registration page
    And create a new account
    And user clicks the person icon
    And select from myAccount links NewsLetter link
    And verify the email and sms consent opton
    When user select Marketing preference consent option
    And verify subscription confirmation mail for new user and approve
    And select from myAccount links NewsLetter link
    Then verify consent option has been selected
    And assert 'registered email' is displayed in newsletter popup
    And assert error is displayed on NewsLetter Signup

  @VuseITAnonReg3
  Scenario: SMS marketing Consent for VuseIT
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And assert country code was already entered as a prefix in registration page
    And create a new account
    And Get email address from my account page
    And select from myAccount links NewsLetter link
    And User fill details of the subscription pop up
    And verify subscription confirmation mail for new user and approve
    When Go back to myAccount links NewsLetter link
    Then verify consent option has been selected

  @VuseUKReg3 @VuseITAnonReg3
  Scenario: Newsletter Guest - SMS Marketing options are displayed
    And click on newsletter button
    Then verify the email and sms consent option is present

  @VuseUKReg
  Scenario: DOB field assertions on Newsletter pop-up as a Guest and a logged-in user
    And click on newsletter button
    And assert text of 'verifyAgeGuidingText.key' is displayed
    And assert DOB error message 'expectedRequiredFieldErrorMessage.key' is displayed on NewsLetter Signup
    And populate DOB field with 'UnderAgeDob.key' on Newsletter Pop-up
    And assert DOB error message 'underAgeErrorMsg.key' is displayed on NewsLetter Signup
    And populate DOB field with 'moreThan100AgeDob.key' on Newsletter Pop-up
    And assert DOB error message 'moreThan100AgeErrorMsg.key' is displayed on NewsLetter Signup
    Then user closes the newsletter pop-up
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And click on newsletter button
    And assert DOB field is not present

    @COReg
#   workitem 110051
  Scenario: NewsLetter Guest - user redirected to third party CRM System
    And click on newsletter button
    Then assert Newsletter pop up is displayed
    When user click on signup button
    Then user should be redirected to third party site to register for newsletter

  @VuseFRReg @VuseFRLive
  Scenario: Vuse FR - DOB field assertions on Newsletter pop-up as a Guest and a logged-in user
    And click on newsletter button
    And assert DOB error message 'expectedRequiredFieldErrorMessage.key' is displayed on NewsLetter Signup
    And populate DOB field with 'UnderAgeDob.key' on Newsletter Pop-up
    And assert DOB error message 'underAgeErrorMsg.key' is displayed on NewsLetter Signup
    And populate DOB field with 'moreThan100AgeDob.key' on Newsletter Pop-up
    And assert DOB error message 'moreThan100AgeErrorMsg.key' is displayed on NewsLetter Signup
    Then user closes the newsletter pop-up
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And click on newsletter button
    And assert DOB field is not present

  #@VuseZAReg //Scenario after newsletter is changed
  Scenario: SMS marketing Consent VuseZA
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And create a new account
    And user clicks the person icon
    And select from myAccount links NewsLetter link
    And verify the email and sms consent opton
    When user select Marketing preference consent option
    And select from myAccount links NewsLetter link
    Then verify consent option has been selected

  @VuseZAReg2
  Scenario: Newsletter flow of VuseZA
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And create a new account
    And user clicks the person icon
    And select from myAccount links NewsLetter link
    Then verify user is directed to 'newNewsleterPage.key'

  @VuseFRReg3
  Scenario: Newsletter Guest - submit newsletter without filling any details
    Then assert error message for submitting newsletter without filling any details

  @VuseFRReg3
  Scenario: Newsletter Guest - Incorrect Email format error
    Then assert error message for incorrect email format