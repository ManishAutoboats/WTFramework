Feature: BAT homepage feature - page scraping for elements

  Background: 11057 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  #@VuseDEReg @VuseDELive
  Scenario: Vuse home page validation
    Then  assert that page title is 'homePageTitle.key'
#    And   user clicks on the cta and navigates to that page
#      | Label                 | Title               |
#      | JETZT VUSE ENTDECKEN  | homePageTitle.key   |

  @VuseDEReg @VuseDELive @VuseITAnonReg2
  Scenario: Vuse header section validation
    And logo is displayed
    And header class is displayed
    And person icon is displayed
    And search icon is displayed
    And user hovers over Shop Devices and verifies the categories in it
      | Title          |
      | EpodDevice.key |

  @VuseDEReg @VuseDELive @VuseITAnonReg2
  Scenario: Vuse news section validation
    And validates news section is present

  @VuseDEReg @VuseDELive @VuseUKReg2 @VuseITAnonReg2
  Scenario: Vuse footer section validation
    And user asserts trust shop logo is displayed
    And footer div is present and displayed
    And footer sublinks are displayed

  @VuseDEReg @VuseDELive
  Scenario: Vuse footer trust badge
    And user asserts trust badge is present
    And user expands the badge

 @VuseUKReg2 @VuseMXReg @VuseITAnonReg2 @VuseFRReg2
  Scenario: Check Instagram social Media link and its Navigation page
    When Vype users click on Instagram link
    Then assert cart pageTitle is 'homepageInstgramTitle.key'

  @VuseUKReg2 @VuseMXReg @VuseITAnonReg2 @VuseFRReg2
  Scenario: Check Facebook social Media link and its Navigation page
    When Vype users click on Facebook link
    Then assert cart pageTitle is 'homepageFacebookTitle.key'

  @VuseFRReg2
  Scenario: Check Twitter social Media link and its Navigation page
    When Vype users click on Twitter link
    Then assert cart pageTitle is 'homepageTwitterTitle.key'

  @VuseFRReg2
  Scenario: Check Health Warning above the footer
    Then assert Health Warning text of 'healthWarningText.key' present
    When user clicks on '95% de substances nocives en moins' link from Information section and assert navigation to 'healthWarningURL.key'

  @VuseZAReg2
  Scenario Outline: VuseZA Promo links verification
    Then user hovers over on promos
    And users clicks on promos '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick           | UrlToContain               |
      | extendedWarranty.key  | extendedWarrantyUrl.key    |
      | onlineExclusive.key   | onlineExclusiveUrl.key     |
#      | exchange.key          | exchangeUrl.key            |

  @VuseZAReg2
  Scenario: Sticky bar on the footer
    Then assert sticky bar is present
    When users clicks on the 'aboutVuse.key' text link
    Then assert sticky bar is present
    When user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then assert sticky bar is present

    #automate backlog bug 866071
  @VuseDEReg
  Scenario: Verify redirect urls on know your vape page correct
    When users clicks on the 'knowVape.key' text link
    And users clicks on the 'knowVapeCTASmoke.key' text link
    Then url contains 'knowVapeCTASmokeUrl.key' text
    And users clicks on the 'knowVape.key' text link
    And users clicks on the 'knowVapeCTAQuality.key' text link
    Then url contains 'knowVapeCTAQualityUrl.key' text


  @VuseFRReg2
  Scenario: Check Instagram social Media link at stayConnected and its Navigation page
    When users click on Instagram link at stayConnected
    Then assert cart pageTitle is 'homepageInstgramTitle.key'

  @VuseFRReg2
  Scenario: Check Facebook social Media link at stayConnected and its Navigation page
    When users click on Facebook link at stayConnected
    Then assert cart pageTitle is 'homepageFacebookTitle.key'

  @VuseFRReg2
  Scenario: Check Twitter social Media link at stayConnected and its Navigation page
    When users click on Twitter link at stayConnected
    Then assert cart pageTitle is 'homepageTwitterTitle.key'

  #automate issue 980620
  @VuseMXReg @VuseITAnonReg2
  Scenario: Check Facebook social Media link on the footer and its Navigation page
    When user click on Facebook link on the footer
    Then assert URL contains text 'FacebookURL.key'

  #automate issue 980620
  @VuseMXReg @VuseITAnonReg2
  Scenario: Check Instagram social Media link on the footer and its Navigation page
    When user click on Instagram link on the footer
    Then assert URL contains text 'InstagramURL.key'
