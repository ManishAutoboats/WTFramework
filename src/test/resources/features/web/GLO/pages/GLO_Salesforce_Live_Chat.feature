Feature: BAT - Glo Salesforce Live Chat

  Background: Navigate to BAT Home page
    Given user navigates to BAT home page
    And select allow all from cookie popup and select over 18 age confirmation option

  @gloDeRegression @gloDeLive @gloPlLive
 # @gloPlRegression
  Scenario: Live Chat visibility and pop up
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears

  @gloItLive
  Scenario: gloit Live Chat visibility and pop up
    When user clicks on the Live chat icon at the bottom right of the page
    Then assert salesforce Chat pop-up appears
    And user clicks the person icon
    Then assert salesforce Chat pop-up appears
    And user clicks on 'signInLink.key' link from Person Menu
    And user clicks the registration button
    Then assert salesforce Chat pop-up appears

  @gloKzRegression @gloKzLive
  Scenario: glokz Whatsapp Chat visibility
    When user clicks on the Whatsapp chat icon at the bottom right of the page
    Then assert a new tab is opened with url contains 'whatsAppApiWebPageUrl.key'