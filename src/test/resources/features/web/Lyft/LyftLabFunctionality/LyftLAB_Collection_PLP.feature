#This feature - Verify features on Lyft Lab Landing page
  #for review / purchase LAB products.
Feature: LAB Collections PLP
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  @LyftDKReg @LyftRegression2
  Scenario: Active Collection LAB Products - PLP
    Then user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    Then user clicks on See The Flavors link from Block 1
    And assert single pack products on LAB PLP page
    And assert bundle pack products on LAB PLP page
    And assert strength for each product is pre-selected for Collection7
    And assert strength for each product is pre-selected for Collection7 on PDP

  @LyftRegression2
  Scenario: Guest user should allow to add Active Collection LAB Products into the cart
    When user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    And user clicks on See The Flavors link from Block 1
    And user selects strength 'Regular.key' for a six pack LAB product and add to cart
    And click on basket icon
    Then assert same products details for LAB products in mini cart
    And assert Strength attribute as 'productStrength.key' appears for only LAB products in mini cart