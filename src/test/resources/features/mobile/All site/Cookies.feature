Feature: BAT Cookie feature - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page

  @RegGloIT_Mobile
  Scenario: Presence of cookie policy banner, link and buttons
    Then oneTrustCookies popup is displayed to user
    And oneTrustCookies link and buttons is displayed to user


  @RegVuseFR_Mobile
  Scenario: Stationary Cookie icon and footer link available
    And select allow all from cookie popup
    Then assert stationary cookies icon and footer links are available

  @RegVuseFR_Mobile
  Scenario: 'Accept All' button enables all type of cookies in Privacy center for user - floating icon
    When user click on manage cookies button
    And user clicks on oneTrustCookies acceptAll button from privacy center
    And user clicks on oneTrust floating icon
    Then ensure all cookies are set to on

  @RegGloIT_Mobile
  Scenario: Presence of cookie banner until choice is made
    When user navigates to the Blog page
    Then oneTrustCookies popup is displayed to user

  Scenario: Presence of Privacy preference center and its all elements
    When user click on manage cookies button
    Then ensure all oneTrust privacy center elements are present