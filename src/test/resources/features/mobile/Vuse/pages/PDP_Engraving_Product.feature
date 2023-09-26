Feature: BAT PDP - Engraving Product Customizable Options Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And user click on search icon and submits the following search term 'serachTerm_EngravingProduct1'


  Scenario: Verify Library of Motifs and mini icons to customize Engraving Device
    And user clicks on Add button to personalize the engraving product
    Then assert personalization options Motifs and Mini Icons are displayed
    And assert library of Motifs are visible to the user
    When user selects a Motif pattern and assert selection
    And assert library of Mini Icons are visible to the user
    When user selects a Mini Icon and assert selection
