#This is the newsletter feature file
  ##Testing
    ## Invalid email entry into the newsletter sub
    ## Entering existing email into newsletter sub
    ## Entering valid (random) email into newsletter sub
Feature: BAT Newsletter Subscription Scenarios as a Guest User
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And ensure newsletter element is present on page
    And ensure newsletter sign up button present on page

  @MXReg @regression @VuseZAReg2 @IEReg #@ITReg #@ITLive due to defect 519149 @NonCaptchaLive
  Scenario: Newsletter Guest - enter valid e-mail address and assert success message
   And enter newsletter details with random email and assert 'newUserEmail' subscription
   Then assert no Success email on newsletter subscription for user

  @MXReg @regression @VuseZAReg @IEReg #@ITReg #@ITLive due to defect 519149 @NonCaptchaLive
  Scenario: Newsletter Guest - enter invalid e-mail address and assert Error
    And enter newsletter details with invalid email address 'loginInvalidEmail.key' and assert 'invalidEmail' subscription

  @MXReg @regression @VuseZAReg @IEReg #@ITReg #@ITLive due to defect 519149 @NonCaptchaLive
  Scenario: Newsletter Guest - enter existing e-mail address and expected message
    And enter newsletter details with existing email address 'loginSubscribedEmail.key' and assert 'alreadySubscribed' subscription

  @dk @dklive @VuseZAReg2 @VuseDKReg
  Scenario: Verify Newsletter Functionality Removed
    And assert Newsletter button is not present on footer
    And assert Newsletter Icon is not present on header

  #@regression- Moved to next release,will update accordingly
  Scenario: SMS marketing Consent
    And click on newsletter button
    And assert country code was already entered as a prefix in Newsletter
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    And assert country code was already entered as a prefix in registration page
    And user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And select from myAccount links NewsLetter link
    And verify the email and sms consent opton
    When user select Marketing preference consent option
    And select from myAccount links NewsLetter link
    Then verify consent option has been selected
    And assert 'loginValidEmail.key' is displayed in newsletter popup
    And assert error is displayed on NewsLetter Signup

  @regression
  Scenario: Newsletter Subscription - Create New Account and assert Subscription Email with Marketing checkbox selection
    And create a new account
    And select from myAccount links NewsLetter link
    And uncheck marketing tickbox
    And select from myAccount links NewsLetter link
    And ensure the newsletter icon isn't selected
    And verify subscription confirmation mail for new user and approve
    And select from myAccount links NewsLetter link
    And assert status of marketing tickbox
    Then assert no Success email on newsletter subscription for user


