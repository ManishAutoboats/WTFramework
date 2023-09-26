@theme @pdp @live @smokeLite @dksmoke @frSmoke @dklive @fr @dk @NLlive  @frlive @de @desmoke @nl @MXReg2 @delive @IEReg @IElive @COReg @CoLive @VuseZAReg2 @VuseDKReg @VuseDKLive @VuseZALive
 Feature: 13842 BAT PDP Theme feature
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

   Scenario: 13842 Navigate to PDP page via search and product selection - ensure expected PDP elements are present as expected
    And user click on search icon and submits the following search term 'searchTermVype.key'
    And get all lists from page
    And click first result
    Then ensure PDP elements are displayed as expected

