#This feature - Verify features on Lyft Lab Landing page
  #for review / purchase LAB products.
Feature: Navigation on Features and Links CTAs

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    When user clicks on LYFT/LAB link from the header menu on Lyft site
    And user navigates to Lyft LAB landing page

  @LyftLive
  Scenario: FAQ Section and Expand/Collapse CTA
    And user clicks on Expand tag and assert each FAQ question is expanded

  Scenario: Collection 3 LAB Products Landing Page
    And user hovers over 'theCollectionsLinkHeader.key' category and assert sub-category
    And user clicks on the currently active collection link from Collections header
    And assert text of 'labCollection3landingPage.key' is displayed
    When user clicks on See The Flavors link for Collection 3
    Then verify user is directed to Lab Products Collection on the same page
    And url contains 'lyftLabProductCollectionUrl.key'