#This feature - Enable easy access to LyfLAB via the Lyft site.
  #can access information about Lyft Lab and review / purchase LAB products.
Feature: Mobile - Navigation to Lyft LAB section via Lyft site

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And resize browser screen
    When user clicks on LYFT/LAB link from the Burger menu on Lyft site
    And user navigates to Lyft LAB landing page on Mobile

  @LyftRegression2 @LyftDKReg
  Scenario: Navigation to LyftLAB on Mobile
    And url contains 'lyftLabUrl.key'

  @LyftDKReg
  Scenario: Mobile - Lyft DK - Lyft LAB and Lyft Navigation
    Then user clicks on the Burger menu on Lyft LAB site
    And users clicks on the 'LYFTLinkBurgerMenu.key' text link
    And url contains 'lyftUrl.key'
    Then assert that page title is 'lyftPageTitle.key'

  @LyftRegression2
  Scenario: Mobile - Lyft SE - Separate Navigation - Burger Menu Links
    Then user clicks on the Burger menu on Lyft LAB site
    Then assert links under LAB Category header
    And user clicks 'theCollectionsLinkHeader.key' category and assert sub-category on Mobile
    #And users clicks on the 'collaboratorLinkHeader.key' text link
   # (Link Removed as of now - Bug 443516, will be enabled when ready, hence commented out steps)
    #And url contains 'lyftLabCollaborationUrl.key'
    #Then user clicks on the Burger menu on Lyft LAB site
    And users clicks on the 'MyLABLinkHeader.key' text link
    Then user navigates to Insights landing page
    And url contains 'lyftLabInsightsUrl.key'


  @LyftDKReg
  Scenario: Mobile - Lyft DK - Separate Navigation - Burger Menu Links
    Then user clicks on the Burger menu on Lyft LAB site
    Then assert links under LAB Category header
    And user clicks 'theCollectionsLinkHeader.key' category and assert sub-category on Mobile
