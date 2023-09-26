
Feature: BAT newsletter Logged In Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When create a new account via api and log in with the account
    And select from myAccount links NewsLetter link
    And ensure the newsletter icon isn't selected
    And ensure newsletter element is present on page
    And ensure newsletter sign up button present on page

  @VuseUKReg
  Scenario: Marketing Preferences -  Style Newsletter Page
    And select from myAccount links NewsLetter link
    And assert text of 'NewsletterSubscriptionText.key' is displayed
    And assert text of 'youHaveRightText.key' is displayed
    And assert text of 'youCanChangeMindText.key' is displayed
    And assert save button is present
    And newsLetter div form displayed

  @VuseMXReg @VuseITAnonReg3
  Scenario: Newsletter Guest - enter invalid e-mail address and assert Error
    And enter newsletter details with invalid email address 'loginInvalidEmail.key' and assert 'invalidEmail' subscription

  @VuseMXReg
  Scenario: Marketing Preferences -  Style Newsletter Page MX
    And assert text of 'NewsletterSubscriptionText.key' is displayed
    And assert save button is present
    And newsLetter div form displayed

  @VuseMXReg
  Scenario: Newsletter Logged in  - sign up with valid details MX
    And enter newsletter details with random email and assert 'newUserEmail' subscription

  @COReg
  Scenario: Verify user can subscribe(save) and unsubscribe(remove) from my account page
    When tick email consent checkbox
    And click subscription save button
    Then assert text of 'subscriptionSavedMsg.key' is displayed
    And select from myAccount links NewsLetter link
    When uncheck email consent checkbox
    And click subscription save button
    Then assert text of 'newsletterUnSubscriptionMsg.key' is displayed
    And user should receive a newsletter "subscribed" confirmation email
    And user should receive a newsletter "unSubscribed" confirmation email

  @COReg
#   workitem 110051
  Scenario: NewsLetter logged in - user redirected to third party CRM System
    And click on newsletter button
    Then assert Newsletter pop up is displayed
    When user click on signup button
    Then user should be redirected to third party site to register for newsletter

  #@VuseFRReg (Reuse with already present one)
  Scenario: Newsletter user
    And enter newsletter details with random email and assert 'newUserEmail' subscription
    Then assert no Success email on newsletter subscription for user