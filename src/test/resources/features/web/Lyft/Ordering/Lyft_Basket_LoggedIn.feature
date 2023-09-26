#@LyftRegression #TODO
Feature: BAT Basket Persistence feature - Logged In

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'

  Scenario: Basket - Persistence - Guest to Logged in item remains in basket
    And users clicks on the 'logoutText.key' text link
    And click on the logo
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And user clicks the person icon -lyft
    And user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    Then confirm mini-basket displayed amount of '1'

  Scenario: Basket - Persistence - Logged in to Logged out item do not remain in basket
    And user click on search icon and submits the following search term 'searchTermdi.key'
    And select product strength or colour
    And from PLP click add to cart
    Then confirm mini-basket displayed amount of '1'
    And user clicks the person icon -lyft
    When users clicks on the 'logoutText.key' text link
    And click on basket icon
    And basket is empty message is presented

  Scenario: Cart Page - Logged-In - assert navigation with no change in cart quantity on switching language using language selector
    Then empty basket
    Then search and view basket for the product 'searchTermdi.key'
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to cart page with 'urlInEnglishOnLangSwitch.key' and response status code as 200
    Then assert that page title is 'basketPageTitleInEN.key'
    Then confirm mini-basket displayed amount of '1'
    Then assert text is displayed in English after switching language
    And assert user remains logged-in after language switch
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language
    Then confirm mini-basket displayed amount of '1'
