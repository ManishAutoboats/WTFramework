Feature: Glo NewsLetter Subscription for logged in user - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When create a new account

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