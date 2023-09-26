Feature: Vuse Cookie feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page

  @VuseUKReg2
  Scenario: verify Cookie Settings link CTA in Footer working
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert stationary cookie icon is not displayed on home page
    And assert Cookie settings link and click it
    Then user can see the on/off toggle for each individual cookie type
    Then user can see the confirm my choices CTA
    When user interact with the on/off toggle for a cookie type
    Then the toggle changes to an active state
    When user interact with the save my preferences CTA
    Then my cookie preferences are saved
    Then user can see the allow all CTA
    When user interact with the allow all CTA
    Then all cookie types are toggled to the active state

  #automate issue 932918,this issue is expected result according to user story 117425 AC4
  @VuseUKReg2
  Scenario: Cookie notice link verification
    When user click on the 'cookieNoticeText.key' link
    And select over 18 age confirmation option
    Then url contains 'cookieNoticeUrl.key'
    And users clicks on the 'aboutVuse.key' text link
    And assert cookie banner should appear

  #automate issue 945680,the cookie policy should redirect to live site cookie policy page.
  @VuseFRReg3
  Scenario: Cookie policy link verfification
    When user click on the 'cookiePolicyText.key' link
    Then url contains 'cookiePolicyUrl.key'

  #automate issue 925185
  @gloItRegression2
  Scenario: The rest of the pages are clickable even though the cookies have not been accepted
    When select over 18 age confirmation option
    And user click toilet paper on glo it home page
    Then assert cookie banner should appear

  #automate issue 965092
  @VuseDEReg2
  Scenario: The cookie pop-up should not be loading in every single page after accept it
    When select allow all from cookie popup and select over 18 age confirmation option
    And user navigates to the Blog page
    Then assert cookie banner should not appear


