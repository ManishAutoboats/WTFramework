#This is the BAT Navigation feature
@smoke @21398
Feature: BAT navigation feature

  Background: Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg3 @VuseUKLive2
  Scenario Outline: 21398 Guest, opens hamburger menu select main hamburger links
    And user hovers over on the About Vype header link
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick                 | UrlToContain              |
      | DeviceCategoryText.key      | AllDevicesUrlText.key     |
      | flavourHeaderLinkText.key   | eLiquidesUrlText.key      |
      | DeviceComparision.key       | deviceComparisionUrlText.key |

  @VuseFRReg2
  Scenario: 21398 Guest, opens hamburger menu select main hamburger links-Vuse
     And user navigate to link and verify URL and title
      | LinkToClick                 | UrlToContain                  | Title                        |
      | DeviceCategoryText.key      | AllDevicesUrlText.key         | deviceTitle.key              |
      | flavourHeaderLinkText.key   | eLiquidesUrlText.key          | eLiquidTitle.key             |
      | abonnement.key              | abonnementUrlText.key         | abonnementTitle.key          |
      | VypeBlog.key                | VypeBlogUrlContain.key        | BlogTitle.key                |
      | toutsavoirsurlavape.key     | toutsavoirsurlavapeUrlText.key| toutsavoirsurlavapeTitle.key |
      | apropos.key                 | aproposUrlText.key            | aproposTitle.key             |
      | personnaliser.key           | personnaliserUrl.key          | personnaliserTitle.key       |
      | nouveauvapoteur.key         | nouveauvapoteurUrl.key        | nouveauvapoteurTitle.key     |

  @VuseDEReg2
  Scenario: open About Vuse link
    And click on the logo
    And user navigates to the About Vuse link
    And url contains 'aboutVuseLink.key'

#  @VuseDEReg2 //disbabled as sub menu link aren't shown on live
  Scenario: Verify About Vuse - sub menu links
    When user hovers over on the About Vuse link
    Then sub menu links are present

  @VuseZAReg2
  Scenario: verify invalid url redirect to 404 error page
    And user navigates to URL 'error404Url.key'
    Then user get error 404 page displayed
    And page should also display a section with flavours
    And clicking on the CTA buttons, users should navigate to the appropriate pages

  @VuseITAnonReg2
  Scenario: invalid url redirect to 404 error page
    And user navigates to URL 'error404Url.key'
    Then user get error 404 page displayed
    And link to Homepage is displayed
    And link to MyAccountpage is displayed

  @VuseITLive
  Scenario: Vuse IT - Menu change from Blog to Scopri Vuse
    And user click on the Link and assert url
      | blog.key           | blogUrl.key           |
      | faq.key            | faqUrl.key            |
      | sustainibilty.key  | sustainibiltyUrl.key  |


  @VuseFRReg2
  Scenario: invalid url redirect to 404 error page with homepage link
    And user navigates to URL 'error404Url.key'
    Then user get error 404 page displayed
    And link to Homepage is displayed


  @VuseZAReg
  Scenario: 979779 - Vuse ZA - check 'Exchange your cliq or Cue for a free Vuse' link removed in the header
    And user hovers over on promos
    And assert link "promosExchangeCliqSubMenu.key" is not present
