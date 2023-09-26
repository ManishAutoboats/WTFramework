#This is the PLP feature file
# @LyftRegression @LyftSmoke
Feature: PLP filtering to left hand side

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And resize browser screen - full screen
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftDKReg @LyftDKLive
  Scenario: PLP filtering to left hand side
    And users clicks on the 'ProductCategoryText.key' text link
    Then url contains 'ProductCategoryURLText.key'

  Scenario: PLP - Guest - assert navigation and change in language on switching language using language selector
    And users clicks on the 'ProductCategoryText.key' text link
    Then url contains 'ProductCategoryURLText.key'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to home page with 'ProductCategoryURLTextInENFrom.key' and response status code as 200
    And assert navigation to home page with 'ProductCategoryURLTextInENTo.key' and response status code as 200
    Then assert text is displayed in English after switching language
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language

  Scenario: PLP - Logged-In - assert navigation and user remains logged-in on switching language using language selector
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And users clicks on the 'ProductCategoryText.key' text link
    Then url contains 'ProductCategoryURLText.key'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to home page with 'ProductCategoryURLTextInEN.key' and response status code as 200
    Then assert text is displayed in English after switching language
    And assert user remains logged-in after language switch
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    And assert user remains logged-in after language switch

  @LyftRegression2
  Scenario: Category landing page for Lyft SE
    When users clicks on the 'ProductCategoryText.key' text link
    Then url contains 'ProductCategoryURLText.key'
    Then verify content of the category



