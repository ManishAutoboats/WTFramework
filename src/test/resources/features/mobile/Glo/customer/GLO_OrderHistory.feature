Feature: BAT Account management - order history feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And Glo user clicks on PersonIcon and Navigate to the Login Page
    When user enters sign in details, with username of 'loginValidEmail.key' and password 'loginValidPassword.key'

  @RegGloIT_Mobile
  Scenario: 9829 21647 My order history
    Then user is successfully logged in
    Then users clicks on the 'recentlyOrderedText.key' text link
    And assert text of 'orderText.key' is displayed
    And assert text of 'orderTotalText.key' is displayed
    And assert text of 'statusText.key' is displayed
    And user navigate to my account page