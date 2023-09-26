Feature: Vuse Cookie feature -  Mobile

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page

  @RegVuseFR_Mobile
  Scenario: Mobile - Presence of cookie policy banner, link and buttons
    Then oneTrustCookies popup is displayed to user
    And oneTrustCookies link and buttons is displayed to user
