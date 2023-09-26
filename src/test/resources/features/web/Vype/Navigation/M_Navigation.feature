#This is the BAT Navigation feature
#@regression @live
Feature: BAT mobile navigation feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to privacy policy page
#@sprint3 @Navigation
  #Scenario: Guest, opens mobile hamburger menu and close
  #  And click mobile hamburger menu
  #  And cycle through menu options and ensure the following is present 'Subscriptions' 'Favourites' 'My basket'
  #  And click mobile hamburger menu
  #  And resize browser screen - full screen

  #@sprint3 @Navigation
  #Scenario: Guest, re-size browser opens hamburger menu and close
  #  And resize browser screen
  #  And click hamburger menu
  #  And cycle through menu options and ensure the following is present 'Subscriptions' 'The usual' 'Favourites' 'My basket'
  #  Then close the hamburger menu

  @sprint3 @Navigation
  Scenario Outline: Guest, opens hamburger menu select main hamburger links
    And click mobile hamburger menu
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick         | UrlToContain        |
      #| Bundles             | bundles             |
      #| Accessories         | accessories         |
      #| Vype x HoH          | house-of-holland    |
      | Store Locator       | store-locator        |
      | Vype Blog           | blog                |
      | My account          | account             |






