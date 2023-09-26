#This feature - Enable easy access to LyfLAB via the Lyft site.
  #can access information about Lyft Lab and review / purchase LAB products.
Feature: Navigation to Lyft LAB section via Lyft site
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  @LyftDKReg @LyftDKLive
  Scenario: Desktop Lyft DK - Separate Navigation - Header Links
    Then assert links under LAB Category header
    And user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category

  @LyftRegression2
  Scenario: Free delivery bar across all LAB pages - Lyft Site
    Given user logins Magento Admin home page and assert Free Delivery Banner Configuration
    And user navigates to BAT home page for FE validation
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And assert Free Delivery Banner is displayed on all LAB pages
    When user clicks on See The Flavors link from Block 1
    And click first Lyft Lab product
    And assert Free Delivery Banner is displayed on all LAB pages
    And user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    And user clicks on the currently active collection link from Collections header
    And click first Lyft Lab product
    And assert Free Delivery Banner is displayed on all LAB pages
    And users clicks on the 'theCollectionsLinkHeader.key' text link
    And assert Free Delivery Banner is displayed on all LAB pages
    And users clicks on the 'MyLABLinkHeader.key' text link
    And assert Free Delivery Banner is displayed on all LAB pages


  Scenario: Free delivery bar across all LAB pages - EN Site
    Given user logins Magento Admin home page and assert Free Delivery Banner Configuration
    And user navigates to BAT home page for FE validation
    And assert Free Delivery Banner for EN Site
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And assert Free Delivery Banner is displayed on all LAB pages
    And users clicks on the 'theCollectionsLinkHeader.key' text link
    And assert Free Delivery Banner is displayed on all LAB pages
    And users clicks on the 'MyLABLinkHeader.key' text link
    And assert Free Delivery Banner is displayed on all LAB pages

  #todo - Never passed 
  Scenario: Free delivery bar across all LAB pages - Lyft DK EN Site
    Given user logins Magento Admin home page and assert Free Delivery Banner Configuration
    And user navigates to BAT home page for FE validation
    And assert Free Delivery Banner for EN Site
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page
    And assert Free Delivery Banner is displayed on all LAB pages

  @LyftDKReg
  Scenario: Free delivery bar across all LAB pages - Lyft DK Site
    Given user logins Magento Admin home page and assert Free Delivery Banner Configuration
    And user navigates to BAT home page for FE validation
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And assert Free Delivery Banner is displayed on all LAB pages
    And click first Lyft Lab product
    And assert Free Delivery Banner is displayed on all LAB pages
    And user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    And user clicks on the currently active collection link from Collections header
    And click first Lyft Lab product
    And assert Free Delivery Banner is displayed on all LAB pages