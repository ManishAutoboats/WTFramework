#This feature - Enable easy access to LyfLAB via the Lyft site.
  #can access information about Lyft Lab and review / purchase LAB products.
@LyftRegression2
Feature: Navigation to Lyft LAB section via Lyft site

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  Scenario: Navigation to LyftLAB on Desktop
    And url contains 'lyftLabUrl.key'

  Scenario: Desktop - Separate Navigation - Header Links
    Then assert links under LAB Category header
    And user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    #And users clicks on the 'collaboratorLinkHeader.key' text link
      # (Link Removed as of now - Bug 443516, will be enabled when ready, hence commented out steps)
    #Then user navigates to Collaboration landing page
    #And url contains 'lyftLabCollaborationUrl.key'
    And users clicks on the 'MyLABLinkHeader.key' text link
    Then user navigates to Insights landing page
    And url contains 'lyftLabInsightsUrl.key'

