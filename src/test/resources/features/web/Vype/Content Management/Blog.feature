Feature: BAT Blog

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

@9251 @IEReg
  Scenario: 9251 Blog page present and correct
  	And click hamburger menu
    And user hovers over on the More header link
 	And user hovers over on the Blog link
    Then users clicks on the Blog link from More Menu
    And url contains 'VypeBlogUrlContain.key'
    And assert text of 'VypeBlogTitle.key' is displayed
    
@regression @MTD
  Scenario: 9251 Blog page present and correct - Vype UK
    And user hovers over on the About Vype header link
    And user hovers over on the Our Blog link
    Then users clicks on the Our Blog link from About Vype Menu
    And url contains 'VypeBlogUrlContain.key'
    And assert text of 'VypeBlogTitle.key' is displayed

  @ITReg2 @dk @dklive @nl @NLlive @de @delive @fr @frlive @VuseDKReg @VuseDKLive #@ITLive not in place
  Scenario: Vype Blog - Categories Menu - Verify Links and Sub Link CTAs
    Then users clicks on Blog link,assert url and page title
    And user clicks on Select Categories menu and assert Sub-Links and their CTAs

  @MXReg2
  Scenario: Validate blog post
    And user navigates to the Blog
    And user lands on 'blog.key' page
    And verify blog tiles are displayed
    And click on the tile and assert user is navigated to that page

