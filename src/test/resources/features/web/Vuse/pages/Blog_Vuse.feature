Feature: BAT Blog

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseDEReg @VuseDELive @VuseITAnonReg2
  Scenario: Validate blog post
    And user navigates to the Blog
    And user lands on 'VypeBlogTitle.key' page
    And verify blog tiles are displayed
    And click on pagination
    And verify blog tiles are displayed
    And click on the tile and assert user is navigated to that page

  @VuseUKReg2
  Scenario: 9251 Blog page present and correct - Vuse UK
    And user hovers over on the About Vype header link
    And user hovers over on the Our Blog link
    Then users clicks on the Our Blog link from About Vype Menu
    And url contains 'VypeBlogUrlContain.key'
    And user closes the alert if present
    And assert text of 'VypeBlogTitle.key' is displayed
    Then blogs are listed in descending order

  @VuseITAnonReg2 @VuseFRReg2 @VuseFRLive
  Scenario: Vuse Blog - Categories Menu - Verify Links and Sub Link CTAs
    Then users clicks on 'Blog' link,assert url and page title
    And user clicks on Select Categories menu and assert Sub-Links and their CTAs


  Scenario: Vuse Advent calendar - Menu - Verify Links
    Then users clicks on 'Advent calendar' link,assert url and page title

  @VuseMXReg @VuseMXLive
  Scenario: Validate blog post-MX
    And user navigates to the Blog
    And user lands on 'blog.key' page
    And verify blog tiles are displayed
    And click on the tile and assert user is navigated to that page

  @VuseMXReg
  Scenario: Change URL linked for BOX ICON
    And assert url of the page is 'TrackMyOrderUrl.key' when users click on Box icon

  @VuseZAReg @VuseDEReg2 @VuseITAnonReg2
  Scenario: Blog page present and correct
    When user navigates to the Blog
    Then url contains 'VypeBlogUrlContain.key'
    And assert text of 'VypeBlogTitle.key' is displayed
    Then blogs are listed in descending order
    Then user can view blogs and navigate to it

  @VuseITAnonReg2
  Scenario: Validate blog post count
    When user navigates to the Blog
    Then Count the paginations in blog page
    And Verify the number of blogs in all the pages
    And verify blog tiles are displayed

  @VuseFRReg2
  Scenario: share blogs using the social media icons - Facebook
    Given users clicks on 'Blog' link,assert url and page title
    And user clicks on the first article
    And user clicks on Facebook Icon
    Then Facebook login page is present

  @VuseFRReg2
  Scenario: share blogs using the social media icons - Twitter
    Given users clicks on 'Blog' link,assert url and page title
    And user clicks on the first article
    And user clicks on Twitter Icon
    Then Twitter login page is present