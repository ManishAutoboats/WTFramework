# This is the editing customer account details story
# This covers BUG - 31562
Feature: BAT Account management - Save Card

  Background: Nav to Bat and Login
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

  @LyftRegression
  Scenario: Ensure Saved Card Tab is present and contains saved card details
    And user clicks on the view all cards link
    Then assert text of 'MySavedCardHeading.key' is displayed
    Then assert saved card div is present
    And users clicks on the 'editText.key' text link
    Then assert text of 'updateCardHeading.key' is displayed
    Then assert text of 'CardNumberText.key' is displayed
    Then assert text of 'cardHolderNameText.key' is displayed
#    And click save card details
#    And assert text of 'MySavedCardHeading.key' is displayed

  @LyftDKReg
  Scenario: Lyft DK - Ensure Saved Card Tab is present and contains saved card details
    And users clicks on Payment link from My Account Dashboard
    Then assert text of 'MySavedCardHeading.key' is displayed
    Then assert saved card div is present
    And users clicks on the 'editText.key' text link
    Then assert text of 'updateCardHeading.key' is displayed
    Then assert text of 'CardNumberText.key' is displayed
    Then assert text of 'cardHolderNameText.key' is displayed


