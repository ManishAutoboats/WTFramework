@sprint1 @privacyPolicy @mobile
Feature: BAT Mobile Privacy Policy feature

  Background: Navigate to BAT Home page and confirm over 18
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option
    And resize browser screen

  Scenario: ensure privacy policy present, follow link to policy page and assert text
    #And privacy policy displayed
    And select privacy policy link
    #Then assert Privacy and Cookie Policy header
    #Then assert text of 'privecyPolicyTitle.key' is displayed
    Then assert text of 'PrivacyNoticeText.key' is displayed
    Then assert text of 'InformationWeCollectAboutYouText.key' is displayed
    Then assert text of 'UseofYourinformationText.key' is displayed
    Then assert text of 'useCookiesText.key' is displayed
    Then assert text of 'otherWebsitesText.key' is displayed
    Then assert text of 'yourPersonalInformationText.key' is displayed




