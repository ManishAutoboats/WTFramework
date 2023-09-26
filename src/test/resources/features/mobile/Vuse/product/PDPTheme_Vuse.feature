 Feature: BAT PDP Theme feature - Mobile
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

   @RegVuseFR_Mobile
   Scenario: Mobile - 13842 Navigate to PDP page via search and product selection - ensure expected PDP elements are present as expected
    And user click on search icon and submits the following search term 'searchTerm.key'
     And user closes the alert if present
    And get all lists from page
    And click first result
    Then ensure PDP elements are displayed as expected


   Scenario: Link for Free Device Trial navigation for a guest user for ePod and ePen devices
     And user click on search icon and submits the following search term 'searchDeviceEPOD.key'
     And user closes the alert if present
     And user clicks on the first link if navigated to PLP page
     Then user clicks on Deferred Payment link for FDT navigation
     Then assert guided sell popup is displayed
     And click popup redirect CTA on popup page
     Then assert URL contains text 'deviceTrial.key'
     Then user selects 'No' option from FDT pop-up for a user with first order
     And user click on search icon and submits the following search term 'searchDeviceEPEN.key'
     And user closes the alert if present
     And user clicks on the first link if navigated to PLP page
     Then user clicks on Deferred Payment link for FDT navigation
     Then assert guided sell popup is displayed
     And click popup redirect CTA on popup page
     Then assert URL contains text 'deviceTrial.key'
