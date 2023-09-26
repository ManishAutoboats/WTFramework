Feature: BAT footer Feature

  Background: 17313 Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @ITReg2 @COReg @theme @footer @live @CoLive @MXReg2
  Scenario: 17313 Browse to main home page and assert all footer elements are displayed as expected
    And footer div is present and displayed
    And footer sublinks displayed
    And social media icons displayed
    And payment content block is preset and displayed
    And footer list items are displayed

  @IEReg @IElive @theme @footer @live
  Scenario Outline: 17313 Cycle through footer links ensure each one is working as expected
    And users clicks on the '<LinkToClick>' text link
    And url contains '<UrlToContain>'
    Examples:
      | LinkToClick           | UrlToContain               |
      | productDetails.key    | productDetailsUrl.key      |
      | Terms&Conditions.key  | Terms&ConditionsUrl.key    |
      #| StoreLocator.key      | StoreLocatorUrl.key        | Removed after meeting with Manish as not common to all locales
      | DeliveryReturn.key    | DeliveryReturnUrl.key      |
      | Accessibility.key     | AccessibilityUrl.key       |

  @delive
  Scenario: Click links and verify URLs in German Language
    Then user clicks on Link and assert Url returns successful response
      | accessibilityLink.key            | accessibilityURL.key             |
      | companyInfoLink.key              | companyInfoURL.key               |
      | disposalProductsLink.key         | disposalProductsURL.key          |
      | satisfactionGuaranteedLink.key   | satisfactionGuaranteedURL.key    |
      | terms&ConditionsLink.key         | terms&ConditionsURL.key          |
      | frequentlyAskedQuestionsLink.key | frequentlyAskedQuestionsURL.key  |

  @delive
  Scenario: Launch URLs and verify URLs in German Language
    And launch given URL and assert URL contains text and returns success
      | contactHeaderEngURL.key        | contactHeaderEngURL.key                  |
      | T&CcompanyInfoEngURL.key       | T&CcompanyInfoUrlContainsGerman.key      |
      | T&CDisposalEngURL.key          | T&CDisposalUrlContainsGerman.key         |
      | T&CFAQsEngURL.key              | T&CFAQsUrlContainsGerman.key             |
      | T&CDeliveryEngURL.key          | T&CDeliveryUrlContainsGerman.key         |
      | T&CSatisfactionEngURL.key      | T&CSatisfactionUrlContainsGerman.key     |
      | T&CRightOfWithdrawlEngURL.key  | T&CRightOfWithdrawlUrlContainsGerman.key |

  @dk @dklive
  Scenario: Click Footer links and verify URLs
    Then user clicks on Link and assert Url returns successful response
      | businessInformationLink.key      | businessInformationURL.key       |
      | disposalProductsLink.key         | disposalProductsURL.key          |
      | termsOfTradeLink.key             | termsOfTradeURL.key              |
      | privacyPolicyLink.key            | privacyPolicyURL.key             |
      | frequentlyAskedQuestionsLink.key | frequentlyAskedQuestionsURL.key  |
      | deliveryAndReturnLink.key        | deliveryAndReturnURL.key         |
      | cookiePolicyLink.key             | cookiePolicyURL.key              |
    And assert Customer Information section in the footer menu

  @delive @de
  Scenario: Footer URLs - Assert Internal Links Redirects
    And assert internal redirect of footer links URLs with success status code

  @MXReg2
  Scenario: verify footer Links and its content
    Then user should see footerGeneralLinkText.key and footerGeneralLinkContent.key under GeneralSection of Footer
    And user should get the success response for the links