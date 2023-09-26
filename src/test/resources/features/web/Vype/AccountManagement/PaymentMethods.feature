# This is the editing customer account details story
# This covers BUG - 31562
@accountDashboard @smokeLite @sav
Feature: BUG - 37915 - BAT Account management - Payment Methods

  Background: Nav to Bat and Login
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'


  Scenario: BUG 37915 : Ensure Payment Methods Tab is present and contains saved card details
    And users clicks on the 'paymentMethodsLinkText.key' text link
    And assert text of 'storedPaymentMethodsTitle.key' is displayed
    And assert title 'MySavedCardHeading.key' is displayed
    And assert saved card div is present
    And users clicks on the 'editText.key' text link
    And assert text of 'MySavedCardHeading.key' is displayed
    And assert text of 'CardNumberText.key' is displayed
    And assert text of 'cardHolderNameText.key' is displayed
    And assert text of 'expiryDateMonthText.key' is displayed
    And click save card details
    And assert text of 'MySavedCardHeading.key' is displayed