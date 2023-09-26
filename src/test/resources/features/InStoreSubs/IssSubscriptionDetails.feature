@iss
Feature:  In Store Subs - Subscription Details
  Background: 432776 AC1 434642 AC1 452445 AC1 Navigate to home page
    Given user is at the In Store Subs home page
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    Given user navigates to the Choose Your Flavours screen
    And he adds item 7 to the basket
    When he clicks on the basket CTA
@isslive
  Scenario Outline:514183 AC5 434373 AC5 434644 AC1 subscription details page
    When he adds sufficient quantity to get "<discount level>" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    Then he moves to the next screen where he sees the subscription details page
    And sees items with prices, subtotal, tier discount and subscription price
    And sees VAT
    And CTA Amend Basket
    And CTA  Send to Customer
    And a separate section for setting up monthly payments
  Examples:
    | discount level  |
    | bronze          |
    | silver          |
    | gold            |
@isslive
  Scenario: 434643 AC1 AC2 Amending subscription basket
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    When the colleague clicks on the Amend Subscription CTA
    Then he should be sent to the choose your flavours page
@isslive
  Scenario: 466136 AC2 Monthly payments set up complete via email - invalid email supplied
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    And clicks on the send via email button
    And the colleague selects the consent radio button
    And enters an invalid email address
    And clicks the send email button
    And they receive an error message explaining email is required
@isslive
  Scenario: 568792 AC1 Finish subscription
    When he adds sufficient quantity to get "bronze" discount
    When he clicks on the CTA SET UP SUBSCRIPTION
    Then he clicks on the CTA finish subscription
    And a modal is displayed explaining the basket will be emptied
    And the user clicks on YES
    And is on the Challenge screen
    Given returns to the Choose Your Flavours screen
    Then the basket is emptied


