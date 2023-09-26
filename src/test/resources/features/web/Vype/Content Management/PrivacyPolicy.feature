@sprint1 @privacyPolicy @smokeLite @IEReg @NonCaptchaLive @dklive @dk @VuseITAnonReg2
Feature: BAT Privacy Policy feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

    And users clicks on the 'privacyPolicy.key' text link

  Scenario: BUG 29272 - ensure privacy policy present, follow link to policy page and assert text
    And privacy policy displayed
    Then assert Privacy and Cookie Policy header
    Then assert text of 'PrivacyNoticeText.key' is displayed
    Then assert text of 'InformationWeCollectAboutYouText.key' is displayed
    Then assert text of 'UseofYourinformationText.key' is displayed
    Then assert text of 'useCookiesText.key' is displayed
    Then assert text of 'otherWebsitesText.key' is displayed
    Then assert text of 'yourPersonalInformationText.key' is displayed




