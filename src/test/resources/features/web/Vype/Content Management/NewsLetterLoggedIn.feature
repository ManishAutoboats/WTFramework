#This is the newsletter feature file
  ##Testing
    ## Invalid email entry into the newsletter sub
    ## Entering existing email into newsletter sub
    ## Entering valid (random) email into newsletter sub
Feature: BAT newsletter Logged In Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When create a new account
    And select from myAccount links NewsLetter link
    And ensure the newsletter icon isn't selected
    And ensure newsletter element is present on page
    And ensure newsletter sign up button present on page

  #@VuseZAReg //Scenario after newsletter is changed
  Scenario: Newsletter Logged in  - sign up with valid details and assert Marketing checkbox still selected
    And enter newsletter details with random email and assert 'newUserEmail' subscription
    And select from myAccount links NewsLetter link
    And assert status of marketing tickbox
    And uncheck marketing tickbox

  @MXReg2
  Scenario: Newsletter Logged in  - sign up with valid details MX
    And enter newsletter details with random email and assert 'newUserEmail' subscription

  @regression #@VuseZAReg //Scenario after newsletter is changed
  Scenario: Marketing Preferences -  Style Newsletter Page
    And select from myAccount links NewsLetter link
    And assert text of 'NewsletterSubscriptionText.key' is displayed
    And assert text of 'youHaveRightText.key' is displayed
    And assert text of 'youCanChangeMindText.key' is displayed
    And assert save button is present
    And newsLetter div form displayed

  @MXReg2
  Scenario: Marketing Preferences -  Style Newsletter Page MX
    And assert text of 'NewsletterSubscriptionText.key' is displayed
    And assert save button is present
    And newsLetter div form displayed

  @regression @MXReg2 #@VuseZAReg //Scenario after newsletter is changed
  Scenario: Newsletter Guest - enter invalid e-mail address and assert Error
    And enter newsletter details with invalid email address 'loginInvalidEmail.key' and assert 'invalidEmail' subscription

   #@ITReg
  #Scenario: Newsletter Logged in  - Subscribe Newsletter via Email
    #  And ensure Email checkbox isn't selected
        # And assert text of 'NewsletterSubscriptionEmail.key' is displayed

  #@ITReg due to defect 519149
  Scenario: Newsletter Logged in  - Subscribe Newsletter via Email
    And check Email checkbox isn't selected
    And ensure text of 'SubscriptionEmail.key' is displayed

  #@ITReg due to defect 519149
  Scenario: Newsletter Logged in  - Subscribe Newsletter via Telephone/SMS
    And check SMS checkbox isn't selected
    And check text of 'NewsletterSubscriptionSMS.key' is displayed

  #@ITReg due to defect 519149
  Scenario: Newsletter Logged in  - Remove Newsletter Subscription
    And check SMS checkbox isn't selected
    And check Email checkbox isn't selected

  @IEReg2
  Scenario: Verify user can subscribe(save) and unsubscribe(remove) from my account page
    And select from myAccount links NewsLetter link
    When uncheck email consent checkbox
    And click subscription save button
    Then assert text of 'RemoveNewsletterSubscription.key' is displayed
    And select from myAccount links NewsLetter link
    When tick email consent checkbox
    And click subscription save button
    Then assert text of 'subscriptionSavedMsg.key' is displayed

  @IEReg2
  Scenario: Subscribe to newsletter by submitting the form from footer and verify in My Account page
    And select from myAccount links NewsLetter link
    When uncheck email consent checkbox
    And click subscription save button
    And click on newsletter button
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'subscribeSuccessText.key' is displayed
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox

  @IEReg2
  Scenario: Subscribe to newsletter by submitting the form from header and verify in My Account page
    And select from myAccount links NewsLetter link
    When uncheck email consent checkbox
    And click subscription save button
    And click on newsletter icon on header
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'subscribeSuccessText.key' is displayed
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox