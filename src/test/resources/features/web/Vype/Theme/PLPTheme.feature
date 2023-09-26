@regression @theme @plp @live @dksmoke @frSmoke @dklive @fr @dk @NLlive @nlSmoke @frlive @de @desmoke @nl  @delive @IEReg @IElive @COReg @CoLive @VuseDKReg @VuseDKLive
Feature: 21063 13845 Theme - PLP - Quick add to basket (including quantities) Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  Scenario: 21063 13845 - Navigate to PLP and ensure Quantity and Strength (or colour) is selectable
    And user click on search icon and submits the following search term 'searchTermePen3.key'
    And get all lists from page
    And select product strength or colour
    And update qty input field and confirm update



