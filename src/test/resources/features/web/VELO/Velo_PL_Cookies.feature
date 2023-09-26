@veloPLReg
Feature: Velo PL Cookies
  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page for language "pl"

  Scenario: Presence of cookie policy banner, link and buttons
    Then oneTrustCookies popup is displayed to user
    And oneTrustCookies link and buttons is displayed to user

  Scenario: Presence of Privacy preference center and its all elements
    When user click on manage cookies button
    Then ensure all oneTrust privacy center elements are present

  Scenario: All cookie types are turned OFF by default except strictly necessary
    When user click on manage cookies button
    Then ensure all cookies are set to off by default except strictly necessary

  Scenario: User click under 18 on age-gate
    And user selects not over 18 age confirmation option, confirm text and google redirection

  Scenario: Click Cookie icon on homepage
    Then select allow all from cookie popup and select over 18 age confirmation option
    And User clicks on cookie icon on homepage
