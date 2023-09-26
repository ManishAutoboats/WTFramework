
Feature: 21063 13845 Theme - PLP - Quick add to basket (including quantities) Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDELive @VuseUKLive2 @VuseFRLive @VuseFRReg3 @VuseUKReg2 @VuseDEReg2
  Scenario: Navigate to PLP and ensure Quantity and Strength (or colour) is selectable
    And user click on search icon and submits the following search term 'searchTermePen3.key'
    And user closes the alert if present
    And get all lists from page
    And select product colour

  @VuseDEReg @VuseUKReg
  Scenario: PLP page review rating
    When user signs in with customer properties 'loginValidEmail.key' 'loginValidPassword.key'
    And user navigates to PLP page
    And assert review rating is present for all the products on PLP

