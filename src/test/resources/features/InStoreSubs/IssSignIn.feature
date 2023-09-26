@iss
Feature: In Store Subs - Sign In

  Background: 432776 AC1 434642 AC1 452445 AC1 Navigate to home page
    Given user is at the In Store Subs home page
    Then he can see the Vuse logo on the header
    When he clicks on STORE drop down with locations

  Scenario: 432776 AC2 incorrect user name and password
    And he can select the store from the drop down where he is sitting
    When he enters incorrect USERNAME and PASSWORD
    And clicks on SIGN IN CTA
    Then he sees a message to enter correct details

  Scenario: 515742 AC1 432776 AC3 valid user and pass
    And he can select the store from the drop down where he is sitting
    And the Staff ID filed is labelled correctly
    When he enters a valid USERNAME and PASSWORD
    And clicks on SIGN IN CTA
    Then he is signed into the InStore Subs
@isslive
  Scenario: 434642 AC1 AC2 Tablet Header details & Sign Out
    And he can select the store from the drop down where he is sitting
    Given the user is signed in
    And is on the Challenge screen
    Then he sees Vuse logo, sign in as staff member name
    And a CTA link for sign out
    When he clicks on the Sign Out CTA in the header
    Then he should navigate back to the Staff Sign In screen

  Scenario: 434642 AC3 433990 AC1 AC2 CHOOSE YOUR FLAVOURS and Challenge 25
    And he can select the store from the drop down where he is sitting
    Given the user is signed in
    And accepts the challenge screen
    And click on Start Subscription button
    Then user is on the Choose your Flavours screen
    And should see an additional link start Again

  Scenario: 452447 AC1 Store list in alphabetical order
    Then he sees list of stores which are enabled for In-Store Subs
    And he can select the store from the drop down where he is sitting
    And the store list is in alphabetical order
@isslive
    Scenario: 434647 AC1 AC2 Retrieve a previous quote
      And he can select the store from the drop down where he is sitting
      Given the user is signed in
      And is on the Challenge screen
      And can see the scan code under the retrieve a previous quote section
      When he clicks on the Scan Code CTA
      Then he gets an overlay to scan the code
      When he clicks on Cancel Scan CTA
      Then is on the Challenge screen

  Scenario Outline: 540190 Allow store staff to change their own password - invalid submissions
    And he can select the store from the drop down where he is sitting
    Given the user is signed in
    And the user clicks on the Change Password link
    Then The change password page is displayed with correct components
    When I enter a "<current password status>" current password
    And I enter a "<new password status>" new password
    And I enter a "<confirm passsword status>" confirm password
    When I submit the password change
    Then I see the correct error message
    Examples:
    | current password status | new password status | confirm passsword status |
    | null                    | null                | null                     |
    | null                    | valid               | valid                    |
    | spaces                  | valid               | valid                    |
    | incorrect               | valid               | valid                    |
    | correct                 | null                | null                     |
    | correct                 | null                | valid                    |
    | correct                 | valid               | null                    |
    | correct                 | spaces              | valid                    |

  Scenario: 540190 Allow store staff to change their own password - valid submissions
    And he can select the store from the drop down where he is sitting
    Given the user is signed in
    And the user clicks on the Change Password link
    Then The change password page is displayed with correct components
    When I enter a "correct" current password
    And I enter a "valid" new password
    And I enter a "valid" confirm password
    When I submit the password change
    Then he should navigate back to the Staff Sign In screen
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    And the user signs in with a "valid" password
    Then he is signed into the InStore Subs
    And the user clicks on the Change Password link
    Then The change password page is displayed with correct components
    When I enter a "valid" current password
    And I enter a "correct" new password
    And I enter a "correct" confirm password
    When I submit the password change
    Then he should navigate back to the Staff Sign In screen
    When he clicks on STORE drop down with locations
    And he can select the store from the drop down where he is sitting
    When he enters a valid USERNAME and PASSWORD
    And clicks on SIGN IN CTA
    Then he is signed into the InStore Subs