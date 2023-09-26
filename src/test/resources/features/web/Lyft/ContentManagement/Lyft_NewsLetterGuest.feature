Feature: BAT Newsletter Subscription Scenarios as a Guest User
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And click on newsletter button

  @LyftRegression2
  Scenario: Newsletter Guest - valid, invalid and existing e-mail address and assert message
    When user enter newsletter details and submits the form with random email
    Then assert text of 'subscribeSuccessText.key' is displayed
    When user enter newsletter details and submits the form with loginInvalidEmail.key
    Then assert text of 'loginInvalidEmailErrorMsg.key' is displayed
    When user enter newsletter details and submits the form with loginSubscribedEmail.key
    When user enter newsletter details and submits the form with loginSubscribedEmail.key
    Then assert text of 'emailAddressAlreadySubscribeTextMsg.key' is displayed

  @LyftRegression2
  Scenario: Newsletter Guest - Under age DOB error message on newsletter registration
    When enter newsletter details and submits the form with below 18 years dob
    Then assert text of 'underAgeNewsLetterError.key' is displayed

  @LyftRegression2
  Scenario: Newsletter Guest - DOB field to be added on newsletter registration
    When enter newsletter details and submits the form with above 18 years dob
    Then assert text of 'subscribeSuccessText.key' is displayed

  @LyftRegression2
  Scenario: Newsletter Guest - do not fill any field and submit verify error message
    When without any field filled click on newsletter subscribe
    Then assert error message for newsletter without filling any details
