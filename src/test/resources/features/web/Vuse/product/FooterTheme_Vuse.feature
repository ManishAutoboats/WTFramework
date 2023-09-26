Feature: BAT footer Feature

  Background: 17313 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


 @VuseUKLive2 @VuseITAnonReg3 @VuseMXReg @VuseZAReg2 @VuseCOReg2
  Scenario: 17313 Browse to main home page and assert all footer elements are displayed as expected
    And footer div is present and displayed
    And footer sublinks displayed
    And social media icons displayed
    And payment content block is preset and displayed
    And footer list items are displayed

  Scenario Outline: 17313 Cycle through footer links ensure each one is working as expected
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick           | UrlToContain               |
      | Terms&Conditions.key  | Terms&ConditionsUrl.key    |
      | DeliveryReturn.key    | DeliveryReturnUrl.key      |
      | Accessibility.key     | AccessibilityUrl.key       |
      | warranty.key          | warrantyUrl.key            |

  @VuseUKReg2
  Scenario Outline: Verify and Assert CookieNotice Url
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    And Assert Url is as expected
    Examples:
      | LinkToClick           | UrlToContain               |
      | cookieNoticeText.key  | cookieNoticeUrl.key        |

  @VuseUKLive2 @VuseUKReg2 @VuseDEReg2 @VuseITAnonReg3
  Scenario: verify footer Links and its content
    Then user should see footerCustomerServiceLinkText.key and footerCustomerServiceLinkContent.key under CustomerService of Footer
    And user should see footerAboutVuseLinkText.key and footerAboutVuseLinkContentUrl.key under AboutVuseSection of Footer
    And user should see footerNeedHelpLinkText.key and footerNeedHelpLinkContentUrl.key under NeedHelpSection of Footer
    And user should get the success response for the links

 @VuseFRReg3 @VuseFRLive
  Scenario: 65296 PDP Avis Upgrade - Stars/Ratings
    Then user click on Avis Ratings icon and assert Avis Reviews pop up is displayed

   #@VuseITAnonReg2 #removed the test as it is covered by "Scenario: verify footer Links and its content"
  @VuseCOReg2
  Scenario: Verify Phone number and email address is displaying in the footer
    And user see the phone number in footer
    And user see the email in footer

  @VuseITAnonReg3
  Scenario: Verify Delivery and Return page
    And users clicks on the 'DeliveryReturn.key' text link
    And assert text of 'deliveryReturnTitle.key' is displayed
    And assert text of 'deliveryReturnSubTitle.Key' is displayed
    Then assert Delivery and Return policies in the page

  @VuseITAnonReg3
  Scenario: Verify Disposal of used products page
    And users clicks on the 'disposalProductsLink.key' text link
    And assert text of 'disposalProductsTitle.key' is displayed
    And assert text of 'disposalProductsSubTitle.Key' is displayed
    And assert text of 'disposalProductsSubTitle2.Key' is displayed

  @VuseITAnonReg3
  Scenario: Subscription policy is available in Global footer
    And footer div is present and displayed
    And users clicks on the 'subscriptionCondition.key' text link
    And url contains 'subscriptionConditionURL.key'
    Then assert that page title is 'subscriptionConditionTitle.key'
    Then assert subtitles of subscription conditions page

  @VuseUKReg2 @VuseZAReg2
  Scenario: Conditions of sale VuseUK
    When users clicks on the 'conditionsofsale.key' text link
    Then url contains 'conditionsofsaleUrl.key'
    And conditions of Sale page is disaplyed

  @VuseZAReg2
  Scenario: Disposal product for VuseZA
    When users clicks on the 'dropTheProd.key' text link
    Then url contains 'dropTheProdUrl.key'
    Then user see How it works description
    And user can join poistive change
    Then url contains 'sustainabilityurl.key'

  @VuseZAReg2
  Scenario: Social media icons footer
    And footer div is present and displayed
    And footer sublinks displayed
    And social media icons are displayed
    And payment icon are displayed

  @VuseZAReg2
  Scenario Outline: Cycle through footer links ensure each one is working as expected
    When users clicks on the '<LinkToClick>' text link
    Then url contains '<UrlToContain>'
    Examples:
      | LinkToClick           | UrlToContain               |
      | privacyNotice.key     | privacyNoticeeurl.key      |
      | Terms&Conditions.key  | Terms&ConditionsUrl.key    |
      | warranty.key          | warrantyUrl.key            |
      | Accessibility.key     | AccessibilityUrl.key       |
      | cookiePolicy.key      | cookiePolicyUrl.key        |
      | conditionsofsale.key  | conditionsofsaleUrl.key    |
      | delivery&Return.key   | delivery&ReturnUrl.key     |
      | aboutVuse.key         | aboutVuseUrl.key           |
      | vapeQuality.key       | vapeQualityUrl.key         |

  @VuseCOReg2
  Scenario Outline: verify footer links
    When users clicks on the '<LinkToClick>' text link
    Then url contains '<UrlToContain>'
    Examples:
      | LinkToClick          | UrlToContain            |
      | privacyNotice.key    | privacyNoticeURL.key    |
      | aboutVuse.key        | aboutVuseUrl.key        |
      | Terms&Conditions.key | termConUrl.key          |
      | dropThePod.key       | dropThePodUrl.key       |
      | store.key            | storeUrl.key            |
      | contactUsText.key    | contactUsUrl.key        |
      | frequentQuestion.key | frequentQuestionUrl.key |

  @VuseITAnonReg3
  Scenario: Verify Instagram link redirected properly
    When users click on Instagram icon and redirect
    Then url contains 'instagramUrl.key'