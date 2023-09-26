Feature: GLO Footer Items and Content

  Background:Navigate to BAT Home Page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @LyftRegression2
  Scenario: verify footer Links and its content
    Then lyft should see footerLeftColumnLinkText.key and footerLeftColumnLinkContent.key under LeftColumn of Footer
    And lyft should see footerProductsLinkText.key and footerProductsLinkContentUrl.key under ProductsSection of Footer
    And lyft should see footerSupportLinkText.key and footerSupportLinkContentUrl.key under SupportSection of Footer
    And lyft should get the success response for the links
    And assert stationary cookies icon and footer links are available
    And assert copyright footerCopyRightLanguage.key is present and assert footerCopyRightText.key text

  @LyftRegression
  Scenario: verify footer Social Media Links and its url content
    Then lyft should see footerSocialMediaLinkUrl.key under FollowUs Section of Footer
    And lyft should get the success response for the links

  #@LyftRegression2 @LyftLive
  Scenario: Footer - verify footer Links and its content on switching language using language selector
    And switch to 'EN' site using language selector
    And select allow all from cookie popup and select over 18 age confirmation option
    And assert navigation to home page with 'urlInEnglishOnLangSwitchFrom.key' and response status code as 200
    And assert navigation to home page with 'urlInEnglishOnLangSwitchTo.key' and response status code as 200
    Then assert text is displayed in English after switching language
    Then lyft should see footerLeftColumnLinkTextInEN.key and footerLeftColumnLinkContentInEN.key under LeftColumn of Footer
    And lyft should see footerSupportLinkTextInEN.key and footerSupportLinkContentUrlInEN.key under SupportSection of Footer
    And lyft should get the success response for the links
    And assert stationary cookies icon and footer links are available
    And assert text of 'footerCopyRightTextInEN.key' is displayed
    And switch to 'SV' site using language selector
    Then assert text is displayed in Swedish after switching language

  @LyftRegression2
  Scenario: verify footer Links navigation
    And user click on footer left column link
    Then assert URL contains text 'ProductCategoryURLText.key'
    When user click on footer product link
    Then assert URL contains text 'productIceCoolStrongURL.key'
    When user click on footer support link
    Then assert URL contains text 'supportTermCondition.key'