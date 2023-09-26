Feature: Glo NewsLetter Subscription for logged in user

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When create a new account

  @gloItRegression2 #@gloKzRegression blocked by 527504- N/A
  Scenario: Newsletter form mandatory field tests and error message
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And ensure newsletter element is present on page
    And ensure newsletter sign up button present on page
    And click on newsletter button
    When user attempts to submit the newsletter form without mandatory fields
    Then assert error message of NewsLetterRequiredField.key is displayed for
      | firstname         |
      | lastname          |
      | dob_date          |
      | dob_month         |
      | dob_year          |
      | email             |
      | termsAndCondition |

  @gloItRegression2 #@gloKzRegression blocked by 527504- N/A
  Scenario: Subscribe to newsletter by submitting the form and verify in My Account page
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And select from myAccount links NewsLetter link
    And uncheck email and sms consent checkbox and click save button
    And click on newsletter button
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'subscribeSuccessText.key' is displayed
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox
    And ensure SMS checkbox is selected
    And uncheck email and sms consent checkbox and click save button

  @gloItRegression2 #@gloKzRegression blocked by 527504- N/A
  Scenario: Already Subscribed to newsletter error message
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And select from myAccount links NewsLetter link
    And uncheck email and sms consent checkbox and click save button
    And click on newsletter button
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'subscribeSuccessText.key' is displayed
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox
    And ensure SMS checkbox is selected
    And click on newsletter button
    When user enter newsletter details and submits the form with registered email
    Then assert text of 'emailAddressAlreadySubscribeTextMsg.key' is displayed
    Then assert status of marketing tickbox
    And ensure SMS checkbox is selected
    And uncheck email and sms consent checkbox and click save button

  @gloItRegression2
    # PL removed from this step as bug #468378
  Scenario: Verify user can subscribe(save and update) and unsubscribe(remove) from my account page
    And select from myAccount links NewsLetter link
    And uncheck email and sms consent checkbox and click save button
    And select from myAccount links NewsLetter link
    When tick email consent checkbox
    And click subscription save button
    Then assert Subscription message 'subscriptionSavedMsg.key' is displayed
    And select from myAccount links NewsLetter link
    When tick SMS consent checkbox
    And click subscription save button
    Then assert Subscription message 'subscriptionUpdatedMsg.key' is displayed
    And select from myAccount links NewsLetter link
    When uncheck email and sms consent checkbox and click save button
    Then assert Subscription message 'RemoveNewsletterSubscription.key' is displayed

  @gloKzRegression
  Scenario: Verify user can subscribe and unsubscribe from my account page
    And user closes the RDB pop-up banner if present
    And select from myAccount links NewsLetter link
    And uncheck email and sms consent checkbox and click save button
    Then assert Subscription message 'RemoveNewsletterSubscription.key' is displayed
    And select from myAccount links NewsLetter link
    When uncheck email and sms consent checkbox and click save button
    Then assert Subscription message 'subscriptionSavedMsg.key' is displayed

  @gloPlRegression #Bug 468378 to be resolved
  Scenario: Verify user can subscribe from my account page
    And user navigate to my account page
    And select from myAccount links NewsLetter link
    And uncheck email and sms consent checkbox and click save button
    And select from myAccount links NewsLetter link
    When tick email consent checkbox
    And click subscription save button
    And assert message about NewsLetter Subscription is displayed

  @gloPlRegression #Bug 468378 to be resolved
  Scenario: Verify user can subscribe to SMS and then unsubscribe
    And user navigate to my account page
    And select from myAccount links NewsLetter link
    When tick SMS consent checkbox
    And click subscription save button
    And assert message about NewsLetter Subscription is displayed
    And select from myAccount links NewsLetter link
    When uncheck email and sms consent checkbox and click save button
    And assert message about NewsLetter Subscription is displayed


  @gloDeRegression2
  Scenario: Verify user can subscribe(save) and unsubscribe(remove) from my account page
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And assert Newsletter button is not present on footer
    And assert Newsletter Icon is not present on header
    And select from myAccount links NewsLetter link
    When tick email consent checkbox
    And click subscription save button
    Then assert text of 'subscriptionSavedMsg.key' is displayed
    And select from myAccount links NewsLetter link
    When uncheck email consent checkbox
    And click subscription save button
    Then assert text of 'newsletterUnSubscriptionMsg.key' is displayed
    And user should receive a newsletter "unSubscribed" confirmation email

#  @gloDeRegression blocked by bug 479857
  Scenario: GloDe Checkout - newsletter subscription - verify in my account page - order and NL email confirmation
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    And Glo user clicks on Buy Link and add the product to mini cart
    And Payment page details confirmed
    And user completes "VISA" payment details
    Then user should see a checkbox with option to subscribe to newsletter with text checkoutNewsLetterSubText.key
    When user select the newsletter subscription checkbox
    And select place order from Card
    And grab and output Order number
    Then Glo assert on Order Confirmation page 'thankYouMessageHeading.key' is displayed
    And user clicks the person icon
    And select from myAccount links NewsLetter link
    Then assert status of marketing tickbox
    And user should receive a newsletter "subscribed" confirmation email
    And user should receive a order confirmation email with order number
