Feature: Vuse Germany FAQ page validations

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option


  @VuseDEReg @VuseDELive @VuseITAnonReg2 @VuseZAReg2
  Scenario: Vuse FAQ validation
    Given users clicks on the FAQ link
    And assert content and expands the accordion

  @VuseMXReg @VuseMXLive @VuseITAnonReg2
  Scenario: Vuse FAQ validation
    And users clicks on the FAQ link
    And assert content and expands the accordion
    And user clicks on contact us


  @VuseUKReg2
  Scenario: redirection URL is set-up correctly for free
    And user navigates to the following URL 'https://www-eu-uat1-global-vuse.non-prod.marketing.bat.net/gb/en/free'
    Then url contains 'urltext.key' text

  @VuseUKReg2
  Scenario: redirection URL is set-up correctly for epod
    And user navigates to the following URL 'https://www-eu-uat1-global-vuse.non-prod.marketing.bat.net/gb/en/epod'
    Then url contains 'urltext.key' text

