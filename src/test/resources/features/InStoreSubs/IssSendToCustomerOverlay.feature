@iss
Feature:  In Store Subs - Send to customer overlay
  Background: Navigate to home page
    Given user is at the In Store Subs home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    And he clicks on the basket CTA
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And he clicks on the send to customer button
    And the send to customer overlay is opened
@isslive
  Scenario: 434645 AC1 AC2 Send to customer journey page components
    And it has a consent with radio button
    And an explanation why the address is being collected
    And an email address field is displayed
    And a submit button which should be disabled initially
@isslive
  Scenario: 434646 AC1 434645 AC3 AC4 Send to customer journey happy path
    Then the colleague selects the consent radio button
    And enters the email address of the customer
    Then the submit CTA gets enabled
    And colleague clicks on the submit CTA
    Then a success message is reported
    And a CTA finish subscription is displayed
    When he clicks on the finish subscription CTA
    Then he should navigate back to the challenge 25 screen
    Then the user receives an email containing "pickUpWhereYouLeftOff"

@isslive
    Scenario: 434645 AC4 Send to customer journey - omit email adress
      Then the colleague selects the consent radio button
      And colleague clicks on the submit CTA
      Then they receive an error message explaining email is required





