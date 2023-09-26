Feature: Velo Avalanche PLP - Mobile


  @RegVeloZA_Mobile
  Scenario:Velo ZA - FAQ section displayed assertion for PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And assert text of 'FAQ.key' is displayed
    And assert text of 'FAQQuestion1' is displayed
    And assert text of 'FAQQuestion2' is displayed
    And assert text of 'FAQQuestion3' is displayed
    And assert text of 'FAQQuestion4' is displayed
    And assert text of 'FAQQuestion5' is displayed
    And assert text of 'FAQQuestion6' is displayed

  @RegVeloZA_Mobile
  Scenario:Velo ZA - Assertion for Filtering in PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    And user clicks on 'filter' option
    And user clicks on 'Medium' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'
    And user clicks on 'filter' option
    And user clicks on 'Mild' option
    And user clicks done in filter pop-up
    And confirm number of results returned is greater than '0'

  @RegVeloZA_Mobile
  Scenario:Velo ZA - Assertion for Sorting in PLP
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    When user navigates to PLP page
    Then user clicks on 'sorting' option
    Then user clicks on 'Name A-Z' option
    And confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    Then user clicks on 'sorting' option
    And user clicks on 'Name Z-A' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price low' option
    Then confirm number of results returned is greater than '0'
    Then user clicks on 'sorting' option
    And user clicks on 'Price high' option
    Then confirm number of results returned is greater than '0'

  @RegVeloZA_Mobile
  Scenario:Velo ZA - verify Cookies Description and Cookie Settings link CTA
    Given user navigates to BAT home page for language "za"
    And select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to PLP page
    When user click on manage cookies button
    And assert description is displayed for each cookie type
    And assert Cookie Settings link CTA for each cookie type