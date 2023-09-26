Feature: 16513 BAT header Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @VuseUKReg2 @VuseUKLive2 @VuseFRReg2 @VuseDEReg2 @VuseZAReg2 @VuseITAnonReg2
  Scenario: Browse to main home page and assert header elements displayed as expected
    And logo is displayed
    And header class is displayed
    And person icon is displayed
    And favorite icon is displayed
    And search icon is displayed
    And ensure basket icon is displayed
    And assert text 'myBasketText.key' on basket icon

  @VuseUKReg @VuseUKLive2 @VuseFRReg2 @VuseDEReg2 @VuseITAnonReg2
  Scenario: Check default delivery message is correct
    And should see Delivery message banner near the header

  @VuseZAReg2
  Scenario: Refer A Friend CMS Page
    Then select Refer a friend from About Vuse header
    Then url contains 'referAFriendUrl.key'
    And refer a friend link button is present

  @VuseZAReg2
  Scenario: About vuse CMS Page
    Then select About vuse from About Vuse header
    Then url contains 'aboutVuseUrl.key'
    And CTA links should be properly handled and navigate to the correct pages


  @VuseUKReg2 @VuseITAnonReg2
  Scenario Outline: Vuse Header menus Flyout
    And user hovers on '<headermenu>' menu
    Then '<headerflyout>' flyout is displayed and navigated
    Then url contains '<UrlToContain>'
    Examples:
      | headermenu           | headerflyout            | UrlToContain             |
      | ShopDevices.key      | viewAllDevices.key      | AllDevicesUrlText.key    |
      | ShopFlavours.key     | viewAllFlavours.key     | shopFlavoursUrl.key      |

  #automate bug 865956 for vuse de
  @VuseDEReg2
  Scenario: Navigate about vuse links for Vuse DE
    And user hovers on header menu and navigates to submenu
      | aboutVuseDE.key | nowVuse.key        | nowVuseTitle.key        |
      | aboutVuseDE.key | knowVape.key       | knowVapeTile.key        |
      | aboutVuseDE.key | sustainability.key | sustainabilityTitle.key |
      | aboutVuseDE.key | testThirtydays.key | testThirtydaysTitle.key |
      | aboutVuseDE.key | inviteFriends.key  | inviteFriendsTitle.key  |
      | aboutVuseDE.key | cigaretteGuide.key | cigaretteGuidetitle.key |


  @VuseZAReg2
  Scenario: Header menus Flyout
    Then user hover on 'headermenu' menu 'headerflyout' flyout is displayed and navigated and verify url contains 'UrlToContain'
      | headermenu           | headerflyout            | UrlToContain             |
      | ShopDevices.key      | viewAllDevices.key      | AllDevicesUrlText.key    |
      | ShopFlavours.key     | viewAllFlavours.key     | shopFlavoursUrl.key      |
      | promos.key           | extendedWarranty.key    | extendedWarrantyUrl.key  |
      | aboutVuse.key        | aboutVuse.key           | aboutVuseUrl.key         |

  @VuseFRReg
  Scenario: Header link and redirection
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    Then user signs in to the site custom details 'loginValidEmail.key' 'loginValidPassword.key'
    And click on logo and assert navigate to Homepage
    Then user hover on 'headermenu' menu 'headerflyout' flyout is displayed and navigated and verify url contains 'UrlToContain'
      | headermenu              | headerflyout         | UrlToContain             |
      | eCigarettes.key         | ePodlink.key         | ePodUrl.key              |
      | eLiquids.key            | capsulesEPodlink.key | eLiquidePodUrl.key       |
      | toutsavoirsurlavape.key | parlons.key          | parlonsLinkText.key  |
      | apropos.key             | VypeBlog.key         | VypeBlogUrlContain.key      |

  @VuseITAnonReg3
  Scenario: Navigate discover vuse links for Vuse IT
    And user hovers on header menu and navigates to submenu
      | scopriVuse.key | VuseBlog.key      | VuseBlogTitle.key       |
      | scopriVuse.key | faq.key           | FAQPageTitle.key        |
      | scopriVuse.key | sustainibilty.key | sustainabilityTitle.key |