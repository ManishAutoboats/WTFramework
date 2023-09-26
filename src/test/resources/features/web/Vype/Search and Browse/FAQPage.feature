#This is the Search feature file
  ##Testing
    ## Presence of search bar
    ## Search returning no results
      ## valid search
      ## invalid search
        ## php data-injection
        ## symbols
    ## Search returning results
    ## Nav to Registration page
    ## Nav to Forgotten password page
  @faq
Feature: FAQ
  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @regression
  Scenario: 26380 - FAQ Page | Test env
    And users clicks on the 'FAQText' text link
    Then assert that page title is 'FAQPageTitle.key'
    And assert text of 'FAQ.key' is displayed
    And assert text of 'FAQQuestion1' is displayed
    And assert text of 'FAQQuestion2' is displayed
    And assert text of 'FAQQuestion3' is displayed
    And assert text of 'FAQQuestion4' is displayed
    And assert text of 'FAQQuestion5' is displayed
    And assert text of 'FAQQuestion6' is displayed

  @live
  Scenario: 26380 - FAQ Page | Production
    And user navigates to the following URL 'https://www.govype.com/gb/en/frequently-asked-questions'
    Then assert that page title is 'FAQPageTitleLive.key'

@NonCaptchaLive
  Scenario: 26380 - FAQ Page | Production
    And user navigates to the following URL 'https://www.govype.com/mx/es/frequently-asked-questions'
    Then assert that page title is 'FAQPageTitleLive.key'

  @MXReg
  Scenario: Vype FAQ validation
    And users clicks on the FAQ link
    And assert content and expands the accordion
    And user clicks on contact us


