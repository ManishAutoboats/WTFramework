@smokeLite @live @ITReg @NonCaptchaLive @CoLive
Feature: 21182 BAT terms and conditions Feature

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup
    And user selects over 18 age confirmation option
    And users clicks on the 'Terms&Conditions.key' text link

  Scenario: 21182 Ensure terms and conditions page is as expected
    And assert text of 'IntroductionText.key' is displayed
    And assert text of 'InfoAboutUsText.Key' is displayed
    And assert text of 'AccessOurWebSiteText.key' is displayed
    And assert text of 'OurContentText.key' is displayed
    And assert text of 'YourPrivacyText.key' is displayed
    And assert text of 'LisbilityText.key' is displayed
    And assert text of 'EventsBeyoundText.key' is displayed
    And assert text of 'OtherImportantText.key' is displayed