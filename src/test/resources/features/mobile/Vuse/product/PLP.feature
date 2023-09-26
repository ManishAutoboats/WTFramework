Feature: PLP filtering to left hand side - Mobile

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@RegVuseFR_Mobile // Different logic for sorting
  Scenario: Mobile - Vuse FR-Product Sorting on PLPs
    When users clicks on the 'eCigarettes.key' text link
    And user closes the alert if present
    And user lands on plp page
    And verify the sorting options 'sortingOptionseCigarette.key' for 'eCigarettes.key'
    And user navigate to 'category' to 'subcategory' and verify the 'sorting option'
      | category           | subcategory           | sorting option                    |
      | eCigarettes.key    | ePenlink.key          | sortingOptionseCigaretteEpen.key  |
      | eCigarettes.key    | ePodlink.key          | sortingOptionseCigaretteEpen.key  |
      | eCigarettes.key    | eTanklink.key         | sortingOptionseCigaretteEpen.key  |
