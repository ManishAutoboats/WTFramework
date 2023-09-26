Feature: BAT Account management - order history feature - Mobile
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user clicks the person icon
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

  @RegVuseFR_Mobile
  Scenario: Mobile - 9829 21647 My order history
    And user is successfully logged in
    And users clicks on the 'recentlyOrderedText.key' text link mobile
    Then assert text of 'recentlyOrderedText.key' is displayed
    Then assert text of 'dateText.key' is displayed
    Then assert text of 'orderTotalText.key' is displayed